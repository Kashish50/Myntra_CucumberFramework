package com.w2a.cucumber.zoho.steps;

import com.aventstack.extentreports.Status;
import com.w2a.zoho.ExtentListeners.ExtentManager;
import com.w2a.zoho.ExtentListeners.ExtentTestManager;
import com.w2a.zoho.PageObjects.HomePage;
import com.w2a.zoho.PageObjects.LoginPage;
import com.w2a.zoho.PageObjects.MyAccountPage;
import com.w2a.zoho.PageObjects.ZohoHomePage;
import com.w2a.zoho.utilities.DriverManager;

import cucumber.api.PendingException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps extends BaseSteps {

	public HomePage home;
	public LoginPage loginPage;
	public MyAccountPage myAccPage;

	protected Scenario scenario;
	static String scenarioName;
	static int x = 0;

	@Before
	public synchronized void before(Scenario scenario) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		x = x + 1;
		this.scenario = scenario;
		scenarioName = scenario.getName();
		ExtentTestManager.startTest("Scenario No : " + x + " : " + scenario.getName());
		ExtentTestManager.getTest().log(Status.INFO, "Scenario started : - " + scenario.getName());
		setUpFramework();
	}

	@After
	public void after(Scenario scenario) {

		if (scenario.isFailed()) {

			ExtentTestManager.logFail("Scenario Failed");
			ExtentTestManager.addScreenShotsOnFailure();
		} else {

			ExtentTestManager.scenarioPass();
		}

		ExtentManager.getReporter().flush();

		quit();

	}

	@Given("^launch browser '(.*?)'$")
	public void launch_browser(String browserName) throws Throwable {
		openBrowser(browserName);
		ExtentTestManager.logInfo("Browser Opened : " + browserName);
	}

	@When("^user navigates to the URL '(.*?)'$")
	public void user_navigates_to_the_URL(String URL) throws Throwable {

		home = new HomePage().open(URL);

	}

	@And("^the user Navigates to the Login Page$")
	public void the_user_Navigates_to_the_Login_Page() throws Throwable {
		loginPage = home.navigateToLoginPage();

	}

	@Then("^Login with Username \"([^\"]*)\" and Password \"([^\"]*)\"$")
	public void login_with_Username_and_Password(String strUsername, String strPassword) throws Throwable {
		myAccPage=loginPage.doLoginAsValidUser(strUsername, strPassword);

	}

	@Then("^User \"([^\"]*)\" gets Logged Into Myntra Account Successfully$")
	public void user_gets_Logged_Into_Myntra_Account_Successfully(String strName) throws Throwable {
		myAccPage.verifyNameOfLoggedInUser(strName);
	}
	
	@And("^I search a Item \"([^\"]*)\"$")
	public void i_search_a_Item(String strItemName) throws Throwable {
		myAccPage.searchAItem(strItemName);
	}

	@And("^I add Item Number \"([^\"]*)\" to the cart$")
	public void i_add_Item_Number_to_the_cart(int ItemNumber) throws Throwable {
		myAccPage.additemToBag(ItemNumber);
	}
	
	@And("^I select Product size\"([^\"]*)\"$")
	public void i_select_Product_size(String strSize) throws Throwable {
		myAccPage.selectProductSize(strSize);
	}
	
	@Then("^I select another product$")
	public void i_select_another_product() throws Throwable {
		myAccPage.selectAProductByImage();
	
	}


}
