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

import play.api.libs.json.Json
import play.api.mvc.Action
import uk.gov.hmrc.play.microservice.controller.BaseController
import uk.gov.hmrc.taxcalc.services._

import scala.concurrent.ExecutionContext.Implicits.global


trait TaxCalculatorController extends BaseController with ErrorHandling {
  val service: TaxCalculatorService

  def calculateTax(isStatePensionAge: String, taxYear: Int, taxCode: String, grossPay: Long, payPeriod: String, hours: Option[Int], journeyId: Option[String]) = Action.async {
    implicit request => {
      errorWrapper {
        service.calculateTax(isStatePensionAge, taxYear, taxCode.toUpperCase, grossPay, payPeriod.toLowerCase(), hours).map {
          response => Ok(Json.toJson(response))
        }
      }
    }
  }
}

object  LiveTaxCalculatorController extends TaxCalculatorController {
  override val service: TaxCalculatorService = LiveTaxCalculatorService
  override val app: String = "LiveTaxCalculatorController"
}
