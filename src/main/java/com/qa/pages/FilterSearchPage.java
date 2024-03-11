package com.qa.pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;

public class FilterSearchPage extends BaseTest {

	WebDriver driver;
	HomePage homePage;
	int totalProductCount;
	String expectedProductName;

	public FilterSearchPage() {
		driver = PageDriver.getInstance().getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card']/div/h5/b")
	List<WebElement> productName;

	@FindBy(xpath = "//section/div/h4[text()='Filters']")
	WebElement filtersBtn;

	@FindBy(xpath = "//section/form/div/input[@name='search']")
	WebElement search;

	@FindBy(xpath = "//section/form/div[2]/div[1]/div[1]/input[@name='minPrice']")
	WebElement miniPrice;

	@FindBy(xpath = "//section/form/div[2]/div[1]/div[2]/input[@name='maxPrice']")
	WebElement maxPrice;

	@FindBy(xpath = "//section/form/div[3]/div[3]/input")
	WebElement checkBox;

	@FindBy(xpath = "//section/form/div[3]/div/label")
	List<WebElement> checkBoxLabel;

	public HomePage getSearchFilter(String productName) throws InterruptedException {
		search.sendKeys(productName);
		Thread.sleep(2000);
		filtersBtn.click();
		return new HomePage();
	}

	public int getProductCount() {

		return totalProductCount = productName.size();
	}

	public ArrayList<String> getProductName() {
		ArrayList<String> a = new ArrayList<String>();
		for (WebElement s : productName) {
			expectedProductName = s.getText();
			a.add(expectedProductName);
		}
		return a;
	}

	public HomePage getMinMaxFilter(String minP, String maxP) throws InterruptedException {
		miniPrice.sendKeys(minP);
		maxPrice.sendKeys(maxP);
		Thread.sleep(2000);
		filtersBtn.click();
		return new HomePage();
	}

	public void getCategoryFilter(String category) throws InterruptedException {
		String e = null;
		for (int i = 0; i < checkBoxLabel.size(); i++) {
			e = checkBoxLabel.get(i).getText();
			System.out.println(e);
			if (e.equals(category) == true) {
				System.out.println(e);
				checkBox.click();
				break;
			}
		}
		Thread.sleep(2000);
		filtersBtn.click();
	}
}
