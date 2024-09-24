package utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import contants.FileConstants;

public class CommonUtils {
	
	public static String getTimeStamp() {
		return new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	}

	public static String captureScreenshot(WebDriver driver) {
		String filepath = FileConstants.SCREENSHOTS_FOLDER_PATH;
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src =  ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(filepath);
		src.renameTo(dst);
		return filepath;
	}
}
