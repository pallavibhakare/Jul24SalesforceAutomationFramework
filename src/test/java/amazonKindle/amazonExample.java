package amazonKindle;

import java.io.File;
import java.time.Duration;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class amazonExample {

	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException {
		
		driver = new ChromeDriver();
	
		driver.get("https://www.amazon.com/");

		WebElement captchaImage = driver.findElement(By.xpath("//div[@class= 'a-row a-text-center']/img"));
        String captchaImageUrl = captchaImage.getAttribute("src");


        // Manually solve CAPTCHA challenge
        System.out.println("Please solve the CAPTCHA challenge by entering the characters displayed in the image:");
        System.out.println("CAPTCHA Image URL: " + captchaImageUrl);
        Scanner sc = new Scanner(System.in);
        String captchaInput = sc.nextLine();
        sc.close();
        // Enter the CAPTCHA input into the text field
        WebElement captchaInputField = driver.findElement(By.id("captchacharacters"));
        captchaInputField.sendKeys(captchaInput);
		WebElement submitCapcha = driver.findElement(By.xpath("//button[@class='a-button-text']"));
		submitCapcha.click();
		
		driver.manage().window().maximize();
		WebElement searchField = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchField.sendKeys("Kindle");
		WebElement searchButton = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchButton.click();
		 // Locate the element containing the "Amazon's Choice" badge
        WebElement amazonChoiceBadge = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[1]/div[1]/div[2]/div/div/span/div/div/div/div[1]/div/div[1]/div/span"));
        // Navigate up to the common ancestor of the badge and the image
        WebElement commonAncestor = amazonChoiceBadge.findElement(By.xpath("//ancestor::div[contains(@class, 'puisg-col-inner')]"));
        // Find the image within the common ancestor
        WebElement choiceImage = commonAncestor.findElement(By.cssSelector("div.s-product-image-container img.s-image"));
        String actualProductName = choiceImage.getAttribute("alt");
        System.out.println("actualProductName"+actualProductName);
        // Click on the image containing "Amazon's Choice" badge
        choiceImage.click();
		
        WebElement addToCart = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
        addToCart.click();
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();


        // Switch to the new window
        for (String windowHandle : allWindowHandles) {
            if (!windowHandle.equals(mainWindowHandle)) {
                driver.switchTo().window(windowHandle);
       
            }
        }
        WebElement cancelButton = driver.findElement(By.xpath("//button[@class=' a-button-close a-declarative']/i"));
        cancelButton.click();
        By cancelBtn = By.xpath("//button[@class=' a-button-close a-declarative']/i");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cancelBtn));
        driver.switchTo().window(mainWindowHandle);

        WebElement goToCart = driver.findElement(By.xpath("//span[@id='sw-gtc']/span/a[contains(text(), 'Go to Cart')]"));
        goToCart.click();
        CaptureScreenShots(driver);
        WebElement cartItem = driver.findElement(By.xpath("//a[@class='a-link-normal sc-product-link']/img"));
        String expected = cartItem.getAttribute("alt");
        String expectedProductName = expected.replace(", Opens in a new tab", "");
        System.out.println("expected "+expectedProductName);
        
        
        boolean isProductContained = expected.contains(actualProductName) || actualProductName.contains(expectedProductName);

        if (isProductContained) {
            System.out.println("The products are the same.");
        } else {
            System.out.println("Different item in the cart.");
        }
//        driver.close();
        
}


	private static void CaptureScreenShots(WebDriver driver) {
		TakesScreenshot page = (TakesScreenshot) driver;
		File src = page.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir")+"/src/main/resources/amazon.png");
		src.renameTo(destination);
		
	}

}
