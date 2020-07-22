package com.android.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.android.util.TestUtil;
import com.android.util.WebEventListener;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class TestBase {

	public static AndroidDriver<MobileElement> driver;
	public static AppiumDriverLocalService service;
	public static Properties prop;
	
	public AppiumDriverLocalService startServer() {

		boolean flag = checkIfServerIsRunning(4723);
		if (!flag) {
			service = AppiumDriverLocalService.buildDefaultService();
			service.start();
		}
		return service;
	}
	public static boolean checkIfServerIsRunning(int port) {
		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);
			serverSocket.close();
		} catch (IOException e) {
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}
	public static void startEmulator() throws IOException, InterruptedException {
		Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\src\\main\\java\\com\\android\\dataset\\emulatorStart.bat");
		Thread.sleep(10000);
	}
	
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\android\\dataset\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static AndroidDriver<MobileElement> initialization() throws IOException, InterruptedException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		String device = (String) prop.getProperty("device");
	//	String device = System.getProperty("deviceName");   ---Use this to run from command line with maven and not passing value from config file
		if (device.contains("emulator")) {
			startEmulator();
		}
		capabilities.setCapability("automationName", "UiAutomator2");

		capabilities.setCapability("platformVersion", "8.1");

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device);
		capabilities.setCapability("platformName", "Android");

		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("fullReset", "false");
		capabilities.setCapability("appWaitDuration", "10000");

		capabilities.setCapability("appPackage", "com.google.android.deskclock");
		capabilities.setCapability("appActivity", "com.android.deskclock.DeskClock");

		capabilities.setCapability("autoGrantPermissions", "true");

		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	
		driver.manage().timeouts().implicitlyWait(TestUtil.implicitWait, TimeUnit.SECONDS);

		return driver;
	}
}
