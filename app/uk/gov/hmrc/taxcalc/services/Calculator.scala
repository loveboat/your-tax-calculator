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

import uk.gov.hmrc.taxcalc.controllers.TaxCalculatorConfigException
import uk.gov.hmrc.taxcalc.domain.{Money, _}


trait Calculator {


  def calculate(): CalculatorResponse = ???

  protected def isAnnual(payPeriod: String): Boolean = {
   payPeriod.equals("annual")
  }

}

case class ExcessPayCalculator(date: LocalDate, taxBandId : Int, payPeriod: String, taxablePay: Money) extends Calculator with TaxCalculatorHelper {

  override def calculate(): ExcessPayResponse = {
    val taxBands = getTaxBands(date)
    if(taxBandId > 1){
      val previousBand = taxBands.taxBands.find(_.band == taxBandId-1).getOrElse(throw new TaxCalculatorConfigException(s"Could not find tax band configured for band ${taxBandId-1}"))
      val periodCalc = previousBand.periods.find(_.periodType.equals(payPeriod)).getOrElse(throw new TaxCalculatorConfigException(s"Could not find period calc configured for period $payPeriod in tax band ${taxBandId-1}"))
      applyResponse(true, Money(periodCalc.threshold.-(taxablePay.value.intValue()).abs))
    }
    else applyResponse(true, taxablePay)
  }

  def applyResponse(success: Boolean, excessPay: Money): ExcessPayResponse = {
    ExcessPayResponse(success, excessPay)
  }
}

case class AllowanceCalculator(taxCode: String) extends Calculator {

  override def calculate(): AllowanceResponse = {
    val taxCodeNumber = taxCode.stripSuffix(taxCode.substring(taxCode.length - 1, taxCode.length)).toInt
    val seedData = new PAYEAllowanceSeedData(taxCodeNumber)
    applyResponse(true,Seq("weekly" -> WeeklyAllowance(seedData), "monthly" -> MonthlyAllowance(seedData), "annual" -> AnnualAllowance(taxCode, taxCodeNumber)))
  }

  def applyResponse(success: Boolean, allowances: Seq[(String, Allowance)]): AllowanceResponse = {
    AllowanceResponse(success, allowances)
  }
}

case class TaxablePayCalculator(taxCode: String, payPeriod: String, grossPay: Money) extends Calculator with TaxCalculatorHelper{

  override def calculate(): TaxablePayResponse = {

    val taxablePay: Seq[Money] = isTaxableCode(taxCode) match {
      case true   => {
        for {
          allowance <- AllowanceCalculator(taxCode).calculate().result.filter(_._1.equals(payPeriod))
        } yield (Money(grossPay-allowance._2.allowance))
      }
      case false  => Seq(Money(0))
    }
    applyResponse(true, taxablePay.head)
  }

  def applyResponse(success: Boolean, taxablePay: Money): TaxablePayResponse = {
    TaxablePayResponse(success, taxablePay)
  }
}

case class TaxBandCalculator(date: LocalDate, payPeriod: String, taxablePay: Money) extends Calculator with TaxCalculatorHelper{

  override def calculate(): TaxBandResponse = {
    val taxBands = getTaxBands(date).taxBands.collect(taxBandFilterFunc(payPeriod, taxablePay))
    val taxBand = !taxBands.isEmpty match {
      case true => taxBands.head
      case false => getTaxBands(date).taxBands.last
    }
    applyResponse(true, taxBand)
  }

  def applyResponse(success: Boolean, taxBand: TaxBand): TaxBandResponse = {
    TaxBandResponse(success, taxBand)
  }

  private def taxBandFilterFunc(periodType: String, taxablePay: Money): PartialFunction[TaxBand, TaxBand] = {
    case taxBand: TaxBand  if isPeriodValid(periodType, taxBand.periods, taxablePay) => taxBand
  }

  private def isPeriodValid(periodType: String, periodCalcs: Seq[PeriodCalc], taxablePay: Money) : Boolean = {
    !periodCalcs.find(_.periodType.equals(periodType)).filter((_.threshold.>(taxablePay.value))).isEmpty
  }
}

case class NICRateCalculator(payPeriod: String, rate: BigDecimal, amount: Money) extends Calculator {

  override def calculate(): NICRateCalculatorResponse = {
    val multiplier = if (isAnnual(payPeriod)) 12 else 1
    applyResponse(true, Money((amount.value * (rate / 100)) - 0.001, 2, true) * multiplier)
  }

  def applyResponse(success: Boolean, rate: Money): NICRateCalculatorResponse = {
    NICRateCalculatorResponse(success, rate)
  }
}

case class EmployeeRateCalculator(date: LocalDate, grossPay: Money, payPeriod: String, limitId: Int) extends Calculator with TaxCalculatorHelper {

  val nicRateLimit = getRateLimits(date)
  val rate = nicRateLimit.employeeRate.collect(rateLimit(s"$limitId", payPeriod)).head.value
  override def calculate(): RateCalculatorResponse = {

    val result = limitId match {
      case 1 => {
        grossPay > nicRateLimit.threshold.collect(rateLimit("primary", payPeriod)).head match {
          case true => RateResult(nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head,
            nicRateLimit.threshold.collect(rateLimit("primary", payPeriod)).head, rate, payPeriod)
          case false => grossPay <= nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head match {
            case true => RateResult(grossPay, nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head, rate, payPeriod)
            case false => zeroRate
          }
        }
      }
      case 3 => {
        grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head match {
          case true => RateResult(nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head,
            nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head, rate, payPeriod)
          case false => grossPay <= nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head &&
            grossPay > nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head match {
            case true => RateResult(grossPay, nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head, rate, payPeriod)
            case false => zeroRate
          }
        }
      }
      case 4 => {
        grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head match {
          case true => RateResult(grossPay, nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head, rate, payPeriod)
          case false => zeroRate
        }
      }
    }

    applyResponse(true, result.aggregation)
  }

  def applyResponse(success: Boolean, aggregation: Aggregation): RateCalculatorResponse = {
    RateCalculatorResponse(success, aggregation)
  }

  private def zeroRate = RateResult(Money(0), Money(0), rate, payPeriod)
}

case class EmployerRateCalculator(date: LocalDate, grossPay: Money, payPeriod: String, limitId: Int) extends Calculator with TaxCalculatorHelper {
  val nicRateLimit = getRateLimits(date)
  val rate = nicRateLimit.employerRate.collect(rateLimit(s"$limitId", payPeriod)).head.value

  override def calculate(): RateCalculatorResponse = {
    val result = limitId match {
      case 2 => {
        grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head match {
          case true => RateResult(nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head,
                                  nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head, rate, payPeriod)
          case false => grossPay <= nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head &&
                        grossPay >  nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head match {
            case true => RateResult(grossPay, nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head, rate, payPeriod)
            case false => zeroRate
          }
        }
      }
      case 3 => {
        grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head match {
          case true => RateResult(grossPay, nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head, rate, payPeriod)
          case false => zeroRate
        }
      }
    }
    applyResponse(true, result.aggregation)
  }

  def applyResponse(success: Boolean, aggregation: Aggregation): RateCalculatorResponse = {
    RateCalculatorResponse(success, aggregation)
  }

  private def zeroRate = RateResult(Money(0), Money(0), rate, payPeriod)
}


case class RateResult(lhs: Money, rhs: Money, rate: BigDecimal, payPeriod: String) {
  val amount = NICRateCalculator(payPeriod, rate, lhs-rhs).calculate().result.value
  val aggregation = Aggregation(rate, amount)
}
