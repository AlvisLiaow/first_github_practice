package com.Excercise.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.second.project.library.Base1;

public class CartPage extends Base1 {
	public static final Logger log = LogManager.getLogger(CartPage.class);
	private WebDriver driver;

	public CartPage(WebDriver driver) {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}

		WebElement cartTagElem = driver.findElement(By.cssSelector("div.breadcrumbs > ol > li.active"));
		assertNotNull(cartTagElem);
	}

	public CartPage verifySubscription(String email) {
		log.info("Enter email and use explicit wait for the flash message is visible..");
		mylibrary.enterText(email, By.cssSelector("#susbscribe_email"));
		mylibrary.clickHidenElement(By.cssSelector("#subscribe"));
		mylibrary.waitForVisibility(By.cssSelector("#success-subscribe > div"));
		return this;
	}

	public CartPage verifyProductAndDetail() {
		log.info("Verifying the product..");
		mylibrary.delay(1);
		List<WebElement> productList = driver.findElements(By.className("cart_product"));
		List<WebElement> productPrice = driver.findElements(By.className("cart_price"));
		List<WebElement> productQuantity = driver.findElements(By.className("cart_quantity"));
		List<WebElement> totalPrice = driver.findElements(By.className("cart_total"));
		for (WebElement list : productList) {
			mylibrary.highLightElem(list);
		}
		for (WebElement price : productPrice) {
			mylibrary.highLightElem(price);
		}
		for (WebElement quantity : productQuantity) {
			mylibrary.highLightElem(quantity);
		}
		for (WebElement total : totalPrice) {
			mylibrary.highLightElem(total);
		}

		return this;
	}

	public CartPage verifyQuantity(String inputQuantity) {
		WebElement quantity = driver.findElement(By.cssSelector("td.cart_quantity > button"));
		quantity.getText();
		log.info("quantity: " + quantity.getText());
		assertEquals(quantity.getText(), inputQuantity);
		return this;
	}

	public CheckoutPage proceedCheckOut() {
		log.info("Proceeding to check out...");
		mylibrary.clickAnElement(By.cssSelector("#do_action > div.container > div > div > a"));
		return new CheckoutPage(driver);
	}

	public LoginAndSignUpPage clickResgisterBtn() {
		log.info("Navigating to the resgister page...");
		mylibrary.waitForVisibility(By.partialLinkText("Register / Login"));
		mylibrary.clickAriaHiddenBtn(By.partialLinkText("Register / Login"));
		return new LoginAndSignUpPage(driver);
	}
	
	public CartPage removeProduct() {
		List<WebElement> xBtn = driver.findElements(By.className("cart_quantity_delete"));
		for(WebElement btn: xBtn) {
			btn.click();
		}
		log.info("Removing item...");
		return this;
	}
	
	public CartPage verifyEmptyCartMessage() {
		log.info("Verifying the message...");
		WebElement emptyElem = driver.findElement(By.id("empty_cart"));
		mylibrary.highLightElem(emptyElem);
	
		return this;
	}
	
}
