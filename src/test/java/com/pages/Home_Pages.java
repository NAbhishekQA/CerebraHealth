package com.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Home_Pages extends Driver{
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);

	@FindBy(xpath = "//a[contains(text(),'Inventory')]")
	WebElement inventory_dropdown_field;
	
	@FindBy(xpath = "//a[contains(text(),'Manage Warehouses')]")
	WebElement manage_warehouses_field;
	
	@FindBy(xpath = "//a[contains(text(),'New')]")
	WebElement newButton;
	
	@FindBy(xpath = "//a[contains(text(),'Edit')]")
	WebElement editButton;
	
	@FindBy(xpath = "//input[@type='submit'][@name='commit']")
	WebElement updateInventory_button;
	
	@FindBy(id = "warehouse_name")
	WebElement warehouseName_Field;
	
	@FindBy(id = "navbarDropdown")
	WebElement navBar;
	
	@FindBy(xpath = "//*[@id='navbarSupportedContent']/div/div/div/a[2]/span")
	WebElement logout_button;
	
	@FindBy(id = "warehouse_address")
	WebElement warehouseAddress_Field;
	
	@FindBy(id = "warehouse_city")
	WebElement warehouseCity_Field;
	
	@FindBy(id = "warehouse_province")
	WebElement warehouseProvince_Field;
	
	@FindBy(id = "warehouse_postal_code")
	WebElement warehousePostalCode_Field;
	
	@FindBy(xpath = "//*[@id='error_explanation']/div[2]/ul/li")
	WebElement warehouseName_Error_Msg;
	
	@FindBy(id = "notice")
	WebElement warehouseName_Success_Msg;
	
	@FindBy(xpath = "//*[@type='submit'][@name='commit']")
	WebElement create_Warehouse_button;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/p[2]")
	WebElement name_Field_Validation;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/p[3]")
	WebElement address_Field_Validation;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/p[5]")
	WebElement city_Field_Validation;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/p[6]")
	WebElement province_Field_Validation;
	
	@FindBy(xpath = "/html/body/div[1]/div[3]/p[7]")
	WebElement postalcode_Field_Validation;
	
	public Home_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void inventory_field() {
		inventory_dropdown_field.click();
	}
	
	public void manage_WareHouses() {
		Actions a = new Actions(driver);
		a.moveToElement(manage_warehouses_field).click().build().perform();
	}
	
	public void new_Button() {
		newButton.click();
	}
	
	public void wareHouse_nameField(String value) {
		warehouseName_Field.sendKeys(value);
	}
	
	public void wareHouse_AddressField(String value) {
		warehouseAddress_Field.sendKeys(value);
	}
	
	public void wareHouse_cityField(String value) {
		warehouseCity_Field.sendKeys(value);
	}
	
	public void wareHouse_ProvinceField(String value) {
		Select select = new Select(warehouseProvince_Field);
		select.selectByValue(value);
	}
	
	public void wareHouse_PostalCodeField(String value) {
		warehousePostalCode_Field.sendKeys(value);
	}
	
	public void create_wareHouse_button() {
		create_Warehouse_button.click();
	}
	
	public String getErrorMsg() {
		return warehouseName_Error_Msg.getText();
	}
	
	public String getSuccessMessage() {
		return warehouseName_Success_Msg.getText();
	}
	
	public String getNameFieldMessage() {
		return name_Field_Validation.getText();
	}
	
	public String getAddressFieldMessage() {
		return address_Field_Validation.getText();
	}
	
	public String getCityFieldMessage() {
		return city_Field_Validation.getText();
	}
	
	public String getProvinceFieldMessage() {
		return province_Field_Validation.getText();
	}
	
	public String getPostalFieldMessage() {
		return postalcode_Field_Validation.getText();
	}
	
	public String wareHouse_getnameField() {
		return warehouseName_Field.getAttribute("value");
	}
	
	public String wareHouse_getAddressField() {
		return warehouseAddress_Field.getAttribute("value");
	}
	
	public String wareHouse_getcityField() {
		return warehouseCity_Field.getAttribute("value");
	}
	
	public WebElement wareHouse_getProvinceField() {
		Select select = new Select(warehouseProvince_Field);
		return select.getFirstSelectedOption();
	}
	
	public String wareHouse_getPostalCodeField() {
		return warehousePostalCode_Field.getAttribute("value");
	}
	
	public void editButton_field() {
		editButton.click();
	}
	
	public void updateInventoryButton_field() {
		updateInventory_button.click();
	}
	
	public void logout() {
		navBar.click();
		Actions a = new Actions(driver);
		a.moveToElement(logout_button).click().build().perform();
	}
}
