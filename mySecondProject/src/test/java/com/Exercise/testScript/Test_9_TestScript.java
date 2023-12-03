package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.ProductsPage;
import com.second.project.library.Base1;

public class Test_9_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_9_TestScript.class);
	private WebDriver driver;
	
	@Test
	public void searchProduct() {
		
		HomePage homePage = new HomePage();
		ProductsPage productsPage = homePage.clickProductsPage();
		productsPage.searchProduct("blue");
	}










}

