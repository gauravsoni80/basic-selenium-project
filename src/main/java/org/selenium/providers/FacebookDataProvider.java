package org.selenium.providers;

import org.selenium.constants.IExcelPath;
import org.selenium.constants.IExcelSheet;
import org.selenium.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

public class FacebookDataProvider {

    @DataProvider(name = "fb_login")
    public Object[][] loginUser() {
        Object[][] obj = ExcelUtil.specifiedExcel(IExcelSheet.SHEET1, IExcelPath.FACEBOOK_LOGIN_XLSX);
        return obj;
    }
}