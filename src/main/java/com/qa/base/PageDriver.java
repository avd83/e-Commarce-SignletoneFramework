package com.qa.base;

import org.openqa.selenium.WebDriver;

public class PageDriver {
	
	private static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
	
	private static PageDriver instance = null;// singleton class private variable 
	
	//public static method for singleton class where logic define
	public static PageDriver getInstance() {
		if(instance == null) {
			instance = new PageDriver();
		}
		return instance;
	}
	
	//create private constructor for singleton class
	private PageDriver(){
		
	}

	public  WebDriver getDriver() {
		return webdriver.get();
	}
	
	public  void setDriver(WebDriver driver) {
		webdriver.set(driver);
	}
}
