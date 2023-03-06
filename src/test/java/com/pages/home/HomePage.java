package com.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.pages.base.BasePage;

public class HomePage extends BasePage {
	
	@FindBy(id="lexBanner")WebElement itsBetterInLightining;
	@FindBy(id="userNavLabel")WebElement userMenu;
	@FindBy(xpath = "/html/body/div/div[1]/table/tbody/tr/td[3]/div/div[3]/div/div/div[2]/div[3]/a[5]")WebElement logout;
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	public String getTextFromPage() {
		return itsBetterInLightining.getText();
	}
	public WebDriver logout() {
		clickElement(logout, "logout");
		return driver;
	}
	public void userMenu() {
		clickElement(userMenu, "userMenu");
	}
	
 

}
