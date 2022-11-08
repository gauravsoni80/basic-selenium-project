package org.selenium.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.selenium.constants.IPath;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportUtil {
    private static ExtentReports extentReports;
    private static ExtentSparkReporter spark;
    private static ExtentTest test;
    private static File file;
    private static FileInputStream fis;


    public ExtentReportUtil(Theme theme, String documentTitle, String reportName){
        getReports(theme,documentTitle,reportName);
    }

    private ExtentReports getReports(Theme theme,String documentTitle,String reportName){
        try {
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            file = new File(IPath.RESOURCES_PATH+"assets\\reports\\report_"+dateName+".html");
            spark = new ExtentSparkReporter(file);
            if(extentReports == null) {
                extentReports = new ExtentReports();
                spark.config().setDocumentTitle(documentTitle);
                spark.config().setReportName(reportName);
                spark.config().setEncoding("UTF-8");
                spark.config().setTheme(theme);
                extentReports.attachReporter(spark);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return extentReports;
    }

    public ExtentTest test(String createTest,Status status,String... args){
        test = extentReports.createTest(createTest);
        test.log(Status.INFO, PropertyUtil.getProperty("browser") + " browser opening");
        test.log(Status.INFO,createTest+" Website Open");
        test.log(Status.INFO, PropertyUtil.getProperty("facebook.url"));

        for(String argument : args) {
            test.log(status, argument);
        }
        return test;
    }

    public ExtentTest testWithScreenShot(WebDriver driver,String screenShotName,String createTest,Status status,String... args){
        test = extentReports.createTest(createTest);
        test.log(Status.INFO, PropertyUtil.getProperty("browser") + " browser opening");
        test.log(Status.INFO,screenShotName+" Website Open");
        test.log(Status.INFO, PropertyUtil.getProperty(""+screenShotName.toLowerCase()+".url"));

        for(String argument : args) {
            test.log(status, argument);
        }

        try {
            test.addScreenCaptureFromPath(getScreenShot(driver,screenShotName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return test;
    }

    private String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //after execution, you could see a folder "ScreenshotsReports" under src folder
        String destination = IPath.RESOURCES_PATH+"assets\\ScreenshotsReports\\"+screenshotName+"_"+dateName+".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        return destination;
    }

    public void reportFlush(){
        extentReports.flush();
    }
}