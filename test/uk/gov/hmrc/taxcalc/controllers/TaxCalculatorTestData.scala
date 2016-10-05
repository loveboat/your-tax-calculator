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
      |          "total": 87993.6,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 50313.6
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 37680
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 317766.48,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 317766.48
      |            }
      |          ]
      |        }
      |      ],
      |      "totalDeductions": 159139.55,
      |      "takeHomePay": 40860.45
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
      |      "totalDeductions": 13261.63,
      |      "takeHomePay": 3405.04
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
      |          "total": 1692.19,
      |          "aggregation": [
      |            {
      |              "percentage": 12,
      |              "amount": 967.57
      |            },
      |            {
      |              "percentage": 2,
      |              "amount": 724.62
      |            }
      |          ]
      |        },
      |        {
      |          "taxType": "employerNationalInsurance",
      |          "total": 6110.89,
      |          "aggregation": [
      |            {
      |              "percentage": 13.8,
      |              "amount": 6110.89
      |            }
      |          ]
      |        }
      |      ],
      |      "totalDeductions": 3060.38,
      |      "takeHomePay": 785.77
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
                                         |          "total": 87993.6,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 12,
                                         |              "amount": 50313.6
                                         |            },
                                         |            {
                                         |              "percentage": 2,
                                         |              "amount": 37680
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employerNationalInsurance",
                                         |          "total": 317766.48,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 13.8,
                                         |              "amount": 317766.48
                                         |            }
                                         |          ]
                                         |        }
                                         |      ],
                                         |      "totalDeductions": 87993.6,
                                         |      "takeHomePay": 112006.4
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
                                         |      "takeHomePay": 9333.87
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
                                         |          "total": 1692.19,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 12,
                                         |              "amount": 967.57
                                         |            },
                                         |            {
                                         |              "percentage": 2,
                                         |              "amount": 724.62
                                         |            }
                                         |          ]
                                         |        },
                                         |        {
                                         |          "taxType": "employerNationalInsurance",
                                         |          "total": 6110.89,
                                         |          "aggregation": [
                                         |            {
                                         |              "percentage": 13.8,
                                         |              "amount": 6110.89
                                         |            }
                                         |          ]
                                         |        }
                                         |      ],
                                         |      "totalDeductions": 1692.19,
                                         |      "takeHomePay": 2153.96
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
