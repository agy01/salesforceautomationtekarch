package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.ActionUtils;
import utils.FileUtils;
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
	
	//Method to validate the developer console window title
	public boolean validateDevConsoleWindowTitle() throws FileNotFoundException, IOException {
		boolean isWindowTitleMatched = false;
		String expectedTitle = FileUtils.readDeveloperConsolePropertiesFile("devconsole.windowtitle");
		if(driver.getTitle().contains(expectedTitle)) {
			isWindowTitleMatched = true;
		}
		return isWindowTitleMatched;
	}
	
	//Method switch back to the main window
	public void closeTheDeveloperConsole() {
		ActionUtils.switchBackToMainWindow(driver);
	}

}
