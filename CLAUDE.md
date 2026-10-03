# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project overview

Playwright + Java UI test automation suite for https://automationexercise.com, using TestNG as the test runner. No `testng.xml` suite file exists — TestNG runs classes/methods directly via Maven Surefire defaults or IDE run configurations.

## Commands

```bash
# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=LoginTest

# Run a single test method
mvn test -Dtest=LoginTest#loginUserWithCorrectEmailAndPassword

# Install Playwright browsers (required once, or after bumping the Playwright version)
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

Tests run with the browser in headed mode (`setHeadless(false)` in `BaseTest`), so a display is required — there is no headless/CI mode configured.

## Architecture

**Page Object Model**, split across source sets:
- `src/main/java/pages/` — page objects (`HomePage`, `SignupLoginPage`, `AccountInformationPage`, `AccountCreatedDeleted`). Each wraps a Playwright `Page` passed via constructor, exposes locators as private fields, and offers action/assertion-support methods (`isXVisible()`, `clickX()`, `fillX()`). Composite flows (e.g. `enterAccountInformation`, `enterAddressInformation`, `login`, `signUp`) chain the smaller methods.
- `src/test/java/tests/` — TestNG test classes, all extending `BaseTest`.
- `src/main/java/utils/ConfigReader.java` — loads `src/test/resources/config.properties` via a static initializer; exposes `getBaseUrl()`, `getNavigationTimeout()`, `getActionTimeout()`, and `getExistingUserEmail()`. Lives under `src/main/java` (not `src/test/java`) because page objects in `src/main/java/pages` (e.g. `HomePage`) also need it, and Maven's main source set has no visibility into `src/test/java` at compile time. Note: it still reads the properties file with a path relative to the working directory (`src/test/resources/config.properties`), so tests must be run from the repo root.
- `src/main/java/config` exists but is currently empty (placeholder for future shared config classes).

**BaseTest** (`src/test/java/tests/BaseTest.java`) drives Playwright lifecycle with TestNG `@BeforeMethod`/`@AfterMethod`: creates `Playwright`, launches Chromium (headed locally, headless when the `CI` env var is `true`, e.g. on GitHub Actions), opens a new `BrowserContext`/`Page`, and applies `ConfigReader.getActionTimeout()` as the context's default (action) timeout and `ConfigReader.getNavigationTimeout()` as its default navigation timeout. A `@Listeners(ScreenshotListener.class)` annotation wires in `src/test/java/utils/ScreenshotListener.java`, which captures a screenshot to `screenshots/` (gitignored) on test failure via `ITestListener.onTestFailure`, reached through a public `BaseTest.getPage()` getter (the `page` field itself stays `protected`). Every test class extends `BaseTest` and gets fresh `playwright`/`browser`/`context`/`page` fields per test method (no shared/reused browser across tests).

**Site-specific quirk**: `HomePage.openHomePage()` navigates to the site and swallows a Ukrainian-language cookie/consent popup (`p:has-text('Погоджуюся')`) in a try/catch, since it doesn't always appear. `WaitTest` demonstrates the same pattern plus other Playwright wait strategies (`waitForLoadState`, `waitForSelector`, `waitFor(HIDDEN)`).

**Test data**: Signup/login tests use hardcoded credentials/emails (e.g. `testforzoloto@gmail.com`, name `AutoTest`/`AutoTest1`) rather than fixtures or generated data, and `SignupTest`/`LoginTest` are annotated `@Test(invocationCount = 10)` to run repeatedly against automationexercise.com's live signup/login flow — `SignupTest.completeSignUpFlow` generates a unique email per run via `System.currentTimeMillis()` and deletes the account at the end of the flow to avoid leaving test accounts behind.

## Configuration

`src/test/resources/config.properties`:
```
base.url=https://automationexercise.com
browser=chromium
navigation.timeout=30000
action.timeout=10000
```
`base.url`, `navigation.timeout`, and `action.timeout` are wired up via `ConfigReader` (`base.url` into `HomePage.openHomePage()`; the two timeouts into `BaseTest`'s `BrowserContext`). `browser` is still declared but not yet read anywhere in code.
