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
		logger.info("Chilcked on the Edit Icon.");
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
		logger.info("Clicked on the 'About' tab");
	}
	public boolean verifyAboutTabOperations(WebDriver driver) throws FileNotFoundException, IOException {
		boolean isLastNameChanged = true;
		lastName.clear();
		String update_lastname = FileUtils.readMyProfilePropertiesFile("update.lastName");
		lastName.sendKeys(update_lastname);
		logger.debug("last name is entered.");
		saveAllBtn.click();
		logger.debug("Save Changes.");
		driver.switchTo().defaultContent();
		if(!userName.getText().contains(update_lastname)) {
			isLastNameChanged = false;
			logger.debug("Can not change last name.");
		}		
		logger.debug("Last Name Changed Successfully.");
		return isLastNameChanged;
	}
	public boolean verifyPostCreated(WebDriver driver) throws FileNotFoundException, IOException {
		boolean postIsCreated =true;
		
		postLink.click();
		logger.info("Post link is clicked.");
		driver.switchTo().frame(iframeForPostText);
		String message = FileUtils.readMyProfilePropertiesFile("postContent");
		postTextArea.sendKeys(message);
		logger.info("Post message is entered.");
		driver.switchTo().defaultContent();
		if(shareButton.isDisplayed()) {
			shareButton.click();
			logger.debug("Post is shared.");
		}	
		WaitUtils.explicitWaitForElementsVisibility(driver, view_highlight);
		if(!view_highlight.isDisplayed()) {
			 postIsCreated = false;		
			 logger.debug("Post is created successfully.");
		}
		return postIsCreated;
	}
	public boolean verifyFileUploaded(WebDriver driver) {
		boolean isFileUploaded = true;
		if(fileLink.isDisplayed()) {
			fileLink.click();
			logger.debug("Clicked on File link.");
			uploadFileAction.click();
			logger.debug("Clicked 'Upload a file from your computer'.");
			chooseFile.sendKeys(FileConstants.TEST_FILE_UPLOAD_PATH);
			logger.debug("File is selected to upload");
			upload.click();
			logger.info("File is shared.");
			if(WaitUtils.explicitWaitForInVisibility(driver, cancelUpload)) {
				isFileUploaded = true;
			}			
		}
		return isFileUploaded;
	}
	public void clickOnAddPhoto(WebDriver driver) {
		CommonActionUtils.mouseHover(driver, moderator);
		addPhotoLink.click();
		logger.debug("Clicked on 'Add Photo'.");
	}
	public boolean verifyAddPhoto(WebDriver driver) {
		boolean isPhotoUploaded = false;
		driver.switchTo().frame(photoUploadIframe);
		if(WaitUtils.explicitlyWaitForClickableElement(driver, uploadPhoto)) {
			uploadPhoto.sendKeys(FileConstants.TEST_FILE_PHOTO_PATH);
			WaitUtils.explicitlyWaitForClickableElement(driver, uploadSaveBtn);
			uploadSaveBtn.click();
			logger.debug("Photo is selected to upload.");
			WaitUtils.explicitWaitForInVisibility(driver, spinnerIcon1);
			cropAndSave.click();	
			logger.debug("Photo is cropped to upload.");
			WaitUtils.explicitWaitForInVisibility(driver, cropSpinner);
			isPhotoUploaded = true;
			logger.debug("Photo is Uploaded Successfully.");
		}else {
			isPhotoUploaded = false;
		}
		driver.switchTo().defaultContent();
		return isPhotoUploaded;
	}
	
}
