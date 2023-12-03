package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import java.time.Duration;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v116.page.model.FrameDetached;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.second.project.library.Base1;

public class ProductsPage extends Base1 {
	public static final Logger log = LogManager.getLogger(ProductsPage.class);
	private WebDriver driver;

	public ProductsPage(WebDriver driver) {

		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}
		log.info("Total Iframe: " + mylibrary.getAllIframes());

		driver.switchTo().frame("aswift_6");
		driver.switchTo().frame("ad_iframe");

		WebElement adPossitionElem = driver.findElement(By.cssSelector("body"));
		assertNotNull(adPossitionElem);
		mylibrary.highLightElem(adPossitionElem);
		mylibrary.clickHidenElement(By.cssSelector("#dismiss-button"));
		driver.switchTo().defaultContent();
	}

	public ProductDetailPage viewProduct() {
		mylibrary.delay(1);
		WebElement searchElem = driver.findElement(By.cssSelector("#search_product"));
		assertNotNull(searchElem);
		mylibrary.scrollIntoCenter(searchElem);
		mylibrary.clickAnElement(By.xpath("/html/body/section[2]/div[1]/div/div[2]/div/div[2]/div/div[2]/ul/li/a"));
		log.info("Navigating to the product detail page..");
		return new ProductDetailPage(driver);
	}

	public SearchedProductPage searchProduct(String inputName) {

		mylibrary.enterText(By.cssSelector("#search_product"), inputName);
		log.info("insert product name: " + inputName);
		mylibrary.clickAnElement(By.id("submit_search"));
		log.info("Navigating to the product searched page...");
		return new SearchedProductPage(driver);
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
		mylibrary.clickAnElement(
				By.cssSelector("div:nth-child(4) > div > div.single-products > div.product-overlay > div > a"));
		mylibrary.clickAnElement(By.cssSelector("p:nth-child(2) > a"));
		log.info("Navigating to the cart page...");
		return new CartPage(driver);

	}
}
