package com.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.PageDriver;

public class OrderReceiptPage {

	WebDriver driver;
	FilterSearchPage filterSearchPage;
	CartPage cartPage;
	public static String actualCartProductName;

	public OrderReceiptPage() {

		driver = PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h1[text()=' Thankyou for the order. ']")
	WebElement receiptMessage;

	@FindBy(xpath = "//td/label[text()=' Orders History Page ']")
	WebElement orderHistoryPagelink;

	@FindBy(xpath = "//td/label[@class='ng-star-inserted']")
	WebElement orderNumber;

	@FindBy(xpath = "//td[2]/div[1]")
	WebElement orderProductName;

	@FindBy(xpath = "//td[3]/div")
	WebElement orderProductPrice;

	public String getOrderReceiptMsg() {
		return receiptMessage.getText();
	}

	public OrderPage getOrderHistoryPageLink() {
		orderHistoryPagelink.click();
		return new OrderPage();
	}

	public String getOrderedNumber() {
		return orderNumber.getText();
		 
	}
	
	public String getOrderedProductName() {
		return orderProductName.getText();
	}

	public String getOrderedProductPrice() {
		return orderProductPrice.getText();
	}
}
