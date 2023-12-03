package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.AccountDeletePage;
import com.Excercise.page.CartPage;
import com.Excercise.page.CheckoutPage;
import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.Excercise.page.PaymentDonePage;
import com.Excercise.page.PaymentPage;
import com.second.project.library.Base1;

public class Test_16_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_16_TestScript.class);
	
	@Test
	public void loginBeforeCheckout() {
		
		HomePage homePage = new HomePage();
		LoginAndSignUpPage loginAndSignUpPage = homePage.signUpPage();
		loginAndSignUpPage.login("test20231104112410752@test12345.com", "123456");
		homePage = loginAndSignUpPage.clickLoginBtn();
		homePage.addProductFromHp();
		CartPage cartPage = homePage.clickCartBtn();
		cartPage.verifyProductAndDetail();
		CheckoutPage checkoutPage = cartPage.proceedCheckOut();
		checkoutPage.comment("null");
		PaymentPage paymentPage = checkoutPage.placeAnOrder();
		paymentPage.paymentDetail("John", "5234158", "523", "10", "2024");
		PaymentDonePage paymentdonePage = paymentPage.comfirmBtn();
		AccountDeletePage accountDeletePage = paymentdonePage.deleteAcc();
		accountDeletePage.clickContinueBtnDone();
	}
}
