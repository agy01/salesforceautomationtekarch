package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ActionUtils;
import utils.waitUtils;

public class DeveloperConsole {
	
	protected WebDriver driver;
	
	public DeveloperConsole(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//body[@id=\"ext-gen1361\"]")
	public WebElement developerConsoleWindow;
	
	//Method switch to develop console window and checks if the window is displayed
	public boolean switchToDeveloperConsole() {
		try {
			ActionUtils.switchToPopUpWindow(driver);
			waitUtils.waitForElementToBeVisible(driver, this.developerConsoleWindow, 20);
				
			if(this.developerConsoleWindow.isDisplayed()) {
				return true;
				}
			}catch (Exception e) {
			System.out.println("Exception wile validating the developer console: " +e.getMessage());
			return false;
			}
		return false;	
	}
	
	//Method switch back to the main window
	public void closeTheDeveloperConsole() {
		ActionUtils.switchBackToMainWindow(driver);
	}

}
