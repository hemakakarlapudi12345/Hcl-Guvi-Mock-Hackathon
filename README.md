# SweetCart — Selenium-Java Test Automation Framework

A Page Object Model-based Selenium automation framework for the [Sweet Shop](https://sweetshop.netlify.app) demo web application, built with Java, TestNG, and Maven.

---

## Table of contents

- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Configuration](#configuration)
- [Running the tests](#running-the-tests)
- [Framework design](#framework-design)
- [Test modules](#test-modules)
- [Reporting](#reporting)
- [Screenshots](#screenshots)
- [Known limitations](#known-limitations)

---

## Tech stack

| Tool | Version | Purpose |
|---|---|---|
| Java | 17+ | Language |
| Selenium WebDriver | 4.21.0 | Browser automation |
| TestNG | 7.10.2 | Test framework |
| WebDriverManager | 5.8.0 | Automatic driver management |
| ExtentReports | 5.1.1 | HTML test reporting |
| Maven | 3.8+ | Build and dependency management |

---

## Project structure

```
SweetCart-main/
├── src/test/java/com/srm/
│   ├── base/
│   │   ├── BasePage.java          # Shared wait utilities for all page classes
│   │   └── BaseTest.java          # Browser setup and teardown (@Before/@AfterMethod)
│   │
│   ├── pages/
│   │   ├── LoginPage.java         # Login form locators and actions
│   │   ├── SweetsPage.java        # Product listing, add to basket
│   │   ├── ProductPage.java       # Product detail — name, price, description
│   │   ├── BasketPage.java        # Basket navigation, item count, remove
│   │   ├── AccountPage.java       # User profile and order history
│   │   └── NavigationPage.java    # Navbar links, About page, footer
│   │
│   ├── tests/
│   │   ├── LoginTest.java         # Auth tests with @DataProvider
│   │   ├── ProductTest.java       # Product listing verification
│   │   ├── BasketTest.java        # Add, verify, and remove basket items
│   │   ├── AccountTest.java       # Profile and order history tests
│   │   └── NavigationTest.java    # Nav links, About page, footer
│   │
│   └── utils/
│       ├── ConfigReader.java      # Reads config.properties
│       ├── ExtentManager.java     # ExtentReports singleton
│       ├── Listener.java          # ITestListener — reporting + screenshot on failure
│       └── ScreenshotUtil.java    # Captures timestamped PNG on failure
│
├── src/test/resources/
│   └── config.properties          # browser, baseUrl, timeout
│
├── reports/
│   └── ExtentReport.html          # Generated after each run
│
├── screenshots/                   # Auto-created; failure screenshots saved here
├── testng.xml                     # Suite definition
└── pom.xml
```

---

## Prerequisites

- **Java 17** or higher (`java -version` to verify)
- **Maven 3.8+** (`mvn -version` to verify)
- **Google Chrome** (latest stable) or **Mozilla Firefox**
- Internet access — tests run against the live site at `https://sweetshop.netlify.app`

No manual ChromeDriver or GeckoDriver download is needed. WebDriverManager handles driver setup automatically.

---

## Configuration

All environment-specific values are stored in `src/test/resources/config.properties`:

```properties
browser=chrome        # chrome | firefox
baseUrl=https://sweetshop.netlify.app
timeout=10            # WebDriverWait timeout in seconds
```

To switch browsers, change `browser=firefox` — no code changes required.

---

## Running the tests

**Run the full suite via Maven (recommended):**

```bash
mvn test
```

The Surefire plugin picks up `testng.xml` automatically as configured in `pom.xml`.

**Run from an IDE:**

1. Right-click `testng.xml`
2. Select `Run As → TestNG Suite` (Eclipse) or `Run 'testng.xml'` (IntelliJ)

**Run a single test class:**

```bash
mvn test -Dtest=LoginTest
```

---

## Framework design

### Page Object Model (POM)

Every page of the application has a dedicated class under `com.srm.pages`. Locators are declared with `@FindBy` and all interactions are exposed as public methods. Test classes never call `driver.findElement()` or interact with `WebElement` objects directly.

### BasePage

`BasePage` is the parent of all page classes. It holds a `WebDriverWait` instance (timeout driven by `config.properties`) and exposes three shared utilities:

- `click(WebElement)` — waits for visibility then clicks
- `type(WebElement, String)` — waits, clears, then sends keys
- `getText(WebElement)` — waits then returns trimmed text

### BaseTest

`BaseTest` is the parent of all test classes. It uses `@BeforeMethod` to launch the configured browser via WebDriverManager and navigate to `baseUrl`, and `@AfterMethod` to quit the driver after every test.

### Wait strategy

`Thread.sleep()` is not used anywhere. All synchronisation is handled through `WebDriverWait` with `ExpectedConditions` in `BasePage`, and lambda-based waits for dynamic list elements in `SweetsPage`.

### Data-driven testing

`LoginTest` uses a `@DataProvider` that supplies valid and invalid credential rows, covering multiple real user accounts from the demo application.

### Listener (ITestListener)

`Listener.java` implements `ITestListener` and is registered in `testng.xml`. It:

- Creates an ExtentTest entry when a test starts (`onTestStart`)
- Marks it passed or failed with the exception detail
- Captures a screenshot on failure and attaches it to the report

---

## Test modules

### Module 1 — Authentication (`LoginTest`)

| Scenario | Status |
|---|---|
| Valid login with multiple user accounts (via @DataProvider) | Covered |
| Invalid login — error message validation | Covered |
| Logout removes login state | Not implemented |
| Account page redirects when not logged in | Not implemented |

### Module 2 — Product browse (`ProductTest`)

| Scenario | Status |
|---|---|
| Sweets listing page displays multiple products | Covered |
| Add-to-basket buttons visible on listing page | Covered |
| Category filter narrows product list | Not implemented |
| Product detail page shows name, price, description | Not implemented |

### Module 3 — Shopping basket (`BasketTest`)

| Scenario | Status |
|---|---|
| Add multiple items and verify basket count updates | Covered |
| Navigate to basket and verify item count | Covered |
| Verify total price is present | Covered |
| Remove all items — verify basket is empty | Covered |

### Module 4 — Account and order history (`AccountTest`)

| Scenario | Status |
|---|---|
| Login and verify username is displayed | Covered |
| Order history section is present | Covered |
| Each order row contains a date and total amount | Covered |

### Module 5 — Navigation and static content (`NavigationTest`)

| Scenario | Status |
|---|---|
| Home, Sweets, About, Login nav links visible and clickable | Covered |
| About page heading is correct | Covered |
| Footer is present and contains text | Covered |

---

## Reporting

After every run, an HTML report is generated at:

```
reports/ExtentReport.html
```

Open this file in any browser. It shows each test with pass/fail status, exception details on failure, and an embedded screenshot where applicable.

---

## Screenshots

Screenshots are captured automatically on test failure by `Listener.java`. They are saved to the `screenshots/` folder (created automatically if absent) with the naming format:

```
screenshots/<TestName>_<yyyyMMddHHmmss>.png
```

Example: `screenshots/testBasketFlow_20260416230819.png`

---

## Known limitations

- The Sweet Shop demo application does not have a visible logout link in the navigation for some account states — the logout test is omitted as a result.
- Product category filtering is present in the UI but was not automated in this version.
- Credentials for `AccountTest` are currently inline — a future improvement would move them to `config.properties` or a `@DataProvider`.
- The `reports/` and `screenshots/` paths are relative to the project root. If running from a different working directory, adjust the paths in `ExtentManager.java` and `ScreenshotUtil.java`.

---

## Author

**Hema K**
