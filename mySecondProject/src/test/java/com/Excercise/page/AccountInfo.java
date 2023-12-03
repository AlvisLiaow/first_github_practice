package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;

import com.second.project.library.Base1;
import com.second.project.library.ExcelManager;

public class AccountInfo extends Base1{
	public static final Logger log = LogManager.getLogger(AccountInfo.class);
	private WebDriver driver;
	private WebElement enterAccInfo;
	private String ExcelPath1 = "src/test/resources/AccountInfo.xlsx"; 
	
	public AccountInfo(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		enterAccInfo = mylibrary.waitForVisibility(By.cssSelector("div.login-form > h2 > b"));
		assertNotNull(enterAccInfo);
	}

	
	
	public AccountInfo fillingAccInfo(String gender, String name, String password,
			String birthDay, String birthMonth, String birthYear) {
		if(gender.contains("Mr.")) {
			mylibrary.clickAnElement(By.id("id_gender1"));
		}else {
			mylibrary.clickAnElement(By.id("id_gender2"));
		}
		mylibrary.enterText(name,By.id("name"));
		WebElement passwordElem = driver.findElement(By.id("password"));
		mylibrary.scrollIntoCenter(passwordElem);
		mylibrary.enterText(By.id("password"), password);
		mylibrary.selectDropdownElem(By.id("days"), birthDay);
		mylibrary.selectDropdownElem(By.id("months"), birthMonth);
		mylibrary.selectDropdownElem(By.id("years"), birthYear);
		log.info("Filling up acount Info...");
		return this;
	}
	
	public AccountInfo selectCheckbox(boolean newsletter, boolean specialOffer) {
		mylibrary.handleCheckBox(By.id("newsletter"), newsletter);
		mylibrary.handleCheckBox(By.id("optin"), specialOffer);
		log.info("Signing up newsletter and receive special offer...");
		return this;
	}
	
	public AccountInfo fillDetail(String firstName, String lastName, String company, String address,
			String country, String state, String city, String zipcod, String MobleNumber) {
		WebElement AccInfo = driver.findElement(By.cssSelector("div > form > h2 > b"));
		mylibrary.scrollIntoCenter(AccInfo);
		mylibrary.enterText(By.id("first_name"), firstName);
		mylibrary.enterText(By.id("last_name"), lastName);
		mylibrary.enterText(By.id("company"), company);
		WebElement addressElem = driver.findElement(By.id("address1"));
		mylibrary.scrollIntoCenter(addressElem);
		mylibrary.enterText(By.id("address1"), address);
		mylibrary.selectDropdownElem(By.id("country"), country);
		WebElement stateElem = driver.findElement(By.id("state"));
		mylibrary.scrollIntoCenter(stateElem);
		mylibrary.enterText(By.id("state"), state);
		mylibrary.enterText(By.id("city"), city);
		mylibrary.enterText(By.id("zipcode"), zipcod);
		mylibrary.enterText(By.id("mobile_number"), MobleNumber);
		log.info("Filling up detail Info...");
		
		return this;
	}
	
	public AccountCreatedPage creatAcc () {
		mylibrary.clickHidenElement(By.cssSelector("div > div.login-form > form > button"));
		log.info("Navigate to account created page..");
		return new AccountCreatedPage(driver);
	}

}
