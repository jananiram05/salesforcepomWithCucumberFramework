package com.base;

import java.io.File;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.utility.Constants;
import com.utility.ExtentReportsUtility;
import com.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	protected static WebDriver driver = null;
	protected static Logger logger = LogManager.getLogger(BaseTest.class.getName());
	protected static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	@BeforeTest
	public void setUpBeforeTest() {
		System.out.println("inside @BeforeTest method");
		logger = LogManager.getLogger(BaseTest.class.getName());
	}

	@Parameters("browserName")
	public void setupBeforeTestMethod(@Optional("chrome") String browserName) {
		extentreport.startSingleTestReport("testcase");
		logger.info("Started test script");
		PropertiesUtility propertiesUtility = new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		getDriverInstance(browserName);
		goToUrl(url);
	}

	@AfterMethod
	public void AfterTearDownMethod() {
		driver.close();
	}

	public void goToUrl(String url) {
		logger.info("goint to url" + url);
		driver.get(url);
	}

	public void tearDownAfterTest() {
		extentreport.endReport();
	}

	public WebDriver returnDriverInstance() {
		return driver;
	}

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

	public String getScreenshotOfThePage(WebDriver driver) {

		if (driver == null) {
			driver = returnDriverInstance();
		}

		String date = new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(new Date());
		String curDir = System.getProperty("user.dir");
		TakesScreenshot screenShot = (TakesScreenshot) driver;
		File imgFile = screenShot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(Constants.SCREENSHOTS_DIRECTORY_PATH + "screenShots" + date + ".png");
		System.out.println(destFile);
		try {
			FileHandler.copy(imgFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destFile.getAbsolutePath();

	}

}