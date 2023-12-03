package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CartPage;
import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_11_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_11_TestScript.class);
	
	@Test
	public void verifySubscriptionInCartPage() {
		HomePage homePage = new HomePage();
		CartPage cartPage = homePage.clickCartBtn();
		cartPage.verifySubscription("Test1234@test.com");
	}
}
