package com.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import java.time.Duration;
import java.util.logging.Logger;

public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = Logger.getLogger(BaseTest.class.getName());
    protected static final int TIMEOUT = 10;

    @BeforeMethod
    public void setUp() {
        String browser = "chrome";  // Default browser
        String url = "https://www.google.com";  // Default URL
        initializeDriver(browser);
        maximizeWindow();
        implicitWait(TIMEOUT);
        navigateToApplication(url);
        logger.info("Browser initialized and navigated to: " + url);
    }

    public void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
        }
    }

    public void navigateToApplication(String url) {
        driver.navigate().to(url);
    }

    public void maximizeWindow() {
        driver.manage().window().maximize();
    }

    public void implicitWait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    public WebDriver getDriver() {
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed successfully");
        }
    }
}
