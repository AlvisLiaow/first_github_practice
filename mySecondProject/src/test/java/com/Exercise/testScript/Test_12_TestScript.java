package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CartPage;
import com.Excercise.page.HomePage;
import com.Excercise.page.ProductsPage;
import com.second.project.library.Base1;

public class Test_12_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_12_TestScript.class);
	
	@Test
	public void addProductInCart() {
		HomePage homePage = new HomePage();
		ProductsPage productsPage = homePage.clickProductsPage();
		CartPage cartPage = productsPage.addProductToCart();
		cartPage.verifyProductAndDetail();
		
	}
}
