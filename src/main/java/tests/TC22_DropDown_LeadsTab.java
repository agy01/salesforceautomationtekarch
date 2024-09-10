package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC22_DropDown_LeadsTab {

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
		
		//Click and validate Leads tab
		Thread.sleep(2000);
		mainHome.LeadsTab.click();
		
		ActionUtils.titleMatch(driver, "Leads: Home ~ Salesforce - Developer Edition", "Leads Home page is displayed", "Leads Home page is not displayed");

		String menuList = ActionUtils.dropdownlist(driver, By.id("fcf"));
		String[] ExpectedList = {"All Open Leads", "My Unread Leads", "Recently Viewed Leads", "Today's Leads"};
		
		ActionUtils.dropdownlistValidate(driver, ExpectedList, menuList);
		
		driver.quit();
		System.out.println("TC22_DropDown_LeadsTab testing is completed");
		
	}

}
