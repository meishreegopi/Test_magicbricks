@ValidLogin
Feature: Test Buying a property

  Background: Launch Magicbricks
    Given I launch the magicbricks website
#TS_MB_04
  Scenario Outline: Get the details of the property and owner successfully
  Scenario: Valid user login successfully
    Given I am on the homepage
    When I click on the login button
    And I am redirected to the login page and click on phonenumber
    And I enter the otp
    Then I should be redirected to the homepage
