package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import utils.CommonActionUtils;
import utils.FileUtils;

public class LoginTest extends BaseTest{
	@Test
	public void loginErrMsgTC01() throws FileNotFoundException, IOException {
//		driver = new ChromeDriver();
//		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));
//		lp = new LoginPage(driver);
//		hp = new HomePage(driver);
		//above 4 lines in @BeforeMethod and last driver.close(); in @AfterMethod
		
		//Repetitive code: is in @BeforeMethod like driver, url and global objects lp,hp etc
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername(expectedUserName);
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);
		
		lp.user_password.clear();
		String actualPassword = CommonActionUtils.getElementValueAttribute(lp.user_password);
		Assert.assertEquals("", actualPassword);
		lp.clickLogin();
		Assert.assertEquals(lp.getErrorMessage(), FileUtils.readLoginPropertiesFile("password_error_text"));
//		driver.close(); //in @AfterMethod
	}
	
	@Test
	public void loginToSalesfoceTC02() throws FileNotFoundException, IOException {		
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername(expectedUserName);
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);
		
		String expectedPassWord = FileUtils.readLoginPropertiesFile("valid.password");
		lp.enterPassword(expectedPassWord);
		String actualPassword = lp.user_password.getAttribute("value");
		Assert.assertEquals(expectedPassWord, actualPassword);
		lp.clickLogin();	
		Assert.assertTrue(hp.isHomePage(driver), "Home Page should be displayed.");
	}
	
//	@Test
//	public void loginToSalesforce() throws FileNotFoundException, IOException {
//		HomePage hp = lp.loginToApp(driver);
//		Assert.assertTrue(hp.isHomePage(driver), "Home Page should be displayed");
//	}
	
	
}
