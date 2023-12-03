package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CartPage;
import com.Excercise.page.HomePage;
import com.Excercise.page.ProductDetailPage;
import com.second.project.library.Base1;

public class Test_13_TestScript extends Base1 {
	public static final Logger log = LogManager.getLogger(Test_13_TestScript.class);

	@Test
	public void verifyQuantityInCart() {
		HomePage homePage = new HomePage();
		ProductDetailPage productDetailPage = homePage.viewProduct();
		CartPage cartPage = productDetailPage.increase("4");
		cartPage.verifyQuantity("4");
	}
}
