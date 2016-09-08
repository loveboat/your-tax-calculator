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
import uk.gov.hmrc.taxcalc.domain.TaxCalc

import scala.concurrent.Future
import scala.io.Source._
import scala.tools.nsc.interpreter._


trait TaxCalculatorService {

  def calculateTax(taxYear: Int, taxCode: String, grossPay: Long, payPeriod: String): Future[TaxCalc] = {

    getClass.getResourceAsStream(s"/tax-calc/tax_calculator_2016_response.json") match {
      case is: InputStream => {
        Future.successful(Json.parse(fromInputStream(is).mkString).as[TaxCalc])
      }
    }
  }
}

object LiveTaxCalculatorService extends TaxCalculatorService {

}

object SandboxTaxCalculatorService extends TaxCalculatorService {

}
