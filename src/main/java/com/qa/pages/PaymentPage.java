package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;

public class PaymentPage extends BaseTest {

	WebDriver driver;
	FilterSearchPage filterSearchPage;
	CartPage cartPage;
	public static String actualCartProductName;

	public PaymentPage() {

		driver = PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div/div[2]/div/input[1]")
	WebElement cvvCode;

	@FindBy(xpath = "//form/div[1]/div[3]/div[1]/input[@type='text']")
	WebElement nameOncard;

	@FindBy(xpath = "//form/div[1]/div[4]/div[1]/input[@type='text']")
	WebElement inputApplyCoupon;

	@FindBy(css = "button[type='submit']")
	WebElement applyCouponBtn;

	@FindBy(css = "input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrderBtn;
	
	@FindBy(xpath="//div/div[1]/div[1]/div")
	WebElement orderSaveMessage;
	
	
	public OrderReceiptPage getPaymentInfo() throws InterruptedException {
		cvvCode.sendKeys("333");
		nameOncard.sendKeys("amar s saxsena");
		inputApplyCoupon.sendKeys("ABC");
		//applyCouponBtn.click();
		Thread.sleep(3000);
		getCountry();
		placeOrderBtn.click();	
		return new OrderReceiptPage();		
	}
	
	public void getCountry() throws InterruptedException {
		country.sendKeys("ind");
		Thread.sleep(3000);
		List<WebElement> countryList = driver.findElements(By.xpath("//section/button"));
		for(WebElement option: countryList) {
			if(option.getText().equalsIgnoreCase("India"))				
			{	option.click();
				break;
			}
		}
	}
	
	public String getOrderSaveMessage() {
		return	orderSaveMessage.getText();		
		 
	}
	
	
}
