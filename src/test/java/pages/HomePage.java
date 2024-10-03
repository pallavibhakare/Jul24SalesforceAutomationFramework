package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;


public class HomePage extends BasePage {
		
	public HomePage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//span[@id='userNavLabel']")
	public WebElement usersNavLabel;

	
	@FindBy(xpath = "//div[@id='userNav-menuItems']")
	public WebElement userNav_menuItems;
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a")
	public List<WebElement> userMenuOptions; 
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[1]")
	public WebElement myProfile; 
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[2]")
	public WebElement mySettings; 
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[3]")
	public WebElement devConsole; 
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[4]")
	public WebElement lightningExerience; 
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[contains(text(), 'Logout')]")
	public WebElement logoutLink;
	
	@FindBy(xpath = "//a[@class='setupSection']/span[contains(text(), 'My Settings')]")
	public WebElement mySettingsSetupText;

	@FindBy(id = "theloginform")
	public WebElement loginForm;
	@FindBy(id = "editors-body")
	public WebElement editorBody;
	
	@FindBy(id = "fcf")
	public WebElement viewDropdownSelect;	
	
	@FindBy(xpath = "//select[@id='fcf']/option")
	public List<WebElement> viewDropdownSelectOptions; 
	
	@FindBy(xpath = "//li[@id='home_Tab']/a")
	public WebElement homeTab;
	
	@FindBy(xpath = "//h1[@class='currentStatusUserName']/a")
	public WebElement userNameLink;
	
	public void clickMySettings() {
		mySettings.click();
		logger.info("Clicked on 'My Settings'.");
	}
	public void clickDeveloperConsole() {
		devConsole.click();
		logger.info("Clicked on 'Developer Console'.");
	}

	public String getUserName(WebDriver driver) {
		return usersNavLabel.getText();
	}
	public void clickUserMenu(WebDriver driver) {
//		WaitUtils.explicitWaitForElementsVisibility(driver, usersNavLabel);
//		WaitUtils.WaitForVisibility(driver, usersNavLabel);
		WaitUtils.explicitlyWaitForClickableElement(driver, usersNavLabel);
		if(usersNavLabel.isDisplayed()) {
			usersNavLabel.click();
			logger.info("Clicked on User Menu");
		}
	}
	public boolean isUserMenuAvailable() {
		return usersNavLabel.isDisplayed();
	}
	public boolean isUserMenuOptionsVisible(WebDriver driver) {
		WaitUtils.WaitForVisibility(driver, userNav_menuItems);
		return userNav_menuItems.isDisplayed();
	}
	public List<String> getUsermenuOptionNames() {
		List<String> optionNames = new ArrayList<>();		
		for(WebElement option :userMenuOptions) {
			optionNames.add(option.getText());
		}
		return optionNames;
	}
	public String getHomePageTitle(WebDriver driver) {
		return driver.getTitle();
		
	}
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();		
	}
	
	/**This function will verify user menu options
	 * @return boolean true if all options are verified
	 * **/
	public boolean verifyUserMenuOptions() throws FileNotFoundException, IOException {
		boolean isUserMenuOptionsVerified = false;
		List<String> actualOptionNames = getUsermenuOptionNames();
		List<String> expectedOptionNames = Arrays.asList(FileUtils.readHomepagePropertiesFile("usermenu.options").split(","));
		if(actualOptionNames.equals(expectedOptionNames)) {
			isUserMenuOptionsVerified = true;
		}else {
			isUserMenuOptionsVerified = false;
		}		
		return isUserMenuOptionsVerified;
	}
	
	public LoginPage logout(WebDriver driver) throws FileNotFoundException, IOException {		
		clickUserMenu(driver);	
		logoutLink.click();		
		return new LoginPage(driver);
	}
	public void clickLogoutLink(WebDriver driver) {
		logoutLink.click();
	}
	
	public boolean isHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.explicitWaitForElementsVisibility(driver, usersNavLabel);
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String expectedHomepageTitle = FileUtils.readHomepagePropertiesFile("homePageTitle");
		String actualHomepageTitle = driver.getTitle();
//		WaitUtils.explicitlyWaitForClickableElement(driver, usersNavLabel);
		if(actualHomepageTitle.equals(expectedHomepageTitle)) {
			return true;
		}else {
			return false;
		}		
	}
	
	public boolean isLoginPage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.WaitForVisibility(driver, loginForm);
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String expectedLoginpageTitle = FileUtils.readHomepagePropertiesFile("loginPageTitle");
		String actualLoginpageTitle = driver.getTitle();
		if(actualLoginpageTitle.equals(expectedLoginpageTitle)) {
			return true;
		}else {
			return false;
		}
		
	}

	public boolean isMySettingsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isMysettingsPage = false;
		WaitUtils.explicitWaitForElementsVisibility(driver, mySettingsSetupText);		
		if(mySettingsSetupText.isDisplayed()) {
			isMysettingsPage = true;
			System.out.println("User is on MySettings page");
		}
		else {
			isMysettingsPage = false;
			System.out.println("MySettings page is not Lunched");
		}
		return isMysettingsPage;
	}
	public void isDevConsoleDisplayed(WebDriver driver) throws FileNotFoundException, IOException {
		// Check if driver is not null
	    if (driver == null) {
	        logger.error("WebDriver instance is null.");
	        return;
	    }

	    // Get the parent window handle and child windows
	    String parentWindow = driver.getWindowHandle();
	    CommonActionUtils.getChildWindows(driver);

	    // Wait for the title
	    WaitUtils.waitForTitleToBe(driver, driver.getTitle());

	    // Get the actual title
	    String actualTitle = driver.getTitle();

	    // Get the expected title from properties file
	    String expectedTitle = FileUtils.readHomepagePropertiesFile("develoerConsoleHomePageTitle");

	    // Check if expectedTitle is not null
	    if (expectedTitle == null) {
	        logger.error("Expected title from properties file is null.");
	        return;
	    }

	    // Compare the actual and expected titles
	    if (actualTitle.equals(expectedTitle)) {
	        logger.info(driver.getTitle() + " is displayed.");
	    } else {
	        logger.info("Cannot display page title.");
	    }

	    // Switch back to the parent window
	    driver.switchTo().window(parentWindow);

	    // Optionally, quit the driver at the end
	    // driver.quit();
	}
	public void clickHomeTab(WebDriver driver) {
		WaitUtils.WaitForVisibility(driver, homeTab);
		homeTab.click();
		logger.info("'Home' tab is clicked.");
		
	}
	public boolean isHomeTabPage(WebDriver driver) throws FileNotFoundException, IOException {
		
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualHomepageTitle = driver.getTitle();	
		String expectedHomepageTitle = FileUtils.readHomepagePropertiesFile("homeTabPage");
		System.out.println("actual home title:"+actualHomepageTitle);
		System.out.println("Expected home title:"+expectedHomepageTitle);
		
		if(actualHomepageTitle.equals(expectedHomepageTitle)) {
			logger.info("Home Page is displayed.");
			return true;
			
		}else {
			logger.info("Can not displayed Home page.");
			return false;
		}		
		
	}
//	public boolean isFirstLastNameAndLinkDisplayed(WebDriver driver) throws FileNotFoundException, IOException {
//		String userName = userNameLink.getText();
//		Assert.assertTrue(userName.contains(FileUtils.readMyProfilePropertiesFile("update.firstName")), "First name is not displayed correctly.");
//		Assert.assertTrue(userName.contains(FileUtils.readMyProfilePropertiesFile("update.lastName")), "Last name is not displayed correctly.");
//		Assert.assertTrue(userNameLink.isDisplayed(), "User Name link is not displayed.");
//		Assert.assertTrue(userNameLink.getAttribute("href").contains("/_ui/core/userprofile/UserProfilePage"), "Link does not point to the correct URL.");
//		return true;
//	}
	public boolean isFirstLastNameAndLinkDisplayed(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isDispalyed= false;
		if(userNameLink.isDisplayed()) {
			String userName = userNameLink.getText();
			String expectedFirstName = FileUtils.readMyProfilePropertiesFile("update.firstName");
			String expectedLastName = FileUtils.readMyProfilePropertiesFile("update.lastName");
			if(userName.contains(expectedFirstName)) {
				logger.info("First name of  account holder is displayed.");
			}else{
				logger.error("First name is not displayed correctly.");
				
			}
			if(userName.contains(expectedLastName)) {
				logger.info("Last name of  account holder is displayed.");
			}else{
				logger.error("Last name is not displayed correctly.");
				
			}
			if(userNameLink.isDisplayed()) {
				logger.info("User Name link is displayed.");
			}else{
				logger.error("User Name link is not displayed.");
				
			}
			if(userNameLink.getAttribute("href").contains("/_ui/core/userprofile/UserProfilePage")) {
				logger.info("Link points to the correct URL.");
			}else{
				logger.error("Link does not point to the correct URL.");
				
			}
			isDispalyed = true;
		}else {
			logger.info("User link is not available.");
			isDispalyed = false;
			
		}
		return isDispalyed;
	}
	public boolean clickUserNameLink(WebDriver driver) throws FileNotFoundException, IOException {
		String userName = userNameLink.getText();
		userNameLink.click();
		logger.info("User Name link is clicked");		
		boolean isUsersHomePage=false;
		String expectedTitle = "User: "+ userName +" ~ Salesforce - Developer Edition";
		String actualTitle = driver.getTitle();
		if(actualTitle.equals(expectedTitle)) {
			isUsersHomePage=true;
			logger.info(actualTitle+" is displayed.");
		}else {
			logger.info("Can not display users profile page.");
			isUsersHomePage=false;
		}
		return isUsersHomePage;
	}
	public boolean isLastNameUpdatedInUserMenu(WebDriver driver) throws FileNotFoundException, IOException {
		String username =getUserName(driver);
		String lname =FileUtils.readRandomScenariosPropertiesFile("update.lastName");
		String actualPageTitle = driver.getTitle();
		return username.contains(lname)  && actualPageTitle.contains(lname);
	}

		

	
}
