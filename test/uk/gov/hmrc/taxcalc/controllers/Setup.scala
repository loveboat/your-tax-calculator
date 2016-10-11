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

import java.time.LocalDate
import java.util.UUID

import play.api.libs.json.{JsValue, Json}
import play.api.test.FakeRequest
import uk.gov.hmrc.play.http._
import uk.gov.hmrc.taxcalc.connectors.VersionCheckConnector
import uk.gov.hmrc.taxcalc.domain.{Money, PreFlightCheckResponse}
import uk.gov.hmrc.taxcalc.services._

import scala.concurrent.{ExecutionContext, Future}

trait Setup {
  implicit val hc = HeaderCarrier()
  val journeyId = UUID.randomUUID
  val expectedJourneyId = journeyId.toString
  val emptyRequest = FakeRequest()
  val deviceRequest = FakeRequest().withBody(Json.parse("""{"os":"chimera", "version":"3.2.1"}"""))
    .withHeaders("Content-Type" -> "application/json", "Accept" -> "application/vnd.hmrc.1.0+json")
}

trait LiveTaxExpenditureSuccess extends Setup {
  val controller = new TaxExpenditureBreakdownController {
    override val service: TaxExpenditureBreakdownService = LiveTaxExpenditureBreakdownService
    override val app: String = "TestLiveTaxExpenditureBreakdownController"
  }
}

trait LiveTaxCalcSuccess extends Setup {
  val controller = new TaxCalculatorController {
    override val service: TaxCalculatorService = LiveTaxCalculatorService
    override val app: String = "TestLiveTaxCalculatorController"
  }
}

trait LivePAYETaxCalcServiceSuccess extends Setup {
  val service = new PAYETaxCalculatorService {

  }
}

trait LiveNICTaxCalcServiceSuccess extends Setup {
  val service = new NICTaxCalculatorService {

  }
}

trait ExcessPayCalculatorSetup extends Setup {
  val taxCode:String
  val payPeriod: String
  val date: LocalDate
  val taxablePay: Money
  val bandId: Int
}

trait ExcessPayCalculatorFullTaxableAmountSetup extends ExcessPayCalculatorSetup {
  override val date: LocalDate = LocalDate.now
  override val bandId: Int = 1
  override val taxablePay: Money = Money(BigDecimal.valueOf(60000.00))
}

class TestVersionCheckService(updateRequired: Boolean, testJourneyId: Option[UUID]) extends VersionCheckService {
  override val connector = VersionCheckConnector
  override def preFlightCheck(inputRequest:JsValue)(implicit hc: HeaderCarrier, ex: ExecutionContext): Future[PreFlightCheckResponse] = {
    Future(PreFlightCheckResponse(updateRequired, testJourneyId))
  }
}

trait VersionCheckControllerNoUpgradeRequired extends Setup {
  val controller = new VersionCheckController {
    override val service: VersionCheckService = new TestVersionCheckService(false, Option(journeyId))
    override val app: String = "TestVersionCheckController"
  }
}

trait VersionCheckControllerUpgradeRequired extends Setup {
  val controller = new VersionCheckController {
    override val service: VersionCheckService = new TestVersionCheckService(true, None)
    override val app: String = "TestVersionCheckController"
  }
}