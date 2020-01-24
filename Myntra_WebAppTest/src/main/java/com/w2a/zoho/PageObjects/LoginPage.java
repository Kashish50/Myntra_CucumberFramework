package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

	@FindBy(xpath = "//p[text()='Login to Myntra']")
	public WebElement welcomeText;

	@FindBy(xpath = "//input[@name='email']")
	public WebElement email;

	@FindBy(xpath = "//input[@name='password']")
	public WebElement password;

	@FindBy(xpath = "//button[@class='login-login-button']")
	public WebElement btnLogin;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(welcomeText);
	}

	public MyAccountPage doLoginAsValidUser(String strUsername, String strPassword) {

		type(email, strUsername, "Username textbox");
		type(password, strPassword, "Password textbox");
		click(btnLogin, "Sign in Button");

		return (MyAccountPage) openPage(MyAccountPage.class);

	
	}

}
