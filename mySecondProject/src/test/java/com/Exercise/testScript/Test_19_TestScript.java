package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_19_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_19_TestScript.class);

	@Test
	public void viewAndCartBrandLink() {

		HomePage homePage = new HomePage();
		homePage.clickProductsPage();
		homePage.clickOnBrand("mademe");
		homePage.clickOnBrand("polo");
	}
}
