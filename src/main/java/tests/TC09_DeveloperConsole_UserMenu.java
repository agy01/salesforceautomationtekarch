package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;

public class TC09_DeveloperConsole_UserMenu extends BaseTest{

		@Test
		public void validateDeveloperConsoleOption() throws FileNotFoundException, IOException, InterruptedException {
		
		//Launch and login to the application
		lp.launchApplication();
		lp.loginToApplication();
		
		//Click user menu drop down and validate if all expected options are present
		hp.clickUserMenu();
		Assert.assertTrue(hp.areAllOptionsPresent(), "All options should be present as expected"); 
		
		//Select Developer console from the options of user menu
		hp.goToDeveloperConsole();
		
		//Validates Window pops up and switch to the new window
		Assert.assertTrue(devConsole.switchToDeveloperConsole(), "Developer console window should be displayed");
		
		//Validates Window title
		Assert.assertTrue(devConsole.validateDevConsoleWindowTitle(), "Dev console window title should match with the expected title");
		Thread.sleep(2000);
		
		//Window closed and switch back to main window
		devConsole.closeTheDeveloperConsole();
		
		System.out.println("TC09_DeveloperConsole_UserMenu Testing completed");
	}

}
