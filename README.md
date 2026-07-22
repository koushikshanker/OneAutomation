# OneAutomation

A multi-module Java test automation framework supporting both **Web UI** (Selenium) and **API** (REST Assured) testing, orchestrated with **Cucumber BDD** and **TestNG**.

---

## Tech Stack

| Component | Technology | Version |
|---|---|---|
| Language | Java | 17 |
| Build Tool | Maven | 3.x |
| UI Automation | Selenium WebDriver | 4.21.0 |
| Driver Management | WebDriverManager | 5.8.0 |
| API Automation | REST Assured | 5.4.0 |
| BDD | Cucumber | 7.18.0 |
| Test Runner | TestNG | 7.10.2 |
| Reporting | ExtentReports + Cucumber Adapter | 5.1.2 / 1.14.0 |
| Logging | SLF4J + Logback | 2.0.13 / 1.5.6 |
| JSON | Jackson | 2.17.1 |

---

## Prerequisites

- **JDK 17** or higher
- **Maven 3.8+**
- **Google Chrome**, **Firefox**, or **Edge** browser installed
- No manual driver downloads needed (WebDriverManager handles it automatically)

---

## Project Structure

```
OneAutomation/
├── pom.xml                          # Parent POM (dependency management)
├── framework/                       # Reusable framework module
│   ├── pom.xml
│   └── src/main/java/com/oneautomation/framework/
│       ├── config/                  # Configuration management
│       │   ├── ConfigMap.java       # Enum of all config keys
│       │   ├── PropertyUtils.java   # Loads .properties, system prop overrides
│       │   └── GlobalVars.java      # OS, paths, directories
│       ├── driver/                  # Browser driver lifecycle
│       │   ├── BrowserType.java     # Enum: CHROME, FIREFOX, EDGE, SAFARI
│       │   ├── DriverManager.java   # ThreadLocal<RemoteWebDriver> singleton
│       │   └── DriverInitiator.java # Browser setup with ChromeOptions etc.
│       ├── locator/
│       │   └── Locator.java         # Abstract locator (wraps Selenium By + description)
│       ├── base/
│       │   ├── CommonAutomation.java
│       │   ├── fe/WebBase.java      # Base class for UI page objects
│       │   └── be/                  # Base classes for API testing
│       │       ├── ApiBase.java     # ThreadLocal request/response management
│       │       ├── ApiRequestSpec.java  # Immutable request builder
│       │       ├── ApiResponse.java     # Response wrapper
│       │       ├── HttpClient.java      # Interface: get/post/put/delete/patch/head
│       │       └── RestAssuredHttpClient.java  # REST Assured implementation
│       ├── util/
│       │   ├── wrapper/             # Interfaces (contracts)
│       │   │   ├── ElementActions.java       # click, sendKeys, getText, isDisplayed...
│       │   │   ├── WaitStrategy.java         # waitForVisibility, waitForClickable...
│       │   │   ├── JavaScriptSupport.java    # jsClick, scrollToElement, executeScript...
│       │   │   └── InteractionHandler.java   # hover, doubleClick, contextClick...
│       │   ├── fe/                  # Selenium implementations
│       │   │   ├── SeleniumElementActions.java
│       │   │   ├── SeleniumWaitStrategy.java
│       │   │   ├── SeleniumJavaScriptSupport.java
│       │   │   └── SeleniumInteractionHandler.java
│       │   ├── ScreenshotUtils.java
│       │   ├── DateUtils.java
│       │   └── JsonUtils.java
│       ├── context/
│       │   ├── CacheMechanism.java   # ThreadLocal key-value cache
│       │   └── SessionInitiator.java # Session lifecycle management
│       ├── hooks/
│       │   ├── CucumberHooks.java    # @Before/@After for @ui/@api tags
│       │   └── TestNGListener.java   # ITestListener for logging
│       └── logging/
│           └── TestNGLogUtility.java # Static log methods
│
└── tests/                           # Test module
    ├── pom.xml
    └── src/test/
        ├── java/com/oneautomation/tests/
        │   ├── base/
        │   │   └── TestRunner.java          # Cucumber + TestNG runner
        │   ├── common/
        │   │   ├── TestDataProvider.java     # Loads test data properties
        │   │   ├── EndpointResolver.java     # URL construction per env
        │   │   └── SharedPageActions.java    # Reusable page helpers
        │   ├── hooks/
        │   │   └── TestHooks.java            # Scenario context logging
        │   ├── pageobjects/                 # Page Object classes by domain
        │   │   ├── authentication/LoginPO.java
        │   │   ├── dashboard/DashboardPO.java
        │   │   ├── pim/EmployeeListPO.java, AddEmployeePO.java
        │   │   ├── admin/UserManagementPO.java
        │   │   ├── leave/ApplyLeavePO.java
        │   │   ├── myinfo/PersonalDetailsPO.java
        │   │   ├── directory/DirectoryPO.java
        │   │   ├── buzz/BuzzPO.java
        │   │   ├── common/SidebarPO.java, TopbarPO.java
        │   │   └── api/BookingApiPO.java, HttpBinApiPO.java, UserApiPO.java
        │   ├── stepdefinitions/             # Cucumber step defs by domain
        │   │   ├── authentication/LoginStepDef.java
        │   │   ├── dashboard/DashboardStepDef.java
        │   │   ├── navigation/NavigationStepDef.java
        │   │   ├── pim/PimStepDef.java
        │   │   ├── admin/AdminStepDef.java
        │   │   ├── leave/LeaveStepDef.java
        │   │   ├── myinfo/MyInfoStepDef.java
        │   │   └── api/BookingApiStepDef.java, HttpBinApiStepDef.java, UserApiStepDef.java
        │   └── features/                   # Gherkin feature files
        │       ├── authentication/login.feature
        │       ├── dashboard/dashboard.feature
        │       ├── navigation/sidebar-navigation.feature
        │       ├── pim/employee-management.feature
        │       ├── admin/user-management.feature
        │       ├── leave/apply-leave.feature
        │       ├── myinfo/personal-details.feature
        │       └── api/booking-api.feature, httpbin-api.feature, user-api.feature
        └── resources/
            ├── config/dev.properties, staging.properties, prod.properties
            ├── testdata/Data.properties
            ├── testng.xml, testng-smoke.xml, testng-api.xml
            ├── extent.properties
            └── extent-config.xml
```

---

## How the Framework Works

### Module Separation

The project is split into two Maven modules:

- **`framework`** - Reusable automation infrastructure. Contains driver management, locators, wrapper interfaces, base classes, hooks, and utilities. This module has zero knowledge of the application under test.
- **`tests`** - Application-specific tests. Contains page objects, step definitions, feature files, and config for the target application (OrangeHRM, Restful-Booker, httpbin).

### Driver Management (Thread-Safe)

`DriverManager` uses a `ThreadLocal<RemoteWebDriver>` to ensure each test thread gets its own browser instance. This enables parallel execution without conflicts.

```
DriverManager.getInstance().getDriver()   // get driver for current thread
DriverManager.getInstance().setDriver(d)  // set driver for current thread
DriverManager.getInstance().quitDriver()  // quit and clean up
```

`DriverInitiator.initDriver("chrome")` handles the full setup: WebDriverManager resolves the correct driver binary, browser options are configured (headless, no-sandbox, etc.), and timeouts are set from properties.

### Locator System

The `Locator` class wraps Selenium `By` objects with a human-readable description. This makes logs and error messages more meaningful.

```java
// Define locators with descriptions
Locator loginButton = Locator.cssSelector("button[type='submit']", "Login button");
Locator menuItem = Locator.xpath("//span[normalize-space(.)='${0}']", "Menu item");

// Parameterized locators (replace ${0}, ${1}, etc.)
Locator adminMenu = genericMethod.parameterizedLocator(menuItem, "Admin");
```

### Wrapper Interfaces

The framework defines **interfaces** for all Selenium operations, with Selenium implementations. This abstracts the test code from the underlying driver:

| Interface | Implementation | Purpose |
|---|---|---|
| `ElementActions` | `SeleniumElementActions` | click, sendKeys, getText, isDisplayed, getElements |
| `WaitStrategy` | `SeleniumWaitStrategy` | waitForVisibility, waitForClickable, hardWait |
| `JavaScriptSupport` | `SeleniumJavaScriptSupport` | jsClick, scrollToElement, executeScript |
| `InteractionHandler` | `SeleniumInteractionHandler` | hover, doubleClick, contextClick, dragAndDrop |

### Page Object Pattern

UI page objects extend `WebBase`, which provides pre-initialized wrappers:

```java
public class LoginPO extends WebBase {
    private final Locator usernameField = Locator.cssSelector("input[name='username']", "Username");
    private final Locator loginButton = Locator.cssSelector("button[type='submit']", "Login");

    public void login(String username, String password) {
        wait.waitForVisibilityOfElement(usernameField);
        genericMethod.sendKeys(usernameField, username);
        genericMethod.sendKeys(passwordField, password);
        genericMethod.click(loginButton);
    }
}
```

Available in every page object via `WebBase`:
- `genericMethod` - element interactions (click, type, read text, check visibility)
- `wait` - explicit waits (visibility, clickability, invisibility)
- `jsUtil` - JavaScript execution (JS click, scroll, page load wait)
- `actionBuilderUtility` - advanced interactions (hover, double-click, drag)

### API Testing Pattern

API page objects extend `ApiBase`, which provides request building and response handling:

```java
public class BookingApiPO extends ApiBase {
    public ApiResponse createBooking(String firstname, String lastname, int price) {
        Map<String, Object> body = new HashMap<>();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", price);

        ApiRequestSpec spec = requestBuild()
                .endpoint("https://restful-booker.herokuapp.com")
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .body(body)
                .build();

        ApiResponse resp = requestMethod.post(spec);
        saveResponse(resp);
        return resp;
    }
}
```

`ApiRequestSpec` uses a builder pattern. `HttpClient` interface defines the HTTP methods, implemented by `RestAssuredHttpClient`.

### Configuration

Properties are loaded per environment from `tests/src/test/resources/config/{env}.properties`. System properties override file values:

```bash
# Override browser and env via command line
mvn test -Dbrowser=firefox -Denv=staging -Dheadless=true
```

Key config values in `ConfigMap` enum: `BROWSER`, `HEADLESS`, `ENV`, `BASE_URL`, `API_BASE_URL`, `IMPLICIT_WAIT`, `EXPLICIT_WAIT`, `PAGE_LOAD_TIMEOUT`, `SCREENSHOT_ON_FAILURE`.

### Hooks Lifecycle

Two hook layers execute in order:

1. **Framework hooks** (`CucumberHooks.java`, order=0) - Driver init/teardown, screenshots on failure
   - `@Before("@ui")` - Initializes browser session
   - `@Before("@api")` - Logs API scenario start
   - `@After("@ui")` - Takes screenshot on failure, quits driver
   - `@After("@api")` - Cleans up API state

2. **Test hooks** (`TestHooks.java`, order=1) - Scenario context logging

### Test Execution Flow

```
Feature File (.feature)
    ↓ matched by
Step Definitions (StepDef.java)
    ↓ use
Page Objects (PO.java)
    ↓ extend
WebBase / ApiBase
    ↓ use
Wrapper Interfaces → Selenium/RestAssured Implementations
    ↓ operate on
DriverManager (ThreadLocal) / HTTP Client
```

---

## Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd OneAutomation
   ```

2. **Build the framework**
   ```bash
   mvn clean install -pl framework
   ```

3. **Compile tests**
   ```bash
   mvn clean test-compile -pl tests -am
   ```

4. **Verify your browser is installed**
   - Chrome, Firefox, or Edge must be installed
   - WebDriverManager automatically downloads the matching driver binary

---

## Running Tests

### Run all tests
```bash
mvn test -pl tests
```

### Run by tag (UI only)
```bash
mvn test -pl tests -Dcucumber.filter.tags="@ui"
```

### Run by tag (API only)
```bash
mvn test -pl tests -Dcucumber.filter.tags="@api"
```

### Run smoke tests
```bash
mvn test -pl tests -Dcucumber.filter.tags="@smoke"
```

### Run with a specific browser
```bash
mvn test -pl tests -Dbrowser=firefox
```

### Run headless
```bash
mvn test -pl tests -Dheadless=true
```

### Run against a different environment
```bash
mvn test -pl tests -Denv=staging
```

### Run with specific TestNG suite
```bash
mvn test -pl tests -DsuiteXmlFile=testng-api.xml
```

### Combine options
```bash
mvn test -pl tests -Dbrowser=chrome -Dheadless=true -Denv=dev -Dcucumber.filter.tags="@ui and @smoke"
```

### Run from IntelliJ IDEA
Right-click any `.feature` file or individual scenario and select **Run**. The IDE uses the Cucumber plugin to match glue code automatically.

---

## Test Coverage

### UI Tests (OrangeHRM - https://opensource-demo.orangehrmlive.com)

| Module | Feature File | Scenarios |
|---|---|---|
| Authentication | `login.feature` | Login with valid/invalid credentials, empty fields, branding, forgot password |
| Dashboard | `dashboard.feature` | Widget visibility, quick launch navigation |
| Navigation | `sidebar-navigation.feature` | Sidebar module navigation, logout, collapse toggle |
| PIM | `employee-management.feature` | Add employee, search, reset, edit |
| Admin | `user-management.feature` | Search users, filter by role/status, reset |
| Leave | `apply-leave.feature` | Apply leave page, my leave list |
| My Info | `personal-details.feature` | View/update personal details, profile image |

### API Tests

| API | Feature File | Scenarios |
|---|---|---|
| Restful-Booker | `booking-api.feature` | CRUD operations, token auth, basic auth, PATCH |
| httpbin | `httpbin-api.feature` | Status codes, custom headers, basic/bearer auth, POST echo, UUID |
| JSONPlaceholder | `user-api.feature` | GET/POST/DELETE users |

---

## Reports

After test execution, reports are generated at:

- **Cucumber HTML** - `tests/target/cucumber-reports/cucumber.html`
- **Cucumber JSON** - `tests/target/cucumber-reports/cucumber.json`
- **Extent Report** - `tests/target/extent-reports/` (via ExtentReports Cucumber Adapter)
- **Screenshots** - `screenshots/` (captured on UI test failure)

---

## Adding New Tests

### New UI Test

1. Create a page object in `tests/.../pageobjects/<module>/YourPagePO.java` extending `WebBase`
2. Create a step definition in `tests/.../stepdefinitions/<module>/YourStepDef.java`
3. Create a feature file in `tests/.../features/<module>/your-feature.feature` with `@ui` tag
4. Run it

### New API Test

1. Create an API page object in `tests/.../pageobjects/api/YourApiPO.java` extending `ApiBase`
2. Create a step definition in `tests/.../stepdefinitions/api/YourApiStepDef.java`
3. Create a feature file in `tests/.../features/api/your-api.feature` with `@api` tag
4. Run it

### New Environment

1. Create `tests/src/test/resources/config/<env>.properties`
2. Run with `-Denv=<env>`

---

## Parallel Execution

Tests run in parallel via TestNG's `@DataProvider(parallel = true)` in `TestRunner.java`. Thread safety is ensured by:

- `ThreadLocal<RemoteWebDriver>` in `DriverManager` - each thread gets its own browser
- `ThreadLocal<ApiResponse>` and `ThreadLocal<ApiRequestSpec.RequestBuilder>` in `ApiBase` - each thread gets its own API state
- `ThreadLocal` cache in `CacheMechanism` - each thread gets isolated test data

Thread count is configured in `testng.xml` (`thread-count="4"`) and `pom.xml` (`dataproviderthreadcount=4`).
