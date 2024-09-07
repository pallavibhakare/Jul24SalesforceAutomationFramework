package pages;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.FileUtils;

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
	
	@FindBy(id = "forget_password_link")
	public WebElement forgetPassword;
	
	@FindBy(name = "rememberUn")
	public WebElement rememberMe;
	
	@FindBy(id = "hint_back_chooser")
	public WebElement savedUserName;
	
	@FindBy(id = "error")
	public WebElement errorMessage;
	
	public void enterUsername(String username) {
		this.userName.sendKeys(username);		
	}
	public void enterPassword(String passWord) {
		this.user_password.sendKeys(passWord);		
	}
	public void clickLogin() {
		this.loginButton.click();		
	}
	public String getErrorMessage() {		
		return errorMessage.getText();
	}
	public HomePage loginToApp(WebDriver driver) throws FileNotFoundException, IOException{
		this.userName.sendKeys(FileUtils.readLoginPropertiesFile("valid.username"));
		this.enterPassword(FileUtils.readLoginPropertiesFile("valid.password"));
		this.clickLogin();
		return new HomePage(driver);
	}
}
