Feature: Shortlist property from listing page

  Background: User on property listing page
    Given the user is on the property listing page
#TS_MB_10,TS_MB_11
  Scenario: Verify shortlisting a property from the listing results
    And clicks on sortby and selects most recent filter
    When I shortlist the first property from the listing
    And I view the shortlisted properties
