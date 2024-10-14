package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import listeners.TestListener;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.RandomScenariosPage;
import utils.FileUtils;
import utils.WaitUtils;

@Listeners(TestListener.class)
public class RandomScenariosTest extends BaseTest {
	LoginPage lp;
	HomePage hp;
	ContactsPage cp;
	MyProfilePage mpp;
	RandomScenariosPage rp;
	
	@BeforeMethod(alwaysRun = true)
	public void login() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);		
		hp = new HomePage(driver);
		cp = new ContactsPage(driver);
		rp=new RandomScenariosPage(driver);
		mpp=new MyProfilePage(driver);
	}
	
	@Test
	public void verifyFirstAndLastNameOfUserTC33() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		logger.info("RandomScenariosTest: verifyFirstAndLastNameOfUserTC33:Browser instance launched");	
		Assert.assertTrue(hp.isHomePage(driver));
		hp.clickHomeTab(driver);
		test.get().info("'Home' tab clicked.");	
		Assert.assertTrue(hp.isHomeTabPage(driver));
		test.get().log(Status.INFO, "Home tab page is displayed.");
		Assert.assertTrue(hp.isFirstLastNameAndLinkDisplayed(driver));
		test.get().log(Status.INFO, "First name, Last name and link is displayed correctly.");
		boolean res=hp.clickUserNameLink(driver);
		Assert.assertTrue(res);
		test.get().info(driver.getTitle()+" is displayed.");
	
	}
	@Test
	public void verifyLastNameUpdatedTC34() throws FileNotFoundException, IOException {
		WebDriver driver= getDriver();
		logger.info("RandomScenariosTest: verifyLastNameUpdatedTC34:Browser instance launched");
			Assert.assertTrue(hp.isHomePage(driver));
			test.get().info("Home page is dispayed.");	
			hp.clickHomeTab(driver);
			test.get().info("'Home' tab clicked.");	
			boolean res=hp.clickUserNameLink(driver);
			Assert.assertTrue(res);
			test.get().info(driver.getTitle()+" is displayed.");
			mpp =new MyProfilePage(driver);
			mpp.clickEditProfileBtn();
			Assert.assertTrue(mpp.verifyContactIframeAvailability(driver));
			Assert.assertTrue(mpp.verifyEditPopupTabSelected(driver));
			test.get().info("The 'Edit Profile' popup is displayed with 'Contact' tab selected.");
			mpp.clickAboutTab();
			Assert.assertTrue(mpp.verifyFirstNameFocus(driver));
			test.get().info("Focus is in First Name fields.");
			mpp.verifyAboutTabOperations1(driver);
			Assert.assertTrue(mpp.verifyEditPopupClosed(driver));
			test.get().info("'Edit profile' popup is closed.");
			Assert.assertTrue(mpp.isLastnameUpdatedInBreadcrumb(driver));
			test.get().info("'Last Name' is updated in breadcrumb.");
			Assert.assertTrue(hp.isLastNameUpdatedInUserMenu(driver));
			test.get().info("'Last Name' is updated in User Menu '"+hp.getUserName(driver)+"' as well as in page title"+driver.getTitle());
		
	}
	@Test
	public void tabCustomizationTC35() throws FileNotFoundException, IOException {
		WebDriver  driver=null;
		String optionRemoved="";
		try {
		driver= getDriver();	
		logger.info("RandomScenariosTest: tabCustomizationTC35:Browser instance launched");
		Assert.assertTrue(hp.isHomePage(driver));
		test.get().info("Home page is dispayed.");	
		rp.clickAllTabs();
		Assert.assertTrue(rp.isAllTabsHomePage(driver));
		test.get().info("All tabs page is displayed.");
		rp.clickCustomizeMyTabs();
		Assert.assertTrue(rp.isCustomizeMyTabsPage(driver));
		test.get().info("Customize My Tabs page is displayed.");
		optionRemoved= rp.removeTab(driver);
		boolean res = rp.removedToSelectedDropdownCheck(driver, optionRemoved);
		Assert.assertTrue(res);
		test.get().info("Tab removed from 'Selected Tabs' is available in 'Available Tabs'.");
		rp.saveBtn();
		Assert.assertTrue(rp.isAllTabsHomePage(driver));
		test.get().info("'All Tabs' page is displayed.");
		Assert.assertTrue(rp.isOptionRemovedFromTabBar(driver, optionRemoved));
		test.get().info("'+optionRemoved+' is removed fron Tab bar.");
		hp.clickUserMenu(driver);
		hp.clickLogoutLink(driver);
		Assert.assertTrue(hp.isLoginPage(driver));
		test.get().info("Salesforce login page is displayed.");
		lp.loginToApp(driver);
		Assert.assertTrue(hp.isHomePage(driver));
		test.get().info("Salesforce home page is displayed.");
		Assert.assertTrue(rp.isOptionRemovedFromTabBar(driver, optionRemoved));
		test.get().info("'+optionRemoved+' is removed fron Tab bar.");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
//			if(driver!=null) {
				rp.addOptionBack(driver,optionRemoved);
//			}
		}
	}
	@Test
	public void blockingAnEventInTheCalanderTC36() throws FileNotFoundException, IOException {
		WebDriver  driver=null;		
		try {
		driver= getDriver();	
		logger.info("RandomScenariosTest: blockingAnEventInTheCalanderTC36:Browser instance launched");
//		Assert.assertTrue(hp.isHomePage(driver));
//		test.get().info("Home page is dispayed.");
		hp.clickHomeTab(driver);
		Assert.assertTrue(rp.verifyDateIsDisplayedAsLink(driver));
		rp.clickDateLink();
		test.get().info("'"+driver.getTitle()+"' page is dispayed.");
		rp.click8AM();
		test.get().info("'"+driver.getTitle()+"' page is dispayed.");
		boolean popupOpen=rp.isComboBoxpopupOpen(driver);
		Assert.assertTrue(popupOpen);
		test.get().info("The 'ComboBox' popup is opened.");

		rp.performPopupAction(driver, popupOpen);
		Assert.assertTrue(rp.isPopUpClosed(driver));
		test.get().info("The 'ComboBox' is closed.");
		boolean res=rp.isOtherInSubjectField(driver);
		Assert.assertTrue(res);
		test.get().info(FileUtils.readRandomScenariosPropertiesFile("comboBoxOption")+" is entered in Subject Field.");
		
		Assert.assertTrue(rp.areDropdownTimesCorrect(driver));
		test.get().info("Dropdown is displayed with time starting from 9:00Pm till 11:30PM.");
		
		Assert.assertTrue(rp.selectTime9PM(driver));
		test.get().info(rp.getSelectedTime(driver)+" is selected in the 'End' field.");
		
		Assert.assertTrue(rp.isSavedCalenderEvent(driver));
		test.get().pass("The '"+driver.getTitle()+"' page is displayed with 'Other' event in the time slot 8:00PM to 9:00 PM, as a link.");
		
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
				driver.close();
			}
		}
	}
	
	@Test
	public void calenderEventWithRecurranceTC37() throws FileNotFoundException, IOException {
		WebDriver  driver=null;		
		try {
			driver= getDriver();	
			logger.info("RandomScenariosTest: calenderEventWithRecurranceTC37:Browser instance launched");
			Assert.assertTrue(hp.isHomePage(driver));
			test.get().info("Home page is dispayed.");
			hp.clickHomeTab(driver);
			Assert.assertTrue(rp.verifyDateIsDisplayedAsLink(driver));
			rp.clickDateLink();
			test.get().info("'"+driver.getTitle()+"' page is dispayed.");
			rp.click4PM();
			test.get().info("'"+driver.getTitle()+"' page is dispayed.");
			
			boolean popupOpen=rp.isComboBoxpopupOpen(driver);
			Assert.assertTrue(popupOpen);
			test.get().info("The 'ComboBox' popup is opened.");
			
			rp.performPopupAction(driver, popupOpen);
			Assert.assertTrue(rp.isPopUpClosed(driver));
			test.get().info("The 'ComboBox' is closed.");
			boolean res=rp.isOtherInSubjectField(driver);
			Assert.assertTrue(res);
			test.get().info(FileUtils.readRandomScenariosPropertiesFile("comboBoxOption")+" is entered in Subject Field.");
			
			Assert.assertTrue(rp.areEndTimesCorrect(driver));
			test.get().info("Dropdown is displayed with time starting from 5:00PM till 11:30PM.");
			
			Assert.assertTrue(rp.selectTime7PM(driver));
			test.get().info(rp.getSelectedTime(driver)+" is selected in the 'End' field.");
			
			Assert.assertTrue(rp.isRecurrenceSelected(driver));
			test.get().info("Recurrence 'checkbox' is selected.");
			Assert.assertTrue(rp.isRecurrenceDetailsAvailable(driver));
			test.get().info("Recurrence 'checkbox' is selected.");
			
			Assert.assertTrue(rp.isSavedCalenderEvent(driver));
			test.get().pass("The '"+driver.getTitle()+"' page is displayed with 'Other' event in the time slot 8:00PM to 9:00 PM, as a link.");
			
			Assert.assertTrue(rp.areBlockedEventsInWeek(driver));
			test.get().info("'"+driver.getTitle()+"' page is dispayed.");
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(driver!=null) {
				System.out.println("TC End 37");
			}
		}
	}
}
