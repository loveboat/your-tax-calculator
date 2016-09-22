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

package uk.gov.hmrc.taxcalc.controllers

import org.scalatest.concurrent.ScalaFutures
import play.api.libs.json.Json
import play.api.test.Helpers._
import uk.gov.hmrc.play.test.{UnitSpec, WithFakeApplication}

class TaxCalculatorControllerSpec extends UnitSpec with WithFakeApplication with ScalaFutures {

  "calculate tax for 2016 tax year" should {
    "return a Mock TaxCalc response" in new LiveTaxCalcSuccess {

        val result = await(controller.calculateTax(true, 2016, "1100L", BigDecimal.valueOf(200000.00), "annual", Option(journeyId))(emptyRequest))
        status(result) shouldBe 200
        println(contentAsJson(result))
        contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.taxCalulator_PAYE_response);
    }
  }
}
