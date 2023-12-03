package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.second.project.library.Base1;

public class PaymentPage extends Base1 {
	public static final Logger log = LogManager.getLogger(PaymentPage.class);
	private WebDriver driver;

	public PaymentPage(WebDriver driver) {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}
		WebElement paymentElem = driver.findElement(By.cssSelector("#cart_items > div > div.payment-information"));
		assertNotNull(paymentElem);
	}

	public PaymentPage paymentDetail(String name, String number, String cvc, String exMonth, String exYear) {
		log.info("filling the payment detail..");
		mylibrary.enterText(By.name("name_on_card"), name);
		mylibrary.enterText(By.name("card_number"), number);
		mylibrary.enterText(By.name("cvc"), cvc);
		mylibrary.enterText(By.name("expiry_month"), exMonth);
		mylibrary.enterText(By.name("expiry_year"), exYear);
		
		return this;
	}

	public PaymentDonePage comfirmBtn() {
		log.info("Navigating to payment done page...");
		String expectedText ="Your order has been placed successfully!";
		mylibrary.clickAnElement(By.cssSelector("#submit"));
		Assert.assertEquals("Your order has been placed successfully!",expectedText);
		return new PaymentDonePage(driver);
	}
}
