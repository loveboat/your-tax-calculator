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

import uk.gov.hmrc.play.test.UnitSpec
import uk.gov.hmrc.taxcalc.controllers.Setup
import uk.gov.hmrc.taxcalc.domain.Money

class TaxablePayCalculatorSpec extends UnitSpec {


  "TaxablePayCalculator calculate " should {
    "calculate the taxable pay duh!!!" in new Setup {
      val result = TaxablePayCalculator(LocalDate.now, "1100T", "annual", Money(BigDecimal(10000.00))).calculate().result


    }
  }

}
