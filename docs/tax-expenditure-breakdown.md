tax-expenditure-breakdown
---

* **URL**

  `/tax-calc/tax-expenditure-breakdown/:taxYear`

* **Method:**
  
  `GET`
  
   **Required:**

   * `taxYear=[Integer]`
   pesa document tax year 
   
   **Optional**
   
   `journeyId=[String]`
   
* **Success Response:**

  * **Code:** 200 <br />
    **Content:** 

```json
{
  "categoryBreakdown": [
    {
      "category": "General public services",
      "allocation": 58708000,
      "subCategory": [
        {
          "category": "Executive and legislative organs, financial and fiscal affairs, external affairs",
          "allocation": 10101000,
          "subCategory": []
        },
        {
          "category": "Foreign economic aid",
          "allocation": 8173000,
          "subCategory": []
        },
        {
          "category": "General services",
          "allocation": 758000,
          "subCategory": []
        },
        {
          "category": "Basic research",
          "allocation": 0,
          "subCategory": []
        },
        {
          "category": "R&D general public services",
          "allocation": 417000,
          "subCategory": []
        },
        {
          "category": "General public services n.e.c.",
          "allocation": 2535000,
          "subCategory": []
        },
        {
          "category": "Public debt transactions",
          "allocation": 36724000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Defense",
      "allocation": 36646000,
      "subCategory": [
        {
          "category": "Military defense",
          "allocation": 33996000,
          "subCategory": []
        },
        {
          "category": "Civil defence",
          "allocation": 115000,
          "subCategory": []
        },
        {
          "category": "Foreign military aid",
          "allocation": 329000,
          "subCategory": []
        },
        {
          "category": "R&D defence",
          "allocation": 1922000,
          "subCategory": []
        },
        {
          "category": "Defence n.e.c.",
          "allocation": 284000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Public order and safety",
      "allocation": 30183000,
      "subCategory": [
        {
          "category": "Police services",
          "allocation": 16645000,
          "subCategory": []
        },
        {
          "category": "Fire-protection services",
          "allocation": 2821000,
          "subCategory": []
        },
        {
          "category": "Law courts",
          "allocation": 5691000,
          "subCategory": []
        },
        {
          "category": "Prisons",
          "allocation": 4092000,
          "subCategory": []
        },
        {
          "category": "R&D public order and safety",
          "allocation": 62000,
          "subCategory": []
        },
        {
          "category": "Public order and safety n.e.c.",
          "allocation": 872000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Economic affairs",
      "allocation": 45132000,
      "subCategory": [
        {
          "category": "General economic, commercial and labour affairs",
          "allocation": 6030000,
          "subCategory": []
        },
        {
          "category": "Agriculture, forestry, fishing and hunting",
          "allocation": 4541000,
          "subCategory": []
        },
        {
          "category": "Fuel and energy",
          "allocation": 463000,
          "subCategory": []
        },
        {
          "category": "Mining, manufacturing and construction",
          "allocation": 72000,
          "subCategory": []
        },
        {
          "category": "Transport",
          "allocation": 28061000,
          "subCategory": []
        },
        {
          "category": "Communication",
          "allocation": 447000,
          "subCategory": []
        },
        {
          "category": "Other industries",
          "allocation": 273000,
          "subCategory": []
        },
        {
          "category": "R&D economic affairs",
          "allocation": 4787000,
          "subCategory": []
        },
        {
          "category": "Economic affairs n.e.c.",
          "allocation": 458000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Environment protection",
      "allocation": 11608000,
      "subCategory": [
        {
          "category": "Waste management",
          "allocation": 8089000,
          "subCategory": []
        },
        {
          "category": "Waste water management",
          "allocation": 0,
          "subCategory": []
        },
        {
          "category": "Pollution abatement",
          "allocation": 469000,
          "subCategory": []
        },
        {
          "category": "Protection of biodiversity and landscape",
          "allocation": 638000,
          "subCategory": []
        },
        {
          "category": "R&D environment protection",
          "allocation": 460000,
          "subCategory": []
        },
        {
          "category": "Environment protection n.e.c.",
          "allocation": 1952000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Housing and community amenities",
      "allocation": 10013000,
      "subCategory": [
        {
          "category": "Housing development",
          "allocation": 5572000,
          "subCategory": []
        },
        {
          "category": "Community development",
          "allocation": 2423000,
          "subCategory": []
        },
        {
          "category": "Water supply",
          "allocation": 814000,
          "subCategory": []
        },
        {
          "category": "Street lighting",
          "allocation": 839000,
          "subCategory": []
        },
        {
          "category": "R&D housing and community amenities",
          "allocation": 0,
          "subCategory": []
        },
        {
          "category": "Housing and community amenities n.e.c.",
          "allocation": 366000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Health",
      "allocation": 138704000,
      "subCategory": [
        {
          "category": "Medical services",
          "allocation": 132103000,
          "subCategory": []
        },
        {
          "category": "Medical research",
          "allocation": 2142000,
          "subCategory": []
        },
        {
          "category": "Central and other health services",
          "allocation": 4460000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Recreation, culture and religion",
      "allocation": 11429000,
      "subCategory": [
        {
          "category": "Recreational and sporting services",
          "allocation": 3194000,
          "subCategory": []
        },
        {
          "category": "Cultural services",
          "allocation": 4003000,
          "subCategory": []
        },
        {
          "category": "Broadcasting and publishing services",
          "allocation": 3945000,
          "subCategory": []
        },
        {
          "category": "Religious and other community services",
          "allocation": 93000,
          "subCategory": []
        },
        {
          "category": "R&D recreation, culture and religion",
          "allocation": 130000,
          "subCategory": []
        },
        {
          "category": "Recreation, culture and religion n.e.c.",
          "allocation": 63000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Education",
      "allocation": 84027000,
      "subCategory": [
        {
          "category": "Pre-primary and primary education",
          "allocation": 30942000,
          "subCategory": []
        },
        {
          "category": "Secondary education",
          "allocation": 38193000,
          "subCategory": []
        },
        {
          "category": "Post-secondary non-tertiary education",
          "allocation": 91000,
          "subCategory": []
        },
        {
          "category": "Tertiary education",
          "allocation": 5896000,
          "subCategory": []
        },
        {
          "category": "Education not definable by level",
          "allocation": 636000,
          "subCategory": []
        },
        {
          "category": "Subsidiary services to education",
          "allocation": 4092000,
          "subCategory": []
        },
        {
          "category": "R&D education",
          "allocation": 1577000,
          "subCategory": []
        },
        {
          "category": "Education n.e.c.",
          "allocation": 2600000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "Social protection",
      "allocation": 264151000,
      "subCategory": [
        {
          "category": "Sickness and disability",
          "allocation": 52956000,
          "subCategory": []
        },
        {
          "category": "Old age",
          "allocation": 120095000,
          "subCategory": []
        },
        {
          "category": "Survivors",
          "allocation": 1164000,
          "subCategory": []
        },
        {
          "category": "Family and children",
          "allocation": 24795000,
          "subCategory": []
        },
        {
          "category": "Unemployment",
          "allocation": 2698000,
          "subCategory": []
        },
        {
          "category": "Housing",
          "allocation": 26374000,
          "subCategory": []
        },
        {
          "category": "Social exclusion n.e.c. (8)",
          "allocation": 31485000,
          "subCategory": []
        },
        {
          "category": "R&D social protection",
          "allocation": 0,
          "subCategory": []
        },
        {
          "category": "Social protection n.e.c.",
          "allocation": 4583000,
          "subCategory": []
        }
      ]
    },
    {
      "category": "EU transactions",
      "allocation": 7671000,
      "subCategory": [
        {
          "category": "VAT-based and GNI-based contributions (net of abatement and collection costs",
          "allocation": 10482000,
          "subCategory": []
        },
        {
          "category": "EU receipts",
          "allocation": -2811000,
          "subCategory": []
        },
        {
          "category": "Attributed aid and Common Foreign and Security Policy",
          "allocation": 0,
          "subCategory": []
        }
      ]
    }
  ],
  "expenditureOnServices": 698271000,
  "accountingAdjustments": 54751000,
  "totalExpenditure": 753022000
}
```

* **Error Response:**

  * **Code:** 400 BADREQUEST <br />
    **Content:** `{"code":"BAD_REQUEST","message":"Invalid Request"}`

  OR when category breakdown configuration does not exist or server failure

  * **Code:** 500 INTERNAL_SERVER_ERROR <br/>



