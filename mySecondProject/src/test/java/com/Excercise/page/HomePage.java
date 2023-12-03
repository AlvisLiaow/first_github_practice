package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import java.time.Duration;
import java.util.List;

import javax.swing.text.html.CSS;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.second.project.library.Base1;

public class HomePage extends Base1 {
	public static final Logger log = LogManager.getLogger(HomePage.class);
	private WebDriver driver;
	private String websiteURL = "https://automationexercise.com/";
	private WebElement sliderVisibility;

	public HomePage(WebDriver driver) {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		} else {
			this.driver = driver;
		}
		gotoHomePaage();
		sliderVisibility = mylibrary.waitForVisibility(By.id("slider"));
		assertNotNull(sliderVisibility);
	}

	public HomePage() {
		if (driver == null) {
			this.driver = mylibrary.getDriver();
		}
		gotoHomePaage();
		sliderVisibility = mylibrary.waitForVisibility(By.id("slider"));
		assertNotNull(sliderVisibility);
	}

	private void gotoHomePaage() {
		log.info("Opening Chrome browser and navigate to the homepage...");
		driver.get(websiteURL);
	}

	public LoginAndSignUpPage signUpPage() {
		mylibrary.clickAnElement(By.cssSelector("div > ul > li:nth-child(4) > a"));
		log.info("Navigate to the sign-up page..");
		return new LoginAndSignUpPage(driver);
	}

	public AccountDeletePage accountDelete() {
		WebElement logedAsUserElem = driver
				.findElement(By.cssSelector("div > div.col-sm-8 > div > ul > li:nth-child(10) > a"));
		assertNotNull(logedAsUserElem);
		mylibrary.clickAnElement(By.cssSelector("div > div.col-sm-8 > div > ul > li:nth-child(5) > a"));
		log.info("Account is deleted.");
		return new AccountDeletePage(driver);
	}

	public LoginAndSignUpPage loginPage() {
		mylibrary.clickAnElement(By.cssSelector("div > ul > li:nth-child(4) > a"));
		log.info("Navigate to the login page..");
		return new LoginAndSignUpPage(driver);
	}

	public LoginAndSignUpPage logout() {
		log.info("Logout...");
		mylibrary.clickAnElement(By.cssSelector("div.col-sm-8 > div > ul > li:nth-child(4) > a"));
		return new LoginAndSignUpPage(driver);
	}

	public ContactUsPage clickContactUs() {
		log.info("Navigate to contact us...");
		mylibrary.clickAnElement(By.cssSelector("div > div.col-sm-8 > div > ul > li:nth-child(8) > a"));
		return new ContactUsPage(driver);
	}

	public TestCasesPage clickTestCasesPage() {
		log.info("Navigating to test cases page...");
		mylibrary.clickAnElement(By.cssSelector("div.col-sm-8 > div > ul > li:nth-child(5) > a"));

		return new TestCasesPage(driver);
	}

	public ProductsPage clickProductsPage() {
		log.info("Navigating to the products page...");
		mylibrary.clickAnElement(By.cssSelector("div.col-sm-8 > div > ul > li:nth-child(2) > a"));
		return new ProductsPage(driver);
	}

	public HomePage subscription(String email) {
		log.info("enter email and subcribe");
		WebElement emailArea = driver.findElement(By.cssSelector("#footer > div.footer-widget > div > div"));
		mylibrary.scrollIntoCenter(emailArea);
		mylibrary.enterText(By.cssSelector("#susbscribe_email"), email);
		WebElement summitBtn = driver.findElement(By.id("subscribe"));
		summitBtn.click();
		WebElement subscribeMassage = driver.findElement(By.cssSelector("#success-subscribe"));
		mylibrary.highLightElem(subscribeMassage);
		return this;
	}

	public CartPage clickCartBtn() {
		log.info("Navigating to the cart page..");
		mylibrary.clickAnElement(By.cssSelector("div > ul > li:nth-child(3) > a"));
		return new CartPage(driver);
	}

	public ProductDetailPage viewProduct() {
		log.info("View product detail");
		WebElement itemView = driver.findElement(By.className("features_items"));
		mylibrary.scrollIntoCenter(itemView);
		mylibrary.clickAnElement(By.cssSelector("div:nth-child(4) > div > div.choose > ul > li > a"));

		return new ProductDetailPage(driver);
	}

	public CartPage addProductFromHpAndViewCart() {
		log.info("Adding product from the home page and click view cart..");
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

	public HomePage addProductFromHp() {
		log.info("Adding product from the home page..");

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
		mylibrary.clickAnElement(By.cssSelector(" div.modal-footer > button"));
		return this;
	}

	public void categoryBtn(String text) {
		WebElement test = driver.findElement(By.id("accordian"));
		log.info("Text: " + test.getText());
		mylibrary.scrollIntoCenter(test);
		if (text.toLowerCase().contains("women")) {
			mylibrary.clickAnElement(By.cssSelector("#accordian > div:nth-child(1) > div.panel-heading > h4 > a"));
		} else if (text.toLowerCase().contains("men")) {
			mylibrary.clickAnElement(By.cssSelector("#accordian > div:nth-child(2) > div.panel-heading > h4 > a"));
		} else if (text.toLowerCase().contains("kids")) {
			mylibrary.clickAnElement(By.cssSelector("#accordian > div:nth-child(3) > div.panel-heading > h4 > a"));
		} else {
			log.info("Wrong text..");
		}
		log.info("click the category...");

	}

	public CategoryPage womenCategoryBtn(String text) {
		WebElement test = driver.findElement(By.id("Women"));
		log.info("Text: " + test.getText());
		mylibrary.scrollIntoCenter(test);
		if (text.toLowerCase().contains("dress")) {
			mylibrary.clickAnElement(By.cssSelector("#Women > div > ul > li:nth-child(1) > a"));
		} else if (text.toLowerCase().contains("tops")) {
			mylibrary.clickAnElement(By.cssSelector("#Women > div > ul > li:nth-child(2) > a"));
		} else if (text.toLowerCase().contains("saree")) {
			mylibrary.clickAnElement(By.cssSelector("#Women > div > ul > li:nth-child(3) > a"));
		} else {
			log.info("Wrong text..");
		}
		return new CategoryPage(driver);
	}

	public HomePage clickOnBrand(String inputBrand) {
		WebElement test = driver.findElement(By.className("brands-name"));
		mylibrary.scrollIntoCenter(test);
		log.info("Text: " + test.getText());

		if (inputBrand.toLowerCase().contains("polo")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(1) > a"));
		} else if (inputBrand.toLowerCase().contains("h&m")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(2) > a"));
		} else if (inputBrand.toLowerCase().contains("mademe")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(3) > a"));
		} else if (inputBrand.toLowerCase().trim().contains("mast & harbour")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(4) > a"));
		} else if (inputBrand.toLowerCase().contains("babyhug")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(5) > a"));
		} else if (inputBrand.toLowerCase().contains("allen solly junior")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(6) > a"));
		} else if (inputBrand.toLowerCase().contains("kookie kids")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(7) > a"));
		} else if (inputBrand.toLowerCase().contains("biba")) {
			mylibrary.clickAnElement(By.cssSelector("div.brands_products > div > ul > li:nth-child(8) > a"));
		} else {
			log.info("Wrong text..");
		}
		WebElement brandProduct = driver
				.findElement(By.cssSelector("body > section > div > div.row > div.col-sm-9.padding-right"));
		assertNotNull(brandProduct);
		return this;
	}

	// String inputItem
	public CartPage addProductFromRecomendedItem(String inputItem) {
		WebElement element;
		WebElement recommendedMessage = driver.findElement(By.cssSelector(
				"body > section:nth-child(3) > div.container > div > div.col-sm-9.padding-right > div.recommended_items"));
		assertNotNull(recommendedMessage);
		mylibrary.scrollIntoCenter(recommendedMessage);
		log.info("Text: " + recommendedMessage.getText());
		WebElement container = driver.findElement(By.className("recommended_items"));
		Actions actions = new Actions(driver);
		log.info("input item...");
		if (inputItem.toLowerCase().contains("stylish dress")) {
			element = container.findElement(By.cssSelector("div.item.active > div:nth-child(1) > div > div > div > a"));
			actions.moveToElement(element).click().build().perform();
		}else if(inputItem.toLowerCase().contains("winter top")) {
			element = container.findElement(By.cssSelector("div.item.active > div:nth-child(2) > div > div > div > a"));
			actions.moveToElement(element).click().build().perform();
		}else if(inputItem.toLowerCase().contains("summer white top")) {
			element = container.findElement(By.cssSelector("div.item.active > div:nth-child(3) > div > div > div > a"));
			actions.moveToElement(element).click().build().perform();
		}else {
			log.info("Item has not initialize yet...");
		}
		log.info("Navigating to cart page..");
		mylibrary.clickAnElement(By.cssSelector("p:nth-child(2) > a"));
		
		return new CartPage(driver);
	}
	
	public HomePage scrollToSubcribe() {
		WebElement emailArea = driver.findElement(By.cssSelector("#footer > div.footer-widget > div > div"));
		mylibrary.scrollIntoCenter(emailArea);
		return this;
	}
	
	public HomePage scrollToTopWithArrow() {
		log.info("Total Iframe: " +  mylibrary.getAllIframes());
		WebElement test = driver.findElement(By.cssSelector("body > ins:nth-child(16)"));
		mylibrary.highLightElem(test);
		log.info("Scroll to the bottom..");
		mylibrary.clickAnElement(By.cssSelector("#scrollUp"));
		gotoHomePaage();
		log.info("Scroll it back to the top..");
		sliderVisibility = mylibrary.waitForVisibility(By.id("slider"));
		assertNotNull(sliderVisibility);
		return this;
	}
	
	public HomePage scrollToTopWithOutArrow() {
		sliderVisibility = mylibrary.waitForVisibility(By.id("slider"));
		mylibrary.scrollIntoCenter(sliderVisibility);
		assertNotNull(sliderVisibility);
		return this;
	}
}
