package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class LoginAndSignUpPage extends Base1{
	public static final Logger log = LogManager.getLogger(LoginAndSignUpPage.class);
	private WebDriver driver;
	private WebElement newUserSignUpElem;
	private WebElement nameElem; 
	
	public LoginAndSignUpPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		newUserSignUpElem = mylibrary.waitForVisibility(By.cssSelector("div:nth-child(3) > div > h2"));
		assertNotNull(newUserSignUpElem);
	}
	
	public LoginAndSignUpPage signup(String name, String email) {
		nameElem = driver.findElement(By.name("name"));
		mylibrary.scrollIntoCenter(nameElem);
		log.info("Filling up the name and email...");
		mylibrary.enterText(By.name("name"), name);
		mylibrary.enterText(By.xpath("/html/body/section/div/div/div[3]/div/form/input[3]"), email);
		return this;
	}
	
	public AccountInfo clickSignUp() {
		mylibrary.clickAnElement(By.cssSelector("div:nth-child(3) > div > form > button"));
		log.info("Navigating to the New Acount Info Page...");
		return new AccountInfo(driver);
	}
	
	 public LoginAndSignUpPage verifyEmailExistMessage() {
		 mylibrary.clickAnElement(By.cssSelector("div:nth-child(3) > div > form > button"));
		 nameElem = driver.findElement(By.name("name"));
		 mylibrary.scrollIntoCenter(nameElem);
		 WebElement existElem = driver.findElement(By.cssSelector("div > div:nth-child(3) > div > form > p"));
		 assertNotNull(existElem);
		 return this;
	 }
	 public LoginAndSignUpPage login(String email, String password) {
			log.info("Filling up the emai and password...");
			mylibrary.enterText(By.cssSelector(" div.col-sm-4.col-sm-offset-1 > div > form > input[type=email]:nth-child(2)"), email);
			mylibrary.enterText(By.name("password"), password);
			return this;
		}
		public void clickLoginBtnAndVarifyTheErroText(){
			log.info("Clicking login button and navigating back to the homepage...");
			mylibrary.clickAnElement(By.cssSelector("div.col-sm-4.col-sm-offset-1 > div > form > button"));
			WebElement errorMessage = driver.findElement(By.cssSelector("div.col-sm-4.col-sm-offset-1 > div > form > p"));
			log.info("Verify the error message...");
			assertNotNull(errorMessage);
		}
		
		public HomePage clickLoginBtn(){
			log.info("Clicking login button and navigating back to the homepage...");
			mylibrary.clickAnElement(By.cssSelector("div.col-sm-4.col-sm-offset-1 > div > form > button"));
			
			return new HomePage();
		}
	 
}
