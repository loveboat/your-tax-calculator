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

object TaxExpenditureTestData {

  val pesa_2016_categories = Json.parse("""["General public services","Defense","Public order and safety","Economic affairs","Environment protection","Housing and community amenities","Health","Recreation, culture and religion","Education","Social protection","EU transactions"]""".stripMargin)

  val pesa_2016_response = Json.parse(
    """{
      |  "categoryBreakdown": [
      |    {
      |      "category": "General public services",
      |      "allocation": 58708000.00,
      |      "subCategory": [
      |        {
      |          "category": "Executive and legislative organs, financial and fiscal affairs, external affairs",
      |          "allocation": 10101000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Foreign economic aid",
      |          "allocation": 8173000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "General services",
      |          "allocation": 758000,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Basic research",
      |          "allocation": 0.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D general public services",
      |          "allocation": 417000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "General public services n.e.c.",
      |          "allocation": 2535000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Public debt transactions",
      |          "allocation": 36724000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Defense",
      |      "allocation": 36646000.00,
      |      "subCategory": [
      |        {
      |          "category": "Military defense",
      |          "allocation": 33996000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Civil defence",
      |          "allocation": 115000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Foreign military aid",
      |          "allocation": 329000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D defence",
      |          "allocation": 1922000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Defence n.e.c.",
      |          "allocation": 284000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Public order and safety",
      |      "allocation": 30183000.00,
      |      "subCategory": [
      |        {
      |          "category": "Police services",
      |          "allocation": 16645000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Fire-protection services",
      |          "allocation": 2821000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Law courts",
      |          "allocation": 5691000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Prisons",
      |          "allocation": 4092000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D public order and safety",
      |          "allocation": 62000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Public order and safety n.e.c.",
      |          "allocation": 872000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Economic affairs",
      |      "allocation": 45132000.00,
      |      "subCategory": [
      |        {
      |          "category": "General economic, commercial and labour affairs",
      |          "allocation": 6030000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Agriculture, forestry, fishing and hunting",
      |          "allocation": 4541000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Fuel and energy",
      |          "allocation": 463000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Mining, manufacturing and construction",
      |          "allocation": 72000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Transport",
      |          "allocation": 28061000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Communication",
      |          "allocation": 447000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Other industries",
      |          "allocation": 273000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D economic affairs",
      |          "allocation": 4787000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Economic affairs n.e.c.",
      |          "allocation": 458000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Environment protection",
      |      "allocation": 11608000.00,
      |      "subCategory": [
      |        {
      |          "category": "Waste management",
      |          "allocation": 8089000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Waste water management",
      |          "allocation": 0.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Pollution abatement",
      |          "allocation": 469000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Protection of biodiversity and landscape",
      |          "allocation": 638000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D environment protection",
      |          "allocation": 460000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Environment protection n.e.c.",
      |          "allocation": 1952000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Housing and community amenities",
      |      "allocation": 10013000.00,
      |      "subCategory": [
      |        {
      |          "category": "Housing development",
      |          "allocation": 5572000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Community development",
      |          "allocation": 2423000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Water supply",
      |          "allocation": 814000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Street lighting",
      |          "allocation": 839000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D housing and community amenities",
      |          "allocation": 0.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Housing and community amenities n.e.c.",
      |          "allocation": 366000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Health",
      |      "allocation": 138704000.00,
      |      "subCategory": [
      |        {
      |          "category": "Medical services",
      |          "allocation": 132103000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Medical research",
      |          "allocation": 2142000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Central and other health services",
      |          "allocation": 4460000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Recreation, culture and religion",
      |      "allocation": 11429000.00,
      |      "subCategory": [
      |        {
      |          "category": "Recreational and sporting services",
      |          "allocation": 3194000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Cultural services",
      |          "allocation": 4003000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Broadcasting and publishing services",
      |          "allocation": 3945000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Religious and other community services",
      |          "allocation": 93000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D recreation, culture and religion",
      |          "allocation": 130000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Recreation, culture and religion n.e.c.",
      |          "allocation": 63000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Education",
      |      "allocation": 84027000.00,
      |      "subCategory": [
      |        {
      |          "category": "Pre-primary and primary education",
      |          "allocation": 30942000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Secondary education",
      |          "allocation": 38193000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Post-secondary non-tertiary education",
      |          "allocation": 91000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Tertiary education",
      |          "allocation": 5896000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Education not definable by level",
      |          "allocation": 636000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Subsidiary services to education",
      |          "allocation": 4092000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D education",
      |          "allocation": 1577000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Education n.e.c.",
      |          "allocation": 2600000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "Social protection",
      |      "allocation": 264151000.00,
      |      "subCategory": [
      |        {
      |          "category": "Sickness and disability",
      |          "allocation": 52956000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Old age",
      |          "allocation": 120095000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Survivors",
      |          "allocation": 1164000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Family and children",
      |          "allocation": 24795000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Unemployment",
      |          "allocation": 2698000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Housing",
      |          "allocation": 26374000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Social exclusion n.e.c. (8)",
      |          "allocation": 31485000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "R&D social protection",
      |          "allocation": 0.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Social protection n.e.c.",
      |          "allocation": 4583000.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    },
      |    {
      |      "category": "EU transactions",
      |      "allocation": 7671000.00,
      |      "subCategory": [
      |        {
      |          "category": "VAT-based and GNI-based contributions (net of abatement and collection costs",
      |          "allocation": 10482000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "EU receipts",
      |          "allocation": -2811000.00,
      |          "subCategory": [
      |
      |          ]
      |        },
      |        {
      |          "category": "Attributed aid and Common Foreign and Security Policy",
      |          "allocation": 0.00,
      |          "subCategory": [
      |
      |          ]
      |        }
      |      ]
      |    }
      |  ],
      |  "expenditureOnServices": 698271000.00,
      |  "accountingAdjustments": 54751000.00,
      |  "totalExpenditure" : 753022000.00
      |}
    """.stripMargin)

  val pesa_2016_category_percentage_response = Json.parse("""[
                                                            |  {
                                                            |    "category": "General public services",
                                                            |    "allocation": 58708000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Executive and legislative organs, financial and fiscal affairs, external affairs",
                                                            |        "allocation": 10101000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 1.45
                                                            |      },
                                                            |      {
                                                            |        "category": "Foreign economic aid",
                                                            |        "allocation": 8173000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 1.17
                                                            |      },
                                                            |      {
                                                            |        "category": "General services",
                                                            |        "allocation": 758000,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.11
                                                            |      },
                                                            |      {
                                                            |        "category": "Basic research",
                                                            |        "allocation": 0.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.00
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D general public services",
                                                            |        "allocation": 417000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.06
                                                            |      },
                                                            |      {
                                                            |        "category": "General public services n.e.c.",
                                                            |        "allocation": 2535000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.36
                                                            |      },
                                                            |      {
                                                            |        "category": "Public debt transactions",
                                                            |        "allocation": 36724000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 5.26
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 8.41
                                                            |  },
                                                            |  {
                                                            |    "category": "Defense",
                                                            |    "allocation": 36646000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Military defense",
                                                            |        "allocation": 33996000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 4.87
                                                            |      },
                                                            |      {
                                                            |        "category": "Civil defence",
                                                            |        "allocation": 115000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.02
                                                            |      },
                                                            |      {
                                                            |        "category": "Foreign military aid",
                                                            |        "allocation": 329000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.05
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D defence",
                                                            |        "allocation": 1922000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.28
                                                            |      },
                                                            |      {
                                                            |        "category": "Defence n.e.c.",
                                                            |        "allocation": 284000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.04
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 5.25
                                                            |  },
                                                            |  {
                                                            |    "category": "Public order and safety",
                                                            |    "allocation": 30183000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Police services",
                                                            |        "allocation": 16645000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 2.38
                                                            |      },
                                                            |      {
                                                            |        "category": "Fire-protection services",
                                                            |        "allocation": 2821000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.40
                                                            |      },
                                                            |      {
                                                            |        "category": "Law courts",
                                                            |        "allocation": 5691000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.82
                                                            |      },
                                                            |      {
                                                            |        "category": "Prisons",
                                                            |        "allocation": 4092000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.59
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D public order and safety",
                                                            |        "allocation": 62000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.01
                                                            |      },
                                                            |      {
                                                            |        "category": "Public order and safety n.e.c.",
                                                            |        "allocation": 872000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.12
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 4.32
                                                            |  },
                                                            |  {
                                                            |    "category": "Economic affairs",
                                                            |    "allocation": 45132000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "General economic, commercial and labour affairs",
                                                            |        "allocation": 6030000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.86
                                                            |      },
                                                            |      {
                                                            |        "category": "Agriculture, forestry, fishing and hunting",
                                                            |        "allocation": 4541000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.65
                                                            |      },
                                                            |      {
                                                            |        "category": "Fuel and energy",
                                                            |        "allocation": 463000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.07
                                                            |      },
                                                            |      {
                                                            |        "category": "Mining, manufacturing and construction",
                                                            |        "allocation": 72000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.01
                                                            |      },
                                                            |      {
                                                            |        "category": "Transport",
                                                            |        "allocation": 28061000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 4.02
                                                            |      },
                                                            |      {
                                                            |        "category": "Communication",
                                                            |        "allocation": 447000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.06
                                                            |      },
                                                            |      {
                                                            |        "category": "Other industries",
                                                            |        "allocation": 273000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.04
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D economic affairs",
                                                            |        "allocation": 4787000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.69
                                                            |      },
                                                            |      {
                                                            |        "category": "Economic affairs n.e.c.",
                                                            |        "allocation": 458000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.07
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 6.46
                                                            |  },
                                                            |  {
                                                            |    "category": "Environment protection",
                                                            |    "allocation": 11608000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Waste management",
                                                            |        "allocation": 8089000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 1.16
                                                            |      },
                                                            |      {
                                                            |        "category": "Waste water management",
                                                            |        "allocation": 0.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.00
                                                            |      },
                                                            |      {
                                                            |        "category": "Pollution abatement",
                                                            |        "allocation": 469000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.07
                                                            |      },
                                                            |      {
                                                            |        "category": "Protection of biodiversity and landscape",
                                                            |        "allocation": 638000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.09
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D environment protection",
                                                            |        "allocation": 460000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.07
                                                            |      },
                                                            |      {
                                                            |        "category": "Environment protection n.e.c.",
                                                            |        "allocation": 1952000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.28
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 1.66
                                                            |  },
                                                            |  {
                                                            |    "category": "Housing and community amenities",
                                                            |    "allocation": 10013000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Housing development",
                                                            |        "allocation": 5572000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.80
                                                            |      },
                                                            |      {
                                                            |        "category": "Community development",
                                                            |        "allocation": 2423000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.35
                                                            |      },
                                                            |      {
                                                            |        "category": "Water supply",
                                                            |        "allocation": 814000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.12
                                                            |      },
                                                            |      {
                                                            |        "category": "Street lighting",
                                                            |        "allocation": 839000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.12
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D housing and community amenities",
                                                            |        "allocation": 0.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.00
                                                            |      },
                                                            |      {
                                                            |        "category": "Housing and community amenities n.e.c.",
                                                            |        "allocation": 366000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.05
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 1.43
                                                            |  },
                                                            |  {
                                                            |    "category": "Health",
                                                            |    "allocation": 138704000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Medical services",
                                                            |        "allocation": 132103000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 18.92
                                                            |      },
                                                            |      {
                                                            |        "category": "Medical research",
                                                            |        "allocation": 2142000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.31
                                                            |      },
                                                            |      {
                                                            |        "category": "Central and other health services",
                                                            |        "allocation": 4460000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.64
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 19.86
                                                            |  },
                                                            |  {
                                                            |    "category": "Recreation, culture and religion",
                                                            |    "allocation": 11429000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Recreational and sporting services",
                                                            |        "allocation": 3194000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.46
                                                            |      },
                                                            |      {
                                                            |        "category": "Cultural services",
                                                            |        "allocation": 4003000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.57
                                                            |      },
                                                            |      {
                                                            |        "category": "Broadcasting and publishing services",
                                                            |        "allocation": 3945000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.56
                                                            |      },
                                                            |      {
                                                            |        "category": "Religious and other community services",
                                                            |        "allocation": 93000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.01
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D recreation, culture and religion",
                                                            |        "allocation": 130000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.02
                                                            |      },
                                                            |      {
                                                            |        "category": "Recreation, culture and religion n.e.c.",
                                                            |        "allocation": 63000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.01
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 1.64
                                                            |  },
                                                            |  {
                                                            |    "category": "Education",
                                                            |    "allocation": 84027000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Pre-primary and primary education",
                                                            |        "allocation": 30942000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 4.43
                                                            |      },
                                                            |      {
                                                            |        "category": "Secondary education",
                                                            |        "allocation": 38193000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 5.47
                                                            |      },
                                                            |      {
                                                            |        "category": "Post-secondary non-tertiary education",
                                                            |        "allocation": 91000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.01
                                                            |      },
                                                            |      {
                                                            |        "category": "Tertiary education",
                                                            |        "allocation": 5896000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.84
                                                            |      },
                                                            |      {
                                                            |        "category": "Education not definable by level",
                                                            |        "allocation": 636000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.09
                                                            |      },
                                                            |      {
                                                            |        "category": "Subsidiary services to education",
                                                            |        "allocation": 4092000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.59
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D education",
                                                            |        "allocation": 1577000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.23
                                                            |      },
                                                            |      {
                                                            |        "category": "Education n.e.c.",
                                                            |        "allocation": 2600000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.37
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 12.03
                                                            |  },
                                                            |  {
                                                            |    "category": "Social protection",
                                                            |    "allocation": 264151000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "Sickness and disability",
                                                            |        "allocation": 52956000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 7.58
                                                            |      },
                                                            |      {
                                                            |        "category": "Old age",
                                                            |        "allocation": 120095000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 17.20
                                                            |      },
                                                            |      {
                                                            |        "category": "Survivors",
                                                            |        "allocation": 1164000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.17
                                                            |      },
                                                            |      {
                                                            |        "category": "Family and children",
                                                            |        "allocation": 24795000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 3.55
                                                            |      },
                                                            |      {
                                                            |        "category": "Unemployment",
                                                            |        "allocation": 2698000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.39
                                                            |      },
                                                            |      {
                                                            |        "category": "Housing",
                                                            |        "allocation": 26374000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 3.78
                                                            |      },
                                                            |      {
                                                            |        "category": "Social exclusion n.e.c. (8)",
                                                            |        "allocation": 31485000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 4.51
                                                            |      },
                                                            |      {
                                                            |        "category": "R&D social protection",
                                                            |        "allocation": 0.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.00
                                                            |      },
                                                            |      {
                                                            |        "category": "Social protection n.e.c.",
                                                            |        "allocation": 4583000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.66
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 37.83
                                                            |  },
                                                            |  {
                                                            |    "category": "EU transactions",
                                                            |    "allocation": 7671000.00,
                                                            |    "subCategory": [
                                                            |      {
                                                            |        "category": "VAT-based and GNI-based contributions (net of abatement and collection costs",
                                                            |        "allocation": 10482000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 1.50
                                                            |      },
                                                            |      {
                                                            |        "category": "EU receipts",
                                                            |        "allocation": -2811000.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": -0.40
                                                            |      },
                                                            |      {
                                                            |        "category": "Attributed aid and Common Foreign and Security Policy",
                                                            |        "allocation": 0.00,
                                                            |        "subCategory": [
                                                            |
                                                            |        ],
                                                            |        "percentage": 0.00
                                                            |      }
                                                            |    ],
                                                            |    "percentage": 1.10
                                                            |  }
                                                            |]""".stripMargin)

}
