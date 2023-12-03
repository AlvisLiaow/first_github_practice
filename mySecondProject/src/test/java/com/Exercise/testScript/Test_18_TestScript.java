package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.CategoryPage;
import com.Excercise.page.HomePage;
import com.second.project.library.Base1;

public class Test_18_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_18_TestScript.class);
	
	@Test
	public void viewCategoryProduct() {
		
		HomePage homePage = new HomePage();
		homePage.categoryBtn("women");
		CategoryPage categoryPage = homePage.womenCategoryBtn("tops");
		categoryPage.womenTopsModule();
		homePage.categoryBtn("men");
		categoryPage.menCategoryBtn("jeans");
		categoryPage.menJeansModule();
	}
}
