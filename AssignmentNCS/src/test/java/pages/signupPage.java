package pages;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import coreUtils.SeleniumCore;
import io.netty.handler.timeout.TimeoutException;

public class signupPage extends SeleniumCore{

	    WebDriver driver;
	    WebDriverWait wait;

	    By loginIcon = By.cssSelector("a[aria-label='login or signup']");
	    By signupLink = By.xpath("//a[text()='Sign Up']");
	    By signupBtn = By.cssSelector("button[aria-label='signup']");
	    By snackbar = By.cssSelector(".snackbar");
	    By username=By.xpath("//*[text()='Username']/following-sibling::input[contains(@id,'input')]");
		By password=By.xpath("//*[text()='Password']/following-sibling::input[contains(@id,'input')]");
		By confirmPassword=By.xpath("//*[text()='Confirm Password']/following-sibling::input[contains(@id,'input')]");
		//By usernameError=By.xpath("//div[text()='Username is required']");
		//By passwordError=By.xpath("//div[text()='Please confirm your password']");
		//By confirmPasswordError=By.xpath("//div[text()='Please confirm your password']");
		  
	    

	    public void clickLoginSignupIcon() {
	    	clickElement(loginIcon);
	    }

	    public void clickLinkByText() {
	    	clickElement(signupLink);
	    }

	    public void clickSignupButton() throws InterruptedException {
	    	clickElement(signupBtn);
	    	Thread.sleep(5000);
	       
	    }

	    public void enterUsername(String value) {
	    	clearAndTypeTextInEditBox(username,value);
	  
	    }
	    public void enterPassword(String value) {
	    	clearAndTypeTextInEditBox(password,value);
	  
	    }
	    public void enterConfirmPassword(String value) {
	    	clearAndTypeTextInEditBox(confirmPassword,value);
	  
	    }


	    public void getError(String field,String message) {
			By ErrorMessage=By.xpath("//div[text()=\"" + message + "\"]");

	      switch (field) {
            case "Username": 
            	//verifyElementText(ErrorMessage,message);
            	Assert.assertTrue(verifyElementText(ErrorMessage,message), "Error Message Not Matched");
            	return;
            case "Password": 
            	Assert.assertTrue(verifyElementText(ErrorMessage,message), "Error Message Not Matched");
            	return;
            case "ConfirmPassword": 
            	Assert.assertTrue(verifyElementText(ErrorMessage,message), "Error Message Not Matched");
            	return;
       
        }
	    }
	    public void getFieldError(String field,String message) {
			By ErrorMessage=By.xpath("//div[text()=\"" + message + "\"]");
            	Assert.assertTrue(verifyElementText(ErrorMessage,message), "Error Message Not Matched");

        }
	    public String getSnackbarMessage() {
	    	return getElementText(snackbar);
	    }
	}



