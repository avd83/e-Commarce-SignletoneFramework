package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.PageDriver;

public class OrderPage {
	
	WebDriver driver;
	FilterSearchPage filterSearchPage;
	CartPage cartPage;
	public static String actualCartProductName;

	public OrderPage() {

		driver = PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		PageFactory.initElements(driver, this);
	}
	
	@FindBy (xpath="//button[@routerlink='/dashboard/myorders']")
	public static WebElement ordersBtn;
	
	
	@FindBy (xpath="//tr/td/button[@class='btn btn-primary']")
	List<WebElement> viewBtn;
	
	
	@FindBy (xpath="//tr/td/button[@class='btn btn-danger']")
	List<WebElement> deleteBtn;
	
	@FindBy (xpath="//tbody/tr/th")
	List<WebElement> orderList;
	
	
	public OrderSummaryPage getOrderDetails(String orderNo) {
		
		for(WebElement option : orderList) {
			
			if(option.getText().equalsIgnoreCase(orderNo)) {
				System.out.println(option);
			 				
			}
		}	
		return new OrderSummaryPage();
	}
	
	

}
