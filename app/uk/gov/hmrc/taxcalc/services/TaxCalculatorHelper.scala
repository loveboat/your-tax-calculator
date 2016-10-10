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

import uk.gov.hmrc.taxcalc.config.TaxCalculatorStartup
import uk.gov.hmrc.taxcalc.domain.{Money, _}

trait TaxCalculatorHelper {

  def isValidTaxCode(taxCode: String): Boolean = {
    isStandardTaxCode(taxCode) ||
    !isTaxableCode(taxCode) ||
    isBasicRateTaxCode(taxCode) ||
    isEmergencyTaxCode(taxCode) ||
    isValidScottishTaxCode(taxCode)
  }

  def isStandardTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}")
  }

  def isTaxableCode(taxCode: String): Boolean = {
    !taxCode.matches("([N,n][T,t]){1}") && !isBasicRateTaxCode(taxCode)
  }

  def isBasicRateTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([B,b][R,r]){1}") ||
    taxCode.matches("([D,d][0,1]){1}")
  }

  def isEmergencyTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([1]{2}[0]{2}[L,l]{1}){1}")
  }

  def isAdjustedTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([0-9]+[.]{1}[0-9]{2}[L,l]{1}){1}")
  }

  def isValidScottishTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([S]{1}[0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}") ||
    taxCode.matches("([S,s][N,n][T,t]){1}") ||
    taxCode.matches("([S,s][B,b][R,r]){1}") ||
    taxCode.matches("([S,s][D,d][0,1]){1}")
  }

  def loadTaxBands() : TaxYearBands = {
    TaxCalculatorStartup.taxCalcData.get("taxYearBands") match {
      case Some(taxYearBands: TaxYearBands) => taxYearBands
      case _ => throw new Exception("Error, no tax bands configured")
    }
  }

  def loadNICRateLimits() : NICRateLimits = {
    TaxCalculatorStartup.taxCalcData.get("nicRateLimits") match {
      case Some(nicRateLimits: NICRateLimits) => nicRateLimits
      case _ => throw new Exception("Error, no national insurance rates and limits configured")
    }
  }

  def getTaxBands(localDate: LocalDate) : TaxBands = {
    val taxBands = loadTaxBands().taxYearBands.sortWith(_.fromDate.getYear < _.fromDate.getYear())
      .filter(band => band.fromDate.isBefore(localDate) || band.fromDate.isEqual(localDate))
    taxBands.last
  }

  def getRateLimits(localDate: LocalDate) : NICRateLimit = {
    val rateLimits = loadNICRateLimits().rateLimits.sortWith(_.fromDate.getYear < _.fromDate.getYear())
      .filter(rateLimit => rateLimit.fromDate.isBefore(localDate) || rateLimit.fromDate.isEqual(localDate))
    rateLimits.last
  }

  def getPreviousBandMaxTaxAmount(payPeriod: String, band: Int): Option[BigDecimal] = {
    Option(getTaxBands(LocalDate.now()).taxBands.filter(_.band == band-1).head.periods.filter(_.periodType.equals(payPeriod)).head.cumulativeMaxTax)
  }

  def rateLimit(limitType: String, payPeriod: String): PartialFunction[RateLimit, Money] = {
    case rateLimit: RateLimit if rateLimit.rateLimitType.equals(limitType) => {
      val limit = Money((rateLimit.getClass.getMethod(payPeriod).invoke(rateLimit)).asInstanceOf[BigDecimal])
      limit
    }
  }

  def splitTaxCode(taxCode: String): String = {
    if(isStandardTaxCode(taxCode) || isAdjustedTaxCode(taxCode))
      taxCode.stripSuffix(taxCode.substring(taxCode.length - 1, taxCode.length))
    else taxCode
  }

  def removeScottishElement(taxCode: String): String = {
    isValidScottishTaxCode(taxCode) match {
      case true => taxCode.substring(1,taxCode.length)
      case false => taxCode
    }
  }

}
