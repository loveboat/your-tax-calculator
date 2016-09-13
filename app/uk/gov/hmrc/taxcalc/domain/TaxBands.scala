package uk.gov.hmrc.taxcalc.domain

import java.util.Calendar

import play.api.libs.json.Json

case class TaxBands(taxBands: Seq[TaxBand])

case class TaxBand(band: Short, fromDate: Calendar, toDate: Calendar, bandWidth: BigDecimal, rate: BigDecimal, cumulativeBandwidth: BigDecimal, maxTax: BigDecimal, cumulativeTax: BigDecimal, weeklyThreshold: BigDecimal, weeklyCumulativeMaxTax: BigDecimal, monthlyThreshold: BigDecimal, monthlyCumulativeMaxTax: BigDecimal)

object TaxBands {
  implicit val formatTaxBand = Json.format[TaxBand]
  implicit val format= Json.format[TaxBands]
}

object TaxBand {
  implicit val format= Json.format[TaxBand]
}