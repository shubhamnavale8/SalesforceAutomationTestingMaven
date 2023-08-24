package com.salesforcetesting.salesforce_automation_testing;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;


public class TestSuiteOne extends TestCaseSetup {
	
	@Test
	   public static void testDemoMethods() throws InterruptedException, IOException {
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			WebDriver driver = new ChromeDriver(options);
			System.out.println("------> "+loginUrl);
	        driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(loginUrl);
			Thread.sleep(4000);
			driver.get("https://enzigmasolutionllp4-dev-ed.develop.lightning.force.com/lightning/o/Lead/list?filterName=00B2w00000bHMIEEA4");
			driver.findElement(By.xpath("(//div[@title='New'])[1]")).click();

			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			// Now you can do whatever you need to do with it, for example copy somewhere
			FileUtils.copyFile(scrFile, new File("C:\\Users\\shubham.navale\\Desktop\\ScreenShots\\screenshot.png"));
	   }
	
}
