package tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest{
	@BeforeMethod
	public void login() throws FileNotFoundException, IOException {
		getAppURL();
		lp.loginToApp(driver);
	}
	
	@Test
	public void selectUserMenuTC05() throws FileNotFoundException, IOException {
//		getAppURL();
//		lp.loginToApp(driver);
		Assert.assertTrue(hp.isHomePage(driver), "Applications home page must be displayed with logged user Name "+hp.getUserName(driver));
		Assert.assertTrue(hp.isUserMenuAvailable(), "User Menu drop down should be available.");
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User menu options '"+hp.getUsermenuOptionNames()+"' should be displayed");	
	}
	
//	@Test
	public void myProfileOptionTC06() throws FileNotFoundException, IOException {
//		getAppURL();
//		lp.loginToApp(driver);
		hp.clickUserMenu(driver);
		Assert.assertTrue(hp.verifyUserMenuOptions(), "User Menu Options should be verified.");
		hp.clickMyProfile();
		Assert.assertTrue(hp.isMyProfilePage(driver), "USer Profile page should be displayed.");
		hp.clickEditProfileBtn();
	}
	
}
