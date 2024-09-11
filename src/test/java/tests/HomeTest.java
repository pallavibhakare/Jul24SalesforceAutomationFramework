package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.FileUtils;


public class HomeTest extends BaseTest{
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		getAppURL();
		lp.loginToApp(driver);
		hp = new HomePage(driver);
	}
	
//	@Test
	public void selectUserMenuTC05() throws FileNotFoundException, IOException {
//		getAppURL();
//		lp.loginToApp(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Applications home page must be displayed with logged user Name "+hp.getUserName(driver));
		Assert.assertTrue(hp.isUserMenuAvailable(), "User Menu drop down should be available.");
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User menu options '"+hp.getUsermenuOptionNames()+"' should be displayed");	
	}
	
	@Test
	public void myProfileOptionTC06() throws FileNotFoundException, IOException {
//		getAppURL();
//		lp.loginToApp(driver);
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu Options should be verified.");
		mp.clickMyProfile();
		Assert.assertTrue(mp.isMyProfilePage(driver), "User Profile page should be displayed.");
		mp.clickEditProfileBtn();
		Assert.assertTrue(mp.verifyContactIframeAvailability(driver), "Edit profile pop up window should be displayed with contact and About tabs ro edit.");
		mp.clickAboutTab();
		Assert.assertTrue(mp.verifyAboutTabOperations(driver), "User Profile Page should be updated with new "+FileUtils.readMyProfilePropertiesFile("update.lastName"));		
		Assert.assertTrue(mp.verifyPostCreated(driver), "Entered post text should be displayed on the page");
		Assert.assertTrue(mp.verifyFileUploaded(driver), "Selected file should be posted");
		mp.clickOnAddPhoto(driver);
		Assert.assertTrue(mp.verifyAddPhoto(driver), "Uploaded photo should appear on the image.");
		
	}
	
}
