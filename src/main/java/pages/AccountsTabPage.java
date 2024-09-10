package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountsTabPage {
	
	public AccountsTabPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name = "new")
	public WebElement NewButton;
	
	@FindBy(linkText = "Create New View")
	public WebElement CreateNewView;
		@FindBy(id = "fname")
		public WebElement viewName;
		@FindBy(id = "devname")
		public WebElement viewUniqueName;
		@FindBy(xpath = "//td[@class=\"pbButtonb\"]/child::input[@data-uidsfdc=\"4\"]")
		public WebElement SaveButton;
	
	

}
