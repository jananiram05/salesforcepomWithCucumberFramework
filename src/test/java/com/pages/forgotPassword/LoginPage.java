package com.pages.forgotPassword;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.base.BasePage;

public class LoginPage extends BasePage{
	
	@FindBy(id="username")WebElement username;
	@FindBy(id="password")WebElement passWord;
	@FindBy(id="Login")WebElement login;
	@FindBy(id="error")WebElement error;
	@FindBy(id="rememberUn")WebElement remember;
	@FindBy(id="forgot_password_link")WebElement forgotPass;
	@FindBy(id="continue")WebElement continueinlogin;
	@FindBy(xpath="/html/body/div[1]/div/div/div[2]/form/div[1]/input[1]")WebElement usernameinforgot;


	
	public LoginPage(WebDriver driver) {
	super(driver);
	}
	public void enterUseName(String data) {
		WaitUntilElementIsVisible(username, "usernameElement");
		enterText( username,data,"usernameElement");
	}
	public void enterpassWord(String data) {
		enterText(passWord,data, "Password");
	}
	public WebDriver clickLogin() {
		clickElement(login, "loginButton");
		return driver;
	}
	
	
	public String error() {
         return error.getText();
	}
	public void clickRemember() {
		clickElement(remember, "remember");
	}
	public void forgotPassword() {
		clickElement(forgotPass, "forgotPass");
	}
	
	public void clickContinue() {
		clickElement(continueinlogin, "continueinlogin");
	

	}
	public void enterUseNameinforgot(String data) {
 	}
}
