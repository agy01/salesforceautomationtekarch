package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;


import org.testng.Assert;
import org.testng.annotations.Test;

import utils.ActionUtils;
import utils.BaseTest;
import utils.FileUtils;

public class TC01_LoginErrorWithoutPassword extends BaseTest{

				
		@Test
		public void loginWithoutPassword() throws FileNotFoundException, IOException{
		
		//Launch Application and validate
		lp.launchApplication();
		lp.validatePageTitle();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//Entering and Validating the username
		String expectedUsername = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUserName(expectedUsername);
		String actualUsername = ActionUtils.getElementAttribute(lp.username);
		Assert.assertEquals(actualUsername, expectedUsername, "Actual and Expected Username should be same");
		
		//Entering and Validating the password
		lp.password.clear();
		String actualPassword = ActionUtils.getElementAttribute(lp.password);
		Assert.assertEquals(actualPassword, "", "Actual and Expected Password should be same");
		lp.clickLogin();
		
		//Validating the error message
		Assert.assertEquals(lp.errorMessage(), FileUtils.readLoginPropertiesFile("error.message"), "Actual and Expected Error Message should be same");
				
		System.out.println("TC01_LoginErrorWithoutPassword testing is completed");
	}

}
