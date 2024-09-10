package tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import pages.AccountsTabPage;
import pages.LoginPage;
import pages.HomePage;

public class TC13_EditView_AccountsTab {

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
		
		//Select the first option from the dropdown
		Select viewDropDown = new Select(driver.findElement(By.id("fcf")));
		viewDropDown.selectByIndex(1);
		
		//Click on Edit button and validate
		WebElement EditButton = driver.findElement(By.linkText("Edit"));
		EditButton.click();
		
		String actualTitle2 = driver.getTitle();
		String expectedTitle2 = "Accounts: Edit View ~ Salesforce - Developer Edition";
		
		if(actualTitle2.equals(expectedTitle2)) {
			System.out.println("Accounts EditView Page is displayed");
		}else {
			System.out.println("EditView page is not displayed");
		}
		
		//Change the viewName to new view name
		WebElement newViewName = driver.findElement(By.id("fname"));
		newViewName.clear();
		newViewName.sendKeys("PQRS");
		
		//selecting the options as per the test case
		Select field = new Select(driver.findElement(By.id("fcol1")));
		field.selectByVisibleText("Account Name");
		
		Select operator = new Select(driver.findElement(By.id("fop1")));
		operator.selectByVisibleText("contains");
		
		WebElement value = driver.findElement(By.id("fval1"));
		value.clear();
		value.sendKeys("a");
		
		//checks if the element Last Activity is available in the AvailableField and add to selected field
		Select AvailableFields = new Select(driver.findElement(By.id("colselector_select_0")));
//		AvailableFields.selectByVisibleText("Last Activity");
		List<WebElement> availablefildDropDown = AvailableFields.getOptions();
		
		Boolean ElementAvailable = false;
		
		for(int i = 0; i < availablefildDropDown.size(); i++) {
			if(availablefildDropDown.get(i).getText().equals("Last Activity")) {
				ElementAvailable = true;
				WebElement AddButton = driver.findElement(By.xpath("//a[@id=\"colselector_select_0_right\"]/child::img"));
				AddButton.click();
				break;
			}
		}
		
		//if element is already in the selected field proceed furthur
		if(ElementAvailable = false) {
			Select SelectedField = new Select(driver.findElement(By.id("colselector_select_1")));
			List<WebElement> selectedFieldDropDown = SelectedField.getOptions();
			for(int i = 0; i < selectedFieldDropDown.size(); i++) {
				if(selectedFieldDropDown.get(i).getText().equals("Last Activity")) {
					break;
				}
			}
		}
		System.out.println("Last Activity is added to selected field");
//		WebElement addButton = driver.findElement(By.xpath("//a[@id=\"colselector_select_0_right\"]/child::img"));
//		addButton.click();
		
		WebElement saveButton = driver.findElement(By.xpath("//td[@class=\"pbButtonb\"]/child::input[@data-uidsfdc=\"5\"]"));
		saveButton.click();
		
		driver.quit();
		System.out.println("End of test case TC13_EditView_AccountsTab");
		

	}

}
