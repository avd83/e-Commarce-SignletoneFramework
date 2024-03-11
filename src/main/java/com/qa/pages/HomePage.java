package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;

public class HomePage extends BaseTest {
	
	WebDriver driver;
	FilterSearchPage filterSearchPage ;
	CartPage cartPage;
	public static String cartCount;
	
	public HomePage() {
		
		//driver=PageDriver.getDriver();
		driver=PageDriver.getInstance().getDriver();
		filterSearchPage = new FilterSearchPage();
		cartPage = new CartPage();
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(xpath = "//div[@class='left mt-1'] ")
	WebElement homePageicon; 
	
	@FindBy(css = "li button")
	List<WebElement> topDashboardBtn; 
	
	@FindBy(xpath = "//div[@class='card']")
	List<WebElement> itemsCount;
	
	@FindBy(xpath = "//div[@class='card']/div/button[2]/i")
	WebElement addToCartBtn;
	
	@FindBy(xpath = "//li[4]/button//label")
	public static WebElement cartCountLabel;
	
	
	
	public String verifyHomePageTitle(){
		
		String homePageTitle = driver.getTitle();
		return homePageTitle;
	}
	
	public String getHomePgageIcon() {
		return homePageicon.getText();
	}
	
	public int getTopDashboardBtn() {
		return topDashboardBtn.size();		
	}
	
	public int getItemsCount() {		
		return itemsCount.size();
	}
	
	public CartPage getAddToCart(String productName) throws InterruptedException {
		for(WebElement e :filterSearchPage.productName) {
			String p = e.getText();
			System.out.println(p);
			if(p.contentEquals(productName))
			{
				addToCartBtn.click();
				break;
			}
		}
		Thread.sleep(3000);
		 getCartCount();
		 cartCountLabel.click();
		 return new CartPage();
	}
	
	public void getCartCount() {
		cartCount = cartCountLabel.getText();
		System.out.println("Cart Count is : "+ cartCount);	
		
	}
	
	
	
	

}
