package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CartPage;
import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.Excercise.page.ProductsPage;
import com.Excercise.page.SearchedProductPage;
import com.second.project.library.Base1;

public class Test_20_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_20_TestScript.class);

	@Test
	public void searchProductAndVerifyProductAfterLogin() {
		
		HomePage homePage = new HomePage();
		ProductsPage productPage = homePage.clickProductsPage();
		SearchedProductPage searchedProductPage = productPage.searchProduct("jeans");
		CartPage cartPage = searchedProductPage.addProductToCart();
		cartPage.verifyProductAndDetail();
		LoginAndSignUpPage loginPage = homePage.loginPage();
		loginPage.login("test20231108150338051@test12345.com", "123456");
		homePage= loginPage.clickLoginBtn();
		cartPage = homePage.clickCartBtn();
		cartPage.verifyProductAndDetail();
	}
}
