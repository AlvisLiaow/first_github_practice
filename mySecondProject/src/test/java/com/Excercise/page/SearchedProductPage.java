package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.second.project.library.Base1;

public class SearchedProductPage extends Base1{
	public static final Logger log = LogManager.getLogger(SearchedProductPage.class);
	private WebDriver driver;
	
	public SearchedProductPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		WebElement searchedElem = driver.findElement(By.cssSelector("div.col-sm-9.padding-right > div > h2"));
		assertNotNull(searchedElem);
		mylibrary.highLightElem(searchedElem);
		List<WebElement> itemCol = driver.findElements(By.className("col-sm-4"));
		WebElement iteamArea = driver.findElement(By.className("features_items"));
		mylibrary.scrollIntoCenter(iteamArea);
		for(WebElement allItem : itemCol) {
			mylibrary.highLightElem(allItem);
			mylibrary.scrollIntoCenter(allItem);
		}
	}
	
	public CartPage addProductToCart() {
		
		log.info("Add product to the cart..");
		Actions actions = new Actions(driver);
		WebElement iteamArea = driver.findElement(By.className("features_items"));
		mylibrary.scrollIntoCenter(iteamArea);
		List<WebElement> itemCol = driver.findElements(By.className("col-sm-4"));
		WebElement firstProduct = itemCol.get(1);
		WebElement secondProduct = itemCol.get(2);
		mylibrary.scrollIntoView(firstProduct);
		actions.moveToElement(firstProduct).build().perform();
		mylibrary.clickAnElement(By.cssSelector(" div.product-overlay > div > a"));
		mylibrary.clickAnElement(By.cssSelector(" div.modal-footer > button"));
		actions.moveToElement(secondProduct).build().perform();
		mylibrary.clickAnElement(By.cssSelector("div:nth-child(4) > div > div.single-products > div.product-overlay > div > a"));
		mylibrary.clickAnElement(By.cssSelector(" div.modal-footer > button"));
		mylibrary.clickAnElement(By.cssSelector("div > ul > li:nth-child(3) > a"));
		log.info("Navigating to the cart page...");
		return new CartPage(driver);
		
	}
}
