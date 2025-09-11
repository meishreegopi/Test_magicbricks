Feature: Navigate to Home Interiors Design Services

  Scenario: Verify user can navigate to Home Interiors Design Services from listing page
    Given the user is on the shortlist page
    And the user clicks on the profile menu
    And the user selects Home Interiors
    And the user clicks on Home Interior Design Services
    And the user click on select your city
    And the user click on ahmedabad
    Then the user should be displayed Home Interiors Designers page
