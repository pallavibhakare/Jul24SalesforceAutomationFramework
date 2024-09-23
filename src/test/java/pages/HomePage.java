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
		WaitUtils.explicitWaitForElementsVisibility(driver, usersNavLabel);
		usersNavLabel.click();
		logger.info("Clicked on User Menu");
	}
	public boolean isUserMenuAvailable() {
		return usersNavLabel.isDisplayed();
	}
	public boolean isUserMenuOptionsVisible() {
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
		boolean isUserMenuOptionsVerified = true;
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
		logoutLink.click();
		return new LoginPage(driver);
	}
		
	
	public boolean isHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.explicitWaitForElementsVisibility(driver, usersNavLabel);
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String expectedHomepageTitle = FileUtils.readHomepagePropertiesFile("homePageTitle");
		String actualHomepageTitle = driver.getTitle();
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

	
		

	
}
