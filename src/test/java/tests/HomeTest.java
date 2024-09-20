package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import listeners.TestListener;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.MySettingsPage;
import utils.FileUtils;

@Listeners(TestListener.class)
public class HomeTest extends BaseTest{
	
	HomePage hp;
	
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		
		WebDriver driver = getDriver();	
		LoginPage lp  = new LoginPage(driver);
		hp = lp.loginToApp(driver);
	}
	
	@Test()
	public void selectUserMenuTC05() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Applications home page must be displayed with logged user Name "+hp.getUserName(driver));
		Assert.assertTrue(hp.isUserMenuAvailable(), "User Menu drop down should be available.");		
		hp.clickUserMenu(driver);		
		test.get().info("Clicked on User menu");
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User menu options '"+hp.getUsermenuOptionNames()+"' should be displayed");
		test.get().info("User menu options '"+hp.getUsermenuOptionNames()+"' is displayed");
		logger.info("User menu options '"+hp.getUsermenuOptionNames()+"' is displayed");
	}
	
	@Test()
	public void myProfileOptionTC06() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().log(Status.INFO, "Logged into the Application.");
		MyProfilePage mp = new MyProfilePage(driver);
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu Options should be verified.");
		mp.clickMyProfile();
		test.get().info("Clicked on User menu");
		Assert.assertTrue(mp.isMyProfilePage(driver), "User Profile page should be displayed.");
		mp.clickEditProfileBtn();
		test.get().info("Clicked on Edit Profile Icon");
		Assert.assertTrue(mp.verifyContactIframeAvailability(driver), "Edit profile pop up window should be displayed with contact and About tabs ro edit.");
		mp.clickAboutTab();
		test.get().info("Clicked on About Tab");
		Assert.assertTrue(mp.verifyAboutTabOperations(driver), "User Profile Page should be updated with new "+FileUtils.readMyProfilePropertiesFile("update.lastName"));
		test.get().info("User Profile Page is updated with new "+FileUtils.readMyProfilePropertiesFile("update.lastName"));
		Assert.assertTrue(mp.verifyPostCreated(driver), "Entered post text should be displayed on the page");
		test.get().info("Entered post is visible.");
		Assert.assertTrue(mp.verifyFileUploaded(driver), "Selected file should be posted");
		test.get().info("File is posted.");
		mp.clickOnAddPhoto(driver);
		test.get().info("Clicked on Add Photo");
		Assert.assertTrue(mp.verifyAddPhoto(driver), "Uploaded photo should appear on the image.");
		test.get().info("Photo Uploaded.");
	}
	@Test()
	public void selectMySettingTC07() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().log(Status.INFO, "Logged into the Application.");
		HomePage hp = new HomePage(driver);
		MySettingsPage ms = new MySettingsPage(driver);
		hp.clickUserMenu(driver);
		test.get().log(Status.INFO, "Click User menu");
		Assert.assertTrue(hp.isUserMenuOptionsVisible(), "Drop down with"+hp.getUsermenuOptionNames()+" is displayed.");
		test.get().log(Status.INFO, "Drop down with "+hp.getUsermenuOptionNames()+" is displayed.");
		hp.clickMySettings();
		Assert.assertTrue(hp.isMySettingsHomePage(driver), "My settings page should be displayed.");
		test.get().log(Status.INFO, "My settings page is displayed.");
	
		boolean isLoginHistoryDownloaded = ms.personalSettings(driver);
		Assert.assertTrue(isLoginHistoryDownloaded, "Login History should be downloaded in .csv format.");
		test.get().log(Status.INFO, "Login History is downloaded in .csv format.");
		    
		ms.displayAndLayouts(driver);
		Assert.assertTrue(ms.verfiySelectedAvailableTabIsInSelectedTabs(), "Reports should be in Selected Tabs");
		test.get().log(Status.INFO, "Reports should be in Selected Tabs.");
		
		ms.emailAndSettings(driver);
		Assert.assertTrue(hp.isMySettingsHomePage(driver), "My settings page should be displayed.");
		test.get().log(Status.INFO, "Email details are saved and My settings page is displayed.");
		
		ms.calenderAndReminders(driver);
		test.get().log(Status.INFO, "Sample event pop up window is displayed.");		
	}
	@Test()
	public void selectDeveloerConsoleTC08() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().log(Status.INFO, "Logged into the Application.");
		HomePage hp = new HomePage(driver);
		hp.clickUserMenu(driver);
		test.get().log(Status.INFO, "Click User menu");
		Assert.assertTrue(hp.isUserMenuOptionsVisible(), "Drop down with"+hp.getUsermenuOptionNames()+" is displayed.");
		test.get().log(Status.INFO, "Drop down with "+hp.getUsermenuOptionNames()+" is displayed.");
		hp.clickDeveloperConsole();
		test.get().log(Status.INFO, "Clicked on the Developer Console");
		hp.isDevConsoleDisplayed(driver);
		test.get().log(Status.PASS, "Developer Console window is closed.");
	}
	@Test()
	public void selectLogoutTC09() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		test.get().log(Status.INFO, "Logged into the Application.");
		HomePage hp = new HomePage(driver);
		hp.clickUserMenu(driver);
		test.get().log(Status.INFO, "Click User menu");
		Assert.assertTrue(hp.isUserMenuOptionsVisible(), "Drop down with"+hp.getUsermenuOptionNames()+" is displayed.");
		test.get().log(Status.INFO, "Drop down with "+hp.getUsermenuOptionNames()+" is displayed.");
		hp.logout(driver);
		test.get().info("Clicked login button");
		logger.info("Logout Button Clicked.");
		Assert.assertTrue(hp.isLoginPage(driver), "Login Page should be displayed.");
		test.get().pass(hp.getPageTitle(driver)+" page is opened.");
		logger.info(hp.getPageTitle(driver)+" page is opened.");
	}
}
