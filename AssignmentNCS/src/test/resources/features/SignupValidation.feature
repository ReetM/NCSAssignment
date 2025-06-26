Feature: Validation Of Error Messages

  Scenario: SignUp validation
    Given I open the PizzaNCS website click on Login signup 
    When I click the signUp link
    And I click the SIGN UP button
    Then I should see the following error messages:
      | Username              | Username is required         |
      | Password              | Password is required         |
      | ConfirmPassword       | Please confirm your password |

    When I enter "abc" in Username field
    And I enter "abc" in Password field
    And I enter "def" in Confirm Password field
    Then I should see the following error messages:
      | Username              | Username must be minimum of 6 characters |
      | Password              | Password must be minimum of 8 characters |
      | ConfirmPassword       | Your passwords do not match              |

    When I enter "donaldtrump" in Username field
    Then I should see error message "Username already exists" for Username
    When I enter "robinhood" in Username field
    And I enter "letmein2019" in Password field
    And I enter "letmein2019" in Confirm Password field
    When I click the SIGN UP button
    And I should see snackbar message "Thanks robinhood, you can now login."
