# Selenium TestNG Framework 🚀

A complete automation testing framework built with **Selenium WebDriver** and **TestNG** following the **Page Object Model (POM)** design pattern.

## 📋 Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Configuration](#configuration)
- [Adding New Tests](#adding-new-tests)
- [Dependencies](#dependencies)
- [Best Practices](#best-practices)
- [Troubleshooting](#troubleshooting)
- [Author](#author)

## ✨ Features

- ✅ **Page Object Model (POM)** - Maintains test code using POM design pattern
- ✅ **Multiple Browser Support** - Chrome, Firefox, Edge
- ✅ **WebDriverManager** - Automatic driver download and setup
- ✅ **TestNG Framework** - Powerful testing framework with rich reporting
- ✅ **Explicit Waits** - Proper wait mechanisms for element interactions
- ✅ **Logging Support** - Built-in Java logging for debugging
- ✅ **Extent Reports** - Detailed HTML test reports
- ✅ **Maven Build** - Maven for dependency management and build automation

## 📁 Project Structure

```
selenium-testng-framework/
│
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/automation/
│   │   │       ├── base/
│   │   │       │   └── BaseTest.java           # Base test class with setup/teardown
│   │   │       ├── pages/
│   │   │       │   └── GoogleHomePage.java     # Page Object Model
│   │   │       ├── tests/
│   │   │       │   └── GoogleSearchTest.java   # Test cases
│   │   │       └── utils/
│   │   │           └── WebElementUtils.java    # Utility methods
│   │   └── resources/
│   │       └── config.properties               # Configuration file
│
├── pom.xml                                      # Maven dependencies
├── testng.xml                                   # TestNG configuration
├── .gitignore                                   # Git ignore file
├── README.md                                    # This file
└── target/                                      # Build output (generated)
```

## 🔧 Prerequisites

- **Java 11** or higher
- **Maven 3.6** or higher
- **Git** (for version control)
- **Windows/Mac/Linux** operating system
- **Chrome/Firefox/Edge** browser installed

### Verify Installation

```bash
# Check Java version
java -version

# Check Maven version
mvn -version

# Check Git version
git --version
```

## 📥 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/hj8836430-prog/AIAGENT.git
cd AIAGENT
```

### 2. Install Dependencies

```bash
mvn clean install
```

This command will:
- Download all dependencies
- Compile the project
- Run tests

## ▶️ Running Tests

### Run All Tests

```bash
mvn clean test
```

### Run Specific Test Class

```bash
mvn clean test -Dtest=GoogleSearchTest
```

### Run Specific Test Method

```bash
mvn clean test -Dtest=GoogleSearchTest#testGoogleSearchBoxIsDisplayed
```

### Run with TestNG XML Suite

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```

### View Test Reports

After running tests, check the reports:
```bash
cat target/surefire-reports/*.txt
```

## ⚙️ Configuration

### Browser Configuration

Edit `src/test/resources/config.properties`:

```properties
# Supported: chrome, firefox, edge
browser=chrome

# Application URL
url=https://www.google.com

# Timeout in seconds
implicit.wait=10
explicit.wait=15

# Environment: development, staging, production
environment=production

# Report path
report.path=./test-output/
```

### TestNG XML Configuration

Edit `testng.xml` to control test execution:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-current.dtd">
<suite name="Selenium TestNG Suite" parallel="false">
    <test name="Google Search Tests">
        <parameter name="browser" value="chrome"/>
        <parameter name="url" value="https://www.google.com"/>
        <classes>
            <class name="com.automation.tests.GoogleSearchTest"/>
        </classes>
    </test>
</suite>
```

## 🆕 Adding New Tests

### Step 1: Create Page Object Class

Create a new class in `src/test/java/com/automation/pages/`:

```java
package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class YourPage {
    private WebDriver driver;

    @FindBy(id = "element_id")
    private WebElement element;

    public YourPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void performAction() {
        element.click();
    }
}
```

### Step 2: Create Test Class

Create a new test class in `src/test/java/com/automation/tests/`:

```java
package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.YourPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class YourTest extends BaseTest {

    @Test(description = "Test description")
    public void testCase() {
        YourPage page = new YourPage(driver);
        page.performAction();
        Assert.assertTrue(true, "Test assertion message");
    }
}
```

### Step 3: Update testng.xml

Add your test class to `testng.xml`:

```xml
<classes>
    <class name="com.automation.tests.YourTest"/>
</classes>
```

### Step 4: Run New Tests

```bash
mvn clean test -Dtest=YourTest
```

## 📦 Dependencies

| Dependency | Version | Purpose |
|-----------|---------|---------|
| Selenium Java | 4.15.0 | Browser automation |
| TestNG | 7.7.1 | Test framework |
| WebDriverManager | 5.6.2 | Driver management |
| Log4j Core | 2.21.0 | Logging |
| Extent Reports | 5.1.1 | Test reporting |
| Maven Compiler | 3.11.0 | Java compilation |
| Maven Surefire | 3.1.2 | Test execution |

## 🎯 Best Practices

1. **Use Page Object Model** - Keep page interactions in separate classes
2. **Use Explicit Waits** - Avoid Thread.sleep(), use WebDriverWait
3. **Separate Test Data** - Use external files for test data
4. **Meaningful Test Names** - Use descriptive names for test methods
5. **Add Test Descriptions** - Use `@Test(description="...")` annotation
6. **Use Logging** - Log important actions and assertions
7. **Clean Code** - Follow SOLID principles
8. **Error Handling** - Proper exception handling and reporting

## 🔍 Example Test

```java
@Test(description = "Verify Google search functionality")
public void testGoogleSearch() {
    // Arrange
    GoogleHomePage homePage = new GoogleHomePage(driver);
    
    // Act
    homePage.searchFor("Selenium");
    
    // Assert
    Assert.assertTrue(homePage.isSearchBoxDisplayed(), 
        "Search box should be displayed");
}
```

## 🐛 Troubleshooting

### Issue: "WebDriver not found"

**Solution:** WebDriverManager should handle this automatically. Ensure internet connection for driver download.

```bash
# Force clean install
mvn clean install -U
```

### Issue: "Element not found" exceptions

**Solution:** Check locators in Page Object classes and ensure page is fully loaded.

```java
// Use explicit waits instead of Thread.sleep()
wait.until(ExpectedConditions.visibilityOf(element));
```

### Issue: Tests failing randomly

**Solution:** Increase wait times or improve wait logic.

```properties
# Increase timeouts in config.properties
implicit.wait=15
explicit.wait=20
```

### Issue: Maven build failing

**Solution:** Clear Maven cache and reinstall dependencies.

```bash
# Clear Maven cache
mvn clean
rm -rf ~/.m2/repository

# Reinstall dependencies
mvn clean install -U
```

## 📊 Test Results

After running tests, results are available in:
- Console output
- `target/surefire-reports/` directory
- TestNG reports (if configured)

## 🚀 CI/CD Integration

This framework can be integrated with CI/CD tools:

### GitHub Actions Example

Create `.github/workflows/test.yml`:

```yaml
name: Selenium Tests

on: [push, pull_request]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '11'
      - run: mvn clean test
```

## 📝 License

MIT License - Feel free to use this framework in your projects.

## 👨‍💻 Author

**hj8836430-prog**

- GitHub: [@hj8836430-prog](https://github.com/hj8836430-prog)
- Email: hj8836430@gmail.com

## 📞 Support

For issues, questions, or suggestions:
1. Check the [Troubleshooting](#troubleshooting) section
2. Create an Issue on GitHub
3. Contact the author

---

**Happy Testing!** 🎉

**Last Updated:** March 6, 2026
