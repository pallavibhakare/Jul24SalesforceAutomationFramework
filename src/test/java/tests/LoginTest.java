package tests;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.aventstack.extentreports.Status;
import listeners.TestListener;
import pages.HomePage;
import pages.LoginPage;
import utils.CommonActionUtils;
import utils.FileUtils;


@Listeners(TestListener.class)
public class LoginTest extends BaseTest{
	@Test()
	public void loginErrMsgTC01() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		logger.info("LoginTest:loginErrMsgTC01:Browser instance launched");		
		LoginPage lp = new LoginPage(driver);	
		test.get().info("Login Page launched");
		logger.info("Login Page launched");
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername();
		test.get().info("Username Entered.");		
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);
		
		lp.user_password.clear();
		test.get().info("Password field is empty.");		
		String actualPassword = CommonActionUtils.getElementValueAttribute(lp.user_password);
		Assert.assertEquals("", actualPassword);
	
		lp.clickLogin();	
		test.get().info("Clicked login button");
		Assert.assertEquals(lp.getErrorMessage(), FileUtils.readLoginPropertiesFile("password_error_text"));
		test.get().log(Status.PASS, "Error message '"+lp.getErrorMessage()+"' is displayed");
//		ScreenshotUtils.captureScreenshot(driver);
	}
	
	@Test()
	public void loginToSalesfoceTC02() throws FileNotFoundException, IOException {				
		WebDriver driver = getDriver();
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		logger.info("loginToSalesfoceTC02: Login Page Launched.");
		String expectedUserName = FileUtils.readLoginPropertiesFile("valid.username");
		lp.enterUsername();
		test.get().info("Username Entered.");
		String actualUserName = CommonActionUtils.getElementValueAttribute(lp.userName);
		Assert.assertEquals(expectedUserName, actualUserName);		
		String expectedPassWord = FileUtils.readLoginPropertiesFile("valid.password");
		lp.enterPassword();
		test.get().info("Password Entered.");
		String actualPassword = lp.user_password.getAttribute("value");
		Assert.assertEquals(expectedPassWord, actualPassword);
		lp.clickLogin();
		test.get().info("Clicked login button");
		Assert.assertTrue(hp.isHomePage(driver), "Home Page should be displayed.");
		test.get().pass(hp.getHomePageTitle(driver)+" page is opened.");
//		throw new ElementNotInteractableException("");
	}
	

	
	@Test()
	public void checkRememberMeTC03() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		logger.info("checkRememberMeTC03: Login page launched.");
		LoginPage lp = new LoginPage(driver);
		HomePage hp = new HomePage(driver);
		Assert.assertTrue(lp.isLoginPage(driver), "SFDC lgin page must be displayed.");
		
		lp.enterUsername();	
		lp.enterPassword();
		test.get().log(Status.INFO, "Username and Password is entered.");		
		lp.clickRememberMe();
		test.get().log(Status.INFO, "Selected Remember me checkbox");		
		lp.clickLogin();
		test.get().log(Status.INFO, "Login Button Clicked.");
		Assert.assertTrue(hp.isHomePage(driver), "Salesforce home page must be displayed");
		
		hp.clickUserMenu(driver);
		test.get().log(Status.INFO, "User Menu is clicked.");
		logger.info("User menu Clicked");
		
		hp.clickLogoutLink(driver);
		test.get().log(Status.INFO, "Logout button is clicked.");
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login | Salaesforce Page");
		Assert.assertTrue(lp.isRememberMeChecked(driver));
		Assert.assertTrue(lp.isSavedUserName(driver), "Validate username displays in use name field.");
		
	}
	
	@Test()
	public void forgetPasswordTC04A() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();
		LoginPage lp = new LoginPage(driver);
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login Page");
		test.get().log(Status.INFO , "On Login Page");	
		lp.forgetPassword(driver);
		Assert.assertTrue(lp.isForgetPasswordPage(driver), "Verify Forget Password Page is displayed.");
		test.get().log(Status.PASS, "Forget Password Page is displayed.");	
		lp.forgotYourPassword(driver);
		Assert.assertTrue(lp.isResetMessagePage(driver), "Password reset message page must be displayed.");
		test.get().log(Status.PASS, "Password reset message page must be displayed.");	
	}
	
	@Test()
	public void validateLoginErrorMessageTC04B() throws FileNotFoundException, IOException {
		WebDriver driver = getDriver();	
		LoginPage lp = new LoginPage(driver);
		Assert.assertTrue(lp.isLoginPage(driver), "Verify Login Page should be displayed.");
		test.get().log(Status.INFO, "Login Page is displayed.");
		lp.enterWrongUsername();
		Assert.assertTrue(lp.isUserNameEntered(), "Verify "+FileUtils.readLoginPropertiesFile("wrong.username")+" is entered in username field.");
		test.get().log(Status.INFO, "Wrong username is entered.");
		lp.enterWrongPassword();
		Assert.assertTrue(lp.isPasswordEntered(), "Verify password is entered in password field.");
		test.get().log(Status.INFO, "Wrong password is entered.");
		lp.clickLogin();
		test.get().log(Status.INFO, "Login button Clicked.");
		Assert.assertTrue(lp.isErrorMsgDisplayed(), "Error message should be displayed.");
		test.get().log(Status.PASS, "Error message '"+lp.getErrorMessage()+"' is displayed.");
		
	}
//	@Test(dataProvider = "Login-credentials", dataProviderClass = dataProviders.DataProviders.class)
//	public void loginToSalesfoceAccounts(String userName, String password) throws FileNotFoundException, IOException {				
//		WebDriver driver = getDriver();
//		LoginPage lp = new LoginPage(driver);
//		HomePage hp = new HomePage(driver);
//		hp = lp.loginToApp(driver,userName, password);
//		Assert.assertTrue(hp.isHomePage(driver), "Home Page should be displayed.");
//	}
}
