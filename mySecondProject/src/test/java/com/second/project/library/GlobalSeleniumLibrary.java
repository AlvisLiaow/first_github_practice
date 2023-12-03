package com.second.project.library;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.sql.Timestamp;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GlobalSeleniumLibrary {
	public static final Logger log = LogManager.getLogger(GlobalSeleniumLibrary.class);
	private WebDriver driver;
	private int waitTimeInSecond = 60;
	private boolean isHeadless;
	private boolean isDemo;
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public void setHeadless(boolean isHeadless) {
		this.isHeadless = isHeadless;
	}
	public void setIsDemo(boolean isDemo) {
		this.isDemo = isDemo;
	}
	
	public enum Browsers{
		CHROME, FIREFOX, SAFARI, EDGE
	}
	
	public WebDriver startABrwoser(Browsers browsers) throws Exception {

		switch (browsers) {
		case CHROME:
			startAChromeBrowser();
			break;
		case SAFARI:
			startASafatiBrowser();
			break;
		case FIREFOX:
			startAFireFoxBrowser();
			break;
		case EDGE:
			startAEdgeBrowser();
			break;

		default:
			log.info("Browser selected : " + browsers);
			log.info("Currently framework don't support this kind of browser!");
			log.info("Default browser set to CHROME");
			startAChromeBrowser();
			break;
		}
		delay(3);
		log.info("Maximize window...");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		waitForPagerLoadComplete();

		return driver;
	}

	

	private void startAChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		//System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		if (isHeadless) {
			options.addArguments("--headless");
			options.addArguments("--window-size=1400,800");
			log.info("Running chrome browser in headless mode..");
		}
		log.info("Starting a chrome browser..");
		driver = new ChromeDriver(options);
	}

	private void startASafatiBrowser() {
		WebDriverManager.safaridriver().setup();
		log.info("Starting a Safari browser..");
		driver = new SafariDriver();
	}

	private void startAFireFoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		log.info("Starting a Firefox browser..");
		driver = new FirefoxDriver();
	}

	private void startAEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		EdgeOptions options = new EdgeOptions();
		if (isHeadless == true) {
			options.addArguments("--headless");
			options.addArguments("--window-size=1400,800");
			log.info("Running edge browser in headless mode..");
		}
		log.info("Starting a Edge browser..");
		driver = new EdgeDriver(options);
	}

	public void waitForPagerLoadComplete() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(waitTimeInSecond));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(waitTimeInSecond));
		driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(waitTimeInSecond));
	}

	public void selectDropdownElem(By by, String optionValue) {
		WebElement dropElem = driver.findElement(by);
		highLightElem(dropElem);
		Select select = new Select(dropElem);
		select.selectByVisibleText(optionValue);
	}

	public void enterText(By by, String inputString) {
		WebElement element = driver.findElement(by);
		highLightElem(element);
		element.clear();
		element.sendKeys(inputString);
	}
	
	public WebElement  enterText(String inputString, By by) {
		WebElement element = driver.findElement(by);
		highLightElem(element);
		element.clear();
		element.sendKeys(inputString);
		
		return element;
	}

	public void clickAnElement(By by) {
		WebElement element = driver.findElement(by);
		highLightElem(element);
		element.click();
	}

	public void handleCheckBox(By by, boolean isCheck) {
		WebElement checkBox = driver.findElement(by);
		highLightElem(checkBox);
		if (isCheck) {
			if (checkBox.isSelected()) {

			} else {
				checkBox.click();
			}
		} else {
			if (checkBox.isSelected()) {
				checkBox.click();
			} else {

			}
		}
	}

	public void highLightElem(WebElement element) {
		if(isDemo) {
			
			for (int i = 1; i < 2; i++) {
				WrapsDriver wrappedEmelent = (WrapsDriver) element;
				JavascriptExecutor js = (JavascriptExecutor) wrappedEmelent.getWrappedDriver();
				// Highlight
				js.executeScript("arguments[0].setAttribute('style' , arguments[1]);", element,
						"color: red; border :2px solid yellow");
				delay(1);
				js.executeScript("arguments[0].setAttribute('style' , arguments[1]);", element, "");
				delay(1);
				// 1 second is 1000 miliseconds
				// 0.5 second is 500 miliseconds
				// 60 second is 60,000 miliseconds
			}
		}
	}

	public void scrollIntoView(WebElement element) {
		highLightElem(element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
		delay(0.25);
	}

	public void scrollIntoCenter(WebElement element) {
		highLightElem(element);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public String takeScreenshot(String screenshotName) {
		String finalFilePath = null;
		try {
			String fileLocation = "target/" + screenshotName + "_" + getCurrentTime() + ".jpg";
			finalFilePath = getAbsolutePath(fileLocation);
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(finalFilePath));
			log.info("Screenshot location---> " + finalFilePath);
		} catch (Exception e) {
			log.error("Error...", e);
		}
		return finalFilePath;
	}

	public void fileUpload(By by, String filePath) {
		WebElement uploadElem = driver.findElement(by);
		highLightElem(uploadElem);
		String absPath = getAbsolutePath(filePath);
		uploadElem.sendKeys(absPath);
		log.info("Uploading file ---> " + absPath);
	}

	public WebElement waitForVisibility(By by) {
		WebElement elem = null;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSecond));
		elem = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		highLightElem(elem);
		return elem;
	}
	public WebElement fluentWait(final By by) {
		WebElement elem = null;
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(Duration.ofSeconds(waitTimeInSecond))
					.pollingEvery(Duration.ofSeconds(3))
					.ignoring(NoSuchElementException.class);

			elem = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(by);
				}
			});
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}
		return elem;
	}

	public WebDriver switchToBrowserWindowByIndex(int index) {
		int totalBrowsers = 0;
		try {
			Set<String> setAllBrowsers = driver.getWindowHandles();
			totalBrowsers = setAllBrowsers.size();
			if(index < totalBrowsers) {
				List<String> listBrowsers = new ArrayList<String>();
				for(String browser : setAllBrowsers) {
					listBrowsers.add(browser);
				}				
				String windowName = listBrowsers.get(index);
				driver = driver.switchTo().window(windowName);
				
			}else {
				int tempBrowser = index + 1;
				log.info("There are only [" + totalBrowsers + "] open browser availabe,"
				+ "can't switch to browser number [" + tempBrowser+"], that doesn't exist!"		);
			}			
		} catch (Exception e) {
			log.error("Error: ", e);
			assertEquals(true, false);
		}		
		return driver;
	}
	public void clickHidenElement(By by) {
		WebElement element = driver.findElement(by);
		highLightElem(element);
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	 public void clickAriaHiddenBtn(By by) {
		 JavascriptExecutor js = (JavascriptExecutor)driver;
		 WebElement element = driver.findElement(by);
		 js.executeScript("arguments[0].setAttribute('aria-hidden', 'false')", element);
		 js.executeScript("arguments[0].style.display='block'", element);
		 element.click();
	 }
	 public int getAllIframes() {
	        int totalIframe = 0;
	        try {
	            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
	            totalIframe = iframes.size();
	        } catch (Exception e) {
	            log.error("Error: ", e);
	            assertEquals(true, false);
	        }
	        return totalIframe;
	    }
	///////////////////////////////////////////////////////////////////////////////////
	
	public void delay (double inSeconds) {
		try {
			Thread.sleep((long)inSeconds * 500);
		} catch (Exception e) {
			log.error("Error:..." + e);
		}
	}
	
	public String getAbsolutePath(String relativePath) {
		String finalAbsolutePath = null;
		try {
			File testDataFile = new File(relativePath);
			finalAbsolutePath = testDataFile.getAbsolutePath();
		} catch (Exception e) {
			log.error("Error:..." + e);
		}return finalAbsolutePath;
	}
	public String getCurrentTime() {
		String finalTimeStamp = null;
		Date date = new Date();
		String tempTime = new Timestamp(date.getTime()).toString();
		finalTimeStamp = tempTime.replace('-', '_').replace(':', '_').replace(',', '_').replace(' ', '_')
				.replace('.', '_').replaceAll("_", "");
		return finalTimeStamp;
	}
	
	public String getNewEmailAddress() {
		String newEmail = "test" +getCurrentTime() +"@test12345.com";
		log.info("new test email: " + newEmail);
		return newEmail;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
