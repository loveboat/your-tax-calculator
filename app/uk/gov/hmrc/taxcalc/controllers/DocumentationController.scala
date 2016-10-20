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

import controllers.AssetsBuilder
import play.api.mvc.{Action, AnyContent}
import play.twirl.api.Xml
import uk.gov.hmrc.play.microservice.controller.BaseController
import uk.gov.hmrc.taxcalc.config.{APIAccessConfig, AppContext}
import uk.gov.hmrc.taxcalc.domain.APIAccess
import uk.gov.hmrc.taxcalc.views._

import scala.language.dynamics

trait DocumentationController extends AssetsBuilder with BaseController {

  def definition(): Action[AnyContent] = Action {
    Ok(txt.definition(buildAccess())).withHeaders("Content-Type" -> "application/json")
  }

  def documentation(version: String, endpointName: String): Action[AnyContent] = Action {
    Documentation.findDocumentation(endpointName, version) match {
      case Some(docs) => Ok(docs).withHeaders("Content-Type" -> "application/xml")
      case None => NotFound
    }
  }

  def raml(version: String, file: String) = {
    super.at(s"/public/api/conf/$version", file)
  }

  private def buildAccess() = {
    val access = APIAccessConfig(AppContext.access)
    APIAccess(access.accessType, access.whiteListedApplicationIds)
  }
}

object DocumentationController extends DocumentationController

object Documentation {
  val version1_0 = "1.0"
  val calculateTax = "Calculate Tax"
  val taxExpenditureBreakdown = "Tax Expenditure Breakdown"
  val taxExpenditureCategories = "Tax Expenditure Categories"
  val taxExpenditureCategoryPercentage = "Tax Expenditure Category Percentage"

  def findDocumentation(endpointName: String, version: String) = applyTemplate(endpointName, version)

  def applyTemplate(apiName: String, version: String): Option[Xml] = {
    (apiName, version) match {
      case (`calculateTax`, `version1_0`) => Some(xml.calculateTax())
      case (`taxExpenditureBreakdown`, `version1_0`) => Some(xml.taxExpenditureBreakdown())
      case (`taxExpenditureCategories`, `version1_0`) => Some(xml.taxExpenditureCategories())
      case (`taxExpenditureCategoryPercentage`, `version1_0`) => Some(xml.taxExpenditureCategoryPercentage())
      case _ => None
    }
  }
}
