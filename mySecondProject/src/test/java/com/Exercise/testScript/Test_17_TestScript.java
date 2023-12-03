package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CartPage;
import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_17_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_17_TestScript.class);
	
	@Test
	public void removeProductFromCart() {
		
		HomePage homePage = new HomePage();
		homePage.addProductFromHp();
		CartPage cartPage = homePage.clickCartBtn();
		cartPage.removeProduct();
		cartPage.verifyEmptyCartMessage();
	}
}
