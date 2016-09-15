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
import uk.gov.hmrc.taxcalc.domain.{TaxBands, TaxYearBands}

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

}
