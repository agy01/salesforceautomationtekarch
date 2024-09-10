package tests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;
import utils.ActionUtils;

public class TC17_CreateNewOpp_OpportunityTab {

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
		
		//Click on new button to create new opportunity
		WebElement NewButton = driver.findElement(By.xpath("//td[@class=\"pbButton\"]/child::input"));
		NewButton.click();
		
		WebElement OpportunityName = driver.findElement(By.id("opp3"));
		OpportunityName.clear();
		OpportunityName.sendKeys("EFGHI");
		
//		WebElement AccountName = driver.findElement(By.id("opp4"));
//		AccountName.clear();
//		AccountName.sendKeys("Sept2024");
		
		WebElement AccountNameLookUp = driver.findElement(By.className("lookupIcon"));
		AccountNameLookUp.click();
		String MainWindow = driver.getWindowHandle();
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		driver.switchTo().window(getWindow[1]);
		Thread.sleep(3000);
		driver.switchTo().frame("searchFrame");
		driver.findElement(By.id("lksrch")).sendKeys("Yuva");
		driver.findElement(By.xpath("//input[@name='go']")).click();
		driver.switchTo().defaultContent();
		
		
		driver.switchTo().frame("resultsFrame");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//tr[@class=\"dataRow even first\"]/th/a")).click();
		
		driver.switchTo().window(MainWindow);
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("opp9")).click();
		ActionUtils.mouseHover(driver, driver.findElement(By.className("calToday")));
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		
		Select Stage = new Select(driver.findElement(By.id("opp11")));
		Stage.selectByVisibleText("Qualification");
		
		WebElement Probablity = driver.findElement(By.id("opp12"));
		Probablity.clear();
		Probablity.sendKeys("100");
		
		Select LeadSource = new Select(driver.findElement(By.id("opp6")));
		LeadSource.selectByVisibleText("Web");
		
		WebElement SaveButton = driver.findElement(By.xpath("//td[@id=\"topButtonRow\"]/child::input[1]"));
		SaveButton.click();
		
		Thread.sleep(2000);
		ActionUtils.titleMatch(driver, "Opportunity: EFGHI ~ Salesforce - Developer Edition", "New Opp was created", "New opp could not be created");
		
		driver.quit();
		System.out.println("TC17_CreateNewOpp_OpportunitiesTab is completed");
	}

}
