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
import com.Excercise.page.PaymentDonePage;
import com.Excercise.page.PaymentPage;
import com.Excercise.page.LoginAndSignUpPage;
import com.second.project.library.Base1;

public class Test_14_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_14_TestScript.class);
	
	@Test
	public void registerWhileCheckout() {
		HomePage homePage = new HomePage();
		CartPage cartPage = homePage.addProductFromHpAndViewCart();
		cartPage.proceedCheckOut();
		LoginAndSignUpPage loginAndSignUpPage = cartPage.clickResgisterBtn();
		loginAndSignUpPage.signup("John", mylibrary.getNewEmailAddress());
		AccountInfo accountInfo = loginAndSignUpPage.clickSignUp();
		accountInfo.fillingAccInfo("Mr.", "John", "123456", "10", "May", "2000");
		accountInfo.selectCheckbox(true, true);
		accountInfo.fillDetail("John", "Lee", "MK", "2010 Cy Ct", "United States","VA", "Fairfax", "20111", "5127853652");
		AccountCreatedPage accCreatedPage = accountInfo.creatAcc();
		homePage = accCreatedPage.clickContinueBtn();
		cartPage = homePage.clickCartBtn();
		cartPage.verifyProductAndDetail();
		CheckoutPage checkoutPage= cartPage.proceedCheckOut();
		checkoutPage.comment("Null");
		PaymentPage paymentPage = checkoutPage.placeAnOrder();
		paymentPage.paymentDetail("John", "5234158", "523", "10", "2024");
		PaymentDonePage paymentdone = paymentPage.comfirmBtn();
		AccountDeletePage accountDeletePage = paymentdone.deleteAcc();
		accountDeletePage.clickContinueBtnDone();
	}
}
