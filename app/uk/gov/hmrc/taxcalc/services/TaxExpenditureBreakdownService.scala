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

import play.api.libs.json.Json
import uk.gov.hmrc.taxcalc.controllers.{BadRequestException, ErrorBadRequest}
import uk.gov.hmrc.taxcalc.domain.CategoryBreakdown

import scala.concurrent.Future
import scala.io.Source._
import scala.tools.nsc.interpreter.InputStream

trait TaxExpenditureBreakdownService {

  def taxExpenditureCategoryBreakdown(taxYear: Int) : Future[CategoryBreakdown] = {
    getClass.getResourceAsStream(s"/categories/pesa_${taxYear}.json") match {
      case is: InputStream => {
        Future.successful(Json.parse(fromInputStream(is).mkString).as[CategoryBreakdown])
      }
      case _ => Future.failed(new BadRequestException(s"No Category Breakdown Found for ${taxYear}"))
    }
  }
}

object LiveTaxExpenditureBreakdownService extends TaxExpenditureBreakdownService {

}

object SandboxTaxExpenditureBreakdownService extends TaxExpenditureBreakdownService {

}
