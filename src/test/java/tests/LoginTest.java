package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonActionUtils;
import utils.FileUtils;

public class LoginTest extends BaseTest{
//	@Test
	public void loginErrMsgTC01() throws FileNotFoundException, IOException {
//		driver = new ChromeDriver();
//		driver.navigate().to(FileUtils.readLoginPropertiesFile("url"));
//		lp = new LoginPage(driver);
//		hp = new HomePage(driver);
		//above 4 lines in @BeforeMethod and last driver.close(); in @AfterMethod
		
		//Repetitive code: is in @BeforeMethod like driver, url and global objects lp,hp etc
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername();
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);
		
		lp.user_password.clear();
		String actualPassword = CommonActionUtils.getElementValueAttribute(lp.user_password);
		Assert.assertEquals("", actualPassword);
		lp.clickLogin();
		Assert.assertEquals(lp.getErrorMessage(), FileUtils.readLoginPropertiesFile("password_error_text"));
//		driver.close(); //in @AfterMethod
	}
	
//	@Test
	public void loginToSalesfoceTC02() throws FileNotFoundException, IOException {		
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername();
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);
		
		String expectedPassWord = FileUtils.readLoginPropertiesFile("valid.password");
		lp.enterPassword();
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
	
//	@Test
	public void checkRememberMeTC03() throws FileNotFoundException, IOException {
		getAppURL();
		Assert.assertTrue(lp.isLoginPage(driver), "SFDC lgin page must be displayed.");
		lp.enterUsername();
		lp.enterPassword();
		lp.clickRememberMe();
		lp.clickLogin();
		Assert.assertTrue(hp.isHomePage(driver), "Salesforce home page must be displayed");
		hp.clickUserMenu(driver);
		hp.logout(driver);
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login | Salaesforce Page");
		Assert.assertTrue(lp.isRememberMeChecked(driver));
		Assert.assertTrue(lp.isSavedUserName(), "Validate username dispalys in use name field.");
		
	}
	
//	@Test
	public void forgetPasswordTC04A() throws FileNotFoundException, IOException {
		getAppURL();
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login Page");
		lp.forgetPassword(driver);
		Assert.assertTrue(lp.isForgetPasswordPage(driver), "Verify Forget Password Page is displayed.");
		lp.forgotYourPassword(driver);
		Assert.assertTrue(lp.isResetMessagePage(driver), "Password reset message page must be displayed.");
	}
	
	@Test
	public void validateLoginErrorMessageTC04B() throws FileNotFoundException, IOException {
		getAppURL();
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login Page should be displayed.");
		lp.enterWrongUsername();
		Assert.assertTrue(lp.isUserNameEntered(), "Verify "+FileUtils.readLoginPropertiesFile("wrong.username")+" is entered in username field.");
		lp.enterWrongPassword();
		Assert.assertTrue(lp.isPasswordEntered(), "Verify password is entered in password field.");
		lp.clickLogin();
		Assert.assertTrue(lp.isErrorMsgDisplayed(), "Error message should be displayed.");
		
	}
}
