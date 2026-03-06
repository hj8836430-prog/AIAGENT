package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class GoogleHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    @FindBy(name = "q")
    private WebElement searchBox;

    @FindBy(name = "btnK")
    private WebElement googleSearchButton;

    @FindBy(xpath = "//span[contains(text(),'Google Search')]")
    private WebElement searchButtonSpan;

    public GoogleHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void enterSearchQuery(String query) {
        wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.clear();
        searchBox.sendKeys(query);
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.submit();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isSearchBoxDisplayed() {
        return searchBox.isDisplayed();
    }

    public void searchFor(String query) {
        enterSearchQuery(query);
        clickSearchButton();
    }
}
