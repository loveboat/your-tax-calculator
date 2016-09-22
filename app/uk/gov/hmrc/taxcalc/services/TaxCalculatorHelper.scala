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
import uk.gov.hmrc.taxcalc.domain.{TaxBands, TaxYearBands}

trait TaxCalculatorHelper {

  def isValidTaxCode(taxCode: String): Boolean = {
    taxCode.matches("([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}")
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

  def getPreviousBandMaxTaxAmount(payPeriod: String, band: Int): Option[BigDecimal] = {
    Option(getTaxBands(LocalDate.now()).taxBands.filter(_.band == band-1).head.periods.filter(_.periodType.equals(payPeriod)).head.cumulativeMaxTax)
  }

}
