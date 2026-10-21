# Automation Exercise – UI & API Test Automation

![CI](https://github.com/irynazolotarevska/automation-exercise/actions/workflows/ci.yml/badge.svg)

End-to-end UI and API test automation suite for [automationexercise.com](https://automationexercise.com), built with **Playwright for Java**, **TestNG** and **REST Assured**, using the **Page Object Model**.

## Tech stack

| Tool | Purpose |
|------|---------|
| Java 25 | Language |
| Playwright 1.44 | Browser automation (Chromium) |
| TestNG 7.7 | Test runner, groups, data providers, listeners |
| REST Assured 6.0 | API testing |
| Maven | Build & dependency management |
| GitHub Actions | CI – runs the suite headless on every push |

## Test coverage

**UI tests** (`src/test/java/tests`)
- Home page – logo, navigation, page elements
- Signup – full registration flow with a unique email per run, followed by account deletion
- Login – valid login and data-driven negative scenarios
- Product details – product page information
- Waits – examples of Playwright wait strategies (`waitForLoadState`, `waitForSelector`, waiting for an element to be hidden)

**API tests** (`src/test/java/tests/api`)
- Login – valid and invalid credentials
- Products list – fetch all products
- Search product – positive and negative search requests

## Project structure

```
src
├── main/java
│   ├── pages/                 # Page Objects: locators + actions per page
│   └── utils/ConfigReader     # Reads config.properties
└── test
    ├── java
    │   ├── tests/             # UI tests (extend BaseTest)
    │   │   └── api/           # API tests (extend BaseApiTest)
    │   └── utils/ScreenshotListener   # Screenshot on test failure
    └── resources/config.properties    # Base URL, timeouts, test data
testng.xml                     # Smoke suite
testng-regression.xml          # Regression suite
.github/workflows/ci.yml       # CI pipeline
```

## Getting started

### Prerequisites
- JDK 25
- Maven 3.9+

### Install Playwright browsers (once, or after a Playwright version bump)
```bash
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

### Set test credentials
Login tests use an existing account on automationexercise.com. Its credentials are read from environment variables, not stored in the repo:

```bash
# bash
export TEST_USER_EMAIL=<email>
export TEST_USER_PASSWORD=<password>
```
```powershell
# PowerShell
$env:TEST_USER_EMAIL="<email>"
$env:TEST_USER_PASSWORD="<password>"
```

In IntelliJ, set them under *Run/Debug Configurations → Environment variables*. On GitHub Actions they come from the `TEST_USER_EMAIL` and `TEST_USER_PASSWORD` repository secrets.

### Run tests
```bash
# All tests
mvn test

# A single class
mvn test -Dtest=LoginTest

# A single method
mvn test -Dtest=LoginTest#loginUserWithCorrectEmailAndPassword

# Smoke suite
mvn test -Dsurefire.suiteXmlFiles=testng.xml

# Regression suite
mvn test -Dsurefire.suiteXmlFiles=testng-regression.xml
```

Tests run from the repository root. The browser runs **headed** locally and **headless** when the `CI` environment variable is `true` (set automatically on GitHub Actions).

## Key features
- **Page Object Model**: page logic is kept separate from test logic
- **Screenshots on failure**: a TestNG `ITestListener` saves a screenshot to `screenshots/`
- **Externalized config**: base URL and timeouts in `config.properties`, credentials in environment variables
- **Test groups**: `smoke` and `regression` suites
- **Data-driven tests** using TestNG `@DataProvider`
- **Clean test data**: signup generates a unique email and deletes the account afterwards
- **CI**: GitHub Actions installs the browsers and runs the suite on every push

## Roadmap
- Add HTML reporting (e.g. Allure)
- Cross-browser runs (Firefox, WebKit) driven by the `browser` config property
- Cover more API endpoints and UI flows (cart, checkout)

## License
[MIT](LICENSE)
