package com.android.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.android.base.TestBase;

public class TestUtil extends TestBase {

	public static long implicitWait = 20;
	public static long pageLoadTimeWait = 30;

	public static String testDataPath = "";

	private static XSSFWorkbook book;
	private static XSSFSheet sheet;

	public void scrollAction(String text) {
		driver.findElementByAndroidUIAutomator("new UiScrollable (new UiSelector()).scrollIntoView(text(\"" + text +"\"));");
	}
	
	public void killServers() throws IOException, InterruptedException {
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(testDataPath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			// book = WorkbookFactory.create(file);
			book = new XSSFWorkbook(file);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);

		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();
			}
		}
		return data;
	}

	public static String takeScreenshotForError(String fname) throws IOException {

		File scr = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String curDirectory = System.getProperty("user.dir");
		String destination = curDirectory + "\\Screenshot\\" + fname + System.currentTimeMillis() + ".png";
	//	String randomName = curDirectory + "\\Screenshot\\" + getRandomvalue(10) + ".png";
	//	FileUtils.copyFile(scr, new File(randomName));
		FileUtils.copyFile(scr, new File(destination));
		return destination;
	}

	public static String getRandomvalue(int length) {
		StringBuilder sb= new StringBuilder();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0; i<length ;i++) {
			int index = (int)(Math.random() * characters.length());
			sb.append(characters.charAt(index));
		}
		return sb.toString();
	}
}
