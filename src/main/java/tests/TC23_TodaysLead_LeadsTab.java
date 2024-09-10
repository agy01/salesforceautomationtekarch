package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.google.j2objc.annotations.GenerateObjectiveCGenerics;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC23_TodaysLead_LeadsTab {

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
		
		Thread.sleep(2000);
//		WebElement ViewDropdown = driver.findElement(By.id("//select[@id='fcf']"));
//		ViewDropdown.click();
		ActionUtils.mouseHover(driver, driver.findElement(By.xpath("//select[@id=\"fcf\"]")));
		Select View = new Select(driver.findElement(By.xpath("//select[@id=\"fcf\"]")));
		View.selectByIndex(3);
		
		Thread.sleep(2000);
		
		WebElement UserMenu = driver.findElement(By.id("userNav-arrow"));
		UserMenu.click();
		
		ActionUtils.mouseHover(driver, driver.findElement(By.xpath("//a[@title=\"Logout\"]")));
		
		Thread.sleep(2000);
		ActionUtils.titleMatch(driver, "Login | Salesforce", "Logged out successfully", "Logout was not successfull");
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		Thread.sleep(2000);
		mainHome.LeadsTab.click();
		
		WebElement goButton = driver.findElement(By.xpath("//input[@title=\"Go!\"]"));
		goButton.click();
		
		Thread.sleep(2000);
		ActionUtils.titleMatch(driver, "Leads ~ Salesforce - Developer Edition", "Today's Lead Report is displayed", "Report was not displayed");
		
		driver.quit();
		System.out.println("TC23_TodaysLead_LeadsTab testing is completed");
	}

}
