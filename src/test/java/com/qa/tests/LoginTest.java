package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginTest extends BaseTest {

	WebDriver driver;
	WebDriverWait wait;
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginTest() {
		super();
	}
	
	public void initialization(){
		
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();				
	}
	
	@Test(priority = 1)
	public void loginTest() throws InterruptedException {

		initialization();	
		loginPage.validLogin();
	}

	@Test(priority = 2)
	public void loginFailTest(){
		
		initialization();
		loginPage.inValidLogin();
		loginPage.visiblityOfElementsLocatedBy((By.xpath("//div[@class='toast-bottom-right toast-container']")));
		String errorMessage = loginPage.getErrorMessage();
		System.out.println(errorMessage);
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}

	@Test(priority = 3)
	public void verifyForgotPasswordLink() {
		initialization();
		String forgotPasswordLink = loginPage.verifyForgotPasswordLink();
		System.out.println(forgotPasswordLink);
	}

	@Test(priority = 4)
	public void getNewUserRegistration() throws InterruptedException {
		initialization();
		loginPage.getUserRegisteration("arad2", "sharm2", "arad2@gmail.com", "8882888888", "Arad@1234", "Arad@1234");
		String verifyUserRegMessage = loginPage.verifyNewUserRegvalidationMessage();
		System.out.println(verifyUserRegMessage);

		Assert.assertEquals(verifyUserRegMessage,"Account Created Successfully");
		loginPage.clickLoginButton();
	}

	@Test(priority = 5)
	public void getExistsUserRegistration() throws InterruptedException {
		initialization();
		loginPage.getUserRegisteration("arav9", "sharma9", "arav9@gmail.com", "9776567474","Arav@1234","Arav@1234");
		loginPage.visiblityOfElementsLocatedBy((By.id("toast-container")));
				
		String verifyUserExistRegMessage = loginPage.verifyExistUserRegvalidationMessage();
		System.out.println(verifyUserExistRegMessage);		
	}
}
