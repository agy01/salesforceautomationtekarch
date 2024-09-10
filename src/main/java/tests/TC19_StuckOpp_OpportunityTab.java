package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC19_StuckOpp_OpportunityTab {

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
		
		WebElement StuckOpp = driver.findElement(By.linkText("Stuck Opportunities"));
		StuckOpp.click();
		
		ActionUtils.titleMatch(driver, "Stuck Opportunities ~ Salesforce - Developer Edition", "Report is displayed", "Report did not display");
		
		driver.quit();
		System.out.println("TC19_StuckOpp_OpportunityTab is completed");
	
	}

}
