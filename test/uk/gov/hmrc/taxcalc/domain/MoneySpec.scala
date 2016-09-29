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

package uk.gov.hmrc.taxcalc.domain

import uk.gov.hmrc.play.test.UnitSpec
import uk.gov.hmrc.taxcalc.controllers.Setup

class MoneySpec extends UnitSpec  {

  "Money " should {
    "should do no rounding" in new Setup {
      val money = Money(1000.4567)
      money.value shouldBe BigDecimal.valueOf(1000.4567)
    }
    "should round to 2 decimal places with no rounding up" in new Setup {
      val money = Money(1000.4567, Option(2), Option(false))
      money.value shouldBe BigDecimal.valueOf(1000.45)
    }
    "should round to 3 decimal places with no rounding up" in new Setup {
      val money = Money(1000.4567, Option(3), Option(false))
      money.value shouldBe BigDecimal.valueOf(1000.456)
    }
    "should round up to 2 decimal places" in new Setup {
      val money = Money(1000.4567, Option(2), Option(true))
      money.value shouldBe BigDecimal.valueOf(1000.46)
    }
    "should round up to 1 decimal places" in new Setup {
      val money = Money(1000.4567, Option(1), Option(true))
      money.value shouldBe BigDecimal.valueOf(1000.5)
    }
  }

}
