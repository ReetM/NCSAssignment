package coreUtils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.Scenario;

public class TakeScreenshot extends BrowserFactory{
	String screenshotPath = "target/Screenshots";
	protected LoggerUtil log;
	
	public void takeScreenshot(Scenario scenario) {
		String testcaseName = scenario.getName().toUpperCase();
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_hh_mm_a");
		Date date = new Date();
		String date_time = dateFormat.format(date);
		File file = new File(System.getProperty("user.dir") + File.separator + screenshotPath);
		boolean exists = file.exists();
		if (!exists) {
			new File(System.getProperty("user.dir") + File.separator + screenshotPath).mkdir();
		}

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", "");
		try {
			String saveImgFile = "./"+screenshotPath+"/"+ date_time + "_" + testcaseName + ".png";
			log.info(" ✔  : Captured Image File Path : " + saveImgFile);
			FileUtils.copyFile(scrFile, new File(saveImgFile));
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}
	}

	public void takeScreenShotOnException(Scenario scenarioName) {
		String testcaseName = scenarioName.getName().toUpperCase();
		String takeScreenshot = prop.getProperty("take-screenshot");
		log.info("*************************************");
		log.info("✔  " + "Scenario Name: " + testcaseName);
		log.info("✔  " + "Scenario Result: " + scenarioName.getStatus().toString().toUpperCase());
		
		testcaseName = testcaseName.trim();
		if (scenarioName.isFailed()) {
			if (takeScreenshot.equalsIgnoreCase("true") || takeScreenshot.equalsIgnoreCase("yes")) {
				try {
					if (driver != null) {
						takeScreenshot(scenarioName);
					}
				} catch (Exception ex) {
					log.warn("⚠" + "[TAKESCREENSHOT EXCEPTION]Driver is null while taking screen shot");
				}
			}
		}
	}
}
