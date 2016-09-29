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

import uk.gov.hmrc.taxcalc.domain.{Aggregation, NICRateLimit, NICTaxResult, RateLimit}

import scala.math.BigDecimal.RoundingMode

trait NICTaxCalculatorService extends TaxCalculatorHelper {

  def calculateNICTax(grossPay: BigDecimal, payPeriod: String): NICTaxResult = {
    val rateLimits = getRateLimits(LocalDate.now)
    NICTaxResult(calculateEmployeeNIC(grossPay, payPeriod, rateLimits), calculateEmployerNIC(grossPay, payPeriod, rateLimits))
  }

  def calculateEmployeeNIC(grossPay: BigDecimal, payPeriod: String, nicRateLimit: NICRateLimit): Seq[Aggregation] = {

    val grossPayAmount = payPeriod match {
      case "annual" => grossPay./(12).setScale(2, RoundingMode.HALF_UP)
      case _ => grossPay
    }

    val rateLimits = getRateLimits(LocalDate.now)
    val isAnnual = payPeriod.equals("annual")
    val rate1 = calculateEmployeeRate1(rateLimits, getPayPeriod(payPeriod), grossPayAmount, isAnnual)
    val rate3 = calculateEmployeeRate3(rateLimits, getPayPeriod(payPeriod), grossPayAmount, isAnnual)

    Seq(Aggregation(rate1.percentage, rate1.amount + rate3.amount),
      calculateEmployeeRate4(rateLimits, getPayPeriod(payPeriod), grossPayAmount, isAnnual))
  }

  def calculateEmployerNIC(grossPay: BigDecimal, payPeriod: String, nicRateLimit: NICRateLimit): Seq[Aggregation] = {
    val grossPayAmount = payPeriod match {
      case "annual" => grossPay./(12).setScale(2, RoundingMode.HALF_UP)
      case _ => grossPay
    }

    val rateLimits = getRateLimits(LocalDate.now)
    val isAnnual = payPeriod.equals("annual")
    val rate2 = calculateEmployerRate2(rateLimits, getPayPeriod(payPeriod), grossPayAmount, isAnnual)
    val rate3 = calculateEmployerRate3(rateLimits, getPayPeriod(payPeriod), grossPayAmount, isAnnual)
    Seq(Aggregation(rate2.percentage, rate2.amount + rate3.amount))
  }

  private def rateLimit(limitType: String, payPeriod: String): PartialFunction[RateLimit, BigDecimal] = {
    case rateLimit: RateLimit if rateLimit.rateLimitType.equals(limitType) => {
      val limit: BigDecimal = (rateLimit.getClass.getMethod(getPayPeriod(payPeriod)).invoke(rateLimit)).asInstanceOf[BigDecimal]
      limit
    }
  }

  private def getPayPeriod(payPeriod: String): String = {
    payPeriod match {
      case "annual" => "monthly"
      case _ => payPeriod
    }
  }

  private def calcRate(rate: BigDecimal, amount: BigDecimal, isAnnual: Boolean): BigDecimal = {
    val multiplier = if (isAnnual) 12 else 1
    ((amount*(rate/(BigDecimal.valueOf(100))))-(0.001)).setScale(2, RoundingMode.HALF_UP).abs*(multiplier)
  }

  private def calculateEmployeeRate1(nicRateLimit: NICRateLimit, payPeriod: String, grossPay: BigDecimal, isAnnual: Boolean): Aggregation = {
    val rate = nicRateLimit.employeeRate.collect(rateLimit("1", payPeriod)).head
    if (grossPay > nicRateLimit.threshold.collect(rateLimit("primary", payPeriod)).head) {
      val amount = nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head - nicRateLimit.threshold.collect(rateLimit("primary", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else if (grossPay <= nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head) {
      val amount = grossPay - nicRateLimit.threshold.collect(rateLimit("primary", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else
    Aggregation(rate, 0)
  }

  private def calculateEmployeeRate3(nicRateLimit: NICRateLimit, payPeriod: String, grossPay: BigDecimal, isAnnual: Boolean): Aggregation = {

    val rate = nicRateLimit.employeeRate.collect(rateLimit("3", payPeriod)).head
    if (grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head) {
      val amount = nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head - nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else if (grossPay <= nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head && grossPay > nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head) {
      val amount = grossPay - nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else
      Aggregation(rate, 0)
  }

  private def calculateEmployeeRate4(nicRateLimit: NICRateLimit, payPeriod: String, grossPay: BigDecimal, isAnnual: Boolean): Aggregation = {
    val rate = nicRateLimit.employeeRate.collect(rateLimit("4", payPeriod)).head
    if (grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head) {
      val amount = grossPay - nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else
      Aggregation(rate, 0)
  }

  private def calculateEmployerRate2(nicRateLimit: NICRateLimit, payPeriod: String, grossPay: BigDecimal, isAnnual: Boolean): Aggregation = {
    val rate = nicRateLimit.employerRate.collect(rateLimit("2", payPeriod)).head
    if(grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head) {
      val amount = nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head - nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else if (grossPay <= nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head && grossPay > nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head) {
      val amount = grossPay - nicRateLimit.threshold.collect(rateLimit("secondary", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else
    Aggregation(rate, 0)
  }

  private def calculateEmployerRate3(nicRateLimit: NICRateLimit, payPeriod: String, grossPay: BigDecimal, isAnnual: Boolean): Aggregation = {
    val rate = nicRateLimit.employerRate.collect(rateLimit("2", payPeriod)).head
    if(grossPay > nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head) {
      val amount = grossPay - nicRateLimit.earningLimit.collect(rateLimit("upper", payPeriod)).head
      Aggregation(rate, calcRate(rate, amount, isAnnual))
    }
    else
      Aggregation(rate, 0)
  }

}

object LiveNICTaxCalculatorService extends NICTaxCalculatorService {
}

object SandboxNICTaxCalculatorService extends NICTaxCalculatorService {
}
