tax-expenditure-categories
---

* **URL**

  `/tax-calc/tax-expenditure-categories/:taxYear`

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
[
  "General public services",
  "Defense",
  "Public order and safety",
  "Economic affairs",
  "Environment protection",
  "Housing and community amenities",
  "Health",
  "Recreation, culture and religion",
  "Education",
  "Social protection",
  "EU transactions"
]
```

* **Error Response:**

  * **Code:** 400 BADREQUEST <br />
    **Content:** `{"code":"BAD_REQUEST","message":"Invalid Request"}`

  OR when category breakdown configuration does not exist or server failure

  * **Code:** 500 INTERNAL_SERVER_ERROR <br/>



