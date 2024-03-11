package com.qa.base;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Listeners extends BaseTest implements ITestListener {
	
	/*public Listeners() {
		driver = PageDriver.getInstance().getDriver();				
	}*/
	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject(); ;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();;// this class for concurrency issue resolved
	@Override
	public void onTestStart(ITestResult result)
	{
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.get().log(Status.PASS, "Test Passed");
		test.pass(result.getThrowable());		
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		test.log(Status.FAIL,"Test Failed");
				extentTest.get().fail(result.getThrowable());		
				try 
				{
					driver = (WebDriver) result.getTestClass().getRealClass().getField("driver")
							.get(result.getInstance());
				} 
				catch (Exception e) 
				{
							e.printStackTrace();
				} 
				
				String filepath = null;
				try {
					filepath = getScreenShort(result.getMethod().getMethodName(),driver);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				extentTest.get().addScreenCaptureFromPath(filepath,result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) 
	{
		
	}

	@Override
	public void onStart(ITestContext context)
	{
		
	}

	@Override
	public void onFinish(ITestContext context) 
	{
		extent.flush();
	}
}
