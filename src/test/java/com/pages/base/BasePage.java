package com.pages.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.Constants;
import com.utility.ExtentReportsUtility;

public class BasePage {
	protected static WebDriver driver = null;
	protected static Logger logger=LogManager.getLogger(BasePage.class.getName());
	protected static ExtentReportsUtility extentreport=ExtentReportsUtility.getInstance();
	protected static WebDriverWait wait=null;

  
	public BasePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public static void enterText(WebElement element, String text, String name) {
		if (element.isDisplayed()) {
			clearElement(element, name);
			element.sendKeys(text);
			System.out.println("text entered in " + name + "field");
		} else {
			System.out.println("fail: " + name + " element not displayed");
		}
		driver.getTitle();
			
		
	}
		public static void clearElement(WebElement element, String ele) {
			if (element.isDisplayed()) {
				element.clear();
				logger.info("pass:" + ele + "  element cleared");
			//	extentreport.logTestInfo("pass:" + ele + "  element cleared");

			} else {
				logger.info("fail:" + ele + " element not displayed");
				extentreport.logTestInfo("fail:" + ele + " element not displayed");

			}
		}
		public static void clickElement(WebElement element, String ele) {
			if (element.isDisplayed()) {
				element.click();
				logger.info("pass:" + ele + "  element clicked");
				extentreport.logTestInfo("pass:" + ele + "  element clicked");


			} else {
				logger.info("fail:" + ele + " element not displayed");
				extentreport.logTestInfo("fail:" + ele + " element not displayed");

			}

}
	
	public static void refershPage() {
		logger.info("Refreshing the page");
		driver.navigate().refresh();
	}
	public static void waitUntiPageloads() {
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	
	public  WebElement selectFromList(List<WebElement> li,String text) {
		WebElement ele=null;
		for(WebElement i:li) {
			if(i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected" + i.getText());
				ele=i;
			}
		}
		return ele;
		
	}
	
	
	public static void alertTest(String expected ) {
		Alert alert = driver.switchTo().alert();
		 String actual = alert.getText();
		alert.accept();
		if (expected.equals(actual)) {
			logger.info("Test case passed");
		} else
			logger.info("testcase failed");
	}
	public static void alertTes() {

		Alert alert = driver.switchTo().alert();

		// Capturing alert message.
		String alertMessage = driver.switchTo().alert().getText();

		// Displaying alert message
		System.out.println(alertMessage);
		//Thread.sleep(5000);

		// Accepting alert
		alert.accept();

	}
	public static void goToUrl(String url) {
		logger.info("going to url"+ url);
		driver.get(url);
	}
	public static  String getPageTitle() {
		logger.info("getting page title");
		return driver.getTitle();
		
	}
	public static void closeBrowser() {
		logger.info("closing the browser");

		driver.close();
	}

	public static void moveToElementAction(WebElement ele, String obj) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element "+obj);
	}
	public static void ContextClickAction(WebElement ele, String obj) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element "+obj);
	}
	
	public static void WaitUntilElementIsVisible(WebElement ele, String obj) {
		logger.info("waiting for an web element "+obj+" for its visibility");
		wait=new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(ele));
		 
		 
	}
	
	public static void waitUntilAlertIsPresent() {
		System.out.println("waiting for alert to be present");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public static void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element "+objName+" to be clickable");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public static void waitFluentForVisibility(WebElement ele, String objName) {
		 Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				 					.withTimeout(Duration.ofSeconds(30))
				 					.ignoring(NoSuchElementException.class);				
				wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public static void screenShotOfThePage() throws IOException {
		String date=new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		String curDir=System.getProperty("user.dir");
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File imgFile=screenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(curDir + "/screenShots/"+ date + ".png");
		try {
		FileHandler.copy(imgFile, destFile);}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
