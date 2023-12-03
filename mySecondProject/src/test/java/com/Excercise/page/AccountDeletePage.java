package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class AccountDeletePage extends Base1{
	public static final Logger log = LogManager.getLogger(AccountDeletePage.class);
	private WebDriver driver;
	
	public AccountDeletePage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		WebElement accDeletedElem = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2/b"));
		assertNotNull(accDeletedElem);
	}
	
	public void clickContinueBtnDone() {
		mylibrary.clickAnElement(By.cssSelector("#form > div > div > div > div > a"));
		log.info("Close the browser...");
	}
}
