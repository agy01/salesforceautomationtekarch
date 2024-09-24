package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.ActionUtils;
import utils.waitUtils;

public class DeveloperConsole extends BasePage{
	
	public DeveloperConsole(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//body[@id=\"ext-gen1361\"]")
	public WebElement developerConsoleWindow;
	
	//Method switch to develop console window and checks if the window is displayed
	public boolean switchToDeveloperConsole(WebDriver driver) {
		boolean isDeveloperConsoleOpened = false;
		try {
			ActionUtils.switchToPopUpWindow(driver);
			waitUtils.waitForElementToBeVisible(driver, this.developerConsoleWindow, 20);
				
			if(this.developerConsoleWindow.isDisplayed()) {
				isDeveloperConsoleOpened = true;
				}
			}catch (Exception e) {
			System.out.println("Exception wile validating the developer console: " +e.getMessage());
			}
		return isDeveloperConsoleOpened;	
	}
	
	
	
	//Method switch back to the main window
	public void closeTheDeveloperConsole(WebDriver driver) {
		ActionUtils.switchBackToMainWindow(driver);
	}

}
