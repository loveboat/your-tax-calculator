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

import uk.gov.hmrc.taxcalc.controllers.BadRequestException
import uk.gov.hmrc.taxcalc.domain._


trait PAYETaxCalculatorService extends TaxCalculatorHelper {


  def calculatePAYETax(taxCode: String, payPeriod: String, grossPay: Money): PAYETaxResult = {
    isValidTaxCode(taxCode) match {
      case true => {
        val today = LocalDate.now
        val taxablePay = TaxablePayCalculator(today, taxCode, payPeriod, grossPay).calculate()
        val taxBand = TaxBandCalculator(taxCode, today, payPeriod, taxablePay.result).calculate().result
        val excessPay = ExcessPayCalculator(taxCode, today, taxBand.band, payPeriod, taxablePay.result).calculate().result
        val finalBandTaxedAmount = Money(excessPay * (taxBand.rate / (100)), 2, true)
        val previousBandMaxTax = if (taxBand.band > 1 && !isBasicRateTaxCode(taxCode)) Money(getPreviousBandMaxTaxAmount(payPeriod, taxBand.band).get, 2, true) else Money(0)
        val rate = if(taxablePay.result.value == 0) BigDecimal(0) else taxBand.rate
        PAYETaxResult(taxablePay.result, excessPay, finalBandTaxedAmount, taxBand.band, previousBandMaxTax, rate, taxablePay.isTapered, taxablePay.additionalTaxablePay)
      }
      case false => throw new BadRequestException("Invalid Tax Code!")
    }
  }
}

object LivePAYETaxCalculatorService extends PAYETaxCalculatorService {
}