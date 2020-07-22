package com.android.test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.android.base.TestBase;
import com.android.util.TestUtil;

import io.appium.java_client.MobileElement;

public class SetAlarmTest extends TestBase {

//	AlarmScreen	alarmScreen = new AlarmScreen(driver);
	TestUtil testUt;
	
	@BeforeTest()
	public void setUp() throws IOException, InterruptedException {
		try {
			testUt = new TestUtil();
			testUt.killServers();
			service = startServer();
			initialization();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 0)
	public void clickTabs() {
		MobileElement clockTab = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='CLOCK']");
		clockTab.click();
		MobileElement timerTab = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='TIMER']");
		timerTab.click();
		MobileElement stopwatchTab = (MobileElement) driver
				.findElementByXPath("//android.widget.TextView[@text='STOPWATCH']");
		stopwatchTab.click();
		MobileElement alarmTab = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text='ALARM']");
		alarmTab.click();
	}
	
	@Test(priority = 1)
	public void setPreferences() {
//		alarmScreen.choosePreference();
		MobileElement expandTab = (MobileElement) driver.findElementByAccessibilityId("Expand alarm");
		expandTab.click();
		MobileElement label = (MobileElement) driver.findElementById("com.google.android.deskclock:id/edit_label");
		label.click();
		MobileElement field = (MobileElement) driver.findElementByClassName("android.widget.EditText");
		field.clear();
		field.sendKeys("Hello");
		MobileElement ok = (MobileElement) driver.findElementByXPath("//android.widget.Button[@text='OK']");
		ok.click();
		MobileElement collapseTab = (MobileElement) driver.findElementByAccessibilityId("Collapse alarm");
		collapseTab.click();
	}

	@Test(priority = 2)
	public void chooseTime() {
		MobileElement analogClock = (MobileElement) driver.findElementById("com.google.android.deskclock:id/digital_clock");
		analogClock.click();
		
		MobileElement hourSelect = (MobileElement) driver.findElementByXPath("//android.widget.RadialTimePickerView."
				+ "RadialPickerTouchHelper[@content-desc=\"5\"]");
		hourSelect.click();
		MobileElement minuteSelect = (MobileElement) driver.findElementByXPath("//android.widget.RadialTimePickerView."
				+ "RadialPickerTouchHelper[@content-desc=\"5\"]");
		minuteSelect.click();
		MobileElement pmSelect = (MobileElement) driver.findElementByXPath("//android.widget.RadioButton[@text='PM']");
		pmSelect.click();
		
		MobileElement timeOk = (MobileElement) driver.findElementById("android:id/button1");
		timeOk.click();
	}
	
	@Test(dependsOnMethods = "chooseTime")
	public void alarmSet() {
		MobileElement switchOn = (MobileElement) driver.findElementById("com.google.android.deskclock:id/onoff");
		String state = switchOn.getAttribute("checked");
		if (state.equalsIgnoreCase("true")) {
			System.out.println("Alarm Set already!");
		} else {
			switchOn.click();
			System.out.println("Alarm Set now!");
		}
//		alarmScreen.setAlarm();
	}

	@Test(priority = 3)
	public void checkSettings() throws InterruptedException {
		MobileElement options = (MobileElement) driver.findElementByXPath("//*[@content-desc=\"More options\"]");
		options.click();
		MobileElement settings = (MobileElement) driver.findElementByXPath("//*[@text='Settings']");
		settings.click();
		testUt.scrollAction("Style");
		Thread.sleep(4000);
		MobileElement navigateBack = (MobileElement) driver.findElementByXPath("//*[@content-desc=\"Navigate up\"]");
		navigateBack.click();	
	}
	
	@AfterTest
	public void tearDown() {
		driver.closeApp();
		service.stop();
	}
}
