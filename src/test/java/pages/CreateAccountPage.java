package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.By;
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
	
	@FindBy(xpath = "//a[contains(text(),'Merge Accounts')]")
	public WebElement mergeAccountsLink;
	@FindBy(xpath = "//input[@id='srch']")
	public WebElement inputTextForFindAccount;
	@FindBy(name = "srchbutton")
	public WebElement findAccount;
	@FindBy(xpath = "//input[@id='cid0']")
	public WebElement radioBtn1;
	@FindBy(xpath = "//input[@id='cid1']")
	public WebElement radioBtn2;
	@FindBy(xpath = "//input[@id='cid2']")
	public WebElement radioBtn3;
	@FindBy(xpath = "//div[contains(@class,'pbTopButtons')]//input[contains(@title,'Next')]")
	public WebElement nextBtn;
	@FindBy(xpath = "//div[@class='pbTopButtons']//input[@title='Merge']")
	public WebElement mergeBtn;
	
	
	@FindBy(partialLinkText = "Accounts with last activity > 30 days")
	public WebElement report_accWithLastActivity;
	@FindBy(id = "ext-comp-1042")
	public WebElement fromDateInput;
	@FindBy(id = "ext-gen152")
	public WebElement fromDate;
	
	@FindBy(id = "ext-gen276")
	public WebElement fromTodayBtn;
	
	@FindBy(id = "ext-comp-1045")
	public WebElement toDateInput;
	
	@FindBy(id = "ext-gen154")
	public WebElement toDate;

	@FindBy(id = "ext-gen292")
	public WebElement toTodayBtn;
	
	@FindBy(id = "ext-gen276")
	public WebElement todayBtn;
	
	@FindBy(id = "ext-gen49")
	public WebElement saveUnsavedReportBtn;
	
	
	@FindBy(id = "saveReportDlg")
	public WebElement reportDlg;
	
	@FindBy(id = "ext-gen27")
	public WebElement reportForm;
	@FindBy(id = "saveReportDlg_reportNameField")
	public WebElement reportName;
	@FindBy(id = "saveReportDlg_DeveloperName")
	public WebElement reportUniqueName;
	@FindBy(xpath = "//textarea[@id='ext-comp-1067']")
	public WebElement report_desc;
	@FindBy(xpath = "//button[@id='ext-gen312']")
	public WebElement saveAndRunReportDlg;
	
	
	@FindBy(id = "ext-comp-1064")
	public WebElement spinner1;
	
	@FindBy(id = "ext-gen172")
	public WebElement spinner2;
	@FindBy(id = "dlgSaveReport")
	public WebElement enableSave;
	
	
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

	public void mergeAccounts(WebDriver driver) throws FileNotFoundException, IOException {
		mergeAccountsLink.click();
		String accountinitialsToMerge = FileUtils.readAccountsPagePropertiesFile("accountinitialsToMerge");
		inputTextForFindAccount.sendKeys(accountinitialsToMerge);
		findAccount.click();
		for(int i=0; i<3; i++) {
			driver.findElement(By.xpath("//input[@id='cid"+i+"']")).click();
		}
		nextBtn.click();
		if(isMergeAccountsPage(driver)) {
			logger.debug("Merge Accounts Step 2 page is displayed with selected accounts details to merge");
			mergeBtn.click();
		}
		driver.switchTo().alert().accept();
		logger.debug("Merge confirmtion is is accepted through popup.");		
	}


	private boolean isMergeAccountsPage(WebDriver driver) throws FileNotFoundException, IOException {
		
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readAccountsPagePropertiesFile("mergeAccountsPageTitle");
		return actual.equals(expected);
	}
	public void chooseReportsType() {
		report_accWithLastActivity.click();
	}
	public void getAccountReports(WebDriver driver) throws FileNotFoundException, IOException {		
		chooseReportsType();
		isUnsavedReportPage(driver);
		logger.debug("Unsaved Reports page is displayed.");
	}

	public boolean isUnsavedReportPage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readAccountsPagePropertiesFile("unsavedReportsPageTitle");
		return actual.equals(expected);			
	}

	public void selectReportOptions(WebDriver driver) {
		
		fromDateInput.clear();
		fromDate.click();
		fromTodayBtn.click();
		logger.info("from Date is selected.");
		
		toDateInput.clear();
		toDate.click();
		toTodayBtn.click();
		logger.info("to Date is selected.");
		saveUnsavedReportBtn.click();
		logger.info("Clicked 'Save' button.");
		int count=0;
		
			logger.info("Save Report dialog is open.");			
			reportName.sendKeys("r"+count++);
			logger.info("Report name entered.");	
			reportUniqueName.click();
			logger.info("Unique Report name generated.");
			report_desc.click();
//			WaitUtils.explicitlyWaitForClickableElement(driver, saveReportDlg);
//			if(enableSave.isEnabled()) {
////				CommonActionUtils.mouseHover(driver, saveReportDlg);
//				WaitUtils.WaitForVisibility(driver, saveAndRunReportDlg);
				saveAndRunReportDlg.click();
//			}
		
		
	}

	public boolean isReportsDashboradPage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		System.out.println("actual:"+actual);
		String expected = FileUtils.readAccountsPagePropertiesFile("reportsGeneratedPageTitle");
		System.out.println("expected:"+expected);
		return actual.contains(expected);
	}
	
	
}
