package com.w2a.zoho.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MyAccountPage extends BasePage {

	@FindBy(xpath = "//span[contains(@class,'myntraweb-header-sprite desktop-iconB')]")
	public WebElement iconBag;

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(iconBag);
	}

}
