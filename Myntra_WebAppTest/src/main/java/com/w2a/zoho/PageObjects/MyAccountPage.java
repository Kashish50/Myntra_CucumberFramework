package com.w2a.zoho.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.w2a.zoho.utilities.DriverManager;

public class MyAccountPage extends BasePage {

	@FindBy(xpath = "//span[contains(@class,'myntraweb-header-sprite desktop-iconB')]")
	public WebElement iconBag;

	@FindBy(xpath = "//div[@class='desktop-infoEmail']")
	public WebElement loggedInUserName;

	@FindBy(xpath = "//div[@class='desktop-userIconsContainer']//span[@class='desktop-userTitle']")
	public WebElement iconProfile;

	@FindBy(xpath = "//input[@class='desktop-searchBar']")
	public WebElement TxtsearchBar;

	@FindBys({ @FindBy(xpath = "//span[@class='product-discountedPrice']") })
	 public List<WebElement> listDiscountedPrices;
	
	@FindBys({ @FindBy(xpath = "//h4[@class='product-product']") })
	 public List<WebElement> listProductCategory;

	
	@FindBys({ @FindBy(xpath = "//h3[@class='product-brand']") })
	 public List<WebElement> listProductBrands;
	
	@FindBys({ @FindBy(xpath = "//span[text()='Add to bag']") })
	 public List<WebElement> listButtonAddToBag;

	private WebDriver driver = DriverManager.getDriver();

	@Override
	protected ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions.visibilityOf(iconBag);
	}

	public void verifyNameOfLoggedInUser(String strName) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		click(iconProfile, "Icon Profile");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertTrue(loggedInUserName.getText().contains(strName),
				"User " + strName + " has been Logged in successfully ..");

	}

	public void searchAItem(String strItemName) {
		type(TxtsearchBar, strItemName, "Search Bar");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TxtsearchBar.sendKeys(Keys.ENTER);
	}

	public void additemToBag(int ItemNumber) {
		
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		hoverOverElement(listProductCategory.get(ItemNumber));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		click(listButtonAddToBag.get(ItemNumber),"Add Item to Bag");
		
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
