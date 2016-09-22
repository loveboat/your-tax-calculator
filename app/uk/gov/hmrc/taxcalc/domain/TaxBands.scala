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

import java.time.LocalDate

import play.api.libs.json.{Format, JsResult, JsValue, Json}

case class TaxYearBands(taxYearBands: Seq[TaxBands])

case class TaxBands(fromDate: LocalDate, taxBands: Seq[TaxBand])

case class TaxBand(band: Int, bandwidth: BigDecimal, rate: BigDecimal, annualBandMaxTax: BigDecimal, periods: Seq[PeriodCalc])

case class PeriodCalc(periodType: String, threshold: BigDecimal, cumulativeMaxTax: BigDecimal)

object TaxYearBands {
  implicit val formatPeriodCalc = Json.format[PeriodCalc]
  implicit val formatTaxBand = Json.format[TaxBand]
  implicit val localDateFormat = new Format[LocalDate] {
    override def reads(json: JsValue): JsResult[LocalDate] =
      json.validate[String].map(LocalDate.parse)

    override def writes(o: LocalDate): JsValue = Json.toJson(o.toString)
  }
  implicit val formatTaxBands = Json.format[TaxBands]
  implicit val format = Json.format[TaxYearBands]
}

object TaxBands {


  implicit val localDateFormat = new Format[LocalDate] {
    override def reads(json: JsValue): JsResult[LocalDate] =
      json.validate[String].map(LocalDate.parse)

    override def writes(o: LocalDate): JsValue = Json.toJson(o.toString)
  }
  implicit val formatPeriodCalc = Json.format[PeriodCalc]
  implicit val formatTaxBand = Json.format[TaxBand]

  implicit val format= Json.format[TaxBands]
}

object TaxBand {
  implicit val formatPeriodCalc = Json.format[PeriodCalc]
  implicit val format= Json.format[TaxBand]
}

object PeriodCalc {
  implicit val format= Json.format[PeriodCalc]
}

object LocalDateOrdered extends Ordering[LocalDate] {
  override def compare(x: LocalDate, y: LocalDate): Int = {
    x.compareTo(y)
  }
}