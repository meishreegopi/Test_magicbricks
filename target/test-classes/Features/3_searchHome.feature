Feature: Magicbricks Property Search

  Scenario Outline: Search and validate property listings by location
    Given the user is on the Magicbricks home page
    When the user enters "<location>" in the Search bar
    And the user selects Property Type from drop down
    And the user selects a budget range from the dropdown
    And the user clicks on the Search button
    Then the user should be redirected to the property listing page
    

  Examples:
    | location   |
    | Chennai  |