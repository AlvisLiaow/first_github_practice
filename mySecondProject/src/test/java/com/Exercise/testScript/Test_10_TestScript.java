package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_10_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_10_TestScript.class);
	
	@Test
	public void verifySubcription() {
		HomePage homePage = new HomePage();
		homePage.subscription("Test1234@Test1234.com");
	}
}
