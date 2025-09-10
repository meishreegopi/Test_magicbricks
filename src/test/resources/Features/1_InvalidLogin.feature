@InvalidLogin
Feature: Invalid Login Scenarios
  Scenario Outline: Login with invalid mobile number
    Given I am on the homepage of magicbricks
    When I click on the login button of home page
    And I click the inner login button
    And I am redirected to the login page 
    And I enter an invalid mobile number "<phone>" and captcha "<captcha>"
    Then I should see an error message for invalid mobile number

    Examples:
      | phone   | captcha |
      | 38345   | kluy    |

  Scenario Outline: Login with missing captcha
    Given I am on the homepage of magicbricks
    When I click on the login button of home page
    And I click the inner login button
    And I am redirected to the login page 
    And I enter a mobile number "<phone>" without captcha
    Then I should see an error message for captcha

    Examples:
      | phone      |
      | 9940114678 |

 