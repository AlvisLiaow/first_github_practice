package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_26TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_26TestScript.class);
	
	@Test
	public void verifyScrollUpWithoutArrowButtonAndScrollDownFunctioanality() {
		
		HomePage homePage = new HomePage();
		homePage.scrollToSubcribe();
		homePage.scrollToTopWithOutArrow();
	}
}
