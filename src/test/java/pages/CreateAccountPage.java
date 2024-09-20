package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class CreateAccountPage extends BasePage{
	public CreateAccountPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//*[@id='Account_Tab']/a")
	public WebElement accounts_Tab;

	@FindBy(xpath = "//a[contains(text(),'Create New View')]")
	public WebElement createNewView;
	
	@FindBy(xpath = "//a[contains(text(), 'Edit')]")
	public WebElement edit;
	
	@FindBy(id = "fname")
	public WebElement viewName;
	
	@FindBy(id = "devname")
	public WebElement viewUniqueName;
	
	@FindBy(name = "fcf")
	public WebElement viewDropDown;
	
	
	@FindBy(xpath = "//*[@id='hotlist']/table/tbody/tr/td[2]/input")
	public WebElement newAccount;
	
	@FindBy(id = "acc2")
	public WebElement accountName;
	
	@FindBy(xpath = "//select[@id='acc6']")
	public WebElement selectType;
	
	@FindBy(xpath = "//select[@id='00N2w00000ZLonD']")
	public WebElement coustomerPriority;

	@FindBy(name = "save")
	public WebElement saveBtn;
	
	@FindBy(id = "fcol1")
	public WebElement fieldDropdown;
	
	@FindBy(id = "fop1")
	public WebElement operatorDropdown;
	
	@FindBy(id = "fval1")
	public WebElement valueInput;
	
	@FindBy(className = "rightArrowIcon")
	public WebElement addArrow;
	
	@FindBy(id = "colselector_select_0")
	public WebElement avaiableFields;
	
	public void clickAccountsLink(WebDriver driver) {
		accounts_Tab.click();
		logger.info("'Accounts' link is clicked.");
	}
	
	public boolean isAccountsPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isAccountsPage = false;		
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualAccountsPageTitle = driver.getTitle();
		String expectedAccountsPageTitle = FileUtils.readAccountsPagePropertiesFile("accountsPageTitle");
		if(actualAccountsPageTitle.equals(expectedAccountsPageTitle)) {
			isAccountsPage = true;
			logger.info("Accounts Page is displayed.");
		}else {
			isAccountsPage = false;
			logger.info("Can not display Accounts Page.");
		}
		return isAccountsPage;
	}
	public String createNewView(WebDriver driver) throws FileNotFoundException, IOException {
		createNewView.click();
		logger.info("'Create New View' link is clicked.");
		viewName.clear();
		String vName = FileUtils.readAccountsPagePropertiesFile("setViewName");
		viewName.sendKeys(vName);
		logger.info("'View Name' is entered.");
		viewUniqueName.clear();
		viewUniqueName.click();
		logger.info("'Unique View Name' is generated.");
		CommonActionUtils.mouseHover(driver, saveBtn);
		saveBtn.click();
		logger.info("Saved Newly created view.");
		return vName;
	} 
	public boolean isNewViewAddedInDropdown(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isNewViewAddedToAccountViewList = false;
		Select select = new Select(viewDropDown);
		WebElement selectedOption = select.getFirstSelectedOption();
		String selectedText = selectedOption.getText();
		String vName = createNewView(driver);
		if(vName.equals(selectedText)) {
			isNewViewAddedToAccountViewList = true;
			logger.info("The default selected option is "+selectedText+"!");
		}else {
			logger.info("The default selected option is incorrect!");
			isNewViewAddedToAccountViewList = false;
		}	
		return isNewViewAddedToAccountViewList;
	}
	public void selectViewName(WebDriver driver) {
		Select dropdown = new Select(viewDropDown);
		dropdown.selectByIndex(3);
		
		edit.click();
	} 
	public boolean isEditViewPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isEditView =false;
		String expectedTitle = FileUtils.readAccountsPagePropertiesFile("accountsEditViewPageTitle");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualTitle = driver.getTitle();
		System.out.println("expectedTitle "+expectedTitle);
		System.out.println("actualTitle "+actualTitle);
		if(actualTitle.equals(expectedTitle)) {
			isEditView = true;
			logger.debug("Edit View page is displayed.");
		}else {
			isEditView = false;
			logger.debug("Edit View page is not displayed.");
		}
		return isEditView;
		
	}

	public void editView(WebDriver driver) throws FileNotFoundException, IOException {
//		viewName.clear();
		viewName.sendKeys(FileUtils.readAccountsPagePropertiesFile("newViewName"));
		fieldDropdown.click();
		Select accountName =  new Select(fieldDropdown);
		accountName.selectByIndex(1);
		Select operatorContains =  new Select(operatorDropdown);
		operatorContains.selectByIndex(3);
		valueInput.clear();
		valueInput.sendKeys("a");
		Select available =  new Select(avaiableFields);
		available.selectByVisibleText("Last Activity");
		addArrow.click();
		WaitUtils.WaitForVisibility(driver, saveBtn);
		saveBtn.click();		
	}

	public boolean validateEditAccount(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualPageTitle = driver.getTitle();
		String expected = FileUtils.readAccountsPagePropertiesFile("accountsAccountsPageTitle");
		if(!actualPageTitle.equals(expected)) {
			return false;
		}
		return true;
	}

	public void mergeAccounts(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	
	
}
