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

import play.api.libs.json._

object TaxCalculatorTestData {

  val taxCalculator_2016_response = Json.parse(
    """{
      |  "statePensionAge": false,
      |  "taxCode": "1100L",
      |  "taxBreakdown": [
      |    {
      |      "period": "week",
      |      "grossPay": 1250.00,
      |      "taxFreePay": 211.73,
      |      "taxablePay": 1038.27,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 292.24,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 123.08
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 169.16
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 0.00
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 89.07,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 80.61
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 8.46
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 150.98,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 150.98
      |            }
      |          ]
      |        }
      |      ],
      |      "taxableDeductions": 381.31,
      |      "takeHomePay": 368.69
      |    },
      |    {
      |      "period": "month",
      |      "grossPay": 5416.67,
      |      "taxFreePay": 917.43,
      |      "taxablePay": 4499.24,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 1266.37,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 533.33
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 733.03
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 0.00
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 385.99,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 349.32
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 36.67
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 654.22,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 654.22
      |            }
      |         ]
      |       }
      |      ],
      |      "taxableDeductions": 1652.36,
      |      "takeHomePay": 3764.31
      |    },
      |    {
      |      "period": "year",
      |      "grossPay": 65000.00,
      |      "taxFreePay": 11009.00,
      |      "taxablePay": 53991.00,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 15196.40,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 6400.00
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 8796.40
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 0.00
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 4631.88,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 4191.84
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 440.04
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 7850.64,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 7850.64
      |            }
      |          ]
      |        }
      |      ],
      |      "taxableDeductions": 19828.28,
      |      "takeHomePay":45171.72
      |    }
      |  ]
      |}""".stripMargin)


}
