package tests;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.LoginPage;
import pages.HomePage;

public class TC36_BlockingAnEventInTheCalender {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage mainHome = new HomePage(driver);
		
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		lp.username.sendKeys("anusha.gy1465@gmail.com");
		lp.password.sendKeys("Admin@123");
		lp.loginButton.click();
		
		//Click Plus icon
		Thread.sleep(1000);
		mainHome.HomeTab.click();
		WebElement dateLink = driver.findElement(By.xpath("//span[@class=\"pageDescription\"]/child::a"));
		dateLink.click();
		
		WebElement Time8PMLink = driver.findElement(By.xpath("//div[@id=\"p:f:j_id25:j_id61:28:j_id64\"]/a"));
		Time8PMLink.click();
		
		WebElement SubjectCombo = driver.findElement(By.className("comboboxIcon"));
		SubjectCombo.click();
		String MainWindow = driver.getWindowHandle();
		Set<String> getAllWindows = driver.getWindowHandles();
		getAllWindows.remove(MainWindow);
		String newHandle = getAllWindows.iterator().next();
		Thread.sleep(5000);
		driver.switchTo().window(newHandle);
		Thread.sleep(5000);
		WebElement OthersOption = driver.findElement(By.xpath("//a[@href=\"javascript:pickValue(4);\"]"));
		OthersOption.click();
		Thread.sleep(2000);
		driver.switchTo().window(MainWindow);
		
		WebElement End_Time = driver.findElement(By.id("EndDateTime_time"));
		End_Time.click();
		
		WebElement End_Timeline = driver.findElement(By.xpath("//div[@id=\"simpleTimePicker\"]/child::div[43]"));
		End_Timeline.click();
		
		driver.quit();
		
		System.out.println("TC36_BlockingAnEventInTheCalender testing is completed");
	}

}
