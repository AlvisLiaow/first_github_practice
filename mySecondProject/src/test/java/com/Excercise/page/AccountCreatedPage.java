package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class AccountCreatedPage extends Base1{
	public static final Logger log = LogManager.getLogger(AccountCreatedPage.class);
	private WebDriver driver;
	private WebElement accCreatedElem;
	
	public AccountCreatedPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}
		accCreatedElem = mylibrary.waitForVisibility(By.cssSelector("#form > div > div > div > h2 > b"));
		assertNotNull(accCreatedElem);
		
	}
	
	public HomePage clickContinueBtn() {
		mylibrary.clickAnElement(By.cssSelector(" div > a"));
		log.info("Back to the homePage...");
		return new HomePage();
	}

}
