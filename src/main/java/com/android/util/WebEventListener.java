package com.android.util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.android.base.TestBase;

public class WebEventListener extends TestBase implements WebDriverEventListener, ISuiteListener, IInvokedMethodListener, ITestListener{

	public void beforeInvocation(WebDriver driver, IInvokedMethod method, ITestResult testResult) {
		System.out.println("Before invocation : " + testResult.getTestClass().getName() + "---" 
				+ method.getTestMethod().getMethodName());
	}
	
	public void afterInvocation(WebDriver driver, IInvokedMethod method, ITestResult testResult) {
		System.out.println("After invocation : " + testResult.getTestClass().getName() + "---" 
				+ method.getTestMethod().getMethodName());
	}
	
	public void onStart(WebDriver driver, ISuite suite) {
		System.out.println("Current execution of suite : " + suite);
	}

	public void onFinish(WebDriver driver, ISuite suite) {
		System.out.println("End of execution of suite : " + suite);
	}
	
	public void onTestStart(WebDriver driver, ITestResult result) {
		System.out.println("on test start -- test name :-- " + result.getName());
	}
	
	public void onTestSuccess(WebDriver driver, ITestResult result) {
		System.out.println("on test success -- test name :-- " + result.getName());
	}

	
	public void onTestFailure(WebDriver driver, ITestResult result) {
		System.out.println("on test failure -- test name :-- " + result.getName());
	}

	
	public void onTestSkipped(WebDriver driver, ITestResult result) {
		System.out.println("on test skipped -- test name :-- " + result.getName());
	}

	// Before test<> tag of xml testng file , not for test method
	public void onStart(WebDriver driver, ITestContext context) {
		System.out.println("on test start -- test name :-- " + context.getName());
		ITestNGMethod methods[] = context.getAllTestMethods();
		System.out.println("These methods will be executed.");
		for (ITestNGMethod method : methods) {
			System.out.println(method.getMethodName());
		}
	}

	
	public void onFinish(WebDriver driver, ITestContext context) {
		System.out.println("on test finish -- test name :-- " + context.getName());
	}

	
	public void beforeNavigateTo(String url, WebDriver driver) {
		System.out.println("Before navigating to: '" + url + "'");
	}

	public void afterNavigateTo(String url, WebDriver driver) {
		System.out.println("Navigated to: '" + url + "'");
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Value of the:" + element.toString() + "before any changes made");
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		System.out.println("Element value changed to: " + element.toString());
	}

	public void beforeClickOn(WebElement element, WebDriver driver) {
		System.out.println("Trying to click on: " + element.toString());
	}

	public void afterClickOn(WebElement element, WebDriver driver) {
		System.out.println("Clicked on: " + element.toString());
	}

	public void beforeNavigateBack(WebDriver driver) {
		System.out.println("Navigating back to previous page");
	}

	public void afterNavigateBack(WebDriver driver) {
		System.out.println("Navigated back to previous page");
	}

	public void beforeNavigateForward(WebDriver driver) {
		System.out.println("Navigating forward to next page");
	}

	public void afterNavigateForward(WebDriver driver) {
		System.out.println("Navigated forward to next page");
	}

	public void onException(ITestResult result, Throwable throwable, WebDriver driver) {
		try {
			TestUtil.takeScreenshotForError(result.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Trying to  find Element By : " + by.toString());
	}

	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		System.out.println("Found Element By : " + by.toString());
	}

	public void beforeScript(String script, WebDriver driver) {
		System.out.println("Start of script : " + script);
	}

	public void afterScript(String script, WebDriver driver) {
		System.out.println("End of script : " + script);
	}

	public void beforeAlertAccept(WebDriver driver) {
		System.out.println("Ready to accept alert");
	}

	public void afterAlertAccept(WebDriver driver) {
		System.out.println("Alert accepted");
	}

	public void beforeAlertDismiss(WebDriver driver) {
		System.out.println("Ready to dismiss alert");
	}

	public void afterAlertDismiss(WebDriver driver) {
		System.out.println("Alert dismissed");
	}

	public void beforeNavigateRefresh(WebDriver driver) {
		System.out.println("page set to be refreshed");
	}

	public void afterNavigateRefresh(WebDriver driver) {
		System.out.println("Page refreshed");
	}

	public void beforeChangeValue(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Before changing value for : " + element);
	}

	public void afterChangeValue(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
		System.out.println("Changed value for : " + element);
	}

	public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
	}

	public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
	}

	public void beforeSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Moving to window :" + windowName);
	}

	public void afterSwitchToWindow(String windowName, WebDriver driver) {
		System.out.println("Current window is : " + windowName);
	}

	public <X> void beforeGetScreenshotAs(OutputType<X> target) {
		System.out.println("Initializing screenshot process");
	}

	public <X> void afterGetScreenshotAs(OutputType<X> target, X screenshot) {
		System.out.println("Completing screenshot process");
	}

	public void beforeGetText(WebElement element, WebDriver driver) {
		System.out.println("Ready to get text value of :" + element);
	}

	public void afterGetText(WebElement element, WebDriver driver, String text) {
		System.out.println("Obtaineed value for : " + element);
	}

	public void onException(Throwable throwable, WebDriver driver) {
	}
}
