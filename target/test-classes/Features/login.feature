Feature: Contacting the Interior Designer
Scenario Outline: Get the details of the commercial property\
Scenario: Login successful
Given I launch the magicbricks website
And I am on the homepage
When I click on the login button
And I am redirected to the login page and click on phonenumber
And I enter the otp
Then I should be redirected to the homepage
