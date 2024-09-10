package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;

public class TC05_ValidateInvalidCredErrorMessage extends BaseTest{

		@Test
		public void validateInvalidCreds() throws FileNotFoundException, IOException {
		
		//Launch Application and Validate
		lp.launchApplication();
		lp.validatePageTitle();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Enter Invalid username and validate
		String expectedUsername = FileUtils.readLoginPropertiesFile("invalid.username");
		lp.enterUserName(expectedUsername);
		String actualUsername = ActionUtils.getElementAttribute(lp.username);
		Assert.assertEquals(actualUsername, expectedUsername, "Actual and Expected Username should be same");
		
		//Enter invalid password and validate
		String expectedPassword = FileUtils.readLoginPropertiesFile("invalid.password");
		lp.enterPassword(expectedPassword);
		String actualPassword = ActionUtils.getElementAttribute(lp.password);
		Assert.assertEquals(actualPassword, expectedPassword, "Actual and Expected password should be same");
		
		//Login for the given creds
		lp.clickLogin();
		
		//Validate the error message
		String InvalidCredsErrorMessage = lp.errorMessage();
		Assert.assertEquals(InvalidCredsErrorMessage, "Please check your username and password. If you still can't log in, contact your Salesforce administrator.", "Actual and Expected Error message should be same");
		
		System.out.println("TC05_ValidateInvalidCredErrorMessage Testing is completed");
		}

}
