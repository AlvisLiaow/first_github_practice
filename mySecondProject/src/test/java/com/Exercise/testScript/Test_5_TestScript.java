package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.second.project.library.Base1;

public class Test_5_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_5_TestScript.class);
	
	@Test
	public void registerWithExistingEmail() {
		HomePage homePage = new HomePage();
		LoginAndSignUpPage loginAndSignupPage = homePage.signUpPage();
		loginAndSignupPage.signup("John","test20231104112410752@test12345.com");
		loginAndSignupPage.verifyEmailExistMessage();
	}
}
