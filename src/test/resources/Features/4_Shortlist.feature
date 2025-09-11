Feature: Shortlist property from listing page

Scenario: Verify shortlisting a property from the listing results
Given the user is on the property listing page
And clicks on sortby and selects most recent filter
When I shortlist the first property from the listing
And I view the shortlisted properties
