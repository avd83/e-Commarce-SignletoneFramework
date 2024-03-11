package com.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.CartPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends BaseTest {

	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	CartPage cartPage;
	public static String expectedPrductName = "ZARA COAT 3";
		
	public void initialization(){
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		homePage = new HomePage();
		cartPage = new CartPage();
	}

	@Test(priority=1)
	public void getHomePageTitleTest() throws InterruptedException {
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		String homePageTitle = homePage.verifyHomePageTitle();
		System.out.println(homePageTitle);
		Assert.assertEquals(homePageTitle, "Let's Shop");
	}
	
	@Test(priority=2)
	public void verifyHomePageIcon() throws InterruptedException{
		
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		String homePageIcon = homePage.getHomePgageIcon();
		System.out.println(homePageIcon);	
		Assert.assertTrue(true);
	}	
	@Test(priority=3)
	public void verifyTopDashboardBtnCount() throws InterruptedException {
		
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		int count = homePage.getTopDashboardBtn();
		System.out.println(count);		
		Assert.assertEquals(count,4);		
	}	
	
	@Test(priority=4)
	public void verifyItemsCount() throws InterruptedException {
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		int itemsCount = homePage.getItemsCount();
		System.out.println(itemsCount);		
		Assert.assertEquals(itemsCount,3);		
	}
	
	@Test(priority=5)
	public void addToCartProduct() throws InterruptedException {
		
		initialization();
		loginPage.validLogin();
		Thread.sleep(1000);
		homePage.getAddToCart(expectedPrductName);		
		Assert.assertEquals(HomePage.cartCount,"1");
		cartPage.verifyCartPage();
		Assert.assertEquals(CartPage.actualCartProductName,	expectedPrductName);			
	}
}
