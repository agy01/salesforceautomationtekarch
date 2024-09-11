package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import utils.BaseTest;
import utils.FileUtils;

public class TC04_ValidateForgotPassword extends BaseTest{

		@Test
		public void validateForgotPassword() throws FileNotFoundException, IOException {
		
		//Launch Application and validate
		lp.launchApplication();
		lp.validatePageTitle();
				
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//click on forgot your password and validate
		lp.clickForgotPassword();
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("forgotPassword.title"),"Forgot Password Page should be displayed");
		
		//enter username in forgot password page and click continue(calling from loginPage modular function)
		lp.enterUserNameInForgotPasswordPage();
		
		//validate if email send to user
		Assert.assertEquals(driver.getTitle(), FileUtils.readLoginPropertiesFile("checkEmail.title"), "Password reset email should be sent to the user");
		
		System.out.println("TC04_ValidateForgotPassword Testing is completed");

	}

}
