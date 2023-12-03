package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class TestCasesPage extends Base1{
	
	public static final Logger log = LogManager.getLogger(TestCasesPage.class);
	private WebDriver driver;
	
	public TestCasesPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		log.info("Total Iframe: " + mylibrary.getAllIframes());
		
		driver.switchTo().frame("aswift_6");
		driver.switchTo().frame("ad_iframe");
		WebElement adPossitionElem = driver.findElement(By.cssSelector("body"));
		assertNotNull(adPossitionElem);
		mylibrary.highLightElem(adPossitionElem);
		mylibrary.clickHidenElement(By.cssSelector("#dismiss-button"));
		WebElement testCaseElem = driver.findElement(By.cssSelector("div.row > div > h2 > b"));
		assertNotNull(testCaseElem);
		driver.switchTo().defaultContent();
	}
	
		
	
}
