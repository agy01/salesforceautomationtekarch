package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openxmlformats.schemas.drawingml.x2006.chart.STRadarStyle;

import pages.AccountsTabPage;
import pages.LoginPage;
import pages.HomePage;

public class TC12_CreateNewView_AccountsTab {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		AccountsTabPage accounts = new AccountsTabPage(driver);
		
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//validating is user logged in
		String actualTitle = driver.getTitle();
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		
		Thread.sleep(2000);
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("User Logged in successfully");
		}else {
			System.out.println("User was not able to login");
		}
		
		//Open Accounts tab and validate
		mainHome.AccountsTab.click();
		String actualTitle1 = driver.getTitle();
		System.out.println(actualTitle1);
		String expectedTitle1 = "Accounts: Home ~ Salesforce - Developer Edition";
		if(actualTitle1.equals(expectedTitle1)) {
			System.out.println("Accounts Home page is displayed");
		}else {
			System.out.println("Accounts Home page is not displayed");
		}
		
		//Creating a new view under accounts Tab and validate
		//need to change the input values everytime executed
		accounts.CreateNewView.click();
		accounts.viewName.sendKeys("ABCD");
		accounts.viewUniqueName.clear();
		accounts.viewUniqueName.sendKeys("aabccd");
		accounts.SaveButton.click();
		
		String actualTitle3 = driver.getTitle();
		String expectedTitle3 = "Accounts ~ Salesforce - Developer Edition";
		if(actualTitle3.equals(expectedTitle3)) {
			System.out.println("Newly added view is displayed");
		}else {
			System.out.println("View was not added");
		}
		
		driver.quit();
	}

}
