package pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import constants.FileConstants;
import utils.CommonActionUtils;
import utils.FileUtils;
import utils.WaitUtils;

public class MyProfilePage extends BasePage {
	public MyProfilePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='userNav-menuItems']/a[1]")
	public WebElement myProfile; 
	
	@FindBy(xpath = "//div[@class='editPen']/a[@class='contactInfoLaunch editLink']/img[@title='Edit Profile']")
	public WebElement editPen;
	
	@FindBy(id = "contactInfoContentId")
	public WebElement iframeAboutTab; 
	
	@FindBy(id = "aboutTab")
	public WebElement aboutTab; 
	
	@FindBy(id = "contactTab")
	public WebElement contactTab;
	
	@FindBy(id = "lastName")
	public WebElement lastName;
	
	@FindBy(id = "tailBreadcrumbNode")
	public WebElement userName; 
	
	@FindBy(xpath = "//input[@value='Save All']")
	public WebElement saveAllBtn; 
	
	@FindBy(xpath = "//span[@class='publisherattachtext '][contains(text(), 'Post')]")
	public WebElement postLink;
	
	@FindBy(xpath = "//iframe[@class='cke_wysiwyg_frame cke_reset']")
	public WebElement iframeForPostText;
	
	@FindBy(xpath = "/html/body")
	public WebElement postTextArea;
	
	@FindBy(xpath = "//input[@id='publishersharebutton']")
	public WebElement shareButton;
	
	@FindBy(xpath = "//div[@class='view highlight']")
	public WebElement view_highlight;
	
	@FindBy(xpath = "//span[@class='publisherattachtext '][contains(text(),'File')]")
	public WebElement fileLink;
	
	@FindBy(id = "chatterUploadFileAction")
	public WebElement uploadFileAction;
	
	@FindBy(id = "chatterFile")
	public WebElement chooseFile;
	
	@FindBy(id = "publishersharebutton")
	public WebElement upload;
	
	@FindBy(xpath = "//input[@value='Cancel Upload']")
	public WebElement cancelUpload;
	
	@FindBy(id = "displayBadge")
	public WebElement moderator;
	
	@FindBy(id = "uploadLink")
	public WebElement addPhotoLink;
	
	@FindBy(id = "uploadPhotoContentId")
	public WebElement photoUploadIframe;
	
	@FindBy(id = "j_id0:uploadFileForm:uploadInputFile")
	public WebElement uploadPhoto;
	
	@FindBy(xpath = "//*[@id='j_id0:waitingForm']/img")
	public WebElement spinnerIcon1;
	
	@FindBy(xpath = "//input[@name='j_id0:uploadFileForm:uploadBtn']")
	public WebElement uploadSaveBtn;
	
	@FindBy(name="j_id0:j_id7:save")
	public WebElement cropAndSave;
	
	@FindBy(xpath = "//*[@id='cropWaitingPage:croppingForm']/img")
	public WebElement cropSpinner;
	
	
	public void clickMyProfile() {
		myProfile.click();		
	}
	public boolean isMyProfilePage(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isMyprofilePage = false;
		String expectedTitle = "User: "+userName.getText()+"~ Salesforce - Developer Edition";
		WaitUtils.waitForTitleToBe(driver, driver.getTitle());
		String actualTitle =  driver.getTitle();		
		if(actualTitle.equals(expectedTitle)) {
			isMyprofilePage = true;
		}else {
			isMyprofilePage = false;
		}
		return isMyprofilePage;
	}
	public void clickEditProfileBtn() {
		editPen.click();		
	}
	
	public boolean verifyContactIframeAvailability(WebDriver driver) {
		boolean isIframeLoaded = false;
		if(WaitUtils.explicitlyWaitForClickableElement(driver, iframeAboutTab)) {
			driver.switchTo().frame(iframeAboutTab);
			if(aboutTab.isDisplayed() && contactTab.isDisplayed()) {
				isIframeLoaded = true;
			}else {
				isIframeLoaded = false;
			}
		}
		return isIframeLoaded;
	}
	public void clickAboutTab() {
		aboutTab.click();
	}
	public boolean verifyAboutTabOperations(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isLastNameChanged = true;
		lastName.clear();
		String update_lastname = FileUtils.readMyProfilePropertiesFile("update.lastName");
		lastName.sendKeys(update_lastname);
		saveAllBtn.click();
		driver.switchTo().defaultContent();
		if(!userName.getText().contains(update_lastname)) {
			isLastNameChanged = false;
		}		
		return isLastNameChanged;
	}
	public boolean verifyPostCreated(WebDriver driver) throws FileNotFoundException, IOException {
		boolean postIsCreated =true;
		
		postLink.click();
		driver.switchTo().frame(iframeForPostText);
		String message = FileUtils.readMyProfilePropertiesFile("postContent");
		postTextArea.sendKeys(message);
		driver.switchTo().defaultContent();
		if(shareButton.isDisplayed()) {
			shareButton.click();
		}	
		WaitUtils.explicitWaitForElementsVisibility(driver, view_highlight);
		if(!view_highlight.isDisplayed()) {
			 postIsCreated = false;			
		}
		return postIsCreated;
	}
	public boolean verifyFileUploaded(WebDriver driver) {
		boolean isFileUploaded = true;
		if(fileLink.isDisplayed()) {
			fileLink.click();
			uploadFileAction.click();
			chooseFile.sendKeys(FileConstants.TEST_FILE_UPLOAD_PATH);
			upload.click();
			if(WaitUtils.explicitWaitForInVisibility(driver, cancelUpload)) {
				isFileUploaded = true;
			}			
		}
		return isFileUploaded;
	}
	public void clickOnAddPhoto(WebDriver driver) {
		CommonActionUtils.mouseHover(driver, moderator);
		addPhotoLink.click();
		
	}
	public boolean verifyAddPhoto(WebDriver driver) {
		boolean isPhotoUploaded = false;
		driver.switchTo().frame(photoUploadIframe);
		if(WaitUtils.explicitlyWaitForClickableElement(driver, uploadPhoto)) {
			uploadPhoto.sendKeys(FileConstants.TEST_FILE_PHOTO_PATH);
			WaitUtils.explicitlyWaitForClickableElement(driver, uploadSaveBtn);
			uploadSaveBtn.click();
			WaitUtils.explicitWaitForInVisibility(driver, spinnerIcon1);
			cropAndSave.click();				
			WaitUtils.explicitWaitForInVisibility(driver, cropSpinner);
			isPhotoUploaded = true;
		}else {
			isPhotoUploaded = false;
		}
		driver.switchTo().defaultContent();
		return isPhotoUploaded;
	}
	
}
