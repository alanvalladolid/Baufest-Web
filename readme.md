# Baufest Web Automation project


### Table of contents
* [Project structure](#project-structure)
* [Properties](#properties)
* [Suites](#suites)
* [Run tests](#run-tests)
* [Reports](#reports)

## About
This project are implemented using the
[TestNG](https://testng.org/), [Maven](https://maven.apache.org/).
Test results are formed by [Extent Reports](https://www.extentreports.com/).

## Project structure
* `/tests` folder contains test scripts.
* `/pages` folder contains locators of screen elements and methods that use them for actions and asserts.
* `/utilitites` folder contains BasePage, BaseTest, RetryTest and DataTest.
* `/reports`folder contains result report and screenshots.

## Properties
`src/test/java/utilities/DataTest.properties` contains test data.

## Suites
Suites files are placed in `testng.xml` folder.
Contains parameters that define:

* test classes that will be included in the test run
```xml
<test verbose="2" preserve-order="true" name="Baufest_Web">
    <classes>
        <class name="tests.SignUpUser"/>
        <class name="tests.LogInLogOutUser"/>
        <class name="tests.AddProductToCart"/>
    </classes>
</test>
```

## Run tests:
To run tests execute the testng.xml

## Reports:
Test reports are formed by [Extent Reports](https://www.extentreports.com/).

`/reports/` folder contains test results.
