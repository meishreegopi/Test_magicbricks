Feature:Test Buying a property 
Scenario Outline:Get the details of the property and owner successfully
Given I launch the magicbricks website
Scenario: Valid user login successfully
  Given I am on the homepage
  When I click on the login button
  And I am redirected to the login page and click on phonenumber
  And I enter the otp
  Then I should be redirected to the homepage