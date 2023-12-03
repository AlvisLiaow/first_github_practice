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

public class Test_24_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_24_TestScript.class);
	
	@Test
	public void downloadInvoiceAfterPurchaseOrser() {
		
		HomePage homePage = new HomePage();
		homePage.addProductFromHp();
		CartPage cartPage = homePage.clickCartBtn();
		cartPage.proceedCheckOut();
		LoginAndSignUpPage loginAndSignPage = cartPage.clickResgisterBtn();
		loginAndSignPage.signup("John", mylibrary.getNewEmailAddress());
		AccountInfo accountInfo = loginAndSignPage.clickSignUp();
		accountInfo.fillingAccInfo("Mr.", "John", "123456", "10", "May", "2000");
		accountInfo.selectCheckbox(true, true);
		accountInfo.fillDetail("John", "Lee", "MK", "2010 Cy Ct", "United States", "VA", "Fairfax", "20111",
				"5127853652");
		AccountCreatedPage accCreatedPage = accountInfo.creatAcc();
		accCreatedPage.clickContinueBtn();
		homePage.clickCartBtn();
		cartPage.verifyProductAndDetail();
		CheckoutPage checkOutPage = cartPage.proceedCheckOut();
		checkOutPage.verifyDeleveryAddressFilled("mr. john lee","mk","2010 cy ct","fairfax va 20111","united states","5127853652");
		checkOutPage.verifyBillingAddressFilled("mr. john lee","mk","2010 cy ct","fairfax va 20111","united states","5127853652");
		checkOutPage.comment("Null");
		PaymentPage paymentPage = checkOutPage.placeAnOrder();
		paymentPage.paymentDetail("John", "123456", "123", "10", "2024");
		PaymentDonePage paymentDonePage = paymentPage.comfirmBtn();
		paymentDonePage.verifyPlacedOrder();
		paymentDonePage.downloadInvoice();
		paymentDonePage.clickContinueBtn();
		AccountDeletePage accountDeletePage = homePage.accountDelete();
		accountDeletePage.clickContinueBtnDone();
	}
}
