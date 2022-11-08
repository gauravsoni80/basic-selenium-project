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

public class Amazon implements IStructure.IAmazon {

    private WebDriverUtil driverClass = null;
    private String AMAZON_PROPERTY_URL = PropertyUtil.getProperty("amazon.url");
    private WebElement element;
    private ExtentReportUtil extentReportUtil;


    @Override
    public void initializeComponent() {
        driverClass = new WebDriverUtil();
        driverClass.driver.get(AMAZON_PROPERTY_URL);
        extentReportUtil = new ExtentReportUtil(Theme.DARK,"Amazon","Amazon Search");
    }

    @Override
    public void mainTest(String search) {
        element = driverClass.driver.findElement(By.xpath(ISeleniumXPath.IAmazon.SEARCH));
        element.sendKeys(search);
        element = driverClass.driver.findElement(By.xpath(ISeleniumXPath.IAmazon.SUBMIT));
        element.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        extentReportUtil.testWithScreenShot(driverClass.driver, "Amazon", search, Status.INFO, search+" Search Successfully ");
    }

    @Override
    public void closeWebDriver() {
        driverClass.driver.close();
    }

    @Override
    public void flushReport() {
        extentReportUtil.reportFlush();
    }
}