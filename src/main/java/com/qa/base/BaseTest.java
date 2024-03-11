package com.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static Properties prop;
	WebDriver driver = null;
	WebDriverWait wait;
	DesiredCapabilities caps;

	public BaseTest() {

		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\Abhay\\eclipse-workspace1\\e-Commarce\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Parameters({"browser"})
	@BeforeMethod
	public void setUp(@Optional String browserName) throws MalformedURLException {
		
		//String browserName = null;
		String browserName1 = prop.getProperty("browser");
		if(browserName!=null) {
			browserName = browserName;
		}else 
			browserName = browserName1;		
		
		//String browserName = prop.getProperty("browser");
		//String browser = browserName;	
		
		caps = new DesiredCapabilities();
		if (browserName.contains("chrome")) {
			//System.setProperty("webdriver.chrome.driver","C:\\Users\\Abhay\\eclipse-workspace1\\e-Commarce\\driver\\chromedriver.exe");
			if(prop.getProperty("platform").equalsIgnoreCase("local")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}else if(prop.getProperty("platform").equalsIgnoreCase("remote")){
				caps.setBrowserName("chrome");
				caps.setPlatform(Platform.WINDOWS);
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);				
			}			
		} else if (browserName.contains("firefox")) {
			//System.setProperty("webdriver.firefox.driver","C:\\Users\\Abhay\\eclipse-workspace1\\e-Commarce\\driver\\geckodriver.exe");
			if(prop.getProperty("platform").equalsIgnoreCase("local")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}else if(prop.getProperty("platform").equalsIgnoreCase("remote")){
				caps.setBrowserName("firefox");
				caps.setPlatform(Platform.WINDOWS);
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);				
			}
		} else if(browserName.contains("edge")) {
			//System.setProperty("webdriver.msedge.driver","C:\\Users\\Abhay\\eclipse-workspace1\\e-Commarce\\driver\\msedgedriver.exe");
			if(prop.getProperty("platform").equalsIgnoreCase("local")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}else if(prop.getProperty("platform").equalsIgnoreCase("remote")){
				caps.setBrowserName("edge");
				caps.setPlatform(Platform.WINDOWS);
				driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps);	
			}	
		}
		
		driver.get(prop.getProperty("URL"));
		//PageDriver.setDriver(driver);
		PageDriver.getInstance().setDriver(driver);
		System.out.println("-----------driver initiated-------------");
		System.out.println("Running on Thread :" + Thread.currentThread().getId());
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	@AfterMethod
	public void tearDown() {
		//PageDriver.getDriver().quit();
		PageDriver.getInstance().getDriver().quit();
	}
	
	public String getScreenShort(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot) PageDriver.getInstance().getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") +"//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") +"//reports//" + testCaseName + ".png";
	}	
}
