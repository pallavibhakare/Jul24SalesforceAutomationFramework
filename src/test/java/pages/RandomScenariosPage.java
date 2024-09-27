package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class RandomScenariosPage extends BasePage {
	public RandomScenariosPage(WebDriver driver) {
		super(driver);	
	}
	
	private String popupWindowHandle;
	
	@FindBy(xpath = "//li[@id='AllTab_Tab']/a/img")
	public WebElement allTabs;
	
	@FindBy(xpath = "//td[@class='bCustomize']/input[@value='Customize My Tabs']")
	public WebElement customizeMyTabs;
	
	@FindBy(xpath = "//select[@id='duel_select_1']")
	public WebElement selectTabsDropdown;
	
	@FindBy(id = "duel_select_0_left")
	public WebElement removeBtn;
	
	@FindBy(xpath = "//td[@id='bottomButtonRow']/input[@value=' Save ']")
	public WebElement saveBtn;
	@FindBy(xpath = "//img[@class='rightArrowIcon']")
	public WebElement addBtn;
	
	@FindBy(xpath ="//select[@id='duel_select_0']")
	public WebElement availableTabsDropdown;
	
	@FindBy(id = "tabBar")
	public WebElement tabBar;
	
	@FindBy(xpath = "//h1[@class='pageType']")
	public WebElement calendarForUser;
	
	
	@FindBy(xpath = "//span[@class='pageDescription']/a")
	public WebElement dateLink;
	
	@FindBy(id = "p:f:j_id25:j_id61:4:j_id64")
	public WebElement am8Link;
	
	@FindBy(id = "p:f:j_id25:j_id61:20:j_id64")
	public WebElement pm4Link;
	
	@FindBy(xpath = "//img[@class='comboboxIcon']")
	public WebElement subjectComboIcon;
	
	@FindBy(id = "evt5")
	public WebElement subjectFieldInput;
	
	
	@FindBy(xpath = "//div[@class='choicesBox tertiaryPalette brandSecondaryBrd']/ul/li[5]")
	public WebElement otherComboBox;
	
	@FindBy(id = "EndDateTime_time")
	public WebElement endTimeField;
	
	@FindBy(id = "timePickerItem_42")
	public WebElement endTime9PM;
	
	@FindBy(id = "simpleTimePicker")
	public WebElement simpleTimePicker;
	
	@FindBy(xpath = "//div[@class='multiLineEventBlock dragContentPointer']")
	public WebElement addedEventElement;
	
	@FindBy(id = "IsRecurrence")
	public WebElement isRecurrence;
	
	@FindBy(xpath = "//div[@id='recpat']/table")
	public WebElement isRecurrenceDetails;
	
	@FindBy(xpath = "//table[@class='recurrenceTable']")
	public WebElement isRecurrenceFrequency;
	@FindBy(id = "RecurrenceStartDateTime")
	public WebElement isRecurrenceStartDateTime;
	@FindBy(id = "RecurrenceEndDateOnly")
	public WebElement isRecurrenceEndDate;
	@FindBy(id = "maxRecurrence")
	public WebElement maxRecurrence;
	@FindBy(xpath = "//a[@title='Month View']/img")
	public WebElement monthView;
	@FindBy(xpath = "//table[@class='calendarMonthView secondaryPalette']")
	public WebElement tableMonthView;
	
	@FindBy(xpath = "//td[@class='calToday']/div")
	public List<WebElement> blockedEvents;
	
	
	public void clickAllTabs() {
		allTabs.click();
		logger.info("Clicked on '+'");
	}
	public boolean isAllTabsHomePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isAllTabsHomePage=false;
		String extected=FileUtils.readRandomScenariosPropertiesFile("allTabsPageTitle");
		String actual= driver.getTitle();
		if(actual.equals(extected)) {
			logger.info("All tabs page is displayed.");
			isAllTabsHomePage = true;
		}else {
			logger.info("Can not display All tabs page.");
			isAllTabsHomePage=false;
		}
		return isAllTabsHomePage;
	}
	public void clickCustomizeMyTabs() {
		customizeMyTabs.click();
		logger.info("Clicked on 'Customize My Tabs'");
	}
	public boolean isCustomizeMyTabsPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isCustomizeMyTabsPage=false;
		String extected=FileUtils.readRandomScenariosPropertiesFile("customizeMyTabsPageTitle");
		String actual= driver.getTitle();
		if(actual.equals(extected)) {
			logger.info("Customize My Tabs Page is displayed.");
			isCustomizeMyTabsPage = true;
		}else {
			logger.info("Can not display Customize My Tabs Page.");
			isCustomizeMyTabsPage=false;
		}
		return isCustomizeMyTabsPage;
	}
	public String removeTab(WebDriver driver) {
		String selectTabOption = CommonActionUtils.selectRandomValueFromDropdown(selectTabsDropdown);
		
		CommonActionUtils.selectDropDownOption(driver, selectTabsDropdown, selectTabOption);
		logger.info("'"+selectTabOption+"' is selected from 'Selected Tabs'");
		removeBtn.click();
		logger.info("'Remove' button is Clicked.");
		return selectTabOption;
	}
	public boolean removedToSelectedDropdownCheck(WebDriver driver, String optionRemoved) {
		
		availableTabsDropdown.click();
		Select dd= new Select(availableTabsDropdown);
		boolean optionFound = false;
		List<WebElement> options =dd.getOptions();
		for(WebElement option:options) {
			if(option.getText().equals(optionRemoved)) {
				optionFound = true;
				logger.info(optionRemoved+" is avaliable in Available Tabs drop down");
				break;
			}else {
				logger.info(optionRemoved+" is  not avaliable in Available Tabs drop down");
				optionFound = false;
			}
		}
		return optionFound;
	}
	public void saveBtn() {
		saveBtn.click();
		logger.info("'Save' button is Clicked.");
	}
	public boolean isOptionRemovedFromTabBar(WebDriver driver, String optionRemoved) {
		List<WebElement> tabs = tabBar.findElements(By.tagName("a"));
		boolean optionFound = false;
		for(WebElement tab : tabs) {
			System.out.println(tab.getText());
			if(!tab.getText().trim().equals(optionRemoved)) {
				optionFound = true;
				break;
			}
		}
		return optionFound;
	}
	public void addOptionBack(WebDriver driver, String optionRemoved) {
		allTabs.click();
		customizeMyTabs.click();
		CommonActionUtils.selectDropDownOption(driver, availableTabsDropdown, optionRemoved);
		addBtn.click();
		saveBtn.click();
	}
	public boolean verifyDateIsDisplayedAsLink(WebDriver driver) {

		WaitUtils.WaitForVisibility(driver, dateLink);
		 boolean isDisplayed = dateLink.isDisplayed();
	     boolean isLink = dateLink.getTagName().equals("a");
	        
	     LocalDate today = LocalDate.now();	        
	     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE MMMM dd, yyyy");
	     String expectedDateText = today.format(formatter);
	     boolean isTextCorrect = dateLink.getText().equals(expectedDateText);
	     return isDisplayed && isLink && isTextCorrect;
	}
	public void clickDateLink() {
		dateLink.click();
		logger.info("Date Link is clicked.");
	}
	
	public void click8AM() {
		am8Link.click();
		logger.info("8:00 AM Link is clicked.");
	}
	public void click4PM() {
		pm4Link.click();
		logger.info("4:00 PM Link is clicked.");
	}
	
	public boolean isComboBoxpopupOpen(WebDriver driver) throws FileNotFoundException, IOException {
		String parentWindow = driver.getWindowHandle();
		subjectComboIcon.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));

		Set<String> allChildWindows = driver.getWindowHandles();
		for(String window : allChildWindows) {
			if(!window.equals(parentWindow)) {
				try {
					driver.switchTo().window(window);
					//validate popup
					String expectedComboBoxTitle=FileUtils.readRandomScenariosPropertiesFile("comboBoxTitle");
					String actualcomboBoxTitle= driver.getTitle();
					if(actualcomboBoxTitle.equals(expectedComboBoxTitle)) {
						logger.info("The 'ComboBox' popup is opened.");						
						this.popupWindowHandle=window;
						return true;
					}else {
						logger.info("Can not open 'ComboBox' popup.");
						return false;
					}
					
				}catch (Exception e) {
					driver.switchTo().window(parentWindow);
					return false;
				}
			}
		}
	driver.switchTo().window(parentWindow);
	return false;
	}
	public void performPopupAction(WebDriver driver, boolean popupOpen) throws FileNotFoundException, IOException {
		if(popupOpen) {
			try {
				driver.switchTo().window(this.popupWindowHandle);
				String text = FileUtils.readRandomScenariosPropertiesFile("comboBoxOption");
				WebElement otherOption = driver.findElement(By.xpath("//a[contains(text(), '"+text+"')]"));
				otherOption.click();
				logger.info("Clicked 'Other' option in the popup.");
				WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.numberOfWindowsToBe(1));
				for(String window :driver.getWindowHandles()) {
					driver.switchTo().window(window);
				}
			}catch(NoSuchWindowException e) {
				logger.warn("Popup window is already closed: "+e.getMessage());
				
			}finally {
				driver.switchTo().defaultContent();
			}
		}else {
			logger.info("Popup was not opened. Can not perform actions in popup.");
		}		
	}
	public boolean isPopUpClosed(WebDriver driver) {
		if(driver.getWindowHandles().size() == 1) {
			logger.info("The popup window is closed.");	
			return true;
		}else {
			logger.info("The popup window is still open.");	
			return false;
		}
		
	}
	public boolean isOtherInSubjectField(WebDriver driver) throws FileNotFoundException, IOException {
		String actual= subjectFieldInput.getAttribute("value");
		String expected = FileUtils.readRandomScenariosPropertiesFile("comboBoxOption");
		if(actual.equals(expected)) {
			logger.info(expected+" is entered in the Subject Field.");	
			return true;
		}else {
			logger.info(expected+" can not be selected.");	
			return false;
		}
		
	}
	public boolean areDropdownTimesCorrect(WebDriver driver) {
		endTimeField.click();
		List<WebElement> timeOptions = simpleTimePicker.findElements(By.className("simpleHour"));
		List<String> expectedTimes = Arrays.asList("9:00 PM", "9:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM");
		List<String> actualTimes = new ArrayList<>();
		for(WebElement option : timeOptions) {
			String timeText = option.getText().trim();
			actualTimes.add(timeText);
		}
		return  actualTimes.containsAll(expectedTimes);      
	}
	
	public boolean areEndTimesCorrect(WebDriver driver) {
		endTimeField.click();
		List<WebElement> timeOptions = simpleTimePicker.findElements(By.className("simpleHour"));
		List<String> expectedTimes = Arrays.asList("5:00 PM", "5:30 PM", "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM", "8:00 PM","8:30 PM","9:00 PM", "9:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM");
		List<String> actualTimes = new ArrayList<>();
		for(WebElement option : timeOptions) {
			String timeText = option.getText().trim();
			actualTimes.add(timeText);
		}
		return  actualTimes.containsAll(expectedTimes);      
	}
	
	public boolean selectTime9PM(WebDriver driver) throws IOException {
		String timeToSelect =FileUtils.readRandomScenariosPropertiesFile("chooseTime9PM");
		List<WebElement> timeOptions = simpleTimePicker.findElements(By.className("simpleHour"));
		for(WebElement option : timeOptions) {
			if(option.getText().trim().equals(timeToSelect)) {
				option.click();
				logger.info("9:00 PM is selected in the 'End' field.");
				return true;
			}
		}			
		return false;
	}
	public boolean selectTime7PM(WebDriver driver) throws IOException {
		String timeToSelect =FileUtils.readRandomScenariosPropertiesFile("chooseTime7PM");
		List<WebElement> timeOptions = simpleTimePicker.findElements(By.className("simpleHour"));
		for(WebElement option : timeOptions) {
			if(option.getText().trim().equals(timeToSelect)) {
				option.click();
				logger.info("7:00 PM is selected in the 'End' field.");
				return true;
			}
		}			
		return false;
	}
	public String getSelectedTime(WebDriver driver) throws IOException {		
		return endTimeField.getAttribute("value");
	}
	public boolean isSavedCalenderEvent(WebDriver driver) throws IOException {
		String originalString = calendarForUser.getText();
		String userName =originalString.split(" - ")[0];
		saveBtn.click();
		boolean isPageSaved = false;
		if(driver.getTitle().contains(userName)) {
			isPageSaved = true;
			
	       
	       	String eventNameLink = addedEventElement.getText();
	       	String expected = FileUtils.readRandomScenariosPropertiesFile("ComboBoxOption");
			if (eventNameLink.equals(expected)) {
				logger.info("Calender is displayed with 'Other' event as a link.");
	        } else {
	        	logger.info("No Calender Event");
	        }
			
		}else {
			isPageSaved = false;
		}
				
		return isPageSaved;
	}
	public boolean isRecurrenceSelected(WebDriver driver) {
		boolean isRecurrenceSelected = false;
		if(isRecurrence.isDisplayed()) {
			isRecurrence.click();
			logger.info("Recurrence 'checkbox' is selected.");
			isRecurrenceSelected = true;
		}else {
			isRecurrenceSelected = false;
			logger.info("Recurrence 'checkbox' is not selected.");
		}
		
		return isRecurrenceSelected;
	}
	public boolean isRecurrenceDetailsAvailable(WebDriver driver) {

		maxRecurrence.click();
		return isRecurrenceFrequency.isDisplayed()&& isRecurrenceStartDateTime.isDisplayed() && isRecurrenceEndDate.isDisplayed();
	}
	public boolean areBlockedEventsInWeek(WebDriver driver) {
		try {
            monthView.click();
            logger.info("Click the month view element."); 
           
            // Get today's date and calculate the week range
            LocalDate today = LocalDate.now();
            LocalDate endOfWeek = today.plusDays(6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd"); // Adjust format as needed
         
            // Loop through the week to check for blocked events
            for (LocalDate date = today; date.isBefore(endOfWeek.plusDays(1)); date = date.plusDays(1)) {
                String dateStr = date.format(formatter); 
                if (!blockedEvents.isEmpty()) {
                	logger.info("Blocked events found for date: " + dateStr);
                    return true; // Blocked event found
                }
            }
            return false; // No blocked events found
        } catch (Exception e) {
            // Handle exceptions (e.g., element not found, not clickable)
        	logger.info("An error occurred: " + e.getMessage());
            return false;
        }
	}
	
}
