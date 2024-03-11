package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;

public class CartPage extends BaseTest{
	
	WebDriver driver;
	FilterSearchPage filterSearchPage ;
	CartPage cartPage;
	public static String actualCartProductName;
	
	public CartPage() {
		
		driver=PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy (xpath="//div/ul/li/div/div/h3")
	WebElement cartPoductName;
	
	@FindBy (xpath="//button[text()='Checkout']")
	WebElement checkOutBtn;
	
	@FindBy (xpath="//button[text()='Continue Shopping']")
	WebElement continueShoppingBtn;
	
	@FindBy (xpath="//button[text()='Buy Now']")
	WebElement buyNowBtn;
	
	@FindBy (css="button[class='btn btn-danger']")
	WebElement deleteProduct;
	
	@FindBy (xpath="//h1[text()='No Products in Your Cart !']")
	WebElement cartPageLabel;	
		
	public void verifyCartPage() throws InterruptedException {		
		Thread.sleep(3000);
		actualCartProductName = cartPoductName.getText();
		System.out.println("Actual Cart productname is : "+ actualCartProductName);
	}
	
	public HomePage getClickContinueShoppingBtn() {
		continueShoppingBtn.click();
		return new HomePage();
	}
	
	public PaymentPage getClickBuyNowBtn() {
		buyNowBtn.click();
		return new PaymentPage();
	}
	
	public PaymentPage getClickCheckOutBtn() {
		checkOutBtn.click();
		return new PaymentPage();
	}
	
	public void getClickDeleteProduct() throws InterruptedException {
		deleteProduct.click();
		Thread.sleep(2000);
		String bookMark = cartPageLabel.getText();
		System.out.println(bookMark);
	}
	
	

	
	

}
