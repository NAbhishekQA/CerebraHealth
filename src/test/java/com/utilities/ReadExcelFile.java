package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static XSSFSheet sheet;
	public static XSSFWorkbook wb;

	public static XSSFSheet readExcel(String Path, String SheetName) throws IOException {
		try {
			// Create an object of File class to open xlsx file
			File file = new File(Path);

			// Create an object of FileInputStream class to read excel file
			FileInputStream inputStream = new FileInputStream(file);

			// Creating workbook instance that refers to .xlsx file
			wb = new XSSFWorkbook(inputStream);

			sheet = wb.getSheet(SheetName);

		} catch (Exception e) {
			System.out.println(e);
		}
		return sheet;
	}
}
