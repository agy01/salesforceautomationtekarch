package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;
import utils.switchTo;

public class TC15_CreateAccountReport_AccountsTab {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		
		//launching the application
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//validating is user logged in
		ActionUtils.titleMatch(driver, "Home Page ~ Salesforce - Developer Edition", "User Logged in successfully", "User was not able to login");
		
		//Open Accounts tab and validate
				Thread.sleep(2000);
				mainHome.AccountsTab.click();
	
				ActionUtils.titleMatch(driver, "Accounts: Home ~ Salesforce - Developer Edition", "Accounts Home page is displayed", "Accounts Home page is not displayed");
		
		//Click on LastACtivity >30 days link and validate
		WebElement AccountActivity = driver.findElement(By.linkText("Accounts with last activity > 30 days"));
		AccountActivity.click();
		
		ActionUtils.titleMatch(driver, "Unsaved Report ~ Salesforce - Developer Edition", "Unsaved Report page is displayed", "Unsaved Report Page is not dispalyed");
		
		//Selecting the date field, from and to date. Save the report and validate
		WebElement DateFieldDropDown = driver.findElement(By.id("ext-gen148"));
		DateFieldDropDown.click();
		
		WebElement CreatedToday = driver.findElement(By.xpath("//div[text()=\"Created Date\"]"));
		CreatedToday.click();
		
		WebElement FromCalender = driver.findElement(By.name("startDate"));
		FromCalender.clear();
		FromCalender.sendKeys("9/2/2024");
		
		WebElement ToCalender = driver.findElement(By.name("endDate"));
		ToCalender.clear();
		ToCalender.sendKeys("9/2/2024");
		
		WebElement SaveButton = driver.findElement(By.id("ext-gen49"));
		SaveButton.click();
				
		ActionUtils.titleMatch(driver, "Unsaved Report ~ Salesforce - Developer Edition", "Pass: List of Accounts displayed", "Fail: List of Accounts not displayed");
		
		//Enter the report name and report unique name. Click save n run
		WebElement ReportName = driver.findElement(By.id("saveReportDlg_reportNameField"));
		ReportName.clear();
		ReportName.sendKeys("Sept1stReport");
		
		WebElement ReportUniqueName = driver.findElement(By.id("saveReportDlg_DeveloperName"));
		ReportUniqueName.clear();
		ReportUniqueName.sendKeys("SeptReport");
		
		WebElement saveAndRun = driver.findElement(By.id("ext-gen285"));
		saveAndRun.click();
		
		ActionUtils.titleMatch(driver, "Unsaved Report ~ Salesforce - Developer Edition", "ReportPage with details is displayed", "ReportPage was not displayed");
		
		driver.quit();
		System.out.println("TC15_CreateAccountReport_AccountsTab is completed");

	}

}
