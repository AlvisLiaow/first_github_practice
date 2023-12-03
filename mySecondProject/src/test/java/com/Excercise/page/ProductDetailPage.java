package com.Excercise.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class ProductDetailPage extends Base1{
	public static final Logger log = LogManager.getLogger(ProductDetailPage.class);
	private WebDriver driver;
	
	
	public ProductDetailPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		log.info("Verify the detail visibility...");
		WebElement productInfo = driver.findElement(By.cssSelector("div.product-details > div.col-sm-7 > div"));
		assertNotNull(productInfo);
		mylibrary.waitForVisibility(By.cssSelector("div.col-sm-7 > div > h2"));
		mylibrary.waitForVisibility(By.cssSelector(" div.col-sm-7 > div > p:nth-child(3)"));
		mylibrary.waitForVisibility(By.cssSelector("div.col-sm-7 > div > span > span"));
		mylibrary.waitForVisibility(By.cssSelector("div.col-sm-7 > div > p:nth-child(6) > b"));
		mylibrary.waitForVisibility(By.cssSelector("div.col-sm-7 > div > p:nth-child(7) > b"));
		mylibrary.waitForVisibility(By.cssSelector("div.col-sm-7 > div > p:nth-child(8) > b"));
	}
	
	public CartPage increase(String quantity) {
		log.info("increasing product");
		mylibrary.enterText( quantity, By.id("quantity"));
		mylibrary.clickAnElement(By.cssSelector("div.col-sm-7 > div > span > button"));
		mylibrary.clickAnElement(By.cssSelector("p:nth-child(2) > a"));
		return new CartPage(driver);
	}
	
	public ProductDetailPage comment(String name, String email, String inputComment) {
		log.info("Giving a comment..");
		WebElement reviewElem = driver.findElement(By.cssSelector("body > section > div > div > div.col-sm-9.padding-right > div.category-tab.shop-details-tab > div.col-sm-12 > ul > li > a"));
		assertNotNull(reviewElem);
		mylibrary.scrollIntoView(reviewElem);
		mylibrary.enterText(By.id("name"), name);
		mylibrary.enterText(By.id("email"), email);
		mylibrary.enterText(By.id("review"), inputComment);
		return this;
	}
	
	public ProductDetailPage summit() {
		log.info("Click summit");
		mylibrary.clickAnElement(By.id("button-review"));
		WebElement thankYouMessage = driver.findElement(By.cssSelector("#review-section > div > div"));
		log.info("text: " + thankYouMessage.getText());
		assertEquals(thankYouMessage.getText(), "Thank you for your review.");
		return this;
	}

}
