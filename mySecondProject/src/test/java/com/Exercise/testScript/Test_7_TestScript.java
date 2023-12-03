package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.TestCasesPage;
import com.second.project.library.Base1;

public class Test_7_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_7_TestScript.class);
	
	@Test
	public void verifyTestCasesPage() {
		HomePage homepage = new HomePage();
		homepage.clickTestCasesPage();
	}
}
