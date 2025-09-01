package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NaukriPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By loginBtn = By.id("login_Layer");
    private By emailInput = By.xpath("//input[@placeholder='Enter your active Email ID / Username']");
    private By passwordInput = By.xpath("//input[@placeholder='Enter your password']");
    private By submitBtn = By.xpath("//button[text()='Login']");
    private By profileMenu = By.xpath("//div[contains(@class,'nI-gNb-drawer__bars')]");
    private By updateResumeBtn = By.xpath("//input[@id='attachCV']");
    private By saveBtn = By.xpath("//button[text()='Save']");
    private By jobsTab = By.xpath("//a[@title='Jobs']");
    private By applyNowBtn = By.xpath("//button[contains(text(),'Apply')]");

    public NaukriPage(WebDriver driver) {
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
            System.out.println("Error during Naukri login: " + e.getMessage());
        }
    }

    public void updateResume(String resumePath) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(profileMenu)).click();
            WebElement upload = wait.until(ExpectedConditions.presenceOfElementLocated(updateResumeBtn));
            upload.sendKeys(resumePath);
            wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
            System.out.println("Resume updated successfully on Naukri.");
        } catch (Exception e) {
            System.out.println("Error updating resume on Naukri: " + e.getMessage());
        }
    }

    public void applyJob(String jobKeyword) {
        try {
            driver.findElement(jobsTab).click();
            // Ideally enter job keyword in search bar (locator needed) and click search
            // Example:
            // driver.findElement(By.id("qsb-keyword-sugg")).sendKeys(jobKeyword);
            // driver.findElement(By.xpath("//button[text()='Search']")).click();

            wait.until(ExpectedConditions.elementToBeClickable(applyNowBtn)).click();
            System.out.println("Applied for job on Naukri with keyword: " + jobKeyword);
        } catch (Exception e) {
            System.out.println("Error applying job on Naukri: " + e.getMessage());
        }
    }
}

