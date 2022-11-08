package org.selenium.utils;

import org.selenium.constants.IPath;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelUtil {

    private static XSSFWorkbook workbook = null;
    private static XSSFSheet sheet = null;
    private static File file = null;
    private static FileInputStream fis = null;
    private static Map<Object, Object> map = null;

    public static Object[][] specifiedExcel(String sheetName, String excelFileName) {
        Object[][] data = null;

        try {
            file = new File(IPath.RESOURCES_PATH+"assets\\excel\\" + excelFileName);
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum() + 1;
            int columnCount = sheet.getRow(0).getLastCellNum();

            data = new String[rowCount - 1][columnCount];

            for (int row = 1; row < rowCount; row++) {
                Row row1 = sheet.getRow(row);
                for (int col = 0; col < columnCount; col++) {
                    Cell cell = row1.getCell(col);
                    data[row - 1][col] = cell.toString();
                }
            }
            workbook.close();
        } catch (Exception e) {
            System.out.println("Exception Data : " + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }


    public static Map<Object, Object> readAllData(String sheetName, String excelFileName) {

        try {
            file = new File(IPath.RESOURCES_PATH+"assets\\excel\\" + excelFileName);
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            map = new TreeMap<>(Collections.reverseOrder().reversed());

            int rowCount = sheet.getLastRowNum() + 1;
            int colCount = sheet.getRow(0).getLastCellNum();
            int count = 0;
            for (int row = 1; row < rowCount; row++) {
                count++;
                Row row1 = sheet.getRow(row - count);
                for (int col = 0; col < colCount; col++) {
                    if (row1.getCell(col).getStringCellValue().equalsIgnoreCase("user") || row1.getCell(col).getStringCellValue().equalsIgnoreCase("password")) {
                        map.put(row1.getCell(col).getStringCellValue() + count, sheet.getRow(row).getCell(col).getStringCellValue());
                    }
                }
            }
            workbook.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Exception Data : " + e.getMessage());
            e.printStackTrace();
        }
        return map;
    }
}