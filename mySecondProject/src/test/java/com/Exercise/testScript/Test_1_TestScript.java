package com.Exercise.testScript;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Excercise.page.AccountCreatedPage;
import com.Excercise.page.AccountDeletePage;
import com.Excercise.page.AccountInfo;
import com.Excercise.page.HomePage;
import com.Excercise.page.LoginAndSignUpPage;
import com.second.project.library.Base1;
import com.second.project.library.ExcelManager;

public class Test_1_TestScript extends Base1{
	public static final Logger log = LogManager.getLogger(Test_1_TestScript.class);
	private String ExcelPath1 = "src/test/resources/AccountInfo.xlsx"; 
	
	@DataProvider(name = "Practice")
	private Object[][] calculatorData(){
		Object [][] data = null;
		try {
			ExcelManager excel = new ExcelManager(ExcelPath1, 0);
			data = excel.getExcelData();
		} catch (Exception e) {
			log.error("Error..",e);
		}
		return data;
	}
	
	@Test(dataProvider = "Practice")
	public void registerTest (String gender, String name, String password,
			String birthDay, String birthMonth, String birthYear,String firstName, String lastName, String company, String address,
			String country, String state, String city, String zipcod, String MobleNumber) {
		HomePage homePage = new HomePage();
		LoginAndSignUpPage loginAndSignupPage = homePage.signUpPage();
		loginAndSignupPage.signup("John", mylibrary.getNewEmailAddress());
		AccountInfo accountInfo = loginAndSignupPage.clickSignUp();
		accountInfo.fillingAccInfo(gender,name, password, birthDay, birthMonth, birthYear);
		accountInfo.selectCheckbox(true, true);
		accountInfo.fillDetail(firstName, lastName, company,address, country,state, city,zipcod, MobleNumber);
		AccountCreatedPage accCreatedPage = accountInfo.creatAcc();
		accCreatedPage.clickContinueBtn();
		AccountDeletePage accountDeletePage = homePage.accountDelete();
		accountDeletePage.clickContinueBtnDone();
	}
	
}
