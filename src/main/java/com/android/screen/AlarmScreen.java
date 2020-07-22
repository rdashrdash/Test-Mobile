package com.android.screen;

import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class AlarmScreen{
	
	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Expand alarm\"][1]")
	MobileElement expandTab;
	@AndroidFindBy(id="com.google.android.deskclock:id/edit_label")
	MobileElement label;
	@AndroidFindBy(className ="android.widget.EditText")
	MobileElement field;
	@AndroidFindBy(xpath ="//android.widget.Button[@text='OK']")	
	MobileElement ok;
	@AndroidFindBy(xpath="//android.widget.ImageButton[@content-desc=\"Collapse alarm\"][1]")
	MobileElement collapseTab;
	@AndroidFindBy(id = "com.google.android.deskclock:id/onoff")
	MobileElement switchOn;

	public AlarmScreen(AndroidDriver<MobileElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator (driver), this);
	}

	public String setAlarm() {
		String state = switchOn.getAttribute("checked");
		if (state.equalsIgnoreCase("true")) {
			System.out.println("Alarm Set already!");
		} else {
			switchOn.click();
			System.out.println("Alarm Set now!");
		}
		return state;
	}

	public void choosePreference() {
		expandTab.click();
		label.click();
		field.clear();
		field.sendKeys("Week Days");
		ok.click();
		collapseTab.click();
	}
}
