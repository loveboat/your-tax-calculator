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

import uk.gov.hmrc.play.http.HeaderCarrier
import uk.gov.hmrc.taxcalc.controllers.{BadRequestException, TaxCalculatorConfigException}
import uk.gov.hmrc.taxcalc.domain.{Money, TaxBreakdown, _}

import scala.concurrent.{ExecutionContext, Future}
import scala.math.BigDecimal
import scala.math.BigDecimal.RoundingMode

trait TaxCalculatorService extends TaxCalculatorHelper {

  val payeTaxCalculatorService: PAYETaxCalculatorService
  val nicTaxCalculatorService: NICTaxCalculatorService

  def calculateTax(isStatePensionAge: String, taxYear: Int, taxCode: String, grossPayPence: Long, payPeriod: String, hours: Option[Int])(implicit hc: HeaderCarrier, ex: ExecutionContext): Future[TaxCalc] = {

    try {
      val isPensionAge = convertToBoolean(isStatePensionAge)

      val grossPay = calculateGrossPay(grossPayPence, hours, payPeriod)
      val updatedTaxCode = removeScottishElement(taxCode)
      val payeTax = payeTaxCalculatorService.calculatePAYETax(updatedTaxCode, payPeriod, grossPay)
      val nicTax = nicTaxCalculatorService.calculateNICTax(isPensionAge, grossPay, payPeriod)

      val aggregation = PAYEAggregateBuilder(updatedTaxCode, LocalDate.now, payeTax.band, payPeriod, payeTax.payeTaxAmount).build().aggregation

      val nicTaxCategories = NICTaxCategoryBuilder(isPensionAge, nicTax).build().taxCategories
      val taxCategories = Seq(TaxCategory(taxType = "incomeTax", payeTax.payeTaxAmount.value, aggregation)) ++ nicTaxCategories

      val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)

      val taxFreePay = grossPay > payeTax.taxablePay match {
        case true => grossPay - (payeTax.taxablePay)
        case false => Money(0)
      }

      val calculatedTaxBreakdown = TaxBreakdown(payPeriod, grossPay.value, taxFreePay.value,
        payeTax.taxablePay.value, calculateScottishElement(payeTax.payeTaxAmount, taxCode, LocalDate.now), taxCategories, totalDeductions,
        (grossPay - totalDeductions).value)

      val taxBreakdown = derivePeriodTaxBreakdowns(LocalDate.now, payeTax.band, taxCode, calculatedTaxBreakdown, payeTax, nicTax, aggregation, isPensionAge)

      val averageAnnualTaxRate = calculateAverageAnnualTaxRate(taxBreakdown.find(_.period == "annual"))

      val taxCalResult = TaxCalc(isPensionAge, taxCode, getHourlyGrossPay(hours, grossPayPence), hours, averageAnnualTaxRate.value, payeTax.bandRate + nicTax.employeeNICBandRate, payeTax.bandRate, nicTax.employeeNICBandRate, payeTax.isTapered, taxBreakdown)

      Future.successful(taxCalResult)
    }
    catch {
      case ex: TaxCalculatorConfigException => Future.failed(ex)
      case ex: BadRequestException => Future.failed(ex)
      case ex: Throwable => Future.failed(ex)
    }
  }

  def convertToBoolean(isStatePensionAge: String): Boolean = {
    isStatePensionAge.toLowerCase() match {
      case "true" => true
      case "false" => false
      case _ => throw new BadRequestException("Invalid value")

    }
  }

  def calculateGrossPay(grossPayPence: Long, hours: Option[Int], payPeriod: String): Money = {
    hours match {
      case Some(value: Int) => {
        val weekly = Money(((BigDecimal.valueOf(grossPayPence) * value) / 100), 2, true)
        val grossPay = payPeriod match {
          case "weekly" => weekly
          case "monthly" => Money((weekly*BigDecimal.valueOf(52)) / BigDecimal.valueOf(12), 2, true)
          case "annual" => Money(weekly * BigDecimal.valueOf(52), 2, true)
        }
        grossPay
      }
      case _ => Money(BigDecimal.valueOf(grossPayPence)/100 , 2, true)
    }
  }

  def calculateAverageAnnualTaxRate(annualTaxBreakdown: Option[TaxBreakdown]): Money = {
    annualTaxBreakdown match {
      case Some(taxBreakdown: TaxBreakdown) => Money((taxBreakdown.totalDeductions / taxBreakdown.grossPay) * BigDecimal.valueOf(100), 2, true)
      case _ => Money(0, 2, true)
    }
  }

  def calculateScottishElement(payeTaxAmount: Money, taxCode: String, date: LocalDate): Option[BigDecimal] = {
    isValidScottishTaxCode(taxCode) match {
      case true => Option((payeTaxAmount*getTaxBands(date).scottishRate/100).value)
      case false => None
    }
  }

  private def derivePeriodTaxBreakdowns(date: LocalDate, bandId: Int, taxCode: String,taxBreakdown: TaxBreakdown, payeTax: PAYETaxResult, nicTax: NICTaxResult, payeAggregation: Seq[Aggregation], isStatePensionAge: Boolean): Seq[TaxBreakdown] = {
    val grossPay = Money(taxBreakdown.grossPay)
    taxBreakdown.period match {
      case "annual" => {
        Seq(taxBreakdown, deriveTaxBreakdown(date, bandId, taxCode, grossPay, "monthly", nicTax, false, 12, payeAggregation, isStatePensionAge),
        deriveTaxBreakdown(date, bandId, taxCode, grossPay, "weekly", nicTax, false , 52, payeAggregation, isStatePensionAge))
      }
      case "monthly" => {
        Seq(deriveTaxBreakdown(date, bandId, taxCode, grossPay, "annual", nicTax, true, 12, payeAggregation, isStatePensionAge), taxBreakdown)
      }
      case "weekly" => {
        Seq(deriveTaxBreakdown(date, bandId, taxCode, grossPay, "annual", nicTax, true, 52, payeAggregation, isStatePensionAge), taxBreakdown)
      }
    }
  }

  private def deriveTaxBreakdown(date: LocalDate, bandId: Int, taxCode:String, grossPay: Money, payPeriod: String, nicTax: NICTaxResult, isMultiplier: Boolean, rhs: Int, payeAggregation: Seq[Aggregation], isStatePensionAge: Boolean): TaxBreakdown = {

    val updatedGrossPay = performIsMultiplyFunction(grossPay, isMultiplier, rhs)
    val updatedTaxablePay = TaxablePayCalculator(date, taxCode, payPeriod, updatedGrossPay).calculate().result
    val payeTotal = Money(payeAggregation.foldLeft(BigDecimal.valueOf(0.0))(if(isMultiplier) _ + _.amount.setScale(2, RoundingMode.HALF_UP)*rhs
                                                                    else _ + _.amount.setScale(2, RoundingMode.HALF_UP)/rhs), 2, true)

    val employeeNICAggregation = nicTax.employeeNIC.collect(NICAggregationFunc(isMultiplier, rhs))

    val employerNICAggregation = nicTax.employerNIC.collect(NICAggregationFunc(isMultiplier, rhs))

    val nicTaxCategories = NICTaxCategoryBuilder(isStatePensionAge, NICTaxResult(nicTax.employeeNICBandRate,employeeNICAggregation, employerNICAggregation)).build().taxCategories
    val taxCategories = Seq(TaxCategory(taxType = "incomeTax", payeTotal.value, derivePAYEAggregation(isMultiplier, rhs, payeAggregation)))++nicTaxCategories

    val taxFreePay = updatedGrossPay > updatedTaxablePay match {
      case true => Money(updatedGrossPay-(updatedTaxablePay), 2, true)
      case false => Money(0)
    }

    val totalDeductions = taxCategories.collect(TotalDeductionsFunc).foldLeft(BigDecimal.valueOf(0.0))(_ + _)
    TaxBreakdown(payPeriod, updatedGrossPay.value, taxFreePay.value,
                 updatedTaxablePay.value, calculateScottishElement(Money(payeTotal, 2, true), taxCode, date),
                 taxCategories, totalDeductions,(updatedGrossPay - totalDeductions).value)
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

  private def getHourlyGrossPay(hours: Option[Int], grossPay: BigDecimal): Option[BigDecimal]  = {
    hours match {
      case Some(value: Int) => Option(grossPay/100)
      case _ => None
    }
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {
  override val payeTaxCalculatorService: PAYETaxCalculatorService = LivePAYETaxCalculatorService
  override val nicTaxCalculatorService: NICTaxCalculatorService = LiveNICTaxCalculatorService
}
