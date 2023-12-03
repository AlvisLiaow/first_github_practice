package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.ProductDetailPage;
import com.Excercise.page.ProductsPage;
import com.second.project.library.Base1;

public class Test_8_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_8_TestScript.class);
	
	@Test
	public void verifyProductDetail() {
		HomePage homePage = new HomePage();
		ProductsPage productsPage = homePage.clickProductsPage();
		productsPage.viewProduct();
	}
}
