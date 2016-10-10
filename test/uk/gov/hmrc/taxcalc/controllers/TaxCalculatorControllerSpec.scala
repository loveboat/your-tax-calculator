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

//  "LiveTaxCalculatorController calculate tax for 2016 tax year" should {
//    "return a annual TaxCalc response" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "1100T", 1000008, "annual", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      println(contentAsJson(result))
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.taxCalculator_2016_response);
//    }
//
//    "return a NT TaxCalc response with no PAYE tax applied" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "NT", 20000000, "annual", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.NT_taxCode_response);
//    }
//
//    "return a BR TaxCalc response with PAYE tax applied at 20%" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "BR", 20000000, "annual", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.BR_taxCode_response);
//    }
//
//    "return a D0 TaxCalc response with PAYE tax applied at 40%" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "D0", 20000000, "annual", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.D0_taxCode_response);
//    }
//
//    "return a D1 TaxCalc response with PAYE tax applied at 45%" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "D1", 20000000, "annual", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.D1_taxCode_response);
//    }
//
//    "return weekly tax calc response using an hourly rate input" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "1100T", 9615, "weekly", Option(40), Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.hour_rate_weekly_response);
//    }
//
//    "return weekly tax calc response using tapering with emergency taxcode input" in new LiveTaxCalcSuccess {
//      val result = await(controller.calculateTax(false, 2016, "SK1100", 221200, "weekly", None, Option(journeyId))(emptyRequest))
//      status(result) shouldBe 200
//      println(contentAsJson(result))
//      contentAsJson(result) shouldBe Json.toJson(TaxCalculatorTestData.hour_rate_weekly_response);
//    }
//  }
}
