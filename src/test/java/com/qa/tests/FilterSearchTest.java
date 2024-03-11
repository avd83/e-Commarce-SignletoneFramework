package com.qa.tests;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;
import com.qa.pages.FilterSearchPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class FilterSearchTest extends BaseTest {
	WebDriver driver;
	LoginPage loginPage;
	HomePage homePage;
	FilterSearchPage filterSearchPage;
	
	public void initialization(){
		
		driver = PageDriver.getInstance().getDriver();
		loginPage = new LoginPage();
		homePage = new HomePage();			
		 filterSearchPage = new FilterSearchPage();
	}
	
	@Test(priority=1)
	public void verifySearchFilter() throws InterruptedException{
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		filterSearchPage.getSearchFilter("ADIDAS ORIGINAL");
		Thread.sleep(2000);
		
		int actualProductCount= filterSearchPage.getProductCount();
		System.out.println(actualProductCount);
		Assert.assertEquals(actualProductCount,1);
		
		ArrayList<String> actualArrayProductName = filterSearchPage.getProductName();
		String actualProductName = actualArrayProductName.get(0);
		System.out.println(actualProductName);
		Assert.assertEquals(actualProductName,"ADIDAS ORIGINAL");
	}
	@Test(priority=2)
	public void verifyMinMaxSearchFilter() throws InterruptedException{
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		filterSearchPage.getMinMaxFilter("31500","231500");
		Thread.sleep(1000);
		
		int actualPriceRangeProductCount= filterSearchPage.getProductCount();
		System.out.println(actualPriceRangeProductCount);
		Assert.assertEquals(actualPriceRangeProductCount,3);
		
		ArrayList<String> actualArrayPriceRangeProductName = filterSearchPage.getProductName();		
		System.out.println(actualArrayPriceRangeProductName);	
	}
	
	@Test(priority=3)
	public void verifyCheckboxSearchFilter() throws InterruptedException{
		initialization();
		loginPage.validLogin();
		Thread.sleep(2000);
		filterSearchPage.getCategoryFilter("electronics");
		Thread.sleep(1000);
	}
	

}
