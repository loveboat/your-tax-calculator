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
import play.api.libs.json.Json
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}
import uk.gov.hmrc.taxcalc.controllers.{LiveNICTaxCalcServiceSuccess, TaxCalculatorTestData}
import uk.gov.hmrc.taxcalc.domain.{NICRateLimit, NICRateLimits, TaxBands, TaxYearBands}

import scala.math.BigDecimal

class NICTaxCalculatorServiceSpec extends UnitSpec with WithFakeApplication with ScalaFutures {


  "NICTaxCalculatorService loadNICRateLimits " should {
    "should load up the static taxBands data" in new LiveNICTaxCalcServiceSuccess {
      val result: NICRateLimits = service.loadNICRateLimits();
      result.rateLimits.size shouldBe 2
      result shouldBe TaxCalculatorTestData.nic_rates_limits.as[NICRateLimits]
    }
  }

  "NICTaxCalculatorService.getNICRateLimits " should {
    "return the correct nic rates and limits for a 2016" in new LiveNICTaxCalcServiceSuccess {

      val date = LocalDate.of(2016, 9, 21)
      val result: NICRateLimit = service.getRateLimits(date)
      result.fromDate shouldBe LocalDate.of(2016,4,5)
    }

    "return the correct nic rates and limits for a 2017" in new LiveNICTaxCalcServiceSuccess {

      val date = LocalDate.of(2017, 8, 22)
      val result: NICRateLimit = service.getRateLimits(date)
      result.fromDate shouldBe LocalDate.of(2017,4,5)
    }
  }

    "NICTaxCalculatorService.calculateEmployeeNIC " should {
      "should calculate the annual rate at the monthly rate" in new LiveNICTaxCalcServiceSuccess {
        val rates = service.getRateLimits(LocalDate.now)
        val result = service.calculateEmployeeNIC(BigDecimal.valueOf(100000.00), "annual", rates)
        result.size shouldBe 2
        result.map{
          aggregation => aggregation.percentage.intValue() match {
            case 12 => aggregation.amount shouldBe BigDecimal.valueOf(4191.84)
            case 2 => aggregation.amount shouldBe BigDecimal.valueOf(1140.12)
          }
        }
      }
      "should calculate at the monthly rate" in new LiveNICTaxCalcServiceSuccess {
        val rates = service.getRateLimits(LocalDate.now)
        val result = service.calculateEmployeeNIC(BigDecimal.valueOf(8333.33), "monthly", rates)
        result.size shouldBe 2
        result.map{
          aggregation => aggregation.percentage.intValue() match {
            case 12 => aggregation.amount shouldBe BigDecimal.valueOf(349.32)
            case 2 => aggregation.amount shouldBe BigDecimal.valueOf(95.01)
          }
        }
      }
      "should calculate at the weekly rate" in new LiveNICTaxCalcServiceSuccess {
        val rates = service.getRateLimits(LocalDate.now)
        val result = service.calculateEmployeeNIC(BigDecimal.valueOf(1923.08), "weekly", rates)
        result.size shouldBe 2
        result.map{
          aggregation => aggregation.percentage.intValue() match {
            case 12 => aggregation.amount shouldBe BigDecimal.valueOf(80.64)
            case 2 => aggregation.amount shouldBe BigDecimal.valueOf(21.92)
          }
        }
      }
  }

  "NICTaxCalculatorService.calculateEmployerNIC " should {
    "should calculate the annual rate at the monthly rate" in new LiveNICTaxCalcServiceSuccess {
      val rates = service.getRateLimits(LocalDate.now)
      val result = service.calculateEmployerNIC(BigDecimal.valueOf(100000.00), "annual", rates)
      result.size shouldBe 1
      result.map{
        aggregation =>
          aggregation.percentage shouldBe 13.8
          aggregation.amount shouldBe BigDecimal.valueOf(12680.52)
        }
      }
    }
    "should calculate at the monthly rate" in new LiveNICTaxCalcServiceSuccess {
      val rates = service.getRateLimits(LocalDate.now)
      val result = service.calculateEmployerNIC(BigDecimal.valueOf(8333.33), "monthly", rates)
      result.size shouldBe 1
      result.map{
        aggregation =>
          aggregation.percentage shouldBe 13.8
          aggregation.amount shouldBe BigDecimal.valueOf(1056.71)
      }
    }
    "should calculate at the weekly rate" in new LiveNICTaxCalcServiceSuccess {
      val rates = service.getRateLimits(LocalDate.now)
      val result = service.calculateEmployerNIC(BigDecimal.valueOf(1923.08), "weekly", rates)
      result.size shouldBe 1
      result.map {
        aggregation =>
          aggregation.percentage shouldBe 13.8
          aggregation.amount shouldBe BigDecimal.valueOf(243.86)
      }
    }
}
