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


trait PAYETaxCalculatorService extends TaxCalculatorHelper {


  def calculatePAYETax(taxCode: String, payPeriod: String, grossPay: Money) : PAYETaxResult = {

    val taxablePay = calculatePAYETaxablePay(taxCode, payPeriod, grossPay).head

    val taxBand = determineTaxBand(taxCode, payPeriod, taxablePay)
    val excessPay = calculateExcessPay(taxBand, payPeriod, taxablePay)
    val finalBandTaxedAmount = Money(excessPay*(taxBand.rate/(100)), Option(2), Option(true))
    val previousBandMaxTax = Money(getPreviousBandMaxTaxAmount(payPeriod, taxBand.band).get, Option(2), Option(true))
    if(taxBand.band > 1) {
      PAYETaxResult(taxablePay, excessPay, finalBandTaxedAmount, taxBand.band, finalBandTaxedAmount.+(previousBandMaxTax))
    }
    else
      PAYETaxResult(taxablePay, excessPay, finalBandTaxedAmount, taxBand.band, finalBandTaxedAmount)
  }

  def calculateAllowance(taxCode: String): Seq[(String, Allowance)] = {
    val taxCodeNumber = taxCode.stripSuffix(taxCode.substring(taxCode.length - 1, taxCode.length)).toInt
    val seedData = new PAYEAllowanceSeedData(taxCodeNumber)
    Seq("weekly" -> WeeklyAllowance(seedData), "monthly" -> MonthlyAllowance(seedData), "annual" -> AnnualAllowance(taxCode, taxCodeNumber))
  }

  def calculatePAYETaxablePay(taxCode: String, payPeriod: String, grossPay: Money) : Seq[Money] = {
    for{
    allowance <- calculateAllowance(taxCode).filter(_._1.equals(payPeriod))
    } yield (grossPay.-(allowance._2.allowance))
  }

  def determineTaxBand(taxCode: String, payPeriod: String, taxablePay: Money) : TaxBand = {
    val taxBands = getTaxBands(LocalDate.now())
    val taxBand = taxBands.taxBands.collect(taxBandFilterFunc(payPeriod, taxablePay))
    if(taxBand.size > 0){
      taxBand.head
    }
    else taxBands.taxBands.last
  }

  def calculateExcessPay(taxBand: TaxBand, payPeriod: String, taxablePay: Money): Money = {
    val taxBands = getTaxBands(LocalDate.now())
    if(taxBand.band > 1){
      val threshold = taxBands.taxBands.filter(_.band == taxBand.band-1).head.periods.filter(_.periodType.equals(payPeriod)).head.threshold
      Money(threshold.-(taxablePay.value.intValue()).abs)
    }
    else taxablePay
  }

  private def taxBandFilterFunc(periodType: String, taxablePay: Money): PartialFunction[TaxBand, TaxBand] = {
    case taxBand: TaxBand  if isPeriodValid(periodType, taxBand.periods, taxablePay) => taxBand
  }

  private def isPeriodValid(periodType: String, periodCalcs: Seq[PeriodCalc], taxablePay: Money) : Boolean = {
    !periodCalcs.filter(_.periodType.equals(periodType)).filter((_.threshold.>(taxablePay.value))).isEmpty
  }

}

object LivePAYETaxCalculatorService extends PAYETaxCalculatorService {
}

object SandboxPAYETaxCalculatorService extends PAYETaxCalculatorService {
}