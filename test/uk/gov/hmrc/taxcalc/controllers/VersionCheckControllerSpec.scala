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

class VersionCheckControllerSpec extends UnitSpec with WithFakeApplication with ScalaFutures{

  "VersionCheckController" should {
    "return a new journeyId given a version that does not require an upgrade and no existing journeyId is provided" in new VersionCheckControllerNoUpgradeRequired {
      val result = await(controller.preFlightCheck(None)(deviceRequest))

      status(result) shouldBe 200
      contentAsJson(result) shouldBe Json.parse(s"""{"upgradeRequired":false,"journeyId":"$expectedJourneyId"}""")
    }

    "return the journeyId provided given a version that does not require an upgrade" in new VersionCheckControllerNoUpgradeRequired {
      val existingJourneyId = "ada-1815-noel"
      val result = await(controller.preFlightCheck(Some(existingJourneyId))(deviceRequest))

      status(result) shouldBe 200
      contentAsJson(result) shouldBe Json.parse(s"""{"upgradeRequired":false,"journeyId":"$existingJourneyId"}""")
    }

    "not return a journeyId given a version that does require an upgrade" in new VersionCheckControllerUpgradeRequired {
      val result = await(controller.preFlightCheck(None)(deviceRequest))

      status(result) shouldBe 200
      contentAsJson(result) shouldBe Json.parse("""{"upgradeRequired":true}""")
    }

    "return an error given problem processing the request" in new VersionCheckControllerProblem {
      val result = await(controller.preFlightCheck(None)(deviceRequest))

      status(result) shouldBe 500
    }
  }
}