package coreUtils;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCore extends BrowserFactory {
	
	LoggerUtil log;
	WebDriverWait wait;
	
	public SeleniumCore() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}	
	
	
	//
	public WebElement finder(By locator) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(locator);
			wait.until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
		} catch (NoSuchElementException e1) {
			logMessage("⚠ [ELEMENT NOT FOUND] : You might have to update the locator:-" + locator);
		} catch (StaleElementReferenceException e2) {
			logWarning("⚠ [Stale Element Exception!!] : Refinding the element after 5 seconds" + locator);
			holdExecution(5000);
			webElement = driver.findElement(locator);
		}
		return webElement;
	}
	
	
	//
	public List<WebElement> finderAll(By locator) {
		List<WebElement> webElements = null;
		try {
			webElements = driver.findElements(locator);
		} catch (NoSuchElementException e) {
			logMessage("❌ [ELEMENT NOT FOUND] : You might have to update the locator:- " + locator);
		}
		return webElements;
	}
	
	/*
	 * Wrapper methods for click operation
	 * 
	 */
	public void clickElement(By locator) {
		WebElement element = finder(locator);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			element.click();
			log.info("✔ Clicked Element " + element + " Successfully!");
		} catch (StaleElementReferenceException ex1) {
			holdExecution(4000);
			element = finder(locator);
			element.click();
			log.warn("⚠ Clicked Element " + element + " after catching Stale Element Exception");
		} catch (WebDriverException ex3) {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			holdExecution(4000);
			performClickByActionBuilder(locator);
			log.warn("⚠ Clicked Element " + element + " after catching WebDriver Exception");
		}
	}
	
	//
	public void performClickByActionBuilder(By locator) {
		WebElement element = finder(locator);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
		builder.moveToElement(element).click().perform();
	}
	
	
	//get title
	public String getTitle() {
		return driver.getTitle();
	}
	
	//
	public boolean verifyElementText(By locator, String expectedText) {
		WebElement elementName = finder(locator);
		System.out.println(elementName.getText());
		logMessage("Expected text: " + expectedText);
		logMessage("Actual text: " + elementName.getText());
		return expectedText.equalsIgnoreCase(elementName.getText().trim());
	}
	public String getElementText(By locator) {
		WebElement elementName = finder(locator);
		System.out.println(elementName.getText());
		return elementName.getText();
	}
	//
	public void clearAndTypeTextInEditBox(By locator, String TextToEnter) {
		WebElement element = finder(locator);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"), TextToEnter);
	}
	
	//
	public void handleAlert() {
		try {
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			logMessage("Alert handled..");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("No Alert window appeared...");
		}
	}
	
	//
	public boolean isElementDisplayed(By locator, String message) {
		WebElement element = finder(locator);
		boolean result = element.isDisplayed();
		if (result) {
			logMessage(message +" is displayed successfully.");
		}else {
			logError(message +" is not displayed successfully.");
		}
		return result;
	}
	
	//
	public void logMessage(String message) {
		log.info("✔  " + message);
	}
	
	//
	public void logWarning(String message) {
		log.warn("⚠ " + message);
	}
	
	//
	public void logError(String message) {
		log.error("❌" + message);
	}
	
	//
	public void holdExecution(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			logMessage("Failed while trying to halt execution!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//
	public void waitForElementToDisplay(By locator, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Throwable e) {
			logWarning("Element not visible...");
		}
	}

	//
	public void waitForElementToBeInteractive(By locator, int timeout) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Throwable e) {
			logWarning("Element is not clickable...");
		}
	}
	
	// Used to handle page loading
	public void validatePageIsReadyForAction() {
		holdExecution(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page loaded successfully.");
			return;
		}
		// This loop will rotate for 60 times to check If page Is ready after every 1
		// second.
		// You can replace your value with 60 If you wants to Increase or decrease wait
		// time.
		for (int i = 0; i < 60; i++) {
			holdExecution(1000);
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}
}
