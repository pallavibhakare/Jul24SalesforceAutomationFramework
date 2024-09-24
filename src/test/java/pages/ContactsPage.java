package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class ContactsPage extends BasePage {
	public ContactsPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//li[@id='Contact_Tab']/a")
	public WebElement contacts_Tab;
	
	@FindBy(xpath = "//td[@class='pbButton']/input[@value=' New ']")
	public WebElement newBtn;
	
	@FindBy(id ="name_lastcon2")
	public WebElement lastName; 
	
	@FindBy(xpath = "//span[@class='lookupInput']/a[@id='con4_lkwgt']/img")
	public WebElement accountNameIcon; 
	
	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value=' Save ']")
	public WebElement savebtn; 
	
	@FindBy(xpath = "//div[@class='pBody']/input[@id='lksrch']")
	public WebElement searchLookup;
	@FindBy(xpath = "//div[@class='pBody']/input[@value=' Go! ']")
	public WebElement goBtn;
	@FindBy(xpath = "//tr[@class='dataRow even first']/th/a")
	public WebElement firstRowData;
	
	@FindBy(xpath = "//span[@class='fFooter']/a[contains(text(),'Create New View')]")
	public WebElement createNewViewLink;
	
	@FindBy(id="fname")
	public WebElement viewNameInput;
	
	@FindBy(id="devname")
	public WebElement viewUniqueNameInput;
	
	@FindBy(xpath = "//td[@class='pbButtonb']/input[@value=' Save ']")
	public WebElement saveViewBtn;
	
	public void clickContactsTabLink() {
		contacts_Tab.click();
		logger.info("'Contacts' tab is clicked.");		
	}
	public boolean isContactsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isContactsHomePage = false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readContactsPropertiesFile("contactsHomePageTitle");
		if(actual.equals(expected)) {
			isContactsHomePage = true;
			logger.info("Contacts Home Page is displayed.");
		}else {
			isContactsHomePage = false;
			logger.info("Can not display Contacts Home Page.");
		}
		return isContactsHomePage;
	}
	public boolean clickNewButton(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isNewContactPage= false;
		newBtn.click();
		logger.info("'New' contact button is clicked.");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readContactsPropertiesFile("newContactsEditPageTitle");
		if(actual.equals(expected)) {
			isNewContactPage = true;
			logger.info("New Contact's Edit Page is displayed.");
		}else {
			isNewContactPage = false;
			logger.info("Can not display New Contact's Edit Page.");
		}
		return isNewContactPage;
	}
	private String createContact(WebDriver driver) throws FileNotFoundException, IOException {
		String lname = FileUtils.readContactsPropertiesFile("lastNameForNewContact");
		lastName.sendKeys(lname);
		logger.info("Last name entered.");
		String parentWindow = driver.getWindowHandle();		
		CommonActionUtils.clickAndSwitchTonewWindow(driver, accountNameIcon);
		driver.switchTo().frame("searchFrame");
		searchLookup.sendKeys("*");
		goBtn.click();
		driver.switchTo().parentFrame();
		driver.switchTo().frame("resultsFrame");
		firstRowData.click();
		driver.switchTo().window(parentWindow);
		savebtn.click();
		logger.info("Saved new contacts.");
		return lname;
	}
	public boolean isNewContactHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		
		boolean isNewContactPage=false;
		String contactCreatedLastName = createContact(driver); 
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		String expected = "Contact: "+contactCreatedLastName+" ~ Salesforce - Developer Edition";
		if(actual.equals(expected)) {
			isNewContactPage=true;
			logger.info("New Contact Page is displayed.");
		}else {
			isNewContactPage=false;
			logger.info("Can not display New Contact Page.");
		}
		return isNewContactPage;
	}
	public boolean clickCreateNewViewLink(WebDriver driver) throws FileNotFoundException, IOException {
		createNewViewLink.click();
		logger.info("'Create New View' link is clicked.");
		boolean isCreateNewViewPage=false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		String expected =FileUtils.readContactsPropertiesFile("createNewViewPageTitle") ;
		if(actual.equals(expected)) {
			isCreateNewViewPage=true;
			logger.info("Create New View Page is displayed.");
		}else {
			isCreateNewViewPage=false;
			logger.info("Can not display Create New View Page.");
		}
		return isCreateNewViewPage;
	}
	public boolean isViewNameEntered(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isViewNameEntered=false;
		String setViewName =FileUtils.readContactsPropertiesFile("viewName"); 
		viewNameInput.sendKeys(setViewName);		
		String actualValue = viewNameInput.getAttribute("value");
		if(actualValue.equals(setViewName)) {
			isViewNameEntered=true;
			logger.info("View Name entered.");
		}else {
			isViewNameEntered=false;
			logger.info("Can not enter View Name.");
		}
		return isViewNameEntered;
	}
	public boolean isViewUniqueNameEntered(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isTrue=false;
		viewUniqueNameInput.click();
		WaitUtils.waitForAttributeTobeNotEmpty(driver, viewUniqueNameInput);
		String autoGenVal=viewUniqueNameInput.getAttribute("value");
		String expectedValue =FileUtils.readContactsPropertiesFile("viewName");  
		if(autoGenVal.equals(expectedValue)) {
			isTrue=true;
			logger.info("View Name entered.");
		}else {
			isTrue=false;
			logger.info("Can not enter View Name.");
		}
		return isTrue;
	}
	public boolean saveDetails(WebDriver driver) throws FileNotFoundException, IOException {
		saveViewBtn.click();
		logger.info("Saving Create New View");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readContactsPropertiesFile("contactaPage");
		
		boolean isViewSaved= false;
		if(actual.equals(expected)) {
			isViewSaved=true;
			logger.info("Contacts home page is displayed with new view selected.");
		}else {
			isViewSaved=false;
			logger.info("Can not display Contacts home page.");
		}
		return isViewSaved;
	}
}
