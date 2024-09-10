package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC16_OpportunitiesTab {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
				
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		Thread.sleep(2000);
		mainHome.OpportunitiesTab.click();
		
		ActionUtils.titleMatch(driver, "Opportunities: Home ~ Salesforce - Developer Edition", "Opportunities home page is displayed", "Opportunities home page is not displayed");
		
		String menuList = ActionUtils.dropdownlist(driver, By.id("fcf"));
		
		String[] ExpectedList = {"All Opportunities", "Closing Next Month", "Closing This Month", "My Opportunities", "New This Week", "Recently Viewed Opportunities","Won"};
		
		ActionUtils.dropdownlistValidate(driver, ExpectedList, menuList);
		
		driver.quit();
		
		System.out.println("TC16_OpportunitiesTab is completed");
	}

}
