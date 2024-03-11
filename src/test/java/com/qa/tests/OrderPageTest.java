package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.OrderPage;
import com.qa.pages.PaymentPage;

public class OrderPageTest extends BaseTest {
	
	WebDriver driver;
	LoginPage loginPage;
	LoginTest loginTest;
	HomePage homePage;
	CartPageTest cartPageTest ;
	CartPage cartPage;
	PaymentPage paymentPage;
	OrderPage orderPage;
	
	public void initialization(){
		
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		loginTest = new LoginTest();
		homePage = new HomePage();
		cartPageTest = new CartPageTest();
		cartPage = new CartPage();
		paymentPage = new PaymentPage();
		orderPage = new OrderPage();
	}
	
	@Test(priority=1)
	public void verifyOrderHistory() throws InterruptedException {
		initialization();
		loginTest.loginTest();
		orderPage.ordersBtn.click();
		Thread.sleep(2000);
		orderPage.getOrderDetails("65e70756a86f8f74dc91706b"); 
		
	}
	
	
	
	
	
	
	
	

}
