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
import uk.gov.hmrc.taxcalc.config.TaxCalculatorStartup
import uk.gov.hmrc.taxcalc.domain._

import scala.concurrent.Future
import scala.io.Source._
import scala.math.BigDecimal.RoundingMode
import scala.tools.nsc.interpreter._


trait TaxCalculatorService {

  def calculateTax(taxYear: Int, taxCode: String, grossPay: Long, payPeriod: String): Future[TaxCalc] = {

    getClass.getResourceAsStream(s"/tax-calc/tax_calculator_2016_response.json") match {
      case is: InputStream => {
        Future.successful(Json.parse(fromInputStream(is).mkString).as[TaxCalc])
      }
    }
  }

  def loadTaxBands() : TaxYearBands = {
    TaxCalculatorStartup.taxBands.get("taxYearBands") match {
      case Some(taxYearBands: TaxYearBands) => taxYearBands
      case _ => throw new Exception("Error, no tax bands configured")
    }
  }

  def getTaxBands(localDate: LocalDate) : TaxBands = {
    val taxBands = loadTaxBands().taxYearBands.sortWith(_.fromDate.getYear < _.fromDate.getYear())
      .filter(band => band.fromDate.isBefore(localDate) || band.fromDate.isEqual(localDate))
    taxBands.last
  }

  def isValidTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}")
  }

  def calculateAllowance(taxCode: String): Seq[(String, Allowance)] = {
    val taxCodeNumber = taxCode.stripSuffix(taxCode.substring(taxCode.length - 1, taxCode.length)).toInt
    val seedData = new PAYEAllowanceSeedData(taxCodeNumber)
    Seq("weekly" -> new WeeklyAllowance(seedData), "monthly" -> new MonthlyAllowance(seedData), "annual" -> new AnnualAllowance(taxCode, taxCodeNumber))
  }


  def calculatePAYETaxablePay(taxCode: String, payPeriod: String, grossPay: BigDecimal) : Seq[BigDecimal] = {
    for{
      allowance <- calculateAllowance(taxCode).filter(_._1.equals(payPeriod))
    } yield (grossPay.-(allowance._2.allowance).setScale(2, RoundingMode.UP))
  }

  def getPreviousBandMaxTaxAmount(payPeriod: String, band: Int): Option[BigDecimal] = {
    Option(getTaxBands(LocalDate.now()).taxBands.filter(_.band == band-1).head.periods.filter(_.periodType.equals(payPeriod)).head.cumulativeMaxTax)
  }

  def calculatePAYETax(taxCode: String, payPeriod: String, grossPay: BigDecimal) :BigDecimal = {
    val taxablePay = calculatePAYETaxablePay(taxCode, payPeriod, grossPay).head
    val taxBand = determineTaxBand(taxCode, payPeriod, taxablePay)
    val excessPay = calculateExcessPay(taxBand, payPeriod, taxablePay)
    val taxedAmount = excessPay.*(taxBand.rate./(100)).setScale(2, RoundingMode.HALF_UP)
    if(taxBand.band > 1) {
      val a = getPreviousBandMaxTaxAmount(payPeriod, taxBand.band).get.setScale(2, RoundingMode.HALF_UP)
      val b = taxedAmount.+(a)
      b
    }
    else
      taxedAmount
  }

  def determineTaxBand(taxCode: String, payPeriod: String, taxablePay: BigDecimal) : TaxBand = {
    val taxBands = getTaxBands(LocalDate.now()).taxBands.collect(taxBandFilterFunc(payPeriod, taxablePay))
    if(taxBands.size > 0){
      taxBands.head
    }
    else getTaxBands(LocalDate.now()).taxBands.last
  }

  private def taxBandFilterFunc(periodType: String, taxablePay: BigDecimal): PartialFunction[TaxBand, TaxBand] = {
    case taxBand: TaxBand  if isPeriodValid(periodType, taxBand.periods, taxablePay) => taxBand
  }

  private def isPeriodValid(periodType: String, periodCalcs: Seq[PeriodCalc], taxablePay: BigDecimal) : Boolean = {
    !periodCalcs.filter(_.periodType.equals(periodType)).filter((_.threshold.>(taxablePay))).isEmpty
  }

  def calculateExcessPay(taxBand: TaxBand, payPeriod: String, taxablePay: BigDecimal): BigDecimal = {
    if(taxBand.band > 1){
      val threshold = getTaxBands(LocalDate.now()).taxBands.filter(_.band == taxBand.band-1).head
        .periods.filter(_.periodType.equals(payPeriod)).head.threshold
      threshold.-(taxablePay.intValue()).abs
    }
    else taxablePay
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {

}

object SandboxTaxCalculatorService extends TaxCalculatorService {

}