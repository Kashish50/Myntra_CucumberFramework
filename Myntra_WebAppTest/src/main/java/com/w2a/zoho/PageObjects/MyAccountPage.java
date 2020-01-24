package com.w2a.zoho.PageObjects;

import java.awt.image.BufferedImage;
import java.util.Iterator;
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

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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

	@FindBys({
			@FindBy(xpath = "//li[@class='product-base product-isActive']//div[contains(@class,'product')]//div[contains(@class,'product')]//button") })
	public List<WebElement> listProductSizes;

	@FindBy(xpath = "//li[@class='product-base product-isActive']//div[@class='product-price']//span[@class='product-discountedPrice'] | //li[@class='product-base product-isActive']//div[@class='product-price']//span[1]")
	public WebElement TxtProductPrice;

	@FindBy(xpath = "//li[@class='product-base product-isActive']//div[@class='product-productMetaInfo']//h3")
	public WebElement TxtProductBrand;

	@FindBy(xpath = "//li[@class='product-base product-isActive']//div[@class='product-productMetaInfo']//a")
	public WebElement TxtProductTitle;

	@FindBy(xpath = "//span[contains(@class,'desktop-badge')]")
	public WebElement TxtNumberOfItemsincart;

	private WebDriver driver = DriverManager.getDriver();
	String strProductPrice;
	String strProductBrand;
	String strProductTitle;
	String strNumberOfItemsInCart;

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
		click(listButtonAddToBag.get(ItemNumber), "Add Item to Bag");

		strProductPrice = TxtProductPrice.getText();
		strProductBrand = TxtProductBrand.getText();
		strProductTitle = TxtProductTitle.getAttribute("innerHTML");
		strNumberOfItemsInCart = TxtNumberOfItemsincart.getText();

		// this.strProductPrice=strProductPrice;
		System.out.println(strProductPrice);
		System.out.println(strProductBrand);
		System.out.println(strProductTitle);
		System.out.println(strNumberOfItemsInCart);

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectProductSize(String strSize) {
		for (WebElement size : listProductSizes) {

			if (strSize.equals(size.getText())) {
				size.click();

			}
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void selectAProductByImage() {

		BufferedImage expectedImage = null;
		try {
			expectedImage = ImageIO.read(new File("D://Automation//Myntra_WebAppTest//Desired_Product1..webp"));
		} catch (IOException e) {

			e.printStackTrace();
		}

		List<WebElement> logoImageElement = driver.findElements(By.tagName("img"));

		for (WebElement productImg : logoImageElement) {

			Screenshot productImgscrnShot = new AShot().takeScreenshot(driver, productImg);
			BufferedImage actualImage = productImgscrnShot.getImage();
			ImageDiffer imgDiff = new ImageDiffer();
			ImageDiff diff = imgDiff.makeDiff(actualImage, expectedImage);

			if (diff.hasDiff() == true) {
				System.out.println("Images are Not Same");
				productImg.click();
			} else {
				System.out.println("Images are Same");
			}

		
		}

	}
}
