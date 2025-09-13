Feature: Navigate to Home Interiors Design Services
#TS_MB_12
  Scenario: Verify user can navigate to Home Interiors Design Services from shortlist page
    Given the user is on the shortlist page
    And the user clicks on the profile menu
    And the user selects Home Interiors
    And the user clicks on Interior Designers in new delhi
    Then the user should be displayed Home Interiors Designers page
    
