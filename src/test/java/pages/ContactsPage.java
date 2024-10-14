package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
	
	@FindBy(xpath = "//tr[@class='dataRow even last first']/th/a")
	public WebElement firstLastRowData;
	
	@FindBy(xpath = "//span[@class='fFooter']/a[contains(text(),'Create New View')]")
	public WebElement createNewViewLink;
	
	@FindBy(id="fname")
	public WebElement viewNameInput;
	
	@FindBy(id="devname")
	public WebElement viewUniqueNameInput;
	
	@FindBy(xpath = "//div[@class='pbHeader']/table/tbody/tr/td[@class='pbButtonb']/input[@value=' Save ']  ")
	public WebElement saveViewBtn;
	
	@FindBy(xpath = "//select[@id='hotlist_mode']")
	public WebElement recentlyCreatedSelect;
	
	@FindBy(id="fcf")
	public WebElement viewContactSelect;
	
	@FindBy(xpath = "//div[@class='pbBody']/table[@class='list']")
	public WebElement contactsListTable;
	
	@FindBy(xpath = "//table[@class='list']/tbody/tr[contains(@class, 'dataRow')]/th")
	public List<WebElement> rows;

	@FindBy(className = "errorMsg")
	public WebElement errorMessage;
	
	@FindBy(xpath = "//div[@class='pbHeader']/table/tbody/tr/td[@class='pbButtonb']/input[@value='Cancel']  ")
	public WebElement cancelViewBtn;
	
	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value='Delete']")
	public WebElement deleteBtn;
	
	@FindBy(xpath = "//div[@class='filterLinks']/a[2]")
	public WebElement deleteView;
	
	public void clickContactsTabLink() {
		contacts_Tab.click();
		logger.info("'Contacts' tab is clicked.");		
	}
	public boolean isContactsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isContactsHomePage = false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		System.out.println("Actual Contact Home title:"+actual);
		String expected = FileUtils.readContactsPropertiesFile("contactsHomePageTitle");
		System.out.println("Expected Contact Home title:"+expected);
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
		
//		WaitUtils.WaitForVisibility(driver, createNewViewLink);
		WaitUtils.explicitlyWaitForClickableElement(driver, createNewViewLink);
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
	public void enterViewName() throws FileNotFoundException, IOException {
		viewNameInput.sendKeys(FileUtils.readContactsPropertiesFile("viewNameForCancel"));
		logger.info("View Name is entered.");
	}
	public void enterUniqueViewName() throws FileNotFoundException, IOException {
		viewUniqueNameInput.clear();
		viewUniqueNameInput.sendKeys(FileUtils.readContactsPropertiesFile("createUniqueName"));
		logger.info("Unique View Name is created.");
	}
	
	public void saveCreateNewView() throws FileNotFoundException, IOException {
		saveViewBtn.click();
		logger.info("Saving Create New View");
	}
	
	public void cancelCreateNewView(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.explicitlyWaitForClickableElement(driver, cancelViewBtn);
		cancelViewBtn.click();
		logger.info("Cancel 'Create New View'");
	}
	public boolean isViewUniqueNameGenerated(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isTrue=false;
		viewUniqueNameInput.click();
		WaitUtils.waitForAttributeTobeNotEmpty(driver, viewUniqueNameInput);
		String autoGenVal=viewUniqueNameInput.getAttribute("value");
		String expectedValue =FileUtils.readContactsPropertiesFile("viewName");  
		if(autoGenVal.equals(expectedValue)) {
			isTrue=true;
			logger.info("Unique View Name is generated.");
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
		String expected = FileUtils.readContactsPropertiesFile("contactsPage");
		System.out.println("actual: New view title:"+actual);
		System.out.println("expected New view title:"+expected);
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
	public void checkRecentlyCreated(WebDriver driver) throws FileNotFoundException, IOException {
		String optionText=FileUtils.readContactsPropertiesFile("recentlyCreated");
		CommonActionUtils.selectDropDownOption(driver, recentlyCreatedSelect, optionText);
		logger.info(optionText+" is selected.");
	}
	public boolean isRecentlyCreatedPage(WebDriver driver) {
		
		Select select = new Select(recentlyCreatedSelect);
		String selectedOption=select.getFirstSelectedOption().getText();
		if(selectedOption.equals("Recently Created"))
		{
			logger.info(selectedOption+" is selected.");
			return true;
			
		}else {
			logger.info("Can not select "+selectedOption);
			return false;
		}
	}
	public void selectMyContacts(WebDriver driver) {
		CommonActionUtils.selectDropDownOption(driver, viewContactSelect, "My Contacts");
		logger.info("My Contacts is selected from the View select");		
	}
	public boolean isMyContactsPage(WebDriver driver) throws FileNotFoundException, IOException {
		String expected = FileUtils.readContactsPropertiesFile("contactsHomePageTitle");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		
		System.out.println("isMyContactsPage actual"+actual);
		System.out.println("isMyContactsPage expected"+expected);
		
		if(actual.equals(expected)) {
			return true;
		}else {
			return false;
		}
	}

	 public String clickAContactName(WebDriver driver) throws FileNotFoundException, IOException {
		WebElement firstDataRow = contactsListTable.findElement(By.xpath(".//tr[2]"));
		WebElement fisrtCell = firstDataRow.findElement(By.xpath(".//th[@scope='row']"));
		WebElement link = fisrtCell.findElement(By.tagName("a"));
		String contactName = link.getText();
		link.click();
		return contactName;
	 }
	public boolean isContactNamesPage(WebDriver driver ,String name) throws FileNotFoundException, IOException {

		boolean isContactPageDispayed =false;
		String contactName = name;
		String[] parts = contactName.split(", "); // Split by comma and space
		String formattedName = parts[1] + " " + parts[0]; // Combine last name and first name
		contactName=formattedName;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualTitle = driver.getTitle();
//		System.out.println("a "+actualTitle);
		if(actualTitle.contains(contactName)) {
			logger.info("Contact page for "+contactName+" is displayed.");
			isContactPageDispayed= true;
		}else {
			logger.info("Can not dispay Contact details.");
			isContactPageDispayed =false;
		}
		return isContactPageDispayed;
	}
	public boolean validateErrorMessage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean errMsgDisplayed=false;
		if(errorMessage.isDisplayed()) {
			
			String expectedErrorMessage = FileUtils.readContactsPropertiesFile("expectedErrorMessage");
		    String actualErrorMessage = errorMessage.getText();
		    
		    System.out.println("ac: "+actualErrorMessage);
		    System.out.println("ex: "+expectedErrorMessage);
		    
		    if(actualErrorMessage.equals(expectedErrorMessage)) {
		    	logger.info("Error message is displayed.");
		    	errMsgDisplayed=true;
		    }
		}else {
			logger.info("Error message is not displayed.");
			errMsgDisplayed=false;
		}
		return errMsgDisplayed;
	}     
	public String createContactWithNewBtn(WebDriver driver) throws FileNotFoundException, IOException {
		String lname = "Indian";
		lastName.sendKeys(lname);
		logger.info("Last name entered.");
		String parentWindow = driver.getWindowHandle();		
		CommonActionUtils.clickAndSwitchTonewWindow(driver, accountNameIcon);
		driver.switchTo().frame("searchFrame");
		searchLookup.sendKeys("Global Media");
		goBtn.click();
		driver.switchTo().parentFrame();
		driver.switchTo().frame("resultsFrame");
		firstLastRowData.click();
		driver.switchTo().window(parentWindow);
		savebtn.click();
		logger.info("Saved new contacts.");
		return lname;
	}
	public boolean isNewContactDetailsPage(WebDriver driver) throws FileNotFoundException, IOException {
		
		boolean isNewContactPage=false;
		String contactCreatedLastName = createContactWithNewBtn(driver); 
//		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		System.out.println("a:"+actual);
		String expected = "Contact: "+contactCreatedLastName+" ~ Salesforce - Developer Edition";
		System.out.println("e:"+expected);
		if(actual.equals(expected)) {
			isNewContactPage=true;
			logger.info("New Contact Page is displayed.");
		}else {
			isNewContactPage=false;
			logger.info("Can not display New Contact Page.");
		}
		return isNewContactPage;
	}
	public void deleteRecord(WebDriver driver) {

			deleteBtn.click();
			driver.switchTo().alert().accept();
		
	}
	public void deleteView(WebDriver driver) {
		
			deleteView.click();
			driver.switchTo().alert().accept();
		
	}
}
