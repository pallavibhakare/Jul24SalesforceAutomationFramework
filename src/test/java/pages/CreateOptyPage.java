package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class CreateOptyPage extends BasePage {
	
	public CreateOptyPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//li[@id='Opportunity_Tab']/a")
	public WebElement createOpty_Tab;
	
	@FindBy(xpath = "//li/a[contains(text(), 'Opportunity Pipeline')]")
	public WebElement optyPipeline;
	
	@FindBy(id = "fcf")
	public WebElement selectOptydropdown;	
	@FindBy(xpath = "//select[@id='fcf']/option")
	public List<WebElement> selectOptydropdownOptions; 
	
	@FindBy(xpath = "//td[@class='pbButton']/input[@value=' New ']")
	public WebElement newOpty; 
	
	@FindBy(xpath = "//li/a[contains(text(),'Stuck Opportunities')]")
	public WebElement stuckOpty; 
	
	@FindBy(id = "quarter_q")
	public WebElement intervalSelect; 
	@FindBy(id = "open")
	public WebElement includeSelect; 
	@FindBy(xpath = "//td/input[@value='Run Report']")
	public WebElement runReportBtn; 
	
	@FindBy(xpath = "//input[@id='opp3']")
	public WebElement optyName;
	@FindBy(id = "opp4")
	public WebElement accountName;
	@FindBy (id = "opp4_lkwgt")
	public WebElement accountNameImgIcon;
	
	@FindBy(id = "opp9")
	public WebElement closeDateInput;
	@FindBy(xpath = "//a[@class='calToday']")
	public WebElement calToday;
	
	@FindBy(id = "searchFrame")
	public WebElement searchFrame;
	
	@FindBy(id = "resultsFrame")
	public WebElement resultFrame;
	@FindBy(id = "opp11")
	public WebElement stageSelect; 
	@FindBy(xpath = "//select[@id='opp11']/option")
	public List<WebElement> stageOptions; 
	@FindBy(id = "opp12")
	public WebElement probability; 
	@FindBy(id = "opp6")
	public WebElement leadSourceSelect;
	@FindBy(xpath = "//select[@id='opp6']/option")
	public List<WebElement> leadSourceOptions; 
	@FindBy(id = "opp17")
	public WebElement primaryCampaignImg;
	@FindBy(xpath = "//td[@id='topButtonRow']/input[@value=' Save ']")
	public WebElement saveBtnTop; 
	
	@FindBy(id = "lksrch")
	public WebElement searchLookupText;
	@FindBy(xpath = "//input[@value=' Go! ']")
	public WebElement goBtn;
	@FindBy(xpath = "//tr[@class='dataRow even first']/th/a")
	public WebElement firstRowData;
	@FindBy(xpath = "//tr[@class='dataRow even last first']/th/a")
	public WebElement firstLastRowData; 
	
	public void clickOptyTabLink() {		
		createOpty_Tab.click();
		logger.info("'Opportunities' tab is clicked");
	}
	public boolean isOptyHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isOptyPage=false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual =driver.getTitle();
		String expected= FileUtils.readCreateOptyPagePropertiesFile("optyHomePageTitle");
	
		if(actual.equals(expected)) {
			isOptyPage = true;
			logger.info(" '"+driver.getTitle()+"' home page is displayed.");
		}else {
			isOptyPage = false;
			logger.info("Can not display 'Opportunities' home page.");
		}
		return isOptyPage;
	}
	public boolean isOptyDropDownPresent(WebDriver driver) {
		if(selectOptydropdown.isDisplayed()) {
			logger.info("Opportunities drop down is present.");
			return true;			
		}
		return false;
	}
	public List<String> getOptyDropdownOptionText() {

		List<String> optionNames = new ArrayList<>();		
		for(WebElement option :selectOptydropdownOptions) {
			optionNames.add(option.getText());
		}
		return optionNames;
	}
	public boolean clickOptyPipeline(WebDriver driver) throws FileNotFoundException, IOException {
		optyPipeline.click();
		boolean optyPipelinePage= false;
		logger.info("'Opportunity Pipeline' link is clicked.");
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readCreateOptyPagePropertiesFile("optyPipelinePageTitle");
		if(actual.equals(expected)) {
			logger.debug("'"+actual+"' page is displayed.");
			optyPipelinePage= true;
		}else {
			logger.debug("Can not display'"+expected+".");
			optyPipelinePage= false;
		}
		return optyPipelinePage;
	}
	
	private String createNewOpty(WebDriver driver) throws FileNotFoundException, IOException {
		newOpty.click();
		logger.info("'New' opportunity button is clicked.");
		isNewOptyPage(driver);
		String name = FileUtils.readCreateOptyPagePropertiesFile("newOptyName");
		optyName.sendKeys(name);
		
		logger.info("'Opportunity Name' is entered.");
		String parentWindow = driver.getWindowHandle();
		CommonActionUtils.clickAndSwitchTonewWindow(driver, accountNameImgIcon);
		
		driver.switchTo().frame("searchFrame");
//		String text = FileUtils.readCreateOptyPagePropertiesFile("wildCard");
		String text="a";
		searchLookupText.sendKeys(text);
		logger.info("A is entered in search input.");
		goBtn.click();
		logger.info("'Go!' button is clicked.");
//		WaitUtils.explicitWaitForInVisibility(driver, goBtn);
		driver.switchTo().parentFrame();
//		WaitUtils.explicitWaitForElementsVisibility(driver, firstRowData);
		driver.switchTo().frame("resultsFrame");
		firstRowData.click();
		logger.info("First row data is selected.");
		driver.switchTo().window(parentWindow);
		String optionName = CommonActionUtils.selectRandomValueFromDropdown(leadSourceSelect);
		CommonActionUtils.selectDropDownOption(driver, leadSourceSelect, optionName);
		closeDateInput.clear();
		closeDateInput.click();
		calToday.click();
		String stageOptionName = CommonActionUtils.selectRandomValueFromDropdown(stageSelect);
		CommonActionUtils.selectDropDownOption(driver, stageSelect, stageOptionName);
		probability.clear();
		probability.sendKeys(FileUtils.readCreateOptyPagePropertiesFile("probabilityPercentage"));
		primaryCampaignImg.clear();	
		
		System.out.print(name);
		saveBtnTop.click();
		logger.info("Saved new opportunity.");
		return name;
	}
	private boolean isNewOptyPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isNewOptyEditPage = false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readCreateOptyPagePropertiesFile("isOptyEditPageTitle");
		if(actual.equals(expected)) {
			logger.debug("'"+actual+"' page is displayed.");
			isNewOptyEditPage= true;
		}else {
			logger.debug("Can not display'"+expected+".");
			isNewOptyEditPage= false;
		}
		return isNewOptyEditPage;
	}
	public boolean isNewlyCreatedOptyPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isNewlyCreatedOptyPage=false;
		String s= createNewOpty(driver);
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		System.out.print("Ac:"+actual);
		String expected = "Opportunity: "+s+" ~ Salesforce - Developer Edition";
		System.out.print("e:"+expected);
		if(actual.equals(expected)) {
			logger.info("New opportunity page is displayed with opportunity details.");
			isNewlyCreatedOptyPage=true;
		}else {
			logger.info("Can not display New opportunity page.");
			isNewlyCreatedOptyPage=false;
		}
		return isNewlyCreatedOptyPage;
	}
	public boolean clickStuckOptyLink(WebDriver driver) throws FileNotFoundException, IOException {
		stuckOpty.click();
		boolean isStuckOptyPage =false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());		
		String actual = driver.getTitle();		
		String expected = FileUtils.readCreateOptyPagePropertiesFile("stuckOptyPageTitle");
		if(actual.equals(expected)) {
			isStuckOptyPage =true;
			logger.info("Report page with Stuck opportunity page is displayed.");
		}else {
			isStuckOptyPage =false;
			logger.info("Can not display Stuck opportunity page.");
		}
		return isStuckOptyPage;
	}
	public boolean quarterlySummery(WebDriver driver) throws FileNotFoundException, IOException {
		String intervalOption = CommonActionUtils.selectRandomValueFromDropdown(intervalSelect);
		CommonActionUtils.selectDropDownOption(driver, intervalSelect, intervalOption);
		logger.info("'"+intervalOption+"' is selected from Interval");
		String includeOption = CommonActionUtils.selectRandomValueFromDropdown(includeSelect);
		CommonActionUtils.selectDropDownOption(driver, includeSelect, includeOption);
		logger.info("'"+includeOption+"' is selected from Include");
		runReportBtn.click();
		logger.info("'Run Report' button is clicked");
		boolean isReportPage= false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actual = driver.getTitle();
		String expected = FileUtils.readCreateOptyPagePropertiesFile("quarterlySummaryPageTitle");
		if(actual.equals(expected)) {
			isReportPage= true;
			logger.info("Report page with Opportunities thar satisfies the search critera is displayed.");
		}else {
			isReportPage= false;
			logger.info("Can not display Quarterly Summary Report page.");
		}
		return isReportPage;
	}
	
}
