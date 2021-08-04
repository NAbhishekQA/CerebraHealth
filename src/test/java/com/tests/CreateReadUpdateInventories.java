package com.tests;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Home_Pages;
import com.pages.Login_Pages;
import com.utilities.ReadPropertiesFile;

public class CreateReadUpdateInventories extends Driver {
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
		home_pages = new Home_Pages(driver);
		Driver.init(prop.getProperty("Browser"));
		login_pages.navigateTo_LoginUI();
	}

	@Test(priority = 2)
	public void createReadandUpdateNewWarehouse() {
		login_pages = new Login_Pages(driver);
		home_pages = new Home_Pages(driver);
		login_pages.email_Input_Field("gurdeep.singh@cerebrahealth.com");
		login_pages.password_Input_Field("Manveer@7");
		login_pages.login_Button();
		home_pages.inventory_field();
		home_pages.manage_WareHouses();
		home_pages.new_Button();
		home_pages.wareHouse_AddressField("unit B - 1470 Willson Pl");
		home_pages.wareHouse_cityField("Winnipeg");
		home_pages.wareHouse_ProvinceField("MB");
		home_pages.wareHouse_PostalCodeField("R3T 3N9");
		home_pages.create_wareHouse_button();
		Assert.assertEquals(home_pages.getErrorMsg(), "Name can't be blank");
		Date d = new Date();
		String s = "Cerebra Health New Warehouse" + d;
		home_pages.wareHouse_nameField(s);
		home_pages.create_wareHouse_button();
		Assert.assertEquals(home_pages.getSuccessMessage(), "Warehouse was successfully created.");
		Assert.assertEquals(home_pages.getNameFieldMessage(), "Name: Cerebra Health New Warehouse" + d);
		Assert.assertEquals(home_pages.getAddressFieldMessage(), "Address: unit B - 1470 Willson Pl");
		Assert.assertEquals(home_pages.getCityFieldMessage(), "City: Winnipeg");
		Assert.assertEquals(home_pages.getProvinceFieldMessage(), "Province: MB");
		Assert.assertEquals(home_pages.getPostalFieldMessage(), "Postal code: R3T 3N9");
		home_pages.editButton_field();
		Assert.assertEquals(home_pages.wareHouse_getnameField(), "Cerebra Health New Warehouse" + d);
		Assert.assertEquals(home_pages.wareHouse_getAddressField(), "unit B - 1470 Willson Pl");
		Assert.assertEquals(home_pages.wareHouse_getcityField(), "Winnipeg");
		Assert.assertEquals(home_pages.wareHouse_getPostalCodeField(), "R3T 3N9");
		home_pages.wareHouse_nameField("Cerebra Health New Warehouse Updated");
		home_pages.wareHouse_AddressField("unit B - 1470 Willson Pl Updated");
		home_pages.wareHouse_cityField("Winnipeg Updated");
		home_pages.updateInventoryButton_field();
		Assert.assertEquals(home_pages.getSuccessMessage(), "Warehouse was successfully updated.");
		home_pages.logout();
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
