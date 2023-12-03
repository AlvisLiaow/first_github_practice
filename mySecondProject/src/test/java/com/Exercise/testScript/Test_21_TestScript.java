package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.Excercise.page.HomePage;
import com.Excercise.page.ProductDetailPage;
import com.Excercise.page.ProductsPage;
import com.second.project.library.Base1;

public class Test_21_TestScript extends Base1{
	public static Logger log = LogManager.getLogger(Test_21_TestScript.class);
	
	@Test
	public void addReviewOnProduct() {
		
		HomePage homePage = new HomePage();
		ProductsPage productPage = homePage.clickProductsPage();
		ProductDetailPage productDetail = productPage.viewProduct();
		productDetail.comment("John", "Test1234@Test.com", "Null");
		productDetail.summit();
	}
}
