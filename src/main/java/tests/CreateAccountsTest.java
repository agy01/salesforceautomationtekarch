package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listeners.ListenersSFDC;
import pages.AccountsTabPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;

@Listeners(ListenersSFDC.class)
public class CreateAccountsTest extends BaseTest {

	protected LoginPage lp = null;
	protected HomePage hp = null;
	protected AccountsTabPage accountsTab = null;
	
	@BeforeMethod
	public void homePage() {
		lp = new LoginPage(getDriver());
		hp = new HomePage(getDriver());
		accountsTab = new AccountsTabPage(getDriver());
	}
	
	@Test
	public void CreateAccountUnderAccountsTab() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		hp.clickOnAccountsTab(driver);
		test.get().info("Click on Accounts tab");
		hp.validateAccountsTabPage(driver);
		test.get().info("Validate Accounts tab page title");
		accountsTab.clickOnNewButton();
		test.get().info("Clicked on new button");
		accountsTab.validateAccountCreated(driver);
		test.get().info("Validate if new account created");
		System.out.println("TC11_CreateAccount_AccountTab Testing is completed");

	}

	@Test
	public void validateCreateNewViewUnderAccountsTab()
			throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		hp.clickOnAccountsTab(driver);
		test.get().info("Click on Accounts tab");
		hp.validateAccountsTabPage(driver);
		test.get().info("Validate Accounts tab page title");
		Thread.sleep(2000);
		accountsTab.validateCreateNewView(driver);
		test.get().info("Validate if new view is created");
		System.out.println("TC12_CreateNewView_AccountsTab Testing is completed");
	}

	@Test
	public void validateEditViewUnderAccountsTab() throws FileNotFoundException, IOException, InterruptedException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		hp.clickOnAccountsTab(driver);
		test.get().info("Click on Accounts tab");
		hp.validateAccountsTabPage(driver);
		test.get().info("Validate Accounts tab page title");
		accountsTab.selectViewFromViewDropDown(driver);
		test.get().info("Select the view from the drop down to edit");
		accountsTab.editSelectedView(driver);
		test.get().info("Changes made in the view page should be saved");
		System.out.println("TC13_EditView_AccountsTab Testing completed");
	}

	@Test
	public void MergeAccountsUnderAccountsTab() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().info("Launching Application");
		lp.launchApplication(driver);
		test.get().info("Validating Application title");
		lp.validatePageTitle(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		test.get().info("Entering valid creads...");
		lp.loginToApplication(driver);
		hp.validateHomePageTitle(driver);
		test.get().info("Validate home page title");
		hp.clickOnAccountsTab(driver);
		test.get().info("Click on Accounts tab");
		hp.validateAccountsTabPage(driver);
		test.get().info("Validate Accounts tab page title");
		accountsTab.selectAccountsToMerge(driver);
		test.get().info("Accounts are selected to merge");
		accountsTab.mergeSelectedAccounts(driver);
		test.get().info("Accounts should be merged");
		System.out.println("TC14_MergeAccounts_AccountTab Testing completed");

	}

}
