# QA Automation Test Suite

Automated test suite covering REST API, GraphQL API, and UI scenarios.

## Prerequisites

- Java 21+
- Maven 3.6+
- Google Chrome / Chromium browser
- Allure CLI, optional, for local report generation

## Tech Stack

- Java
- Maven
- JUnit 5
- Rest Assured
- Jackson
- Playwright
- AssertJ
- Lombok
- Owner
- JavaFaker
- Allure Report

## How to Run

### Run all tests

```bash
mvn clean test
```

### Run only REST API tests

```bash
mvn test -Dgroups=rest
```

### Run only GraphQL tests

```bash
mvn test -Dgroups=graphql
```

### Run only UI tests

```bash
mvn test -Dgroups=ui
```

## Allure Report

Allure reporting is configured for REST API, GraphQL API, and UI tests.

REST and GraphQL requests and responses are automatically attached to the report.

UI screenshots are attached automatically when UI tests fail.

### Generate and open Allure report

```bash
allure serve target/allure-results
```
## Test Strategy
 

The project is structured by test layers to keep REST API, GraphQL API, UI, configuration, and test data separated and easy to maintain.

The `api.rest` package contains REST API tests, models, helpers, constants, and test data.
REST tests focus on the Restful Booker API and cover the main booking flows: authentication, create booking, get booking by id, update booking, 
delete booking etc 

REST API requests are placed in helper classes.
This keeps test methods focused on test logic and assertions instead of repeating Rest Assured request setup in every test.
Request configuration such as base URL, content type, accept header, and Allure Rest Assured filter is handled in the base helper.

The `api.graphql` package contains GraphQL tests, client/service logic, and query files.
GraphQL queries are stored separately as `.graphql` files, so the test code stays clean and queries are easier to update without changing test logic.

The `ui` package contains UI tests, page objects, base setup, listeners, and utilities. 
UI tests are implemented with Playwright and follow the Page Object pattern.
This separates page actions from test assertions and makes UI tests easier to read and maintain.

The `config` package contains project configuration.

Test data is stored in dedicated test data classes, such as `BookingTestData` and `AuthTestData`. 
This avoids hardcoding test data directly inside test methods and makes test data reusable across different scenarios.

Authentication credentials are taken from configuration. 
The auth token itself is not stored in files; 
it is requested during test execution and used only for endpoints that require authorization, such as update and delete booking requests.

The project also includes data-driven testing with JUnit 5 parameterized tests.
This is used for authentication negative scenarios, where the same test logic is executed with different invalid credential combinations.

Allure reporting is integrated for better debugging. 
REST and GraphQL requests and responses are attached automatically, while UI screenshots are attached on test failures.

## Challenges & Solutions

O## Challenges & Solutions

The main challenge was the non-standard behavior of the public REST API.

Invalid authentication credentials return `200 OK` with an error message in the response body instead of `401 Unauthorized`, 
so the test validates the actual response body.

I also considered adding a negative test for invalid check-in/check-out date formats. 
The documentation states that dates should use the `CCYY-MM-DD` format, but the API accepts invalid formats and still returns `200 OK`.
Because of this behavior, I decided not to include a test expecting a validation error, as it would make the suite misleading and unstable.

Filtering by dates can also return an empty array when no matching bookings exist in the public API database, so the tests avoid relying on unstable public data.

Allure reporting was added to simplify debugging: REST and GraphQL request/response details are attached automatically, and UI screenshots are attached on failure.

## What I Would Add With More Time

- Parallel execution after improving test data cleanup
- More negative REST API scenarios
- More UI edge case coverage
- Environment-specific configuration profiles
- More API + UI integration tests

