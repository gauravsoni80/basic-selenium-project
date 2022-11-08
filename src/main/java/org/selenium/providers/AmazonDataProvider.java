package org.selenium.providers;

import org.selenium.constants.IExcelPath;
import org.selenium.constants.IExcelSheet;
import org.selenium.utils.ExcelUtil;
import org.testng.annotations.DataProvider;

public class AmazonDataProvider {

    @DataProvider(name = "amazon_search")
    public Object[][] amazonDataProvider() {
        Object[][] obj = ExcelUtil.specifiedExcel(IExcelSheet.SHEET1, IExcelPath.AMAZON_XLSX);
        return obj;
    }
}
