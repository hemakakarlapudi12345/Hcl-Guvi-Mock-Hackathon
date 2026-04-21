package com.srm.utils;

import org.openqa.selenium.*;
import java.io.File;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

	public static String captureScreenshot(WebDriver driver, String testName) {

	    String folderPath = "screenshots/";
	    new File(folderPath).mkdirs();

	    String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	    String path = folderPath + testName + "_" + timestamp + ".png";

	    try {
	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        FileUtils.copyFile(src, new File(path));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return path;
	}
}
