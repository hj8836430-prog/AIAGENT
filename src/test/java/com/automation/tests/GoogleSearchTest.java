package com.automation.tests;

import com.automation.base.BaseTest;
import com.automation.pages.GoogleHomePage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleSearchTest extends BaseTest {

    @Test(description = "Verify Google search box is displayed")
    public void testGoogleSearchBoxIsDisplayed() {
        GoogleHomePage homePage = new GoogleHomePage(driver);
        Assert.assertTrue(homePage.isSearchBoxDisplayed(), 
                "Search box is not displayed on Google home page");
    }

    @Test(description = "Verify Google home page title")
    public void testGoogleHomePageTitle() {
        GoogleHomePage homePage = new GoogleHomePage(driver);
        String title = homePage.getPageTitle();
        Assert.assertTrue(title.contains("Google"), 
                "Page title does not contain 'Google'");
    }

    @Test(description = "Perform search on Google")
    public void testPerformSearch() {
        GoogleHomePage homePage = new GoogleHomePage(driver);
        homePage.searchFor("Selenium");
        
        // Wait for search results page to load
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        String title = homePage.getPageTitle();
        Assert.assertTrue(title.contains("Selenium") || title.contains("search"), 
                "Search results page is not displayed. Title: " + title);
    }
}
