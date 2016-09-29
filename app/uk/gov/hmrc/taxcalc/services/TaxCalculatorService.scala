/*
 * Copyright 2016 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.taxcalc.services

import java.time.LocalDate

import play.api.libs.json.Json
import uk.gov.hmrc.taxcalc.domain._

import scala.collection.GenTraversableOnce
import scala.concurrent.Future
import scala.io.Source._
import scala.math.BigDecimal.RoundingMode
import scala.tools.nsc.interpreter._

trait TaxCalculatorService extends TaxCalculatorHelper {

  val payeTaxCalculatorService: PAYETaxCalculatorService
  val nicTaxCalculatorService: NICTaxCalculatorService

  def appendCalculatedAggregation(total: BigDecimal, payeAggregation: Seq[Aggregation]): Seq[Aggregation] = {
    payeAggregation++Seq(Aggregation(getTaxBands(LocalDate.now).taxBands.last.rate, (total - payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)).setScale(2, RoundingMode.HALF_UP)))
  }

  def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPay: BigDecimal, payPeriod: String): Future[TaxCalc] = {

    val payeTax: PAYETaxResult = payeTaxCalculatorService.calculatePAYETax(taxCode, payPeriod, grossPay)
    val nicTax: NICTaxResult = nicTaxCalculatorService.calculateNICTax(grossPay, payPeriod)

    val payeAggregation: Seq[Aggregation] = appendCalculatedAggregation(payeTax.payeTaxAmount,getTaxBands(LocalDate.now()).taxBands.collect(PAYEAggregationFunc(payeTax.band, payPeriod)))
    val employeeNICTotal = nicTax.employeeNIC.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)
    val employerNICTotal = nicTax.employerNIC.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)


    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", payeTax.payeTaxAmount , payeAggregation),
                                              TaxCategory(taxType = "employeeNationalInsurance", employeeNICTotal, nicTax.employeeNIC),
                                              TaxCategory(taxType = "employerNationalInsurance", employerNICTotal, nicTax.employerNIC))
    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val calculatedTaxBreakdown = TaxBreakdown(payPeriod, grossPay, grossPay.-(payeTax.taxablePay), payeTax.taxablePay, taxCategories, totalDeductions, grossPay - totalDeductions)
    val taxBreakdown: Seq[TaxBreakdown] = derivePeriodTaxBreakdowns(calculatedTaxBreakdown, payeTax, nicTax, payeAggregation)
    val taxCalResult: TaxCalc = TaxCalc(isStatePensionAge, taxCode, taxBreakdown)

    Future.successful(taxCalResult)
  }

  def derivePeriodTaxBreakdowns(taxBreakdown: TaxBreakdown, payeTax: PAYETaxResult, nicTax: NICTaxResult, payeAggregation: Seq[Aggregation]): Seq[TaxBreakdown] = {
    val grossPay = taxBreakdown.grossPay
    taxBreakdown.period match {
      case "annual" => {

        Seq(taxBreakdown, deriveTaxBreakdown(payeTax.band, grossPay, "monthly", payeTax.taxablePay, nicTax, false, 12, payeAggregation),
                          deriveTaxBreakdown(payeTax.band, grossPay, "weekly", payeTax.taxablePay, nicTax, false , 52, payeAggregation))
      }
      case "monthly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay, "annual", payeTax.taxablePay, nicTax, true, 12, payeAggregation), taxBreakdown)
      }
      case "weekly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay, "annual", payeTax.taxablePay, nicTax, true, 52, payeAggregation), taxBreakdown)
      }
    }
  }

  def deriveTaxBreakdown(band: Int, grossPay: BigDecimal, payPeriod: String, taxablePay: BigDecimal, nicTax: NICTaxResult, isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation]): TaxBreakdown = {

    val updatedGrossPay = if (isMultiplier) grossPay.*(rhs).setScale(2, RoundingMode.HALF_UP) else grossPay./(rhs).setScale(2, RoundingMode.HALF_UP)
    val updatedTaxablePay = if (isMultiplier) taxablePay.*(rhs).setScale(2, RoundingMode.HALF_UP) else taxablePay./(rhs).setScale(2, RoundingMode.HALF_UP)
    val payeTotal = payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(if(isMultiplier) _ + _.amount*rhs else _ + _.amount/rhs).setScale(2, RoundingMode.HALF_UP)

    val employeeNICAggregation: Seq[Aggregation] = nicTax.employeeNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employeeNICTotal = employeeNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount).setScale(2, RoundingMode.HALF_UP)

    val employerNICAggregation: Seq[Aggregation] = nicTax.employerNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employerNICTotal = employerNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount).setScale(2, RoundingMode.HALF_UP)

    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", payeTotal, derivePAYEAggregation(isMultiplier, rhs, payeAggregation)),
                                              TaxCategory(taxType = "employeeNationalInsurance", employeeNICTotal, employeeNICAggregation),
                                              TaxCategory(taxType = "employerNationalInsurance", employerNICTotal, employerNICAggregation))
    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val derivedTaxBreakdown: TaxBreakdown = TaxBreakdown(payPeriod, updatedGrossPay, updatedGrossPay.-(updatedTaxablePay),
      updatedTaxablePay, taxCategories, totalDeductions, updatedGrossPay - totalDeductions)
    derivedTaxBreakdown
  }

  private def derivePAYEAggregation(isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation]): Seq[Aggregation] = {
    for{
      aggregation <- payeAggregation
    } yield (Aggregation(aggregation.percentage,
      if (isMultiplier) (aggregation.amount*(rhs)).setScale(2, RoundingMode.HALF_UP)
    else (aggregation.amount/(rhs)).setScale(2, RoundingMode.HALF_UP)))
  }

  def createPAYEAggregation(taxBand: TaxBand, payPeriod: String, band: Int): Aggregation = {
    val periodCalc = taxBand.periods.filter(_.periodType.equals(payPeriod)).head

    Aggregation(taxBand.rate, periodCalc.maxTax.setScale(2, RoundingMode.HALF_UP))
  }

  def PAYEAggregationFunc(band: Int, payPeriod: String) : PartialFunction[TaxBand, Aggregation] = {
    case taxBand: TaxBand if taxBand.band <= band && taxBand.band != 1 && taxBand.band != 4 => createPAYEAggregation(taxBand, payPeriod, band)
  }

  def createNICAggregation(isMultiplier: Boolean, rhs: Int, aggregate: Aggregation): Aggregation = {
    Aggregation(aggregate.percentage, if (isMultiplier) aggregate.amount.*(rhs).setScale(2, RoundingMode.HALF_UP) else aggregate.amount./(rhs).setScale(2, RoundingMode.HALF_UP))
  }

  def NICAggregationFunc(isMultiplier: Boolean, rhs: Int) : PartialFunction[Aggregation, Aggregation] = {
    case aggregate:Aggregation => createNICAggregation(isMultiplier, rhs, aggregate)
  }

  def TotalDeductionsFunc: PartialFunction[TaxCategory, BigDecimal] ={
    case taxCategory: TaxCategory if !taxCategory.taxType.equals("employerNationalInsurance") => taxCategory.total
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = LivePAYETaxCalculatorService
  override val nicTaxCalculatorService: NICTaxCalculatorService = LiveNICTaxCalculatorService
}

object SandboxTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = SandboxPAYETaxCalculatorService
  override val nicTaxCalculatorService: NICTaxCalculatorService = SandboxNICTaxCalculatorService

  override def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPay: BigDecimal, payPeriod: String): Future[TaxCalc] = {

    getClass.getResourceAsStream(s"/tax-calc/tax_calculator_2016_response.json") match {
      case is: InputStream => {
        Future.successful(Json.parse(fromInputStream(is).mkString).as[TaxCalc])
      }
    }
  }
}
