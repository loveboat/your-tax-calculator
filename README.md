
# your-tax-calculator

[![Build Status](https://travis-ci.org/hmrc/your-tax-calculator.svg?branch=master)](https://travis-ci.org/hmrc/your-tax-calculator) [ ![Download](https://api.bintray.com/packages/hmrc/releases/your-tax-calculator/images/download.svg) ](https://bintray.com/hmrc/releases/your-tax-calculator/_latestVersion)

Your Tax Calculator API services - 

Requirements
------------

The following services are exposed from the micro-service.

API
---

| *Task* | *Supported Methods* | *Description* |
|--------|----|----|
| ```/tax-calc/tax-expenditure-breakdown/:taxYear``` | GET |  [More...](docs/tax-expenditure-breakdown.md) |
| ```/tax-calc/tax-expenditure-categories/:taxYear``` | GET |   [More...](docs/tax-expenditure-categories.md) |
| ```/tax-calc/tax-expenditure-category-percentage/:taxYear``` | GET |  [More...](docs/tax-expenditure-category-percentage.md) |
| ```/tax-calc/calculate-tax/:isStatePensionAge/:taxYear/:taxCode/:grossPay/:payPeriod``` | GET | Calculates income tax and national insurance contributions  [More...](docs/calculate-tax.md) |


### License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
    