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

import uk.gov.hmrc.taxcalc.domain._

import scala.math.BigDecimal

trait Builder {

  def build(): BuildResult

  protected def calculateAggregationTotal(aggregation: Seq[Aggregation]) : BigDecimal = {
    aggregation match {
      case aggregate: Seq[Aggregation] => aggregate.foldLeft(BigDecimal.valueOf(0.0))(_ + _.amount)
      case _ => BigDecimal.valueOf(0.0)
    }
  }
}

case class PAYEAggregateBuilder(date: LocalDate, bandId: Int, payPeriod: String, payeTaxAmount: Money) extends Builder with TaxCalculatorHelper {

  val taxbands = getTaxBands(date)

  override def build(): AggregationBuildResult = {
    appendAggregate(AggregationBuildResult(taxbands.taxBands.collect(PAYEAggregationFunc(bandId, payPeriod, payeTaxAmount))))
  }

  private def PAYEAggregationFunc(band: Int, payPeriod: String, payeTaxAmount: Money) : PartialFunction[TaxBand, Aggregation] = {
    case taxBand if payeTaxAmount == Money(0) && taxBand.band != 1 => Aggregation(taxBand.rate, BigDecimal.valueOf(0.0))
    case taxBand if payeTaxAmount != Money(0) && taxBand.band <= band && taxBand.band != 1 && taxBand.band != 4 => createPAYEAggregation(taxBand, payPeriod, band, payeTaxAmount)
  }

  private def createPAYEAggregation(taxBand: TaxBand, payPeriod: String, band: Int, payeTaxAmount: Money): Aggregation = {
    val periodCalc = taxBand.periods.filter(_.periodType.equals(payPeriod)).head
    val amount = if(payeTaxAmount > Money(0)) Money(periodCalc.maxTax, 2, true) else Money(0)
    Aggregation(taxBand.rate, amount.value)
  }

  private def appendAggregate(result: AggregationBuildResult): AggregationBuildResult = {
    if(payeTaxAmount != Money(0)){
      val total = calculateAggregationTotal(result.aggregation)
      val append = Seq(Aggregation(taxbands.taxBands.last.rate, (payeTaxAmount - total).value))
      AggregationBuildResult(result.aggregation++append)
    }
    else
      result
  }
}

case class NICTaxCategoryBuilder(isStatePensionAge: Boolean, taxResult: NICTaxResult) extends Builder {

  override def build(): TaxCategoryBuildResult = {
    isStatePensionAge match {
      case false => {
        TaxCategoryBuildResult(Seq(TaxCategory(taxType = "employeeNationalInsurance", calculateAggregationTotal(taxResult.employeeNIC), taxResult.employeeNIC),
          TaxCategory(taxType = "employerNationalInsurance", calculateAggregationTotal(taxResult.employerNIC), taxResult.employerNIC)))
      }
      case true => TaxCategoryBuildResult(Seq())
    }
  }
}

case class TaxCategoryBuilder() extends Builder {
  override def build(): BuildResult = ???
}


trait BuildResult{}

case class AggregationBuildResult(aggregation: Seq[Aggregation]) extends BuildResult

case class TaxCategoryBuildResult(taxCategories: Seq[TaxCategory]) extends BuildResult
