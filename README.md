# Tech Test - H
Tech Test as requested to test log in functionality.

# Tech / Framework

### Languages Used
- Java
- Gherkin

### Tools Used
- Selenium
- JUnit
- Cucumber

# How To Use

## Prerequisites
To run this test pack, you should have the following installed, and the respective environment variables set up where appropriate.
- Java 11+
- Maven 3.3+

## How to Run
These tests can be run via the command line or using the built-in Maven runner within IntelliJ or your IDE of choice.
You will need to pass in the user email and password when running the tests.
This can be done by using the following command to run the tests:
`mvn -Demail=<email> -Dpwd=<password> test`

## Reporting
An html report will be generated and stored in:

target > cucumber-reports > cucumber-html-reports
