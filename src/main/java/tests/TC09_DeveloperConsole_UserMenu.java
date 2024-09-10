package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;
import utils.waitUtils;

public class TC09_DeveloperConsole_UserMenu extends BaseTest{

		@Test
		public void validateDeveloperConsoleOption() throws FileNotFoundException, IOException, InterruptedException {
		
		//Launch and login to the application
		driver.navigate().to(FileUtils.readLoginPropertiesFile("prod.url"));
		lp.loginToApplication(driver, FileUtils.readLoginPropertiesFile("valid.username"), FileUtils.readLoginPropertiesFile("valid.password"));
		
		waitUtils.waitForElementToBeClickable(driver, hp.userMenuDropDown, 20);
		//Click user menu drop down and validate if all expected options are present
		hp.clickUserMenu();
		hp.areAllOptionsPresent();
		
		//Select Developer console from the options of user menu
		hp.goToDeveloperConsole();
		
		//Validates Window pops up and switch to the new window
		boolean isDeveloperConsoleOpened = devConsole.switchToDeveloperConsole();
		Assert.assertTrue(isDeveloperConsoleOpened, "Developer console window should be opened");
		
		//Validates Window title
		Assert.assertTrue(driver.getTitle().contains("Developer Console"), "Window title should contain Developer console");
		Thread.sleep(2000);
		
		//Window closed and switch back to main window
		devConsole.closeTheDeveloperConsole();
		
		System.out.println("TC09_DeveloperConsole_UserMenu Testing completed");
	}

}
