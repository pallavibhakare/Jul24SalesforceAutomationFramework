package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class LeadsPage extends BasePage {
	public LeadsPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//li[@id='Lead_Tab']/a")
	public WebElement leads_Tab;
	
	@FindBy(xpath = "//select[@name='fcf']")
	public WebElement selectLeadsViewDropdown;	
	@FindBy(xpath = "//select[@id='fcf']/option")
	public List<WebElement> selectLeadsViewDropdownOptions; 
	
	@FindBy(xpath = "//td[@class='pbButton']/input[@value=' New ']")
	public WebElement newBtn; 
	
	@FindBy(xpath ="//input[@id='name_lastlea2']")
	public WebElement lastName; 
	
	@FindBy(id = "lea3")
	public WebElement companyName; 
	
	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value=' Save ']")
	public WebElement savebtn; 
	
	public void clickLeadsTabLink(WebDriver driver) {	
		WaitUtils.WaitForVisibility(driver, leads_Tab);
		leads_Tab.click();
		logger.info("'Leads' tab is clicked");
	}
	public boolean isLeadsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		String expected= FileUtils.readLeadsPagePropertiesFile("leadsHomePageTitle");
		return actual.equals(expected);
	}
	public List<String> getListViewOptions(WebDriver driver) {
		return CommonActionUtils.getDropdownOptionNames(driver, selectLeadsViewDropdownOptions);		
	}
	
	public boolean isListViewOptionsAvailable(WebDriver driver) throws FileNotFoundException, IOException {
		selectLeadsViewDropdown.click();
		boolean isLeadsViewOptionAvailable = false;
		List<String> actualOptionNames = getListViewOptions(driver);
		List<String> expectedOptionNames = Arrays.asList(FileUtils.readLeadsPagePropertiesFile("leadsView.options").split(","));
		if(actualOptionNames.equals(expectedOptionNames)) {
			isLeadsViewOptionAvailable = true;
		}else {
			isLeadsViewOptionAvailable = false;
		}		
		return isLeadsViewOptionAvailable;	
	}
	public boolean viewOptionsSelect(WebDriver driver) throws FileNotFoundException, IOException {
		CommonActionUtils.selectDropDownOption(driver, selectLeadsViewDropdown, "Today's Leads");
		String lastSelectedViewOption="My Unread Leads";
		CommonActionUtils.selectDropDownOption(driver, selectLeadsViewDropdown, lastSelectedViewOption);		
		HomePage hp = new HomePage(driver);
		hp.logout(driver);		
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(driver);
		logger.info("Logged in again.");
		Assert.assertTrue(hp.isHomePage(driver));		
		leads_Tab.click();
		Select select = new Select(selectLeadsViewDropdown);
		String currentSelected= select.getFirstSelectedOption().getText();
		if(currentSelected.equals(lastSelectedViewOption)) {
			logger.info("Same as last option selected.");
			return true;
		}else {
			logger.info("Not same as last selected option.");
			return false;
		}
	}
	public boolean isTodaysLeadsPage(WebDriver driver) {
		String lastSelectedViewOption="Today's Leads";
		CommonActionUtils.selectDropDownOption(driver, selectLeadsViewDropdown, lastSelectedViewOption);
		Select select = new Select(selectLeadsViewDropdown);
		String currentSelected= select.getFirstSelectedOption().getText();
		if(currentSelected.equals(lastSelectedViewOption)) {
			logger.info("Today's Lead page is displayed.");
			return true;
		}else {
			logger.info("Can not display Today's Lead.");
			return false;
		}
	}
	public void clikcNewLeads(WebDriver driver) throws FileNotFoundException, IOException {
		newBtn.click();
		isNewLeadCreationPage(driver);
		logger.info("'New Lead Creation' page is open");
	}
	public boolean isNewLeadCreationPage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual= driver.getTitle();
		String expected = FileUtils.readLeadsPagePropertiesFile("newLeadCreationPageTitle");
		if(actual.equals(expected)) {
			logger.info("'New Lead Creation' page is displayed.");
			return true;
		}else {
			logger.info("Can not display 'New Lead Creation' page.");
			return false;
		}
	}
	public boolean enterNewLeadsDetails(WebDriver driver) throws FileNotFoundException, IOException {
		String lname=FileUtils.readLeadsPagePropertiesFile("lastNameForNewLead");
		lastName.sendKeys(lname);
		logger.info("Last Name is entered.");
		companyName.sendKeys(FileUtils.readLeadsPagePropertiesFile("companyNameForNewLead"));
		logger.info("Company Name is entered.");
		savebtn.click();
		logger.info("'Save' button is clicked.");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String acual = driver.getTitle();
		String expected = "Lead: "+lname+" ~ Salesforce - Developer Edition";
		if(acual.equals(expected)) {			
			logger.info("New lead is saved and "+driver.getTitle()+" is opened.");
			return true;
		}else {
			logger.info("Can not save New lead page.");
			return false;
		}
	}
}
