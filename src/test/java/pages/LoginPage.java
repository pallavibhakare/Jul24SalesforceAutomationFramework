package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileUtils;
import utils.WaitUtils;

public class LoginPage extends BasePage{
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(id = "Login")
	public WebElement loginButton;
	
	@FindBy(xpath = "//input[@id='username']")
	public WebElement userName;
	
	@FindBy(id = "password")
	public WebElement user_password;
	
	@FindBy(id = "login_form")
	public WebElement login_form;
	
	@FindBy(id = "forgot_password_link")
	public WebElement forgetPassword;
	
	@FindBy(name = "rememberUn")
	public WebElement rememberMe;
	
	@FindBy(id = "idcard-identity")
	public WebElement savedUserName;
	
	@FindBy(id = "error")
	public WebElement errorMessage;
	
	@FindBy(xpath = "//input[@id='un']")
	public WebElement UserNameForForgetPassword;
	
	@FindBy(id = "continue")
	public WebElement continueBtn;
		
	@FindBy(xpath = "//div[@id='forgotPassForm']/div[@class='message']/p[1]")
	public WebElement passwordResetMsg;
	
	public void enterUsername() throws FileNotFoundException, IOException {
		this.userName.clear();
		this.userName.sendKeys(FileUtils.readLoginPropertiesFile("valid.username"));
		logger.debug("Username is entered.");
	}
	public void enterWrongUsername() throws FileNotFoundException, IOException {
		this.userName.clear();
		this.userName.sendKeys(FileUtils.readLoginPropertiesFile("wrong.username"));		
		logger.debug("Wrong username is entered.");
	}
	public boolean isUserNameEntered() {
		boolean usernameEntered = false;
		if(userName.getCssValue("value") != null) {
			usernameEntered = true;
			logger.info("Username entered.");
		}else {
			usernameEntered = false;
			logger.info("can not enter Username.");
		}
		return usernameEntered;
	}
	public boolean isPasswordEntered() {
		boolean passwordEntered = false;
		if(user_password.getCssValue("value") != null) {
			passwordEntered = true;
			logger.info("Password entered.");
		}else {
			passwordEntered = false;
			logger.info("please enter password.");
		}
		return passwordEntered;
	}
	
	public boolean isSavedUserName(WebDriver driver) {
		boolean displayed = true;
		WaitUtils.WaitForVisibility(driver, savedUserName);
		if(!savedUserName.isDisplayed()) {
			displayed = false;
			logger.info("Username is not saved.");
		}
		return displayed;
	}
	
	public void enterPassword() throws FileNotFoundException, IOException {
		this.user_password.clear();
		this.user_password.sendKeys(FileUtils.readLoginPropertiesFile("valid.password"));
		logger.debug("Password is Entered.");
	}
	public void enterWrongPassword() throws FileNotFoundException, IOException {
		this.user_password.sendKeys(FileUtils.readLoginPropertiesFile("wrong.password"));
		logger.debug("Wrong Password is Entered.");
	}
	public void clickLogin() {
		this.loginButton.click();	
		logger.info("'Login' button is clicked");
	}
	public String getErrorMessage() {		
		return errorMessage.getText();
	}
	public boolean isLoginPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isALoginPage = true;
		WaitUtils.fluentlyWaitForLoginPageTitle(driver, driver.getTitle());
		if(!driver.getTitle().equals(FileUtils.readLoginPropertiesFile("loginPageTitle"))) {
			isALoginPage = false;
		}else {
			isALoginPage = true;
			logger.info("Login Page is displayed.");
		}
		return isALoginPage;
	}
	public void clickRememberMe() {
		rememberMe.click();
		logger.info("Checked Remember Me checkbox.");
	}
	public boolean isRememberMeChecked(WebDriver driver) {
		boolean rememberMeChecked = false;
		WaitUtils.explicitWaitForElementsVisibility(driver, rememberMe);
		if(rememberMe.isSelected()) {			
			rememberMeChecked = true;
			logger.info("Remember Me is checked.");
		}else {
			rememberMeChecked = false;
			logger.info("'Remember Me' checkbox is not checked.");
		}
		return rememberMeChecked;
	}
	
	public HomePage loginToApp(WebDriver driver) throws FileNotFoundException, IOException{		
		enterUsername();
		enterPassword();		
		this.clickLogin();
		return new HomePage(driver);
	}
	
	public HomePage loginToApp(WebDriver driver, String uName, String pass) throws FileNotFoundException, IOException{		
		userName.sendKeys(uName);
		user_password.sendKeys(pass);		
		this.clickLogin();
		return new HomePage(driver);
	}
	public void forgetPassword(WebDriver driver) {
		forgetPassword.click();	
		logger.info("'Forget Password' link is cliked.");
	}
	public boolean isForgetPasswordPage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isForgetPasswordPage = true;
		if(!driver.getTitle().equals(FileUtils.readLoginPropertiesFile("forgetPasswordPageTitle"))) {
			isForgetPasswordPage = false;
		}
		return isForgetPasswordPage;
	}
	public void forgotYourPassword(WebDriver driver) throws FileNotFoundException, IOException {
		
		UserNameForForgetPassword.sendKeys(FileUtils.readLoginPropertiesFile("UserNameForForgetPassword"));		
		continueBtn.click();
		logger.info("'Continue' button is cliked.");
	}
	public boolean isResetMessagePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isPasswordResetPage = false;
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		if(driver.getTitle().equals(FileUtils.readLoginPropertiesFile("emailSentPageTitle")) ) {
			isPasswordResetPage = true;
			logger.info(" Password reset link is sent.");
		}else {
			isPasswordResetPage = false;
		}
		return isPasswordResetPage;
	}
	public boolean isErrorMsgDisplayed() throws FileNotFoundException, IOException {
		if(errorMessage.isDisplayed() ) {
			return true;
		}else {
			return false;
		}		
	}
}
