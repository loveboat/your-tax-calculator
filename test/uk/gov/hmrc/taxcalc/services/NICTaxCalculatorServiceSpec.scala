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
import uk.gov.hmrc.taxcalc.controllers.{LiveNICTaxCalcServiceSuccess, TaxCalculatorTestData}
import uk.gov.hmrc.taxcalc.domain.{NICRateLimit, NICRateLimits, TaxBands, TaxYearBands}

class NICTaxCalculatorServiceSpec extends UnitSpec with WithFakeApplication with ScalaFutures {


  "NICTaxCalculatorService loadTaxBands " should {
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

}
