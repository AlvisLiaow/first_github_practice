package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.ContactUsPage;
import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_6_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_6_TestScript.class);
	
	@Test
	public void contactUsForm() {
		HomePage homePage = new HomePage();
		ContactUsPage contactUsPage = homePage.clickContactUs();
		contactUsPage.fillUpTheForm("John", "1234Test@test.com", "null", "null", "src/test/resources/Dummy_Data.txt");
		contactUsPage.clickSubmitBtn();
		contactUsPage.clickBackToHome();
	}
}
