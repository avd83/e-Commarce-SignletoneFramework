package com.qa.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.PaymentPage;

public class PaymentPageTest extends BaseTest {
	
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CartPageTest cartPageTest ;
	CartPage cartPage;
	PaymentPage paymentPage;
	
	public void initialization(){
		
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		homePage = new HomePage();
		cartPageTest = new CartPageTest();
		cartPage = new CartPage();
		paymentPage = new PaymentPage();
	}
	
	@Test(priority=1)
	public void addPaymentInfo() throws InterruptedException {
		
		initialization();
		cartPageTest.verifyCheckOutButton();
		paymentPage.getPaymentInfo();
		loginPage.visiblityOfElementsLocatedBy((By.xpath("//div[@class='toast-bottom-right toast-container']")));
		String orderSaveVAlidationMessage = paymentPage.getOrderSaveMessage();
		System.out.println(orderSaveVAlidationMessage);
		
		Assert.assertEquals(orderSaveVAlidationMessage, "Order Placed Successfully");
		
	}
	
	
	
	

}
