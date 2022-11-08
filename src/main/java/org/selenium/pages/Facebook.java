package org.selenium.pages;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.selenium.constants.ISeleniumXPath;
import org.selenium.constants.IStructure;
import org.selenium.utils.ExtentReportUtil;
import org.selenium.utils.PropertyUtil;
import org.selenium.utils.WebDriverUtil;
import org.testng.Assert;

public class Facebook implements IStructure.IFacebook {

    private WebDriverUtil driverClass = null;
    private String FACEBOOK_PROPERTY_URL = PropertyUtil.getProperty("facebook.url");
    private WebElement element;
    private ExtentReportUtil extentReportUtil;

    @Override
    public void initializeComponent() {
        driverClass = new WebDriverUtil();
        driverClass.driver.get(FACEBOOK_PROPERTY_URL);
        extentReportUtil = new ExtentReportUtil(Theme.DARK,"Facebook","Facebook Login");
    }

    @Override
    public void mainTest(String user, String pass) {
        element = driverClass.driver.findElement(By.xpath(ISeleniumXPath.IFacebook.USER));
        element.sendKeys(user);
        element = driverClass.driver.findElement(By.xpath(ISeleniumXPath.IFacebook.PASSWORD));
        element.sendKeys(pass);
        element = driverClass.driver.findElement(By.xpath(ISeleniumXPath.IFacebook.SUBMIT));
        element.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (driverClass.driver.getTitle().equals("Facebook")) {
            extentReportUtil.testWithScreenShot(driverClass.driver, "Facebook", user, Status.INFO, user+" Login Test Success");

        } else if (driverClass.driver.getTitle().equals("Log in to Facebook")) {
            extentReportUtil.testWithScreenShot(driverClass.driver, "Facebook", user, Status.FAIL, user+" Login Test Failed!");
            Assert.fail();
        }
    }

    @Override
    public void closeWebDriver() {
        driverClass.closeDriver();
    }

    @Override
    public void flushReport() {
        extentReportUtil.reportFlush();
    }
}