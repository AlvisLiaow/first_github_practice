package com.second.project.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.second.project.library.GlobalSeleniumLibrary.Browsers;

public class Base1 {
	public static final Logger log = LogManager.getLogger(Base1.class);
	
	public WebDriver driver;
	public static GlobalSeleniumLibrary mylibrary;
	private String configurationFilePath = "src/test/resources/configuration.properties";
	private String browserType;
	private String isHeadless;
	private String isScreenshotCapture;
	private String isDemoString;
	
	@BeforeClass
	public void beforeClass() {
		log.info("Before class...");
		JavaPropertiesManager readProp = new JavaPropertiesManager(configurationFilePath);
		browserType = readProp.readProperty("browser");
		isHeadless = readProp.readProperty("isHeadless");
		isScreenshotCapture = readProp.readProperty("isScreenshotCapture");
		isDemoString = readProp.readProperty("isDemoMode");
	}
	
	@AfterClass
	public void afterClass() {
		
		log.info("After class...");
	}
	
	@BeforeMethod
	public void beforeEachTest() {
		log.info("Before Method...");
		try {
			mylibrary = new GlobalSeleniumLibrary();
			if(isHeadless.toLowerCase().contains("yes")) {
				mylibrary.setHeadless(true);
			}
			if(isDemoString.toLowerCase().contains("on")) {
				mylibrary.setIsDemo(true);
			}
			if(browserType.toLowerCase().contains("chrome")) {
				driver = mylibrary.startABrwoser(Browsers.CHROME);
			}
			else if(browserType.toLowerCase().contains("edge")) {
				driver =mylibrary.startABrwoser(Browsers.EDGE);
			}
			else if(browserType.toLowerCase().contains("firefox")) {
				driver =mylibrary.startABrwoser(Browsers.FIREFOX);
			}
			else if(browserType.toLowerCase().contains("safari")) {
				driver =mylibrary.startABrwoser(Browsers.SAFARI);
			}else {
				log.info("you choose browser type: " + browserType);
				log.info("Current framework do not support this browser type.");
				log.info("Starting default browser chrome...");
				driver = mylibrary.startABrwoser(Browsers.CHROME);
			}
			
		} catch (Exception e) {
			log.error("Error...", e);
		}
	}
	
	@AfterMethod
	public void afterEachTest(ITestResult result) {
		try {
			mylibrary.delay(5);
			if(isScreenshotCapture.toLowerCase().contains("enable")) {
				if(ITestResult.FAILURE == result.getStatus()) {
					mylibrary.takeScreenshot(result.getName());
				}
			}
			if(!(driver == null)) {
				driver.close();
				if(!(driver.getClass().equals(FirefoxDriver.class))) {
					driver.quit();
				}
			}
			
		} catch (Exception e) {
			log.error("Error...", e);
		}
	}
	
	
	
	
}
