package com.qa.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.BaseTest;
import com.qa.base.PageDriver;

public class LoginPage extends BaseTest {

	WebDriver driver;
	HomePage homePage;
	WebDriverWait wait;
	
	public LoginPage() {

		// driver = PageDriver.getDriver();
		driver = PageDriver.getInstance().getDriver();
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement password;

	@FindBy(css = "input[id=login]")
	WebElement loginBtn;

	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerBtn;

	@FindBy(id = "firstName")
	WebElement firstName;

	@FindBy(id = "lastName")
	WebElement lastName;

	@FindBy(id = "userMobile")
	WebElement userMobile;

	@FindBy(css = "input[value='Male']")
	WebElement gender;

	@FindBy(id = "confirmPassword")
	WebElement confirmPassword;

	@FindBy(css = "input[type='checkbox']")
	WebElement checkBox;

	@FindBy(css = "input[value='Register']")
	WebElement registerSubmitBtn;

	@FindBy(xpath = "//div/div[1]/div[1]/div[contains(text(),' Incorrect email or password. ')]")
	WebElement errorMessage;

	@FindBy(xpath = "//a[text()='Forgot password?']")
	WebElement forgotPwdLink;

	@FindBy(xpath ="//h1[text()='Account Created Successfully']")
	WebElement newUserRegValidationMessage;

	@FindBy(xpath ="//div/div[1]/div[1]/div[contains(text(),'User already exisits with this Email Id!')]")
	WebElement existUserRegValidationMessage;

	@FindBy(xpath = "//button[text()='Login']")
	WebElement loginButton;

	public HomePage getLogin(String name, String pwd) {
		userName.sendKeys(name);
		password.sendKeys(pwd);
		loginBtn.click();
		return new HomePage();
	}
	
	public void validLogin() throws InterruptedException{
		homePage = getLogin(prop.getProperty("validUserName"), prop.getProperty("validPassword"));
		Thread.sleep(2000);
	}
	
	public void inValidLogin(){
		getLogin(prop.getProperty("validUserName"), prop.getProperty("inValidPassword"));
	}

	public String getErrorMessage(){
		return errorMessage.getText();
	}

	public String verifyForgotPasswordLink() {
		return forgotPwdLink.getText();
	}

	public void getUserRegisteration(String fName, String lName, String email, String mNo, String pwd, String cPwd)
			throws InterruptedException {
		registerBtn.click();
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		userName.sendKeys(email);
		userMobile.sendKeys(mNo);
		gender.click();
		password.sendKeys(pwd);
		confirmPassword.sendKeys(cPwd);
		checkBox.click();
		Thread.sleep(3000);
		registerSubmitBtn.click();
	}

	public String verifyNewUserRegvalidationMessage() throws InterruptedException{
		Thread.sleep(3000);
		return newUserRegValidationMessage.getText();
	}

	public void clickLoginButton() {
		loginButton.click();
	}

	public String verifyExistUserRegvalidationMessage(){
		return existUserRegValidationMessage.getText();
	}

	public void visiblityOfElementsLocatedBy(By webElement) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(webElement));
	}
}
