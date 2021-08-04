package com.tests;

import java.io.File;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Home_Pages;
import com.pages.Login_Pages;
import com.utilities.ReadExcelFile;
import com.utilities.ReadPropertiesFile;

public class CerebraHealthTest extends Driver {
	public static final String filename = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(filename);
	public static XSSFSheet sheet;
	public static Login_Pages login_pages;
	public static XSSFWorkbook wb;
	public static XSSFCell cell;
	public static Home_Pages home_pages;

	@BeforeClass
	public void init() {
		login_pages = new Login_Pages(driver);
		Driver.init(prop.getProperty("Browser"));
		login_pages.navigateTo_LoginUI();
	}

	@Test(priority = 1)
	public void loginForMultipleUsers() throws Throwable {
		login_pages = new Login_Pages(driver);
		sheet = ReadExcelFile.readExcel(
				System.getProperty("user.dir") + "\\testData\\QA Assignment-V3 - Multiple Data sheet.xlsx", "Sheet1");
		// get all rows in the sheet
		int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		for (int i = 1; i <= rowCount; i++) {
			String username = sheet.getRow(i).getCell(0).getStringCellValue();
			System.out.println(username);
			String password = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println(password);
			login_pages.email_Input_Field(username);
			login_pages.password_Input_Field(password);
			login_pages.login_Button();
			if (login_pages.alert_message().equals("Invalid Email or password.")) {
				login_pages.email_Input_Field_Clear();
				login_pages.password_Input_Field_Clear();
				cell = sheet.getRow(i).createCell(2);
				cell.setCellValue("FAIL");
			} else {
				cell.setCellValue("PASS");
			}
		}
	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterClass
	public void quit() {
		driver.quit();
	}
}
