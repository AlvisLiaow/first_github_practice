package com.Excercise.page;

import static org.testng.Assert.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class CheckoutPage extends Base1 {
	public static final Logger log = LogManager.getLogger(CheckoutPage.class);
	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}

	}

	public CheckoutPage comment(String inputComment) {
		log.info("Writing comment...");
		mylibrary.waitForVisibility(By.cssSelector(("#address_delivery")));
		mylibrary.waitForVisibility(By.cssSelector("#address_invoice"));
		WebElement reviewElem = driver.findElement(By.cssSelector("#cart_items > div > div:nth-child(4) > h2"));
		mylibrary.scrollIntoView(reviewElem);

		mylibrary.enterText(By.cssSelector("#ordermsg > textarea"), inputComment);
		return this;
	}

	public PaymentPage placeAnOrder() {
		log.info("Navigating to the payment page...");
		mylibrary.clickAnElement(By.cssSelector("#cart_items > div > div:nth-child(7) > a"));
		return new PaymentPage(driver);
	}

	public CheckoutPage verifyDeleveryAddressFilled(String name, String company, String adress,
			String cityAndZipCode, String country, String tel) {
		WebElement addressElem = driver.findElement(By.id("address_delivery"));
		log.info("Text: " + addressElem.getText());
		log.info("Veriying the delivery detail...");
		WebElement nameElem = driver
				.findElement(By.cssSelector("#address_delivery > li.address_firstname.address_lastname"));
		assertEquals(nameElem.getText().toLowerCase().contains(name), true);
		WebElement companyElem = driver.findElement(By.cssSelector("#address_delivery > li:nth-child(3)"));
		assertEquals(companyElem.getText().toLowerCase().contains(company), true);
		WebElement addressElem1 = driver.findElement(By.cssSelector("#address_delivery > li:nth-child(4)"));
		assertEquals(addressElem1.getText().toLowerCase().contains(adress), true);
		WebElement cityAndZipCodeElem = driver
				.findElement(By.cssSelector("#address_delivery > li.address_city.address_state_name.address_postcode"));
		assertEquals(cityAndZipCodeElem.getText().toLowerCase().contains(cityAndZipCode), true);
		WebElement countryElem = driver.findElement(By.cssSelector("#address_delivery > li.address_country_name"));
		assertEquals(countryElem.getText().toLowerCase().contains(country), true);
		WebElement telElem = driver.findElement(By.cssSelector("#address_delivery > li.address_phone"));
		assertEquals(telElem.getText().contains(tel), true);

		return this;
	}
	
	public CheckoutPage verifyBillingAddressFilled(String name, String company, String adress,
			String cityAndZipCode, String country, String tel) {
		WebElement addressElem = driver.findElement(By.id("address_invoice"));
		log.info("Text: " + addressElem.getText());
		log.info("Veriying the billing detail...");
		WebElement nameElem = driver
				.findElement(By.cssSelector("#address_invoice > li.address_firstname.address_lastname"));
		assertEquals(nameElem.getText().toLowerCase().contains(name), true);
		WebElement companyElem = driver.findElement(By.cssSelector("#address_invoice > li:nth-child(3)"));
		assertEquals(companyElem.getText().toLowerCase().contains(company), true);
		WebElement addressElem1 = driver.findElement(By.cssSelector("#address_invoice > li:nth-child(4)"));
		assertEquals(addressElem1.getText().toLowerCase().contains(adress), true);
		WebElement cityAndZipCodeElem = driver
				.findElement(By.cssSelector("#address_invoice > li.address_city.address_state_name.address_postcode"));
		assertEquals(cityAndZipCodeElem.getText().toLowerCase().contains(cityAndZipCode), true);
		WebElement countryElem = driver.findElement(By.cssSelector("#address_invoice > li.address_country_name"));
		assertEquals(countryElem.getText().toLowerCase().contains(country), true);
		WebElement telElem = driver.findElement(By.cssSelector("#address_invoice > li.address_phone"));
		assertEquals(telElem.getText().contains(tel), true);

		return this;
	}
}
