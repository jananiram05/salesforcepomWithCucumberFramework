package com.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.utility.ExtentReportsUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTestInCucumber {
	protected static WebDriver driver = null;
	protected static Logger logger = null;
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();
	
	public static void getDriverInstance(String browserName) {
		switch (browserName) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			break;
		default:
			System.out.println("not entered proper browsername");

		}

	}

}
