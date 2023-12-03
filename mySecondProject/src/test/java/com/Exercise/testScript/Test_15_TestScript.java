package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.AccountCreatedPage;
import com.Excercise.page.AccountDeletePage;
import com.Excercise.page.AccountInfo;
import com.Excercise.page.CartPage;
import com.Excercise.page.CheckoutPage;
import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.Excercise.page.PaymentDonePage;
import com.Excercise.page.PaymentPage;
import com.second.project.library.Base1;

public class Test_15_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_14_TestScript.class);

	@Test
	public void registerBeforeCheckout() {
		HomePage homePage = new HomePage();
		LoginAndSignUpPage signPage = homePage.signUpPage();
		signPage.signup("John", mylibrary.getNewEmailAddress());
		AccountInfo accountInfo = signPage.clickSignUp();
		accountInfo.fillingAccInfo("Mr.", "John", "123456", "10", "May", "2000");
		accountInfo.selectCheckbox(true, true);
		accountInfo.fillDetail("John", "Lee", "MK", "2010 Cy Ct", "United States","VA", "Fairfax", "20111", "5127853652");
		AccountCreatedPage accountCreatedPage = accountInfo.creatAcc();
		homePage = accountCreatedPage.clickContinueBtn();
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
