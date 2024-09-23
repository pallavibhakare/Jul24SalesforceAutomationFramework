package pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class MySettingsPage extends BasePage{

	public MySettingsPage(WebDriver driver) {
		super(driver);	
	}
	
	@FindBy(xpath = "//*[@id='PersonalInfo_font']")
	public WebElement personal;
	@FindBy(xpath = "//*[@id='LoginHistory_font']")
	public WebElement login_history;
	@FindBy(xpath = "//*[@id='RelatedUserLoginHistoryList_body']/div/a")
	public WebElement login_historyDownloadLink;
	@FindBy(xpath = "//*[@id='DisplayAndLayout_font']")
	public WebElement displayAndLayout;
	@FindBy(xpath = "//*[@id='CustomizeTabs_font']")
	public WebElement customizeTabs;
	@FindBy(id = "p4")
	public WebElement chatterDropdown;
	@FindBy(xpath = "//*[@id='duel_select_0']")
	public WebElement availableTabs;
	@FindBy(xpath = "//*[@id='duel_select_0_right']/img")
	public WebElement add_Arrow;
	@FindBy(id = "duel_select_1")
	public WebElement selectedTabs;
	
	//email Tab	
	@FindBy(id = "EmailSetup")
	public WebElement emailLink;
	@FindBy(id = "EmailSettings_font")
	public WebElement myEmailSettings;	
	@FindBy(id = "sender_name")
	public WebElement emailSenderName;
	@FindBy(id = "auto_bcc1")
	public WebElement bccRadioButton;
	@FindBy(name = "save")
	public WebElement saveButton;
	
	//calender & Reminders
	@FindBy(id = "CalendarAndReminders")
	public WebElement calenderAndReminders;
	@FindBy(xpath = "//*[@id='Reminders_font']")
	public WebElement activityReminders;	
	@FindBy(className = "btn")
	public WebElement testReminder;
	
	
	
	public boolean personalSettings(WebDriver driver) throws FileNotFoundException, IOException {
	    boolean isFileDownloaded = false;

	    // Click on Personal Settings and Login History
	    personal.click();
	    logger.info("'Personal' tab is clicked from My Settings.");
	    login_history.click();
	    logger.info("'Login History' tab is clicked from 'Personal' tab.");

	    // Check if the download link is available and click it
	    if (login_historyDownloadLink.isDisplayed()) {
	        login_historyDownloadLink.click();
	        logger.info("'Download login history' link is clicked.");
	        isFileDownloaded = true;
	        logger.info("File downloaded successfully.");
	    }

	    // Get the download path and check if the file has been downloaded
	    String downloadPath = FileUtils.readHomepagePropertiesFile("downloadPath");
	    File latestFile = getLatestFilefromDir(downloadPath);
	    
	    // Check if a file was found and handle the null case
	    if (latestFile != null) {
	        // Check if the file has the correct extension
	        if (isFileDownloaded_Extension(downloadPath, ".csv")) {
	            System.out.println("File is downloaded: " + latestFile.getName());
	            isFileDownloaded = true;
	        } 
	    } else {
	        System.out.println("No file was downloaded.");
	    }

	    return isFileDownloaded;
	}

	//This method checks the extension of the file downloaded
	public static boolean isFileDownloaded_Extension(String dirPath, String extension) throws FileNotFoundException, IOException {
		boolean flag = false;
		String downloadPath = FileUtils.readHomepagePropertiesFile("downloadPath");
		File dir = new File(downloadPath);
		File[] files = dir.listFiles();
		if(files == null || files.length==0) {
			flag = false;
		}
		for(int i=1; i<files.length; i++) {
			if(files[i].getName().contains(extension)) {
				flag = true;
			}
		}
		return flag;
	}
	// This method is used to get the latest downloaded file from the directory
	public static File getLatestFilefromDir(String dirPath) throws FileNotFoundException, IOException {
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();

	    if (files == null || files.length == 0) {
	        System.out.println("No files found in the directory: " + dirPath);
	        return null; // Return null if no files found
	    }

	    // Find the last modified file
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	        if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	            lastModifiedFile = files[i];
	        }
	    }

	    return lastModifiedFile;
	}

	public void displayAndLayouts(WebDriver driver) throws FileNotFoundException, IOException {
		displayAndLayout.click();
		logger.info("Clicked on Display and Layouts");
		customizeTabs.click();	
		logger.info("Clicked on Customize Tabs");
		WaitUtils.explicitWaitForElementsVisibility(driver, chatterDropdown);
		CommonActionUtils.selectDropDownOption(driver, chatterDropdown, FileUtils.readMySettingsPropertiesFile("customAppOptionToSelect"));
		logger.info("Selected '"+FileUtils.readMySettingsPropertiesFile("customAppOptionToSelect")+"' option from 'Custom App'");
		WaitUtils.explicitWaitForElementsVisibility(driver, availableTabs);
		CommonActionUtils.selectDropDownOption(driver, availableTabs, FileUtils.readMySettingsPropertiesFile("availableTabsOptionToSelect"));
		logger.info("Selected '"+FileUtils.readMySettingsPropertiesFile("availableTabsOptionToSelect")+"' option from 'Available  Tabs'.");
		WaitUtils.explicitWaitForElementsVisibility(driver, add_Arrow);
		add_Arrow.click();
		logger.info("Clicked on Add Arrow to move to selected Tabs");				
	}
	public boolean verfiySelectedAvailableTabIsInSelectedTabs() throws FileNotFoundException, IOException {
		 try {
		WaitUtils.explicitWaitForElementsVisibility(null, selectedTabs);
		String expectedTab = FileUtils.readMySettingsPropertiesFile("availableTabsOptionToSelect");
		// Fetch all options in the 'Selected Tabs' dropdown
        List<WebElement> selectedOptions = selectedTabs.findElements(By.tagName("option"));
        
        // Iterate through the options and check if the expected tab is found
        for (WebElement option : selectedOptions) {
            System.out.println("Selected tab: " + option.getText());
            if (option.getText().equals(expectedTab)) {
                System.out.println("Expected tab '" + expectedTab + "' found in Selected Tabs.");
                return true;
            }
        }

        // If the expected tab is not found
        System.out.println("Expected tab '" + expectedTab + "' not found in Selected Tabs.");
        return false;
	    } catch (NoSuchElementException e) {
	        System.err.println("Error: 'Selected Tabs' dropdown not found. " + e.getMessage());
	        return false;
	    } catch (Exception e) {
	        System.err.println("An error occurred while verifying the selected tab: " + e.getMessage());
	        return false;
	    }
	}
	
	public void emailAndSettings(WebDriver driver) throws FileNotFoundException, IOException {
		WaitUtils.WaitForVisibility(driver, emailLink);
		emailLink.click();
		logger.info("'Email' tab is clicked from 'My Settings'.");
		WaitUtils.WaitForVisibility(driver, myEmailSettings);
		myEmailSettings.click();
		logger.info("'My Email Settings' tab is clicked from 'Email' tab.");
		emailSenderName.clear();
		emailSenderName.sendKeys(FileUtils.readMySettingsPropertiesFile("eamilSenderName"));
		logger.info("'Email Name' is entered.");
		bccRadioButton.click();
		logger.info("'Automatic Bcc' radio button is clicked.");
		saveButton.click();		
		logger.info("'My Email Settings' are saved.");
	}

	public void calenderAndReminders(WebDriver driver) throws FileNotFoundException, IOException {
		
		calenderAndReminders.click();
		logger.info("'Calender & Reminders' is Clicked.");
		activityReminders.click();
		logger.info("'Activity Reminders' is Clicked.");
		testReminder.click();
		logger.info("'Open a Test Reminder' is Clicked.");
		String parentWindow = CommonActionUtils.getParentWindowHandle(driver);
		WaitUtils.waitForNewWindow(driver);
	    Set<String> windowHandles = driver.getWindowHandles();
	    for (String handle : windowHandles) {
	        if (!handle.equals(parentWindow)) {
	            driver.switchTo().window(handle);	
	            logger.info("'Reminders' window is open.");
	        }
	    }
	    WaitUtils.waitForTitleToBe(driver, driver.getTitle());
	    String newWindowTitle = driver.getTitle();
	    Assert.assertTrue(newWindowTitle.contains(FileUtils.readMySettingsPropertiesFile("reminderPageTitle")), "New window should be displayed with the correct title.");
	    driver.switchTo().window(parentWindow);
	}
		
}
