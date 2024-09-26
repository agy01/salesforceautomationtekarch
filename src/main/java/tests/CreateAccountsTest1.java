package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.ListenersSFDC;
import pages.AccountsTabPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

@Listeners(ListenersSFDC.class)
public class CreateAccountsTest1 extends BaseTest{
	
	@Test
	public void CreateAccountsUnderAccountsTab() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match"); 
		test.get().info("Clicking on Accounts tab...");
		hp.clickOnAccountsTab(getDriver());
		test.get().info("Validate Accounts tab page title");
		Assert.assertTrue(hp.validateAccountsTabPage(getDriver()), "Actual and Expected accounts tab page title should match"); 
		AccountsTabPage accountsTab = new AccountsTabPage(getDriver());
		test.get().info("Clicking on new button...");
		accountsTab.clickOnNewButton();
		test.get().info("Validate if new account created");
		Assert.assertTrue(accountsTab.validateAccountCreated(getDriver()), "New Account should be created");
		System.out.println("TC11_CreateAccountsUnderAccountsTab Testing is completed");
		logger.info("TC11_CreateAccountsUnderAccountsTab testing is completed");
	}
	
	@Test
	public void validateCreateNewViewUnderAccountsTab() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match"); 
		test.get().info("Clicking on Accounts tab...");
		hp.clickOnAccountsTab(getDriver());
		test.get().info("Validate Accounts tab page title");
		Assert.assertTrue(hp.validateAccountsTabPage(getDriver()), "Actual and Expected accounts tab page title should match"); 
		AccountsTabPage accountsTab = new AccountsTabPage(getDriver());
		test.get().info("Validating new view created..");
		accountsTab.validateCreateNewView(getDriver());
		System.out.println("TC12_validateCreateNewViewUnderAccountsTab Testing is completed");
		logger.info("TC12_validateCreateNewViewUnderAccountsTab testing is completed");

	}
	
	@Test
	public void validateEditViewUnderAccountsTab() throws FileNotFoundException, IOException, InterruptedException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match"); 
		test.get().info("Clicking on Accounts tab...");
		hp.clickOnAccountsTab(getDriver());
		test.get().info("Validate Accounts tab page title");
		Assert.assertTrue(hp.validateAccountsTabPage(getDriver()), "Actual and Expected accounts tab page title should match"); 
		AccountsTabPage accountsTab = new AccountsTabPage(getDriver());
		test.get().info("Selecting the view from the drop down to edit...");
		accountsTab.selectViewFromViewDropDown(getDriver());
		accountsTab.editSelectedView(getDriver());
		test.get().info("Changes made in the view page should be saved");
		System.out.println("TC13_validateEditViewUnderAccountsTab Testing completed");
		logger.info("TC13_validateEditViewUnderAccountsTab testing is completed");
	}
	
	@Test
	public void MergeAccountsUnderAccountsTab() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match"); 
		test.get().info("Clicking on Accounts tab...");
		hp.clickOnAccountsTab(getDriver());
		test.get().info("Validate Accounts tab page title");
		Assert.assertTrue(hp.validateAccountsTabPage(getDriver()), "Actual and Expected accounts tab page title should match"); 
		AccountsTabPage accountsTab = new AccountsTabPage(getDriver());
		test.get().info("Selecting Accounts to merge...");
		accountsTab.selectAccountsToMerge(getDriver());
		test.get().info("Validting Accounts merged...");
		accountsTab.mergeSelectedAccounts(getDriver());
		System.out.println("TC14_MergeAccountsUnderAccountsTab Testing completed");
		logger.info("TC14_MergeAccountsUnderAccountsTab testing is completed");
	}
	
	@Test
	public void createAccountsReport() throws FileNotFoundException, IOException {
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		LoginPage lp = new LoginPage(getDriver());
		test.get().info("Launching Application");
		lp.launchApplication(getDriver());
		test.get().info("Validating Application title");
		Assert.assertTrue(lp.validatePageTitle(getDriver()), "Actual and Expected page title should match");
		getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(getDriver());
		HomePage hp = new HomePage(getDriver());
		test.get().info("Validate home page title");
		Assert.assertTrue(hp.validateHomePageTitle(getDriver()), "Actual and Expected Home page title should match"); 
		test.get().info("Clicking on Accounts tab...");
		hp.clickOnAccountsTab(getDriver());
		test.get().info("Validate Accounts tab page title");
		Assert.assertTrue(hp.validateAccountsTabPage(getDriver()), "Actual and Expected accounts tab page title should match"); 
		AccountsTabPage accountsTab = new AccountsTabPage(getDriver());
		test.get().info("Clicking on last Activity link");
		accountsTab.clickOnLastActivityLink();
		test.get().info("Validate last activity page title");
		accountsTab.validateLastActivityReport(getDriver());
		test.get().info("Clicking on date field and select created date option");
		accountsTab.selectDateField();
		test.get().info("Select Today button in from field");
		accountsTab.selectTodaysDateinFromField();
		test.get().info("Select Today button in To field");
		accountsTab.selectTodayInToField(getDriver());
		test.get().info("Clicking on save button in the unsaved report");
		accountsTab.saveUnsavedReport();
		test.get().info("Switch to pop up window and edit save report details");
		accountsTab.switchToPopUpWindow(getDriver());
		test.get().info("Validate if report is saved");
		accountsTab.validateSavedReport();
		System.out.println("TC15_createAccountsReport Testing completed");
		logger.info("TC15_createAccountsReport testing is completed");
	}

}
