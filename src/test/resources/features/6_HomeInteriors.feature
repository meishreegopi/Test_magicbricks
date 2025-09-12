Feature: Navigate to Home Interiors Design Services

  Background:
    Given the user is on the shortlist page
    And the user clicks on the profile menu
    And the user selects Home Interiors
    And the user clicks on Interior Designers in new delhi
    Then the user should be displayed Home Interiors Designers page

  Scenario: Verify user can navigate to a designer profile from designers page
    And the user clicks on the first designer
    Then the designer's profile page should be displayed
    And the user clicks on the designer image
