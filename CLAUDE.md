# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Playwright + Java UI test automation suite, plus REST Assured API tests, for https://automationexercise.com, using TestNG as the test runner. Two TestNG suite files live in the repo root: `testng.xml` (smoke, includes the `smoke` group) and `testng-regression.xml` (regression, includes the `regression` group). Plain `mvn test` uses Maven Surefire defaults and runs every test class, ignoring the suite files.

## Commands

```bash
# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=LoginTest

# Run a single test method
mvn test -Dtest=LoginTest#loginUserWithCorrectEmailAndPassword

# Run a suite
mvn test -Dsurefire.suiteXmlFiles=testng.xml
mvn test -Dsurefire.suiteXmlFiles=testng-regression.xml

# Install Playwright browsers (required once, or after bumping the Playwright version)
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

Login tests need the `TEST_USER_EMAIL` and `TEST_USER_PASSWORD` environment variables (an existing account on automationexercise.com). On GitHub Actions they come from repository secrets of the same names.

## Architecture

**Page Object Model**, split across source sets:
- `src/main/java/pages/` — page objects (`HomePage`, `SignupLoginPage`, `AccountInformationPage`, `AccountCreatedDeleted`, `ProductsPage`, `ProductDetailPage`). Each wraps a Playwright `Page` passed via constructor, exposes locators as private fields, and offers action/assertion-support methods (`isXVisible()`, `clickX()`, `fillX()`). Composite flows (e.g. `enterAccountInformation`, `enterAddressInformation`, `login`, `signUp`) chain the smaller methods.
- `src/test/java/tests/` — TestNG UI test classes, all extending `BaseTest`.
- `src/test/java/tests/api/` — REST Assured API tests, all extending `BaseApiTest`, which sets `baseURI` to `https://automationexercise.com/api` and registers a JSON parser for `text/html` responses (the API returns JSON with an HTML content type).
- `src/main/java/utils/ConfigReader.java` — loads `src/test/resources/config.properties` via a static initializer; exposes `getBaseUrl()`, `getNavigationTimeout()`, `getActionTimeout()`, plus `getExistingUserEmail()`/`getExistingUserPassword()`, which read the `TEST_USER_EMAIL`/`TEST_USER_PASSWORD` env vars and throw `IllegalStateException` if missing. Lives under `src/main/java` (not `src/test/java`) because page objects in `src/main/java/pages` (e.g. `HomePage`) also need it, and Maven's main source set has no visibility into `src/test/java` at compile time. Note: it reads the properties file with a path relative to the working directory, so tests must be run from the repo root.
- `src/main/java/config` exists but is currently empty (placeholder for future shared config classes).

**BaseTest** (`src/test/java/tests/BaseTest.java`) drives Playwright lifecycle with TestNG `@BeforeMethod`/`@AfterMethod`: creates `Playwright`, launches Chromium (headed locally, headless when the `CI` env var is `true`, e.g. on GitHub Actions), opens a new `BrowserContext`/`Page`, and applies `ConfigReader.getActionTimeout()` as the context's default (action) timeout and `ConfigReader.getNavigationTimeout()` as its default navigation timeout. A `@Listeners(ScreenshotListener.class)` annotation wires in `src/test/java/utils/ScreenshotListener.java`, which captures a screenshot to `screenshots/` (gitignored) on test failure via `ITestListener.onTestFailure`, reached through a public `BaseTest.getPage()` getter (the `page` field itself stays `protected`). Every test class extends `BaseTest` and gets fresh `playwright`/`browser`/`context`/`page` fields per test method (no shared/reused browser across tests).

**Site-specific quirk**: `HomePage.openHomePage()` navigates to the site and swallows a Ukrainian-language cookie/consent popup (`p:has-text('Погоджуюся')`) in a try/catch, since it doesn't always appear. `WaitTest` demonstrates the same pattern plus other Playwright wait strategies (`waitForLoadState`, `waitForSelector`, `waitFor(HIDDEN)`).

**Test data**: The existing-user credentials come from env vars (see above). Negative login cases (`LoginTest`, `LoginApiTest`) use `@DataProvider`s with a non-existent email and a wrong password. `SignupTest.completeSignUpFlow` generates a unique email per run via `System.currentTimeMillis()` and deletes the account at the end of the flow to avoid leaving test accounts behind.

## CI

`.github/workflows/ci.yml` runs on every push: sets up Temurin JDK 25, installs Playwright browsers with `--with-deps`, and runs `mvn test` with the credential secrets exposed as env vars.

## Configuration

`src/test/resources/config.properties`:
```
base.url=https://automationexercise.com
browser=chromium
navigation.timeout=30000
action.timeout=10000
```
`base.url`, `navigation.timeout`, and `action.timeout` are wired up via `ConfigReader` (`base.url` into `HomePage.openHomePage()`; the two timeouts into `BaseTest`'s `BrowserContext`). `browser` is still declared but not yet read anywhere in code.
