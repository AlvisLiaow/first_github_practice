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
import com.second.project.library.Base1;

public class Test_23_TestScript extends Base1 {
	public static Logger log = LogManager.getLogger(Test_23_TestScript.class);

	@Test
	public void VerifyAddressDetailInCheckout() {

		HomePage homePage = new HomePage();
		LoginAndSignUpPage loginAndSignupPage = homePage.signUpPage();
		loginAndSignupPage.signup("John", mylibrary.getNewEmailAddress());
		AccountInfo accountInfo = loginAndSignupPage.clickSignUp();
		accountInfo.fillingAccInfo("Mr.", "John", "123456", "10", "May", "2000");
		accountInfo.selectCheckbox(true, true);
		accountInfo.fillDetail("John", "Lee", "MK", "2010 Cy Ct", "United States", "VA", "Fairfax", "20111",
				"5127853652");
		AccountCreatedPage accountCreatedPage = accountInfo.creatAcc();
		accountCreatedPage.clickContinueBtn();
		homePage.addProductFromHp();
		CartPage cartPage = homePage.clickCartBtn();
		CheckoutPage checkOutPage = cartPage.proceedCheckOut();
		checkOutPage.verifyDeleveryAddressFilled("mr. john lee","mk","2010 cy ct","fairfax va 20111","united states","5127853652");
		checkOutPage.verifyBillingAddressFilled("mr. john lee","mk","2010 cy ct","fairfax va 20111","united states","5127853652");
		AccountDeletePage accountDeletePage = homePage.accountDelete();
		accountDeletePage.clickContinueBtnDone();
	}
}
