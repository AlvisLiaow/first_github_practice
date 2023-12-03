package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.second.project.library.Base1;

public class PaymentDonePage extends Base1{
	public static final Logger log = LogManager.getLogger(PaymentPage.class);
	private WebDriver driver;
	
	public  PaymentDonePage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		mylibrary.delay(1);
	}
	
	public PaymentDonePage verifyPlacedOrder() {
		log.info("Verifying succesfully Massage..");
		String successMasage = "Congratulations! Your order has been confirmed!";
		Assert.assertEquals("Congratulations! Your order has been confirmed!",successMasage);
		return this;
	}
	
	public AccountDeletePage deleteAcc() {
		WebElement logedAsUserElem = driver.findElement(By.cssSelector("div > div.col-sm-8 > div > ul > li:nth-child(10) > a"));
		assertNotNull(logedAsUserElem);
		mylibrary.clickAnElement(By.cssSelector("div > div.col-sm-8 > div > ul > li:nth-child(5) > a"));
		log.info("Account is deleted.");
		return new AccountDeletePage(driver);
	}
	
	public PaymentDonePage downloadInvoice() {
		log.info("download the invoice...");
		mylibrary.clickAnElement(By.cssSelector("#form > div > div > div > a"));
		return this;
	}
	
	public HomePage clickContinueBtn() {
		log.info("continue..");
		mylibrary.clickAnElement(By.cssSelector("#form > div > div > div > div > a"));
		return new HomePage(driver);
	}
	
	
}
