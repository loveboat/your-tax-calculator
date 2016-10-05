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

import uk.gov.hmrc.taxcalc.domain._

import scala.concurrent.Future
import scala.math.BigDecimal

trait TaxCalculatorService extends TaxCalculatorHelper {

  val payeTaxCalculatorService: PAYETaxCalculatorService
  val nicTaxCalculatorService: NICTaxCalculatorService

  def calculateTax(isStatePensionAge: Boolean, taxYear: Int, taxCode: String, grossPayPence: Long, payPeriod: String): Future[TaxCalc] = {

    val grossPay = Money(grossPayPence/100)
    val payeTax  = payeTaxCalculatorService.calculatePAYETax(taxCode, payPeriod, grossPay)
    val nicTax   = nicTaxCalculatorService.calculateNICTax(isStatePensionAge, grossPay, payPeriod)

    val aggregation = PAYEAggregateBuilder(LocalDate.now, payeTax.band, payPeriod, payeTax.payeTaxAmount).build().aggregation

    val nicTaxCategories = NICTaxCategoryBuilder(isStatePensionAge, nicTax).build().taxCategories
    val taxCategories = Seq(TaxCategory(taxType = "incomeTax", payeTax.payeTaxAmount.value , aggregation))++nicTaxCategories

    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)

    val calculatedTaxBreakdown = TaxBreakdown(payPeriod, grossPay.value, (grossPay-(payeTax.taxablePay)).value,
                                              payeTax.taxablePay.value, taxCategories, totalDeductions,
                                              (grossPay - totalDeductions).value)

    val taxBreakdown = derivePeriodTaxBreakdowns(calculatedTaxBreakdown, payeTax, nicTax, aggregation, isStatePensionAge)

    val taxCalResult = TaxCalc(isStatePensionAge, taxCode, taxBreakdown)

    Future.successful(taxCalResult)
  }

  private def derivePeriodTaxBreakdowns(taxBreakdown: TaxBreakdown, payeTax: PAYETaxResult, nicTax: NICTaxResult, payeAggregation: Seq[Aggregation], isStatePensionAge: Boolean): Seq[TaxBreakdown] = {
    val grossPay = Money(taxBreakdown.grossPay)
    taxBreakdown.period match {
      case "annual" => {
        Seq(taxBreakdown, deriveTaxBreakdown(payeTax.band, grossPay, "monthly", payeTax.taxablePay, nicTax, false, 12, payeAggregation, isStatePensionAge),
        deriveTaxBreakdown(payeTax.band, grossPay, "weekly", payeTax.taxablePay, nicTax, false , 52, payeAggregation, isStatePensionAge))
      }
      case "monthly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay, "annual", payeTax.taxablePay, nicTax, true, 12, payeAggregation, isStatePensionAge), taxBreakdown)
      }
      case "weekly" => {
        Seq(deriveTaxBreakdown(payeTax.band, grossPay, "annual", payeTax.taxablePay, nicTax, true, 52, payeAggregation, isStatePensionAge), taxBreakdown)
      }
    }
  }

  private def deriveTaxBreakdown(band: Int, grossPay: Money, payPeriod: String, taxablePay: Money, nicTax: NICTaxResult, isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation], isStatePensionAge: Boolean): TaxBreakdown = {

    val updatedGrossPay = performIsMultiplyFunction(grossPay, isMultiplier, rhs)
    val updatedTaxablePay = performIsMultiplyFunction(taxablePay, isMultiplier, rhs)
    val payeTotal = Money(payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(if(isMultiplier) _ + _.amount*rhs else _ + _.amount/rhs), 2, true)

    val employeeNICAggregation = nicTax.employeeNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employeeNICTotal = Money(employeeNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount), 2, true)

    val employerNICAggregation = nicTax.employerNIC.collect(NICAggregationFunc(isMultiplier, rhs))
    val employerNICTotal = Money(employerNICAggregation.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount), 2, true)

    val nicTaxCategories = NICTaxCategoryBuilder(isStatePensionAge, NICTaxResult(employeeNICAggregation, employerNICAggregation)).build().taxCategories
    val taxCategories = Seq(TaxCategory(taxType = "incomeTax", payeTotal.value, derivePAYEAggregation(isMultiplier, rhs, payeAggregation)))++nicTaxCategories

    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    TaxBreakdown(payPeriod, updatedGrossPay.value, (updatedGrossPay.-(updatedTaxablePay)).value,
                 updatedTaxablePay.value, taxCategories, totalDeductions,(updatedGrossPay - totalDeductions).value)
  }

  private def derivePAYEAggregation(isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation]): Seq[Aggregation] = {
    for{
      aggregation <- payeAggregation
    } yield (Aggregation(aggregation.percentage, performIsMultiplyFunction(Money(aggregation.amount), isMultiplier, rhs).value))
  }

  private def createPAYEAggregation(taxBand: TaxBand, payPeriod: String, band: Int, payeTaxAmount: Money): Aggregation = {
    val periodCalc = taxBand.periods.filter(_.periodType.equals(payPeriod)).head
    val amount = if(payeTaxAmount > Money(0)) Money(periodCalc.maxTax, 2, true) else Money(0)
    Aggregation(taxBand.rate, amount.value)
  }

  private def PAYEAggregationFunc(band: Int, payPeriod: String, payeTaxAmount: Money) : PartialFunction[TaxBand, Aggregation] = {
    case taxBand if taxBand.band <= band && taxBand.band != 1 => createPAYEAggregation(taxBand, payPeriod, band, payeTaxAmount)
  }

  private def createNICAggregation(isMultiplier: Boolean, rhs: Int, aggregate: Aggregation): Aggregation = {
    Aggregation(aggregate.percentage, performIsMultiplyFunction(Money(aggregate.amount), isMultiplier, rhs).value)
  }

  private def NICAggregationFunc(isMultiplier: Boolean, rhs: Int) : PartialFunction[Aggregation, Aggregation] = {
    case aggregate => createNICAggregation(isMultiplier, rhs, aggregate)
  }

  private def TotalDeductionsFunc: PartialFunction[TaxCategory, BigDecimal] = {
    case taxCategory if !taxCategory.taxType.equals("employerNationalInsurance") => taxCategory.total
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
