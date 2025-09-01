package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class InstahyrePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loginBtn = By.xpath("//a[text()='Login']");
    private By emailInput = By.id("authEmail");
    private By passwordInput = By.id("authPass");
    private By submitBtn = By.xpath("//button[text()='Log In']");
    private By profileMenu = By.xpath("//a[contains(text(),'My Profile')]");
    private By updateResumeBtn = By.xpath("//input[@type='file']");
    private By saveBtn = By.xpath("//button[contains(text(),'Save')]");
    private By jobsTab = By.xpath("//a[text()='Jobs']");
    private By applyNowBtn = By.xpath("//button[contains(text(),'Apply')]");

    public InstahyrePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void login(String email, String password) {
        try {
            driver.findElement(loginBtn).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
            driver.findElement(passwordInput).sendKeys(password);
            driver.findElement(submitBtn).click();
        } catch (Exception e) {
            System.out.println("Error during Instahyre login: " + e.getMessage());
        }
    }

    public void updateResume(String resumePath) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(profileMenu)).click();
            driver.findElement(updateResumeBtn).sendKeys(resumePath);
            wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
            System.out.println("Resume updated successfully on Instahyre.");
        } catch (Exception e) {
            System.out.println("Error updating resume on Instahyre: " + e.getMessage());
        }
    }

    public void applyJob(String jobKeyword) {
        try {
            driver.findElement(jobsTab).click();
            // Example: search for a job and apply (locator needs adjustment)
            wait.until(ExpectedConditions.elementToBeClickable(applyNowBtn)).click();
            System.out.println("Applied for job on Instahyre with keyword: " + jobKeyword);
        } catch (Exception e) {
            System.out.println("Error applying job on Instahyre: " + e.getMessage());
        }
    }
}

