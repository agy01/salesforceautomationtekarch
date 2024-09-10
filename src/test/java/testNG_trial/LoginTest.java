package testNG_trial;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import pages.LoginPage;

public class LoginTest {

	@Test
	public void testMethod() {
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		driver.navigate().to("https://login.salesforce.com");
		lp.username.sendKeys("anusha.gy1465@gmail.com");
	}
	
	@Test
	public void testComplete() {
		System.out.println("Testing Completed");
	}

}
