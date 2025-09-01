package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;

public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		// ⏳ Explicit wait of 180 seconds
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(180));
	}

	protected void type(By locator, String text) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		element.clear();
		element.sendKeys(text);
	}

	protected void click(By locator) { 
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator)); 
		element.click(); 
		 // 🔄 Wait until page load completes after click
	    wait.until((ExpectedCondition<Boolean>) driver ->
	        ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete")
	    );
	}

	protected void waitForAjax() {
	    wait.until(driver -> ((Long)((JavascriptExecutor) driver)
	        .executeScript("return window.jQuery != undefined ? jQuery.active : 0")) == 0);
	}
	protected void uploadFile(By locator, String filePath) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		element.sendKeys(filePath);
	}
}
