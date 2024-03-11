package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.PageDriver;

public class OrderSummaryPage {
	
	WebDriver driver;
	FilterSearchPage filterSearchPage;
	CartPage cartPage;
	public static String actualCartProductName;
	
	public OrderSummaryPage() {

		driver = PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		PageFactory.initElements(driver, this);
	}
	

}
