package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.PaymentPage;

public class CartPageTest extends BaseTest {
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	HomePageTest homePageTest;
	PaymentPage paymentPage;
			
	public void initialization(){
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		homePage = new HomePage();
		cartPage = new CartPage();
		homePageTest = new HomePageTest();
		paymentPage =  new PaymentPage();
		
	}
	
	@Test(priority=1)
	public void verifyContinueButton() throws InterruptedException {
		initialization();
		homePageTest.addToCartProduct();
		homePage = cartPage.getClickContinueShoppingBtn();
		Thread.sleep(3000);	
	}
	
	@Test(priority=2)
	public void verifyCheckOutButton() throws InterruptedException {
		initialization();
		homePageTest.addToCartProduct();
		paymentPage = cartPage.getClickCheckOutBtn();
		Thread.sleep(1000);	
	}
	
	@Test(priority=3)
	public void verifyBuyNowButton() throws InterruptedException {
		initialization();
		homePageTest.addToCartProduct();
		paymentPage = cartPage.getClickBuyNowBtn();
		Thread.sleep(1000);	
	}
	
	@Test(priority=4)
	public void verifyDeleteProductButton() throws InterruptedException {
		initialization();
		homePageTest.addToCartProduct();
		cartPage.getClickDeleteProduct();
		Thread.sleep(3000);	
	}
	

}
