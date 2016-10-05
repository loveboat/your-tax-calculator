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
  val taxBands = Json.parse("""{
                              |  "taxYearBands": [
                              |    {
                              |      "fromDate": "2016-04-05",
                              |      "taxBands": [
                              |        {
                              |          "band": 1,
                              |          "bandwidth": 0.00,
                              |          "rate": 10,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 2,
                              |          "bandwidth": 32000.00,
                              |          "rate": 20,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 32000.00,
                              |              "cumulativeMaxTax": 6400.00,
                              |              "maxTax": 6400.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 2666.6666,
                              |              "cumulativeMaxTax": 533.3333,
                              |              "maxTax": 533.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 615.3846,
                              |              "cumulativeMaxTax": 123.0769,
                              |              "maxTax": 123.0769
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 3,
                              |          "bandwidth": 118000.00,
                              |          "rate": 40,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 150000.00,
                              |              "cumulativeMaxTax": 53600.00,
                              |              "maxTax": 47200.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 12500.00,
                              |              "cumulativeMaxTax": 4466.6666,
                              |              "maxTax": 3933.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 2884.6153,
                              |              "cumulativeMaxTax": 1030.7692,
                              |              "maxTax": 907.6923
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 4,
                              |          "bandwidth": -1,
                              |          "rate": 45,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            }
                              |          ]
                              |        }
                              |      ]
                              |    },
                              |    {
                              |      "fromDate": "2017-04-05",
                              |      "taxBands": [
                              |        {
                              |          "band": 1,
                              |          "bandwidth": 0.00,
                              |          "rate": 10,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0,
                              |              "maxTax": 0
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 2,
                              |          "bandwidth": 32000.00,
                              |          "rate": 20,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 32000.00,
                              |              "cumulativeMaxTax": 6400.00,
                              |              "maxTax": 6400.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 2666.6666,
                              |              "cumulativeMaxTax": 533.3333,
                              |              "maxTax": 533.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 615.3846,
                              |              "cumulativeMaxTax": 123.0769,
                              |              "maxTax": 123.0769
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 3,
                              |          "bandwidth": 118000.00,
                              |          "rate": 40,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 150000.00,
                              |              "cumulativeMaxTax": 53600.00,
                              |              "maxTax": 47200.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 12500.00,
                              |              "cumulativeMaxTax": 4466.6666,
                              |              "maxTax": 3933.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 2884.6153,
                              |              "cumulativeMaxTax": 1030.7692,
                              |              "maxTax": 907.6923
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 4,
                              |          "bandwidth": -1,
                              |          "rate": 45,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1,
                              |              "maxTax": -1
                              |            }
                              |          ]
                              |        }
                              |      ]
                              |    }
                              |  ]
                              |}""".stripMargin)


  val taxCalculator_2016_response = Json.parse(
    """{
      |  "statePensionAge": false,
      |  "taxCode": "1100L",
      |  "taxBreakdown": [
      |    {
      |      "period": "annual",
      |      "grossPay": 200000,
      |      "taxFreePay": 11009,
      |      "taxablePay": 188991,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 71145.95,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 6400
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 47200
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 17545.95
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 7332.8,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 4192.8
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 3140
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 26480.54,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 26480.54
      |            }
      |          ]
      |        }
      |      ],
      |      "totalDeductions": 78478.75,
      |      "takeHomePay": 121521.25
      |    },
      |    {
      |      "period": "monthly",
      |      "grossPay": 16666.67,
      |      "taxFreePay": 917.42,
      |      "taxablePay": 15749.25,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 5928.83,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 533.33
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 3933.33
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 1462.16
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 611.07,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 349.4
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 261.67
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 2206.71,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 2206.71
      |            }
      |          ]
      |        }
      |      ],
      |      "totalDeductions": 6539.9,
      |      "takeHomePay": 10126.77
      |    },
      |    {
      |      "period": "weekly",
      |      "grossPay": 3846.15,
      |      "taxFreePay": 211.71,
      |      "taxablePay": 3634.44,
      |      "taxCategories": [
      |        {
      |          "taxType": "incomeTax",
      |          "total": 1368.19,
      |          "aggregation": [
      |            {
      |              "percentage": 20,
      |              "amount": 123.08
      |            },
      |            {
      |              "percentage": 40,
      |              "amount": 907.69
      |            },
      |            {
      |              "percentage": 45,
      |              "amount": 337.42
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employeeNationalInsurance",
      |          "total": 141.01,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 80.63
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 60.38
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 509.24,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 509.24
      |            }
      |          ]
      |        }
      |      ],
      |      "totalDeductions": 1509.2,
      |      "takeHomePay": 2336.95
      |    }
      |  ]
      |}""".stripMargin)


  val NT_taxCode_response = Json.parse("""{
                                         |  "statePensionAge": false,
                                         |  "taxCode": "NT",
                                         |  "taxBreakdown": [
                                         |    {
                                         |      "period": "annual",
                                         |      "grossPay": 200000,
                                         |      "taxFreePay": 200000,
                                         |      "taxablePay": 0,
                                         |      "taxCategories": [
                                         |        {
                                         |          "taxType": "incomeTax",
                                         |          "total": 0,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 20,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 40,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 45,
                                         |              "amount": 0
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employeeNationalInsurance",
                                         |          "total": 7332.8,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 12,
                                         |              "amount": 4192.8
                                         |            },
                                         |            {
                                         |              "percentage": 2,
                                         |              "amount": 3140
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employerNationalInsurance",
                                         |          "total": 26480.54,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 13.8,
                                         |              "amount": 26480.54
                                         |            }
                                         |          ]
                                         |        }
                                         |      ],
                                         |      "totalDeductions": 7332.8,
                                         |      "takeHomePay": 192667.2
                                         |    },
                                         |    {
                                         |      "period": "monthly",
                                         |      "grossPay": 16666.67,
                                         |      "taxFreePay": 16666.67,
                                         |      "taxablePay": 0,
                                         |      "taxCategories": [
                                         |        {
                                         |          "taxType": "incomeTax",
                                         |          "total": 0,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 20,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 40,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 45,
                                         |              "amount": 0
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employeeNationalInsurance",
                                         |          "total": 611.07,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 12,
                                         |              "amount": 349.4
                                         |            },
                                         |            {
                                         |              "percentage": 2,
                                         |              "amount": 261.67
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employerNationalInsurance",
                                         |          "total": 2206.71,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 13.8,
                                         |              "amount": 2206.71
                                         |            }
                                         |          ]
                                         |        }
                                         |      ],
                                         |      "totalDeductions": 611.07,
                                         |      "takeHomePay": 16055.6
                                         |    },
                                         |    {
                                         |      "period": "weekly",
                                         |      "grossPay": 3846.15,
                                         |      "taxFreePay": 3846.15,
                                         |      "taxablePay": 0,
                                         |      "taxCategories": [
                                         |        {
                                         |          "taxType": "incomeTax",
                                         |          "total": 0,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 20,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 40,
                                         |              "amount": 0
                                         |            },
                                         |            {
                                         |              "percentage": 45,
                                         |              "amount": 0
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employeeNationalInsurance",
                                         |          "total": 141.01,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 12,
                                         |              "amount": 80.63
                                         |            },
                                         |            {
                                         |              "percentage": 2,
                                         |              "amount": 60.38
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employerNationalInsurance",
                                         |          "total": 509.24,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 13.8,
                                         |              "amount": 509.24
                                         |            }
                                         |          ]
                                         |        }
                                         |      ],
                                         |      "totalDeductions": 141.01,
                                         |      "takeHomePay": 3705.14
                                         |    }
                                         |  ]
                                         |}""".stripMargin)

 val BR_taxCode_response = Json.parse("""{
                                        |  "statePensionAge": false,
                                        |  "taxCode": "BR",
                                        |  "taxBreakdown": [
                                        |    {
                                        |      "period": "annual",
                                        |      "grossPay": 200000,
                                        |      "taxFreePay": 0,
                                        |      "taxablePay": 200000,
                                        |      "taxCategories": [
                                        |        {
                                        |          "taxType": "incomeTax",
                                        |          "total": 40000,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 20,
                                        |              "amount": 40000
                                        |            },
                                        |            {
                                        |              "percentage": 40,
                                        |              "amount": 0
                                        |            },
                                        |            {
                                        |              "percentage": 45,
                                        |              "amount": 0
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employeeNationalInsurance",
                                        |          "total": 7332.8,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 12,
                                        |              "amount": 4192.8
                                        |            },
                                        |            {
                                        |              "percentage": 2,
                                        |              "amount": 3140
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employerNationalInsurance",
                                        |          "total": 26480.54,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 13.8,
                                        |              "amount": 26480.54
                                        |            }
                                        |          ]
                                        |        }
                                        |      ],
                                        |      "totalDeductions": 47332.8,
                                        |      "takeHomePay": 152667.2
                                        |    },
                                        |    {
                                        |      "period": "monthly",
                                        |      "grossPay": 16666.67,
                                        |      "taxFreePay": 0,
                                        |      "taxablePay": 16666.67,
                                        |      "taxCategories": [
                                        |        {
                                        |          "taxType": "incomeTax",
                                        |          "total": 3333.33,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 20,
                                        |              "amount": 3333.33
                                        |            },
                                        |            {
                                        |              "percentage": 40,
                                        |              "amount": 0
                                        |            },
                                        |            {
                                        |              "percentage": 45,
                                        |              "amount": 0
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employeeNationalInsurance",
                                        |          "total": 611.07,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 12,
                                        |              "amount": 349.4
                                        |            },
                                        |            {
                                        |              "percentage": 2,
                                        |              "amount": 261.67
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employerNationalInsurance",
                                        |          "total": 2206.71,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 13.8,
                                        |              "amount": 2206.71
                                        |            }
                                        |          ]
                                        |        }
                                        |      ],
                                        |      "totalDeductions": 3944.4,
                                        |      "takeHomePay": 12722.27
                                        |    },
                                        |    {
                                        |      "period": "weekly",
                                        |      "grossPay": 3846.15,
                                        |      "taxFreePay": 0,
                                        |      "taxablePay": 3846.15,
                                        |      "taxCategories": [
                                        |        {
                                        |          "taxType": "incomeTax",
                                        |          "total": 769.23,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 20,
                                        |              "amount": 769.23
                                        |            },
                                        |            {
                                        |              "percentage": 40,
                                        |              "amount": 0
                                        |            },
                                        |            {
                                        |              "percentage": 45,
                                        |              "amount": 0
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employeeNationalInsurance",
                                        |          "total": 141.01,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 12,
                                        |              "amount": 80.63
                                        |            },
                                        |            {
                                        |              "percentage": 2,
                                        |              "amount": 60.38
                                        |            }
                                        |          ]
                                        |        },
                                        |        {
                                        |          "taxType": "employerNationalInsurance",
                                        |          "total": 509.24,
                                        |          "aggregation": [
                                        |            {
                                        |              "percentage": 13.8,
                                        |              "amount": 509.24
                                        |            }
                                        |          ]
                                        |        }
                                        |      ],
                                        |      "totalDeductions": 910.24,
                                        |      "takeHomePay": 2935.91
                                        |    }
                                        |  ]
                                        |}""".stripMargin)

val D0_taxCode_response = Json.parse("""{
                                       |  "statePensionAge": false,
                                       |  "taxCode": "D0",
                                       |  "taxBreakdown": [
                                       |    {
                                       |      "period": "annual",
                                       |      "grossPay": 200000,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 200000,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 80000,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 80000
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 0
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 7332.8,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 4192.8
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 3140
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 26480.54,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 26480.54
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 87332.8,
                                       |      "takeHomePay": 112667.2
                                       |    },
                                       |    {
                                       |      "period": "monthly",
                                       |      "grossPay": 16666.67,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 16666.67,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 6666.67,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 6666.67
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 0
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 611.07,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 349.4
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 261.67
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 2206.71,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 2206.71
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 7277.74,
                                       |      "takeHomePay": 9388.93
                                       |    },
                                       |    {
                                       |      "period": "weekly",
                                       |      "grossPay": 3846.15,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 3846.15,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 1538.46,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 1538.46
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 0
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 141.01,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 80.63
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 60.38
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 509.24,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 509.24
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 1679.47,
                                       |      "takeHomePay": 2166.68
                                       |    }
                                       |  ]
                                       |}""".stripMargin)
val D1_taxCode_response = Json.parse("""{
                                       |  "statePensionAge": false,
                                       |  "taxCode": "D1",
                                       |  "taxBreakdown": [
                                       |    {
                                       |      "period": "annual",
                                       |      "grossPay": 200000,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 200000,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 90000,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 90000
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 7332.8,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 4192.8
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 3140
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 26480.54,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 26480.54
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 97332.8,
                                       |      "takeHomePay": 102667.2
                                       |    },
                                       |    {
                                       |      "period": "monthly",
                                       |      "grossPay": 16666.67,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 16666.67,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 7500,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 7500
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 611.07,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 349.4
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 261.67
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 2206.71,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 2206.71
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 8111.07,
                                       |      "takeHomePay": 8555.6
                                       |    },
                                       |    {
                                       |      "period": "weekly",
                                       |      "grossPay": 3846.15,
                                       |      "taxFreePay": 0,
                                       |      "taxablePay": 3846.15,
                                       |      "taxCategories": [
                                       |        {
                                       |          "taxType": "incomeTax",
                                       |          "total": 1730.77,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 20,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 40,
                                       |              "amount": 0
                                       |            },
                                       |            {
                                       |              "percentage": 45,
                                       |              "amount": 1730.77
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employeeNationalInsurance",
                                       |          "total": 141.01,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 12,
                                       |              "amount": 80.63
                                       |            },
                                       |            {
                                       |              "percentage": 2,
                                       |              "amount": 60.38
                                       |            }
                                       |          ]
                                       |        },
                                       |        {
                                       |          "taxType": "employerNationalInsurance",
                                       |          "total": 509.24,
                                       |          "aggregation": [
                                       |            {
                                       |              "percentage": 13.8,
                                       |              "amount": 509.24
                                       |            }
                                       |          ]
                                       |        }
                                       |      ],
                                       |      "totalDeductions": 1871.78,
                                       |      "takeHomePay": 1974.37
                                       |    }
                                       |  ]
                                       |}""".stripMargin)

  val nic_rates_limits = Json.parse("""{
                                      |  "rateLimits": [
                                      |    {
                                      |      "fromDate": "2016-04-05",
                                      |      "earningLimit": [
                                      |        {
                                      |          "rateLimitType": "lower",
                                      |          "weekly": 112,
                                      |          "monthly": 486,
                                      |          "annual": 5824
                                      |        },
                                      |        {
                                      |          "rateLimitType": "upper",
                                      |          "weekly": 827,
                                      |          "monthly": 3583,
                                      |          "annual": 43000
                                      |        }
                                      |      ],
                                      |      "threshold": [
                                      |        {
                                      |          "rateLimitType": "primary",
                                      |          "weekly": 155,
                                      |          "monthly": 672,
                                      |          "annual": 8060
                                      |        },
                                      |        {
                                      |          "rateLimitType": "secondary",
                                      |          "weekly": 156,
                                      |          "monthly": 676,
                                      |          "annual": 8112
                                      |        }
                                      |      ],
                                      |      "employeeRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 12,
                                      |          "monthly": 12,
                                      |          "annual": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 12,
                                      |          "monthly": 12,
                                      |          "annual": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 2,
                                      |          "monthly": 2,
                                      |          "annual": 2
                                      |        }
                                      |      ],
                                      |      "employerRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8,
                                      |          "annual": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8,
                                      |          "annual": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        }
                                      |      ]
                                      |    },
                                      |    {
                                      |      "fromDate": "2017-04-05",
                                      |      "earningLimit": [
                                      |        {
                                      |          "rateLimitType": "lower",
                                      |          "weekly": 112,
                                      |          "monthly": 486,
                                      |          "annual": 5824
                                      |        },
                                      |        {
                                      |          "rateLimitType": "upper",
                                      |          "weekly": 827,
                                      |          "monthly": 3583,
                                      |          "annual": 43000
                                      |        }
                                      |      ],
                                      |      "threshold": [
                                      |        {
                                      |          "rateLimitType": "primary",
                                      |          "weekly": 155,
                                      |          "monthly": 672,
                                      |          "annual": 8060
                                      |        },
                                      |        {
                                      |          "rateLimitType": "secondary",
                                      |          "weekly": 156,
                                      |          "monthly": 676,
                                      |          "annual": 8112
                                      |        }
                                      |      ],
                                      |      "employeeRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 12,
                                      |          "monthly": 12,
                                      |          "annual": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 12,
                                      |          "monthly": 12,
                                      |          "annual": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 2,
                                      |          "monthly": 2,
                                      |          "annual": 2
                                      |        }
                                      |      ],
                                      |      "employerRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8,
                                      |          "annual": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8,
                                      |          "annual": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 0,
                                      |          "monthly": 0,
                                      |          "annual": 0
                                      |        }
                                      |      ]
                                      |    }
                                      |  ]
                                      |}""".stripMargin)

}
