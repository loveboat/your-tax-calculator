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
                              |          "annualBandMaxTax": 0,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 2,
                              |          "bandwidth": 32000.00,
                              |          "rate": 20,
                              |          "annualBandMaxTax": 6400,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 32000.00,
                              |              "cumulativeMaxTax": 6400.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 2666.6666,
                              |              "cumulativeMaxTax": 533.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 615.3846,
                              |              "cumulativeMaxTax": 123.0769
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 3,
                              |          "bandwidth": 118000.00,
                              |          "rate": 40,
                              |          "annualBandMaxTax": 47200.00,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 150000.00,
                              |              "cumulativeMaxTax": 53600.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 12500.00,
                              |              "cumulativeMaxTax": 4466.6666
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 2884.6153,
                              |              "cumulativeMaxTax": 1030.7692
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 4,
                              |          "bandwidth": -1,
                              |          "rate": 45,
                              |          "annualBandMaxTax": -1,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
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
                              |          "annualBandMaxTax": 0,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 0,
                              |              "cumulativeMaxTax": 0
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 2,
                              |          "bandwidth": 32000.00,
                              |          "rate": 20,
                              |          "annualBandMaxTax": 6400,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 32000.00,
                              |              "cumulativeMaxTax": 6400.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 2666.6666,
                              |              "cumulativeMaxTax": 533.3333
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 615.3846,
                              |              "cumulativeMaxTax": 123.0769
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 3,
                              |          "bandwidth": 118000.00,
                              |          "rate": 40,
                              |          "annualBandMaxTax": 47200.00,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": 150000.00,
                              |              "cumulativeMaxTax": 53600.00
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": 12500.00,
                              |              "cumulativeMaxTax": 4466.6666
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": 2884.6153,
                              |              "cumulativeMaxTax": 1030.7692
                              |            }
                              |          ]
                              |        },
                              |        {
                              |          "band": 4,
                              |          "bandwidth": -1,
                              |          "rate": 45,
                              |          "annualBandMaxTax": -1,
                              |          "periods": [
                              |            {
                              |              "periodType": "annual",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
                              |            },
                              |            {
                              |              "periodType": "monthly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
                              |            },
                              |            {
                              |              "periodType": "weekly",
                              |              "threshold": -1,
                              |              "cumulativeMaxTax": -1
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
      |      "totalDeductions": 381.31,
      |      "takeHomePay": 868.69
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
      |      "totalDeductions": 1652.36,
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
      |      "totalDeductions": 19828.28,
      |      "takeHomePay":45171.72
      |    }
      |  ]
      |}""".stripMargin)


  val taxCalulator_PAYE_response = Json.parse("""{
                                     |  "statePensionAge": true,
                                     |  "taxCode": "1100L",
                                     |  "taxBreakdown": [
                                     |    {
                                     |      "period": "annual",
                                     |      "grossPay": 200000.0,
                                     |      "taxFreePay": 11009.00,
                                     |      "taxablePay": 188991.00,
                                     |      "taxCategories": [
                                     |        {
                                     |          "taxType": "incomeTax",
                                     |          "total": 60000.00,
                                     |          "aggregation": [
                                     |            {
                                     |              "percentage": 20,
                                     |              "amount": 6400.00
                                     |            },
                                     |            {
                                     |              "percentage": 40,
                                     |              "amount": 53600.00
                                     |            },
                                     |            {
                                     |              "percentage": 45,
                                     |              "amount": 0
                                     |            }
                                     |          ]
                                     |        }
                                     |      ],
                                     |      "totalDeductions": 60000.00,
                                     |      "takeHomePay": 140000.00
                                     |    },
                                     |    {
                                     |      "period": "monthly",
                                     |      "grossPay": 16666.67,
                                     |      "taxFreePay": 917.42,
                                     |      "taxablePay": 15749.25,
                                     |      "taxCategories": [
                                     |        {
                                     |          "taxType": "incomeTax",
                                     |          "total": 5000.00,
                                     |          "aggregation": [
                                     |            {
                                     |              "percentage": 20,
                                     |              "amount": 533.3333
                                     |            },
                                     |            {
                                     |              "percentage": 40,
                                     |              "amount": 4466.6666
                                     |            },
                                     |            {
                                     |              "percentage": 45,
                                     |              "amount": 0
                                     |            }
                                     |          ]
                                     |        }
                                     |      ],
                                     |      "totalDeductions": 5000.00,
                                     |      "takeHomePay": 11666.67
                                     |    },
                                     |    {
                                     |      "period": "weekly",
                                     |      "grossPay": 3846.15,
                                     |      "taxFreePay": 211.71,
                                     |      "taxablePay": 3634.44,
                                     |      "taxCategories": [
                                     |        {
                                     |          "taxType": "incomeTax",
                                     |          "total": 1153.85,
                                     |          "aggregation": [
                                     |            {
                                     |              "percentage": 20,
                                     |              "amount": 123.0769
                                     |            },
                                     |            {
                                     |              "percentage": 40,
                                     |              "amount": 1030.7692
                                     |            },
                                     |            {
                                     |              "percentage": 45,
                                     |              "amount": 0
                                     |            }
                                     |          ]
                                     |        }
                                     |      ],
                                     |      "totalDeductions": 1153.85,
                                     |      "takeHomePay": 2692.30
                                     |    }
                                     |  ]
                                     |}""".stripMargin)

  val nic_rates_limits = Json.parse("""{ "rateLimits": [
                                      |    {
                                      |      "fromDate": "2016-04-05",
                                      |      "earningLimit": [
                                      |        {
                                      |          "rateLimitType": "lower",
                                      |          "weekly": 112.00,
                                      |          "monthly": 486.00
                                      |        },
                                      |        {
                                      |          "rateLimitType": "upper",
                                      |          "weekly": 827.00,
                                      |          "monthly": 3583.00
                                      |        }
                                      |      ],
                                      |      "threshold": [
                                      |        {
                                      |          "rateLimitType": "primary",
                                      |          "weekly": 155.00,
                                      |          "monthly": 672.00
                                      |        },
                                      |        {
                                      |          "rateLimitType": "secondary",
                                      |          "weekly": 156.00,
                                      |          "monthly": 676.00
                                      |        }
                                      |      ],
                                      |      "employeeRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 12,
                                      |          "monthly": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 12,
                                      |          "monthly": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 2,
                                      |          "monthly": 2
                                      |        }
                                      |      ],
                                      |      "employerRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        }
                                      |      ]
                                      |    },
                                      |    {
                                      |      "fromDate": "2017-04-05",
                                      |      "earningLimit": [
                                      |        {
                                      |          "rateLimitType": "lower",
                                      |          "weekly": 112.00,
                                      |          "monthly": 486.00
                                      |        },
                                      |        {
                                      |          "rateLimitType": "upper",
                                      |          "weekly": 827.00,
                                      |          "monthly": 3583.00
                                      |        }
                                      |      ],
                                      |      "threshold": [
                                      |        {
                                      |          "rateLimitType": "primary",
                                      |          "weekly": 155.00,
                                      |          "monthly": 627.00
                                      |        },
                                      |        {
                                      |          "rateLimitType": "secondary",
                                      |          "weekly": 156.00,
                                      |          "monthly": 676.00
                                      |        }
                                      |      ],
                                      |      "employeeRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 12,
                                      |          "monthly": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 12,
                                      |          "monthly": 12
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 2,
                                      |          "monthly": 2
                                      |        }
                                      |      ],
                                      |      "employerRate": [
                                      |        {
                                      |          "rateLimitType": "1",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        },
                                      |        {
                                      |          "rateLimitType": "2",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "3",
                                      |          "weekly": 13.8,
                                      |          "monthly": 13.8
                                      |        },
                                      |        {
                                      |          "rateLimitType": "4",
                                      |          "weekly": 0,
                                      |          "monthly": 0
                                      |        }
                                      |      ]
                                      |    }
                                      |  ]
                                      |}""".stripMargin)

  val tax_calculator_paye_nic_response = Json.parse("""{
                                                      |  "statePensionAge": true,
                                                      |  "taxCode": "1100L",
                                                      |  "taxBreakdown": [
                                                      |    {
                                                      |      "period": "annual",
                                                      |      "grossPay": 200000.0,
                                                      |      "taxFreePay": 11009.00,
                                                      |      "taxablePay": 188991.00,
                                                      |      "taxCategories": [
                                                      |        {
                                                      |          "taxType": "incomeTax",
                                                      |          "total": 60000.00,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 20,
                                                      |              "amount": 6400.00
                                                      |            },
                                                      |            {
                                                      |              "percentage": 40,
                                                      |              "amount": 53600.00
                                                      |            },
                                                      |            {
                                                      |              "percentage": 45,
                                                      |              "amount": 0
                                                      |            }
                                                      |          ]
                                                      |        },
                                                      |        {
                                                      |          "taxType": "employeeNationalInsurance",
                                                      |          "total": 7331.88,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 12,
                                                      |              "amount": 4191.84
                                                      |            },
                                                      |            {
                                                      |              "percentage": 2,
                                                      |              "amount": 3140.04
                                                      |            }
                                                      |          ]
                                                      |        }
                                                      |      ],
                                                      |      "totalDeductions": 67331.88,
                                                      |      "takeHomePay": 132668.12
                                                      |    },
                                                      |    {
                                                      |      "period": "monthly",
                                                      |      "grossPay": 16666.67,
                                                      |      "taxFreePay": 917.42,
                                                      |      "taxablePay": 15749.25,
                                                      |      "taxCategories": [
                                                      |        {
                                                      |          "taxType": "incomeTax",
                                                      |          "total": 5000.00,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 20,
                                                      |              "amount": 533.3333
                                                      |            },
                                                      |            {
                                                      |              "percentage": 40,
                                                      |              "amount": 4466.6666
                                                      |            },
                                                      |            {
                                                      |              "percentage": 45,
                                                      |              "amount": 0
                                                      |            }
                                                      |          ]
                                                      |        },
                                                      |        {
                                                      |          "taxType": "employeeNationalInsurance",
                                                      |          "total": 610.99,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 12,
                                                      |              "amount": 349.32
                                                      |            },
                                                      |            {
                                                      |              "percentage": 2,
                                                      |              "amount": 261.67
                                                      |            }
                                                      |          ]
                                                      |        }
                                                      |      ],
                                                      |      "totalDeductions": 5610.99,
                                                      |      "takeHomePay": 11055.68
                                                      |    },
                                                      |    {
                                                      |      "period": "weekly",
                                                      |      "grossPay": 3846.15,
                                                      |      "taxFreePay": 211.71,
                                                      |      "taxablePay": 3634.44,
                                                      |      "taxCategories": [
                                                      |        {
                                                      |          "taxType": "incomeTax",
                                                      |          "total": 1153.85,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 20,
                                                      |              "amount": 123.0769
                                                      |            },
                                                      |            {
                                                      |              "percentage": 40,
                                                      |              "amount": 1030.7692
                                                      |            },
                                                      |            {
                                                      |              "percentage": 45,
                                                      |              "amount": 0
                                                      |            }
                                                      |          ]
                                                      |        },
                                                      |        {
                                                      |          "taxType": "employeeNationalInsurance",
                                                      |          "total": 141.00,
                                                      |          "aggregation": [
                                                      |            {
                                                      |              "percentage": 12,
                                                      |              "amount": 80.61
                                                      |            },
                                                      |            {
                                                      |              "percentage": 2,
                                                      |              "amount": 60.39
                                                      |            }
                                                      |          ]
                                                      |        }
                                                      |      ],
                                                      |      "totalDeductions": 1294.85,
                                                      |      "takeHomePay": 2551.30
                                                      |    }
                                                      |  ]
                                                      |}""".stripMargin)
}
