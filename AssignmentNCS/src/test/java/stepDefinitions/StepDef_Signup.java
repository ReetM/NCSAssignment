package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import pages.signupPage;

public class StepDef_Signup {
	

	signupPage signUpPage=new signupPage();
	    @Given("I open the PizzaNCS website click on Login signup")
	    public void clickLoginSignup() {
	    	signUpPage.clickLoginSignupIcon();

	    }

	    @When("I click the signUp link")
	    public void clickLink() {
	    	signUpPage.clickLinkByText();
	    }

	    @Then("I click the SIGN UP button")
	    public void clickSignup() throws InterruptedException {
	    	signUpPage.clickSignupButton();
	    }

	    @Then("I should see the following error messages:")
	    public void verifyErrorMessages(io.cucumber.datatable.DataTable dataTable) {
	        Map<String, String> expectedErrors = dataTable.asMap(String.class, String.class);
	        expectedErrors.forEach((field, message) -> {
	        	signUpPage.getError(field,message);
	          // Assert.assertEquals(message, signUpPage.getError(field));
	        });
	    }

	    @When("I enter {string} in Username field")
	    public void enterUsername(String username) {
	    	signUpPage.enterUsername(username);
	    }
	    @Then("I enter {string} in Password field")
	    public void enterPassowrd(String Password) {
	    	signUpPage.enterPassword(Password);
	    }
	    @Then("I enter {string} in Confirm Password field")
	    public void enterConfirmPassowrd(String ConfirmPassword) {
	    	signUpPage.enterConfirmPassword(ConfirmPassword);
	    }
	    @Then("I should see error message {string} for {word}")
	    public void verifyFieldError(String expected, String field) {
	    	signUpPage.getFieldError(field,expected);
	    }
	    @Then("I should see snackbar message {string}")
	    public void verifySnackbarMessage(String expectedMsg) {
	        Assert.assertEquals(expectedMsg, signUpPage.getSnackbarMessage());
	    }
	}



