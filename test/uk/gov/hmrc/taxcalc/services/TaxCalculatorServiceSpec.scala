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

import org.scalatest.concurrent.ScalaFutures
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}
import uk.gov.hmrc.taxcalc.controllers.{LiveTaxCalcServiceSuccess, TaxCalculatorTestData}
import uk.gov.hmrc.taxcalc.domain._

class TaxCalculatorServiceSpec extends UnitSpec with WithFakeApplication with ScalaFutures {


  "TaxCalculatorService loadTaxBands " should {
    "should load up the static taxBands data" in new LiveTaxCalcServiceSuccess {
      val result: TaxYearBands = service.loadTaxBands()
      result.taxYearBands.size shouldBe 2
      result shouldBe TaxCalculatorTestData.taxBands.as[TaxYearBands]
    }
  }

  "TaxCalculatorService " should {
    "return the correct tax band for a 2016" in new LiveTaxCalcServiceSuccess {

      val date = LocalDate.of(2016, 9, 21)
      val result: TaxBands = service.getTaxBands(date)
      result.fromDate shouldBe LocalDate.of(2016,4,5)
    }

    "return the correct tax band for a 2017" in new LiveTaxCalcServiceSuccess {

      val date = LocalDate.of(2017, 8, 22)
      val result: TaxBands = service.getTaxBands(date)
      result.fromDate shouldBe LocalDate.of(2017,4,5)
    }
  }

  "TaxCalculatorService.isValidTaxCode " should {
    "return true if pattern matches ([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}" in new LiveTaxCalcServiceSuccess {
      service.isValidTaxCode("1000l") shouldBe true
    }

    "return false for X1919 its pattern doesn't match ([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}" in new LiveTaxCalcServiceSuccess {
      service.isValidTaxCode("X1919") shouldBe false
    }

    "return false for 99999m its pattern doesn't match ([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}" in new LiveTaxCalcServiceSuccess {
      service.isValidTaxCode("99999m") shouldBe false
    }

    "return true for 0X which matched the pattern ([0-9]{1,4}[L-N,l-n,T,t,X,x]{1}){1}" in new LiveTaxCalcServiceSuccess {
      service.isValidTaxCode("0X") shouldBe true
    }
  }

  "TaxCalculatorService.calculateAllowance " should {
    "calculate the Annual, Monthly and Weekly PAYE allowances" in new LiveTaxCalcServiceSuccess {
      val result = service.calculateAllowance("1100L")
      result.size shouldBe 3
      result.map{
        allowance => allowance._2 match {
          case weekly: WeeklyAllowance => {
            weekly.quotient.toDouble shouldBe 192.32
            weekly.remainder.toDouble shouldBe 19.41
            weekly.allowance.toDouble shouldBe 211.73
          }
          case monthly: MonthlyAllowance => {
            monthly.quotient.toDouble shouldBe 833.34
            monthly.remainder.toDouble shouldBe 84.09
            monthly.allowance.toDouble shouldBe 917.43
          }
          case annual: AnnualAllowance => {
            annual.quotient shouldBe 0.00
            annual.remainder shouldBe 0.00
            annual.allowance shouldBe 11009.00
          }
        }
      }
    }
  }

  "TaxCalculatorService.calculatePAYETaxablePay " should {
    "calculate weekly taxable pay" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETaxablePay("1100L", "weekly", BigDecimal.valueOf(673.08))
      result.size shouldBe 1
      result.head shouldBe 461.35
    }

    "calculate monthly taxable pay" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETaxablePay("1100L", "monthly", BigDecimal.valueOf(2916.86))
      result.size shouldBe 1
      result.head shouldBe 1999.43
    }

    "calculate annual taxable pay" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETaxablePay("1100L", "annual", BigDecimal.valueOf(35002.32))
      result.size shouldBe 1
      result.head shouldBe 23993.32
    }
  }

  "TaxCalculatorService.determineTaxBand " should {
    "return annual taxBand 2" in new LiveTaxCalcServiceSuccess { //35002.32
      val result = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(23993.32))
      result.band shouldBe 2
    }
    "return annual taxBand 3" in new LiveTaxCalcServiceSuccess { //70000
      val result = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(58991.00))
      result.band shouldBe 3
    }
    "return annual taxBand 4" in new LiveTaxCalcServiceSuccess { //200000
      val result = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(188991.00))
      result.band shouldBe 4
    }
    "return monthly taxBand 2" in new LiveTaxCalcServiceSuccess { //2120.20
      val result = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(1102.77))
      result.band shouldBe 2
    }
    "return monthly taxBand 3" in new LiveTaxCalcServiceSuccess { //11500.00
      val result = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(10582.57))
      result.band shouldBe 3
    }
    "return monthly taxBand 4" in new LiveTaxCalcServiceSuccess { //15000.00
      val result = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(14082.57))
      result.band shouldBe 4
    }
    "return weekly taxBand 2" in new LiveTaxCalcServiceSuccess { //600.00
      val result = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(388.27))
      result.band shouldBe 2
    }
    "return weekly taxBand 3" in new LiveTaxCalcServiceSuccess { //2550.55
      val result = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(2338.82))
      result.band shouldBe 3
    }
    "return weekly taxBand 4" in new LiveTaxCalcServiceSuccess { //4500.00
      val result = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(4288.27))
      result.band shouldBe 4
    }
  }

  "TaxCalculatorService.calculateExcessPay " should {
    "calculate the annual excess value against tax band 2" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(23993.32))
      val result = service.calculateExcessPay(taxBand, "annual", BigDecimal.valueOf(23993.32))
      result shouldBe BigDecimal.valueOf(23993)
    }
    "calculate the annual excess value against tax band 3" in new LiveTaxCalcServiceSuccess { //70000
      val taxBand = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(58991.00))
      val result = service.calculateExcessPay(taxBand, "annual", BigDecimal.valueOf(58991.00))
      result shouldBe BigDecimal.valueOf(26991)
    }
    "calculate the annual excess value against tax band 4" in new LiveTaxCalcServiceSuccess { //200000
      val taxBand = service.determineTaxBand("1100L", "annual", BigDecimal.valueOf(188991.00))
      val result = service.calculateExcessPay(taxBand, "annual", BigDecimal.valueOf(188991.00))
      result shouldBe BigDecimal.valueOf(38991)
    }
    "calculate the monthly excess value against tax band 2" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(1202.77))
      val result = service.calculateExcessPay(taxBand, "monthly", BigDecimal.valueOf(1202.77))
      result shouldBe BigDecimal.valueOf(1202)
    }
    "calculate the monthly excess value against tax band 3" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(10582.57))
      val result = service.calculateExcessPay(taxBand, "monthly", BigDecimal.valueOf(10582.57))
      result shouldBe BigDecimal.valueOf(7915.3334)
    }
    "calculate the monthly excess value against tax band 4" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "monthly", BigDecimal.valueOf(14082.57))
      val result = service.calculateExcessPay(taxBand, "monthly", BigDecimal.valueOf(14082.57))
      result shouldBe BigDecimal.valueOf(1582)
    }
    "calculate the weekly excess value against tax band 2" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(388.27))
      val result = service.calculateExcessPay(taxBand, "weekly", BigDecimal.valueOf(388.27))
      result shouldBe BigDecimal.valueOf(388)
    }
    "calculate the weekly excess value against tax band 3" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(2338.82))
      val result = service.calculateExcessPay(taxBand, "weekly", BigDecimal.valueOf(2338.82))
      result shouldBe BigDecimal.valueOf(1722.6154)
    }
    "calculate the weekly excess value against tax band 4" in new LiveTaxCalcServiceSuccess {
      val taxBand = service.determineTaxBand("1100L", "weekly", BigDecimal.valueOf(4288.27))
      val result = service.calculateExcessPay(taxBand, "weekly", BigDecimal.valueOf(4288.27))
      result shouldBe BigDecimal.valueOf(1403.3847)
    }
  }

  "TaxCalculatorService.calculatePAYETax " should {
    "Calculate Annual PAYE tax for a gross salary of 35002.32 in tax band 2" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "annual", BigDecimal.valueOf(35002.32))
      result shouldBe BigDecimal.valueOf(4798.60)
    }

    "Calculate Annual PAYE tax for a gross salary of 70000.00 in tax band 3" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "annual", BigDecimal.valueOf(70000.00))
      result shouldBe BigDecimal.valueOf(17196.40)
    }

    "Calculate Annual PAYE tax for a gross salary of 200000.00 in tax band 4" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "annual", BigDecimal.valueOf(200000.00))
      result shouldBe BigDecimal.valueOf(71145.95)
    }

    "Calculate Monthly PAYE tax for a gross salary of 2120.20 in tax band 2" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "monthly", BigDecimal.valueOf(2120.20))
      result shouldBe BigDecimal.valueOf(240.40)
    }

    "Calculate Monthly PAYE tax for a gross salary of 11500.00 in tax band 3" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "monthly", BigDecimal.valueOf(11500.00))
      result shouldBe BigDecimal.valueOf(3699.46)
    }

    //TODO query rounding problem
    "Calculate Monthly PAYE tax for a gross salary of 15000.00 in tax band 4" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "monthly", BigDecimal.valueOf(15000.00))
      result shouldBe BigDecimal.valueOf(5178.57) //rounding problem 5178.5666
    }

    "Calculate Weekly PAYE tax for a gross salary of 600.00 in tax band 2" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "weekly", BigDecimal.valueOf(600.00))
      result shouldBe BigDecimal.valueOf(77.60)
    }

    "Calculate Weekly PAYE tax for a gross salary of 2550.55 in tax band 3" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "weekly", BigDecimal.valueOf(2550.55))
      result shouldBe BigDecimal.valueOf(812.13)
    }

    "Calculate Weekly PAYE tax for a gross salary of 4500.00 in tax band 4" in new LiveTaxCalcServiceSuccess {
      val result = service.calculatePAYETax("1100L", "weekly", BigDecimal.valueOf(4500.00))
      result shouldBe BigDecimal.valueOf(1662.29)
    }
  }
}
