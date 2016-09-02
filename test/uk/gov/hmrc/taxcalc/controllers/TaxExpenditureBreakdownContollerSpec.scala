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

class TaxExpenditureBreakdownContollerSpec extends UnitSpec with WithFakeApplication with ScalaFutures {

  "taxExpenditureCategoryBreakdown for 2016 tax year" should {
    "return a CategoryBreakdown response" in new LiveSuccess {

        val result = await(controller.taxExpenditureCategoryBreakdown(2016)(emptyRequest))
        status(result) shouldBe 200
        contentAsJson(result) shouldBe Json.toJson(TaxExpenditureTestData.pesa_2016_response);
    }
  }

  "taxExpenditureCategoryBreakdown for 1900 tax year" should {
    "return an error response" in new LiveSuccess {

      val result = await(controller.taxExpenditureCategoryBreakdown(1900)(emptyRequest))
      status(result) shouldBe 400
    }
  }



}
