package com.pages;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Login_Pages extends Driver{
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);

	@FindBy(id = "user_email")
	WebElement email_Field;
	
	@FindBy(id = "user_password")
	WebElement password_Field;
	
	@FindBy(xpath = "//input[@type='submit'][@name='commit'][@value='Log in']")
	WebElement login_button;
	
	@FindBy(xpath = "//div[contains(text(),'Invalid Email or password')]")
	WebElement alert_msg;

	public Login_Pages(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateTo_LoginUI() {
		driver.get(prop.getProperty("URL"));
	}

	public void email_Input_Field(String value) {
		email_Field.sendKeys(value);
	}
	
	public void email_Input_Field_Clear() {
		email_Field.clear();
	}
	
	public void password_Input_Field(String value) {
		password_Field.sendKeys(value);
	}
	
	public void password_Input_Field_Clear() {
		password_Field.clear();
	}
	
	public void login_Button() {
		login_button.click();
	}
	
	public String alert_message() {
		return alert_msg.getText();
	}
}
