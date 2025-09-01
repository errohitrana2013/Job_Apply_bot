package stepdefinitions;


import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.awt.AWTException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.LinkedInPage;
import pages.NaukriPage;
import util.ConfigReader;
import pages.InstahyrePage;

public class JobApplySteps {
    WebDriver driver;
    LinkedInPage linkedInPage;
    NaukriPage naukriPage;
    InstahyrePage instahyrePage;
    String downloadPath;

    @Given("I open the browser")
    public void i_open_the_browser() {
    	WebDriverManager.chromedriver().setup();

    	 // Get project directory dynamically
        String projectDir = System.getProperty("user.dir");
        downloadPath = projectDir +"/src/test/resources"; 
        
        // Set preferences for Chrome
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath); // folder for downloads
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("safebrowsing.enabled", "true");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*"); // helps with WebSocket errors
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("prefs", prefs);
        
        driver = new ChromeDriver(options);
    }
    @When("I login to LinkedIn")
    public void login_linkedin() {
        String username = ConfigReader.getProperty("linkedin.username");
        String password = ConfigReader.getProperty("linkedin.password");

        driver.get(ConfigReader.getProperty("linkedin.url"));
        linkedInPage = new LinkedInPage(driver);
        linkedInPage.login(username, password);
        System.out.println("✅ Successfully logged in and LinkedIn Home Page is loaded.");
    }
    @When("I delete my resume on LinkedIn")
    public void delete_resume_linkedin() {
        linkedInPage.deleteResumeIfPresent();
    }

    @When("I update my resume on LinkedIn")
    public void upload_job_linkedin() {
        try {
			linkedInPage.uploadResume(downloadPath);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    // ---- Similar steps for Naukri & Instahyre ---- //

    @Then("I close the browser")
    public void close_browser() {
        driver.quit();
    }
}

