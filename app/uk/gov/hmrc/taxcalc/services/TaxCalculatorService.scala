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


  def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPayPence: Long, payPeriod: String): Future[TaxCalc] = {

    val grossPay = Money(grossPayPence/100)
    val payeTax: PAYETaxResult = payeTaxCalculatorService.calculatePAYETax(taxCode, payPeriod, grossPay)
    val nicTax: NICTaxResult = nicTaxCalculatorService.calculateNICTax(grossPay, payPeriod)

    val payeAggregation: Seq[Aggregation] = appendCalculatedAggregation(payeTax.payeTaxAmount,
      getTaxBands(LocalDate.now()).taxBands.collect(PAYEAggregationFunc(payeTax.band, payPeriod)))

    val employeeNICTotal = nicTax.employeeNIC.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)
    val employerNICTotal = nicTax.employerNIC.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)


    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", payeTax.payeTaxAmount.value , payeAggregation),
    TaxCategory(taxType = "employeeNationalInsurance", employeeNICTotal, nicTax.employeeNIC),
    TaxCategory(taxType = "employerNationalInsurance", employerNICTotal, nicTax.employerNIC))
    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val calculatedTaxBreakdown = TaxBreakdown(payPeriod, grossPay.value, (grossPay-(payeTax.taxablePay)).value, payeTax.taxablePay.value, taxCategories, totalDeductions, (grossPay - totalDeductions).value)
    val taxBreakdown: Seq[TaxBreakdown] = derivePeriodTaxBreakdowns(calculatedTaxBreakdown, payeTax, nicTax, payeAggregation)
    val taxCalResult: TaxCalc = TaxCalc(isStatePensionAge, taxCode, taxBreakdown)

    Future.successful(taxCalResult)
  }

  private def appendCalculatedAggregation(total: Money, payeAggregation: Seq[Aggregation]): Seq[Aggregation] = {
    payeAggregation++Seq(Aggregation(getTaxBands(LocalDate.now).taxBands.last.rate,
      (total - payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)).value))
  }

  private def derivePeriodTaxBreakdowns(taxBreakdown: TaxBreakdown, payeTax: PAYETaxResult, nicTax: NICTaxResult, payeAggregation: Seq[Aggregation]): Seq[TaxBreakdown] = {
    val grossPay = Money(taxBreakdown.grossPay)
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

  private def deriveTaxBreakdown(band: Int, grossPay: Money, payPeriod: String, taxablePay: Money, nicTax: NICTaxResult, isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation]): TaxBreakdown = {

    val updatedGrossPay = performIsMultiplyFunction(grossPay, isMultiplier, rhs)
    val updatedTaxablePay = performIsMultiplyFunction(taxablePay, isMultiplier, rhs)
    val payeTotal = Money(payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(if(isMultiplier) _ + _.amount*rhs else _ + _.amount/rhs), 2, true)

    val employeeNICAggregation: Seq[Aggregation] = nicTax.employeeNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employeeNICTotal = Money(employeeNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount), 2, true)

    val employerNICAggregation: Seq[Aggregation] = nicTax.employerNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employerNICTotal = Money(employerNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount), 2, true)

    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", payeTotal.value, derivePAYEAggregation(isMultiplier, rhs, payeAggregation)),
                                              TaxCategory(taxType = "employeeNationalInsurance", employeeNICTotal.value, employeeNICAggregation),
                                              TaxCategory(taxType = "employerNationalInsurance", employerNICTotal.value, employerNICAggregation))

    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val derivedTaxBreakdown: TaxBreakdown = TaxBreakdown(payPeriod, updatedGrossPay.value, (updatedGrossPay.-(updatedTaxablePay)).value,
      updatedTaxablePay.value, taxCategories, totalDeductions,(updatedGrossPay - totalDeductions).value)
    derivedTaxBreakdown
  }

  private def derivePAYEAggregation(isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation]): Seq[Aggregation] = {
    for{
      aggregation <- payeAggregation
    } yield (Aggregation(aggregation.percentage, performIsMultiplyFunction(Money(aggregation.amount), isMultiplier, rhs).value))
  }

  private def createPAYEAggregation(taxBand: TaxBand, payPeriod: String, band: Int): Aggregation = {
    val periodCalc = taxBand.periods.filter(_.periodType.equals(payPeriod)).head
    Aggregation(taxBand.rate, Money(periodCalc.maxTax, 2, true).value)
  }

  private def PAYEAggregationFunc(band: Int, payPeriod: String) : PartialFunction[TaxBand, Aggregation] = {
    case taxBand: TaxBand if taxBand.band <= band && taxBand.band != 1 && taxBand.band != 4 => createPAYEAggregation(taxBand, payPeriod, band)
  }

  private def createNICAggregation(isMultiplier: Boolean, rhs: Int, aggregate: Aggregation): Aggregation = {
    Aggregation(aggregate.percentage, performIsMultiplyFunction(Money(aggregate.amount), isMultiplier, rhs).value)
  }

  private def NICAggregationFunc(isMultiplier: Boolean, rhs: Int) : PartialFunction[Aggregation, Aggregation] = {
    case aggregate:Aggregation => createNICAggregation(isMultiplier, rhs, aggregate)
  }

  private def TotalDeductionsFunc: PartialFunction[TaxCategory, BigDecimal] = {
    case taxCategory: TaxCategory if !taxCategory.taxType.equals("employerNationalInsurance") => taxCategory.total
  }

  private def performIsMultiplyFunction(amount: Money, isMultiplier: Boolean, rhs: Int): Money = {
    if(isMultiplier){
      Money(amount.*(rhs), 2, true)
    }
    else
      Money(amount./(rhs), 2, true)
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = LivePAYETaxCalculatorService
  override val nicTaxCalculatorService: NICTaxCalculatorService = LiveNICTaxCalculatorService
}
