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

import scala.concurrent.Future
import scala.io.Source._
import scala.math.BigDecimal.RoundingMode
import scala.tools.nsc.interpreter._

trait TaxCalculatorService extends TaxCalculatorHelper {

  val payeTaxCalculatorService: PAYETaxCalculatorService

  def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPay: BigDecimal, payPeriod: String): Future[TaxCalc] = {

    val payeTax: PAYETaxResult = payeTaxCalculatorService.calculatePAYETax(taxCode, payPeriod, grossPay)

    val payeAggregation: Seq[Aggregation] = getTaxBands(LocalDate.now()).taxBands.collect(AggregationFunc(payeTax.band, payPeriod))
    val total = payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)

    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", total , payeAggregation))
    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val calculatedTaxBreakdown = TaxBreakdown(payPeriod, grossPay, grossPay.-(payeTax.taxablePay), payeTax.taxablePay, taxCategories, totalDeductions, grossPay - totalDeductions)
    val taxBreakdown: Seq[TaxBreakdown] = derivePeriodTaxBreakdowns(calculatedTaxBreakdown, payeTax)
    val taxCalResult: TaxCalc = TaxCalc(isStatePensionAge, taxCode, taxBreakdown)

    Future.successful(taxCalResult)
  }

  def derivePeriodTaxBreakdowns(taxBreakdown: TaxBreakdown, payeTax: PAYETaxResult): Seq[TaxBreakdown] = {
    val grossPay = taxBreakdown.grossPay
    taxBreakdown.period match {
      case "annual" => {
        Seq(taxBreakdown, deriveTaxBreakdown(payeTax.band, grossPay./(12).setScale(2, RoundingMode.HALF_UP), "monthly", payeTax.taxablePay./(12).setScale(2, RoundingMode.HALF_UP)),
                          deriveTaxBreakdown(payeTax.band, grossPay./(52).setScale(2, RoundingMode.HALF_UP), "weekly", payeTax.taxablePay./(52).setScale(2, RoundingMode.HALF_UP)))
      }
      case "monthly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay.*(12).setScale(2, RoundingMode.HALF_UP), "annual", payeTax.taxablePay.*(12).setScale(2, RoundingMode.HALF_UP)), taxBreakdown)
      }
      case "weekly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay.*(52).setScale(2, RoundingMode.HALF_UP), "annual", payeTax.taxablePay.*(52).setScale(2, RoundingMode.HALF_UP)), taxBreakdown)
      }
    }
  }

  def deriveTaxBreakdown(band: Int, grossPay: BigDecimal, payPeriod: String, taxablePay: BigDecimal): TaxBreakdown = {
    val payeAggregation: Seq[Aggregation] = getTaxBands(LocalDate.now()).taxBands.collect(AggregationFunc(band, payPeriod))
    val total = payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount).setScale(2, RoundingMode.HALF_UP)

    val taxCategories: Seq[TaxCategory] = Seq(TaxCategory(taxType = "incomeTax", total, payeAggregation))
    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    val derivedTaxBreakdown: TaxBreakdown = TaxBreakdown(payPeriod, grossPay, grossPay.-(taxablePay),
      taxablePay, taxCategories, totalDeductions, grossPay - totalDeductions)
    derivedTaxBreakdown
  }

  def createAggregation(taxBand: TaxBand, payPeriod: String, band: Int): Aggregation = {
    val periodCalc = taxBand.periods.filter(_.periodType.equals(payPeriod)).head

    Aggregation(taxBand.rate, if(periodCalc.cumulativeMaxTax >= 0) periodCalc.cumulativeMaxTax else 0)
  }

  def AggregationFunc(band: Int, payPeriod: String) : PartialFunction[TaxBand, Aggregation] = {
    case taxBand: TaxBand if taxBand.band <= band && taxBand.band != 1 => createAggregation(taxBand, payPeriod, band)
  }

  def TotalDeductionsFunc: PartialFunction[TaxCategory, BigDecimal] ={
    case taxCategory: TaxCategory if !taxCategory.taxType.equals("employerNationalInsurance") => taxCategory.total
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = LivePAYETaxCalculatorService
}

object SandboxTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = SandboxPAYETaxCalculatorService

  override def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPay: BigDecimal, payPeriod: String): Future[TaxCalc] = {

    getClass.getResourceAsStream(s"/tax-calc/tax_calculator_2016_response.json") match {
      case is: InputStream => {
        Future.successful(Json.parse(fromInputStream(is).mkString).as[TaxCalc])
      }
    }
  }
}
