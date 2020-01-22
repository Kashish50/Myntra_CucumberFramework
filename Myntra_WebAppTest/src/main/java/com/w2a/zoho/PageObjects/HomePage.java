package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.w2a.zoho.utilities.DriverManager;

public class HomePage extends BasePage {

	@FindBy(xpath = "//div[@class='desktop-userIconsContainer']//span[@class='desktop-userTitle']")
	public WebElement iconProfile;

	@FindBy(xpath = "//a[@data-track='login']")
	public WebElement btnLogin;

	@FindBy(xpath = "//a[@data-track='signup']")
	public WebElement btnSignUp;

	public HomePage open(String url) {

		System.out.println("Page Opened");
		DriverManager.getDriver().navigate().to(url);
		return (HomePage) openPage(HomePage.class);
	}

	public HomePage doLoginAsInvalidUser(String username, String password) {

		// type(email, username, "Username textbox");
		// type(pass, password, "Password textbox");
		// click(signin, "Sign in Button");

		return this;

	}

	public ZohoAppPage doLoginAsValidUser(String username, String password) {

		// type(email, username, "Username textbox");
		// type(pass, password, "Password textbox");
		// click(signin, "Sign in Button");

		return (ZohoAppPage) openPage(ZohoAppPage.class);

	}

	public LoginPage navigateToLoginPage() {
		click(iconProfile,"Icon Profile");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	click(btnLogin, "Button Login");
		return (LoginPage) openPage(LoginPage.class);

	}

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		// TODO Auto-generated method stub
		return ExpectedConditions.visibilityOf(iconProfile);
	}

}
