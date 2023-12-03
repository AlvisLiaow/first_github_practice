package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.second.project.library.Base1;

public class Test_3_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_3_TestScript.class);
	
	@Test
	public void loginIncorecct() {
		HomePage homePage = new HomePage();
		LoginAndSignUpPage accountDeletePage = homePage.loginPage();
		accountDeletePage.login("Test94945@test.com", "11111111");
		accountDeletePage.clickLoginBtnAndVarifyTheErroText();
	}
}
