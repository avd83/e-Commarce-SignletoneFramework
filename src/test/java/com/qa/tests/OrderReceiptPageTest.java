package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.OrderReceiptPage;
import com.qa.pages.PaymentPage;

public class OrderReceiptPageTest extends BaseTest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CartPageTest cartPageTest ;
	CartPage cartPage;
	PaymentPage paymentPage;
	PaymentPageTest paymentPageTest;
	OrderReceiptPage orderReceiptPage;
	
	public void initialization(){
		
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		homePage = new HomePage();
		cartPageTest = new CartPageTest();
		cartPage = new CartPage();
		paymentPage = new PaymentPage();
		paymentPageTest = new PaymentPageTest();
		orderReceiptPage = new OrderReceiptPage();
	}	
	@Test(priority=1)
	public void verifyOrderReceipt() throws InterruptedException {
		
		initialization();
		paymentPageTest.addPaymentInfo();
		Thread.sleep(3000);
		String OrderReceiptMsg = orderReceiptPage.getOrderReceiptMsg();
		System.out.println(OrderReceiptMsg);
		Assert.assertEquals(OrderReceiptMsg, "THANKYOU FOR THE ORDER.");
		
		String orderNumber = orderReceiptPage.getOrderedNumber();
		System.out.println(orderNumber);
		//Assert.assertEquals(orderNumber, " | 65e6f491a86f8f74dc914420 |");
		
		String orderProductName = orderReceiptPage.getOrderedProductName();
		System.out.println(orderProductName);
		Assert.assertEquals(orderProductName, HomePageTest.expectedPrductName);
		
		String orderPrice = orderReceiptPage.getOrderedProductPrice();
		System.out.println(orderPrice);
		
		orderReceiptPage.getOrderHistoryPageLink();
		Thread.sleep(3000);
	}

}
