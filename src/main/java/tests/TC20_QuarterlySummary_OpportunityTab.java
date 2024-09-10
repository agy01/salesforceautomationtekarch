package tests;

import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC20_QuarterlySummary_OpportunityTab {

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
		
		//Click and validate opportunity tab
		Thread.sleep(2000);
		mainHome.OpportunitiesTab.click();
		
		ActionUtils.titleMatch(driver, "Opportunities: Home ~ Salesforce - Developer Edition", "Opportunities home page is displayed", "Opportunities home page is not displayed");
		
		Select Interval = new Select(driver.findElement(By.id("quarter_q")));
		Interval.selectByVisibleText("Current FQ");
		
		Select Include = new Select(driver.findElement(By.id("open")));
		Include.selectByVisibleText("All Opportunities");
			
		WebElement RunReport = driver.findElement(By.xpath("//input[@value=\"Run Report\"]"));
		RunReport.click();
		
		ActionUtils.titleMatch(driver, "Opportunity Report ~ Salesforce - Developer Edition", "Report Generated", "Report did not generate");
		
//		Select Interval1 = new Select(driver.findElement(By.id("quarter_q")));
//		Interval1.selectByVisibleText("Next FQ");
//		
//		Select Include1 = new Select(driver.findElement(By.id("open")));
//		Include1.selectByVisibleText("Open Opportunities");
//		
//		String actualTitle2 = driver.getTitle();
//		System.out.println(actualTitle2);
//		
//		Select Interval2 = new Select(driver.findElement(By.id("quarter_q")));
//		Interval2.selectByVisibleText("Current FQ");
//		
//		Select Include2 = new Select(driver.findElement(By.id("open")));
//		Include2.selectByVisibleText("Closed Opportunities");
		
//		String actualTitle3 = driver.getTitle();
//		System.out.println(actualTitle3);
		driver.close();
		System.out.println("TC20_QuarterlySummary_OpportunityTab is completed");
	}

}
