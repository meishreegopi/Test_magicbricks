Feature: Shortlist property from listing page

  Background: User on property listing page
    Given the user is on the property listing page

  Scenario: Verify shortlisting a property from the listing results
    And clicks on sortby and selects most recent filter
    When I shortlist the first property from the listing
    And I view the shortlisted properties
