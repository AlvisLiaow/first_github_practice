package com.Excercise.page;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class CategoryPage extends Base1 {
	public static Logger log = LogManager.getLogger(CategoryPage.class);
	public WebDriver driver;

	public CategoryPage(WebDriver driver) {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}

		int count = 0;
		List<WebElement> allList = driver.findElements(By.tagName("iframe"));
		for (WebElement list : allList) {
			log.info("total Iframe: " + "(" + count + ")" + list.getSize());
			count++;
		}
		driver.switchTo().frame("aswift_6");
		driver.switchTo().frame("ad_iframe");

		WebElement adPossitionElem = driver.findElement(By.cssSelector("body"));
		assertNotNull(adPossitionElem);
		mylibrary.highLightElem(adPossitionElem);
		mylibrary.clickHidenElement(By.cssSelector("#dismiss-button"));

		WebElement itemElem = driver
				.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right"));
		assertNotNull(itemElem);

	}

	public CategoryPage womenTopsModule() {
		WebElement test = driver
				.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right > div > h2"));
		String topText = test.getText();
		log.info("Text: " + topText);
		assertEquals(topText, "WOMEN - TOPS PRODUCTS");

		return this;
	}

	public CategoryPage menCategoryBtn(String text) {
		WebElement test = driver.findElement(By.id("accordian"));
		log.info("Text: " + test.getText());
		mylibrary.scrollIntoCenter(test);
		if (text.toLowerCase().contains("tshirts")) {
			mylibrary.clickAnElement(By.cssSelector("#Men > div > ul > li:nth-child(1) > a"));
		} else if (text.toLowerCase().contains("jeans")) {
			mylibrary.clickAnElement(By.cssSelector("#Men > div > ul > li:nth-child(2) > a"));
		} else {
			log.info("Wrong text..");
		}
		return this;
	}

	public CategoryPage menJeansModule() {
		WebElement test = driver.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right > div > h2"));
		String jeansText = test.getText();
		log.info("Text: " + jeansText);
		assertNotNull(jeansText);

		return this;
	}
}
