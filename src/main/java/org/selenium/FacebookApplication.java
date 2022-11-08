package org.selenium;

import org.selenium.pages.Facebook;
import org.selenium.providers.FacebookDataProvider;
import org.testng.annotations.*;

public class FacebookApplication {

    Facebook facebook = null;

    @BeforeMethod
    public void beforeMethod() {
        facebook = new Facebook();
        facebook.initializeComponent();
    }

    @Test(dataProvider = "fb_login",dataProviderClass = FacebookDataProvider.class)
    public void test(String user, String pass) {
        facebook.mainTest(user, pass);
    }

    @AfterTest
    public void afterTest(){
        facebook.flushReport();
    }

    @AfterMethod
    public void afterClass() {
        facebook.closeWebDriver();
    }
}