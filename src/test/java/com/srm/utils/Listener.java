package com.srm.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.srm.base.BaseTest;

public class Listener implements ITestListener {

	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
	    test = extent.createTest(result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
	    test.pass("Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {

	    Object testClass = result.getInstance();
	    WebDriver driver = ((BaseTest) testClass).driver;

	    String path = ScreenshotUtil.captureScreenshot(driver, result.getName());

	    test.fail(result.getThrowable());
	    test.addScreenCaptureFromPath(path);
	}

	@Override
	public void onFinish(ITestContext context) {
	    extent.flush();
	}
}