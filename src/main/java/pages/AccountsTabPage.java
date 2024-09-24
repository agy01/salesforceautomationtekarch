package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.ActionUtils;
import utils.FileUtils;
import utils.waitUtils;

public class AccountsTabPage extends BasePage {
	
	public AccountsTabPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@title= \"New\"]")
	public WebElement newButton;
		@FindBy(xpath = "//input[@id= \"acc2\"]")
		public WebElement accountName;
		@FindBy(xpath = "//td[@id= \"topButtonRow\"]/child::input[1]")
		public WebElement saveAccount;
		@FindBy(xpath = "//div[@class= \"textBlock\"]")
		public WebElement accountCreated;
	
	@FindBy(linkText = "Create New View")
	public WebElement createNewView;
		@FindBy(id = "fname")
		public WebElement viewName;
		@FindBy(id = "devname")
		public WebElement viewUniqueName;
		@FindBy(xpath = "//td[@class=\"pbButtonb\"]/child::input[@data-uidsfdc=\"4\"]")
		public WebElement saveNewView;
		
	@FindBy(id = "fcf")
	public WebElement viewOptions;
		
	
	
	@FindBy(xpath = "//span[@class=\"fFooter\"]/a[1]")
	public WebElement editButton;
		@FindBy(id = "fcol1")
		public WebElement fieldDropDown;
		@FindBy(id = "fop1")
		public WebElement operatorDropDown;
		@FindBy(id = "fval1")
		public WebElement valueInput;
		@FindBy(id = "colselector_select_0")
		public WebElement availableFields;
		@FindBy(id = "colselector_select_1")
		public WebElement selectedFields;
		@FindBy(xpath = "//img[@title=\"Add\"]")
		public WebElement addButton;
		@FindBy(xpath = "//td[@class=\"pbButtonb\"]/child::input[@data-uidsfdc=\"5\"]")
		public WebElement saveButton;
	
	@FindBy(xpath = "//a[contains(text(), \"Merge Accounts\")]")
	public WebElement mergeAccounts;
		@FindBy(id = "srch")
		public WebElement findAccountsTextBox;
		@FindBy(xpath = "//input[@name=\"srchbutton\"]")
		public WebElement findAccountButton;
		@FindBy(xpath = "//table[@class=\"list\"]//input[@type=\"checkbox\"]")
		public List<WebElement> checkboxAccounts;
		@FindBy(xpath = "//div[@class=\"pbTopButtons\"]/input[1]")
		public WebElement nextButton;
		@FindBy(xpath = "//div[@class=\"pbTopButtons\"]/input[2]")
		public WebElement mergeButton;
		
	public void clickOnNewButton() {
		this.newButton.click();
	}
	
	//Method will enter the details to create new account and validates
	public boolean validateAccountCreated(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isAccountCreated = false;
		String accountNameToCreate = FileUtils.readAccountsTabPropertiesFile("createaccount.accountname");
		this.accountName.sendKeys(accountNameToCreate);
		this.saveAccount.click();
		waitUtils.waitForPageToLoad(driver, 20);
		if(driver.getTitle().contains(accountNameToCreate)) {
			isAccountCreated = true;
		}
		return isAccountCreated;
	}
	
	//Method will enter the details to create new view and validates
	public boolean validateCreateNewView(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isNewViewCreated = false;
		this.createNewView.click();
		String viewName = FileUtils.readAccountsTabPropertiesFile("createnewview.viewname");
		this.viewName.sendKeys(viewName);
		this.viewUniqueName.clear();
		this.viewUniqueName.sendKeys(FileUtils.readAccountsTabPropertiesFile("createnewview.viewuniquename"));
		this.saveNewView.click();
		waitUtils.waitForPageToLoad(driver, 20);
		String expectedTitle = FileUtils.readAccountsTabPropertiesFile("creatednewview.title");
		if(driver.getTitle().contains(expectedTitle)) {
			isNewViewCreated = true;
		}
		return isNewViewCreated;
	}
	
	//Method to select the view from the viewDropDown
	public boolean selectViewFromViewDropDown(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isEditPageOpened = false;
		this.viewOptions.click();
		ActionUtils.selectElementByVisibleText(this.viewOptions,
				FileUtils.readAccountsTabPropertiesFile("createnewview.viewname"));
		this.editButton.click();
		String expectedEditPageTitle = FileUtils.readAccountsTabPropertiesFile("edit.title");
		if (driver.getTitle().contains(expectedEditPageTitle)) {
			isEditPageOpened = true;
		}
		return isEditPageOpened;

	}
	
	//Method to edit the view selected from the drop down and validate
	public boolean editSelectedView(WebDriver driver) throws FileNotFoundException, IOException {
		this.viewName.clear();
		this.viewName.sendKeys(FileUtils.readAccountsTabPropertiesFile("edit.change.viewname"));
		this.fieldDropDown.click();
		ActionUtils.selectElementByVisibleText(this.fieldDropDown, FileUtils.readAccountsTabPropertiesFile("edit.field"));
		this.operatorDropDown.click();
		ActionUtils.selectElementByVisibleText(this.operatorDropDown, FileUtils.readAccountsTabPropertiesFile("edit.operator"));
		this.valueInput.sendKeys(FileUtils.readAccountsTabPropertiesFile("edit.value"));
		boolean isLastActivityAdded = ActionUtils.isElementPresentInList(this.selectedFields, FileUtils.readAccountsTabPropertiesFile("addto.selectedField"));
		if(!isLastActivityAdded) {
			ActionUtils.selectElementInList(this.availableFields, FileUtils.readAccountsTabPropertiesFile("addto.selectedField"));
			this.addButton.click();
			this.saveButton.click();
			System.out.println("Last Activity is added to Selected fields");
		}else {
			System.out.println("LastActivity was already in Selected fields");
			return isLastActivityAdded;
		}
		boolean isChangeSaved = false;
		String expectedPageTitle = FileUtils.readAccountsTabPropertiesFile("creatednewview.title");
		if(driver.getTitle().contains(expectedPageTitle)) {
			System.out.println("Changes updated");
			isChangeSaved = true;
		}
		return isChangeSaved;
	}

	//Method to select 2 accounts to merge
	public boolean selectAccountsToMerge(WebDriver driver) throws FileNotFoundException, IOException {
		this.mergeAccounts.click();
		this.findAccountsTextBox.sendKeys(FileUtils.readAccountsTabPropertiesFile("mergeaccounts.findaccount"));
		this.findAccountButton.click();
		ActionUtils.selectAllCheckboxes(this.checkboxAccounts);
		this.nextButton.click();
		boolean isAccountsSelectedToMerged = false;
		String expectedTitle = FileUtils.readAccountsTabPropertiesFile("mergeaccountselected.title");
		if (driver.getTitle().contains(expectedTitle)) {
			isAccountsSelectedToMerged = true;
		}
		return isAccountsSelectedToMerged;
	}
	
	//Method to merge 2 account by handling pop up alert
	public boolean mergeSelectedAccounts(WebDriver driver) throws FileNotFoundException, IOException {
		this.mergeButton.click();
		try {
			Alert alert = driver.switchTo().alert();
			String altertText = alert.getText();
			System.out.println("Alert Text: " + altertText);
			alert.accept();
		} catch (Exception e) {
			System.out.println("No Alerts found: " + e.getMessage());
		}
		boolean areAccountsMerged = false;
		String expectedTitle = FileUtils.readAccountsTabPropertiesFile("accountstab.title");
		if(driver.getTitle().contains(expectedTitle)) {
			areAccountsMerged = true;
		}
		return areAccountsMerged;
	}
}
