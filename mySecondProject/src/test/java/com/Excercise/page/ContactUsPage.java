package com.Excercise.page;

import static org.testng.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.second.project.library.Base1;

public class ContactUsPage extends Base1{
	public static final Logger log = LogManager.getLogger(ContactUsPage.class);
	private WebDriver driver;
	
	public ContactUsPage(WebDriver driver) {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}else {
			this.driver = driver;
		}
		WebElement getInTouchElem = driver.findElement(By.cssSelector("div.col-sm-8 > div > h2"));
		assertNotNull(getInTouchElem);
	}
	
	public ContactUsPage() {
		if(driver == null) {
			this.driver = mylibrary.getDriver();
		}
		WebElement getInTouchElem = driver.findElement(By.cssSelector("div.col-sm-8 > div > h2"));
		assertNotNull(getInTouchElem);
	}
	
	public ContactUsPage fillUpTheForm(String name, String email, 
			String subject, String message, String filePath) {
		log.info("Filling up the form..");
		mylibrary.enterText(By.name("name"), name);
		mylibrary.enterText(By.name("email"), email);
		WebElement subjectElem = driver.findElement(By.name("subject"));
		mylibrary.scrollIntoCenter(subjectElem);
		mylibrary.enterText(By.name("subject"), subject);
		mylibrary.enterText(By.name("message"), message);
		mylibrary.fileUpload(By.name("upload_file"), filePath);
		return this;
	}
	
	public void clickSubmitBtn() {
		log.info("Click the submit");
		mylibrary.clickAnElement(By.name("submit"));
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	
	public HomePage clickBackToHome() {
		log.info("verify the successful message and navigate back to homepage...");
		WebElement successMessage = driver.findElement(By.cssSelector("div.col-sm-8 > div > div.status.alert.alert-success"));
		mylibrary.scrollIntoCenter(successMessage);
		assertNotNull(successMessage);
		mylibrary.clickAnElement(By.cssSelector("a > span"));
		return new HomePage();
	}
}
