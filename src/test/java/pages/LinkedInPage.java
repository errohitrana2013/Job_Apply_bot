package pages;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedInPage extends BasePage {

	private By usernameField = By.id("username");
	private By passwordField = By.id("password");
	private By loginBtn = By.cssSelector("button.btn__primary--large.from__button--floating");
	private String titleExpected="LinkedIn";
	private By HomeTitle= By.xpath("//nav[@class='global-nav__nav']//span[@title='Home']");
	private By deleteButtonDropDown =By.cssSelector(".jobs-resume-card__options-button-container svg");
	private By deleteButtonPath =By.xpath("//*[@class='jobs-resume-card__options-button-container']//div[text()='Delete']");
	private By resumeUpload = By.xpath("(//div[contains(@class,'jobs-application-settings')]//label)[3]");
	private Actions actions;
	private String filePath="/Rohit_Rana_CV.pdf";
	
	public LinkedInPage(WebDriver driver) {
		super(driver);
		this.actions = new Actions(driver);
	}

	public void login(String user, String pass) {
		// ✅ Assert: Wait until LinkedIn home page is visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		type(usernameField, user);
		type(passwordField, pass);
		try{
			click(loginBtn);
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(HomeTitle));
		}
		catch(Exception e) {
			System.out.println("Linkdin login issue ");
		}
	}
	public void deleteResumeIfPresent() {
        try {
        	WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(20));
            // Wait for up to 10 seconds for the options button to appear
            List<WebElement> optionsButtons = wait1.until(driver -> driver.findElements(deleteButtonDropDown));

            if (optionsButtons.size() > 0) {
                WebElement optionsButton = optionsButtons.get(0);

                // Hover and click using Actions
                actions.moveToElement(optionsButton).click().perform();

                // Wait for the "Delete" option to be clickable and click
                WebElement deleteButton = wait1.until(ExpectedConditions.elementToBeClickable(deleteButtonPath));
                deleteButton.click();

                System.out.println("✅ Resume deleted successfully!");
            } else {
                System.out.println("ℹ️ No resume options button found. Nothing to delete.");
            }
        } catch (Exception e) {
            System.out.println("❌ Failed to delete resume: " + e.getMessage());
        }
    }
	public void uploadResume(String downloadPath) throws AWTException, InterruptedException {
//		String filePathUpload=downloadPath+filePath;
		String filePathUpload=filePath;
        // Wait for page to load properly (replace with explicit wait in real use)
        Thread.sleep(5000);

        // Click the 3rd label
        WebElement uploadLabel = driver.findElement(resumeUpload);
        uploadLabel.click();

        // Wait for the Windows file dialog to appear
        Thread.sleep(2000);

        // Copy file path to clipboard
        System.out.println(filePathUpload);
        StringSelection selection = new StringSelection(filePathUpload);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

        // Use Robot to paste path and press Enter
        Robot robot = new Robot();

        // CTRL + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        Thread.sleep(500);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        Thread.sleep(2000);

        System.out.println("Resume uploaded successfully!");
    }
}
