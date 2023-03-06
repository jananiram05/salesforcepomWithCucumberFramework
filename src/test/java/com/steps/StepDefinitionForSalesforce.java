package com.steps;

import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import com.base.BaseTest;
import com.pages.home.HomePage;
import com.pages.login.LoginPage;
import com.utility.ExtentReportsUtility;
import com.utility.PropertiesUtility;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionForSalesforce extends BaseTest {
	// public static WebDriver driver;
	public static PropertiesUtility propertiesUtility = new PropertiesUtility();
	public static Logger logger = null;

	public static ExtentReportsUtility extentreport = ExtentReportsUtility.getInstance();

	LoginPage login;
	HomePage home;

	@Before
	public void setup() throws IOException {

		propertiesUtility.loadFile("applicationDataProperties");
		String url = propertiesUtility.getPropertyValue("url");
		getDriverInstance("chrome");
		goToUrl(url);
	}

	@After
	public void tearDownMethod() {
		AfterTearDownMethod();
	}

	@Given("User open Salesforce apllication")
	public void user_open_salesforce_apllication() throws IOException {
		// propertiesUtility.loadFile("applicationDataProperties");
		// logger.info("Inside saesforce error login");

	}

	@When("User on {string}")
	public void user_on(String page) {
		if (page.equalsIgnoreCase("loginpage"))
			login = new LoginPage(driver);
		else if (page.equalsIgnoreCase("homepage"))
			home = new HomePage(driver);
	}

	@When("User enters a usernname and Password")
	public void user_enters_a_usernname_and_password() throws IOException {
		String username = propertiesUtility.getPropertyValue("login.valid.userid");
		String password = propertiesUtility.getPropertyValue("login.valid.password");
		login.enterUseName(username);
		extentreport.logTestInfo("entered username");
		login.enterpassWord(password);
		extentreport.logTestInfo("entered password");

	}

	@When("User Click on Login button")
	public void user_click_on_login_button() {
		login.clickLogin();
		extentreport.logTestInfo("clicked login button");

	}

	@Then("verify the home page text")
	public void verify_the_home_page_text() {
		String expected = "Home Page ~ Salesforce - Developer Edition";
		String actual = driver.getTitle();
		extentreport.logTestInfo("got a title");

		Assert.assertEquals(actual, expected);

	}

	@When("User enters a usernname and invalid Password")
	public void user_enters_a_usernname_and_invalid_password() throws IOException {

		String username = propertiesUtility.getPropertyValue("login.valid.userid");
		String password = propertiesUtility.getPropertyValue("login.empty.password");
		login.enterUseName(username);
		extentreport.logTestInfo("entered username");

		login.enterpassWord(password);
		extentreport.logTestInfo("entered password");

	}

	@Then("verify the error text")
	public void verify_the_error_text() {
		String expected = "Please enter your password.";
		String actual = login.error();
		Assert.assertEquals(actual, expected);
		// extentreport.logTestInfo("Inside saesforce error login method ended");

	}

	@When("User click Logout")
	public void user_click_logout() {
		home.logout();
		extentreport.logTestInfo("user clicked logout");


	}

	@Then("verify Login page with rememberMe")
	public void verify_login_page_with_remember_me() {
		// String expected = "Login | Salesforce";
		String expected = "Home Page ~ Salesforce - Developer Edition";
		String actual = driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	@When("User enters a invalid usernname and invalid Password")
	public void user_enters_a_invalid_usernname_and_invalid_password() {
		String username = propertiesUtility.getPropertyValue("login.invalid.userid");
		String password = propertiesUtility.getPropertyValue("login.invalid.password");
		login.enterUseName(username);
		extentreport.logTestInfo("entered username");

		login.enterpassWord(password);
		extentreport.logTestInfo("entered password");

	}

	@When("User enters a valid usernname")
	public void user_enters_a_valid_usernname() {
		String username = propertiesUtility.getPropertyValue("login.invalid.userid");
		login.enterUseName(username);
		extentreport.logTestInfo("entered username");


	}

	@Then("verify the error text for invalid username,password")
	public void verify_the_error_text_for_invalid_username_password() {
		String expected = "Login | Salesforce";

		String actual = driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Then("verify the error text like forgot password")
	public void verify_the_error_text_like_forgot_password() {
		String expected = "Forgot Your Password | Salesforce";

		String actual = driver.getTitle();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@When("User click forgot password")
	public void user_click_forgot_password() {
		login.forgotPassword();
		extentreport.logTestInfo("user clicked forgot password");

	}

	@When("User click continue button")
	public void user_click_continue_button() {
		login.clickContinue();
		extentreport.logTestInfo("user enterd continue button");


	}

}
