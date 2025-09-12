@invalidsearch
Feature: Search Home Invalid Scenarios

Background: 
	Given I am on the Magicbricks home page

  Scenario: Invalid location entered
    When I enter "Invalid Location" in the Search bar
    And I click on the Search button
    Then user should remain on the home page

  Scenario: No location entered
    When I enter "" in the Search bar
    And I click on the Search button
    Then user should remain on the home page