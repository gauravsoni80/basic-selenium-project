package org.selenium;

import org.selenium.pages.Amazon;
import org.selenium.providers.AmazonDataProvider;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AmazonApplication {

    private Amazon amazon = null;

    @BeforeMethod
    public void beforeMethod() {
        amazon = new Amazon();
        amazon.initializeComponent();
    }

    @Test(dataProvider = "amazon_search", dataProviderClass = AmazonDataProvider.class)
    public void test(String search) {
        amazon.mainTest(search);
    }

    @AfterTest
    public void afterTest() {
        amazon.flushReport();
    }

    @AfterMethod
    public void afterClass() {
        amazon.closeWebDriver();
    }
}