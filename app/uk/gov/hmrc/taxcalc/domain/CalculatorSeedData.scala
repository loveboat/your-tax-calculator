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

import scala.math.BigDecimal.RoundingMode

class PAYEAllowanceSeedData (taxCodeNumber: Int) {
  val recalculatedTaxCodeNumber = taxCodeNumber-1
  val initQuotient = recalculatedTaxCodeNumber/500
  val initRemainder = recalculatedTaxCodeNumber%500
  val middleRemainder = ((initRemainder.+(1)).*(10)).+(9)
}

class AnnualAllowance(taxCode: String, taxCodeNumber: Int) extends Allowance {
  override val quotient: BigDecimal = 0
  override val remainder: BigDecimal = 0
  override val allowance: BigDecimal = taxCode.matches("([0]{1}[L-N,l-n,T,t,X,x]{1}){1}") match {
    case true => 0
    case false => (BigDecimal.valueOf(taxCodeNumber).*(10)).+(9)
  }
}

class MonthlyAllowance(payeSeedData: PAYEAllowanceSeedData) extends Allowance {
  override val quotient  = BigDecimal.valueOf(payeSeedData.initQuotient.*(416.67))
  override val remainder = BigDecimal.valueOf(payeSeedData.middleRemainder)./(BigDecimal.valueOf(12)).setScale(2, RoundingMode.UP)
  override val allowance = quotient.+(remainder)
}

class WeeklyAllowance(payeSeedData: PAYEAllowanceSeedData) extends Allowance {
  override val quotient  = BigDecimal.valueOf(payeSeedData.initQuotient.*(96.16))
  override val remainder = (BigDecimal.valueOf(payeSeedData.middleRemainder)./(BigDecimal.valueOf(52))).setScale(2,RoundingMode.UP)
  override val allowance = quotient.+(remainder)
}

trait Allowance {
  def quotient: BigDecimal = ???
  def remainder: BigDecimal = ???
  def allowance: BigDecimal = ???
}

object AnnualAllowance {
  def apply(taxCode: String, taxCodeNumber: Int): AnnualAllowance = new AnnualAllowance(taxCode, taxCodeNumber)
}

object MonthlyAllowance {
  def apply(payeSeedData: PAYEAllowanceSeedData): MonthlyAllowance = new MonthlyAllowance(payeSeedData)
}

object WeeklyAllowance {
  def apply(payeSeedData: PAYEAllowanceSeedData): WeeklyAllowance = new WeeklyAllowance(payeSeedData)
}


