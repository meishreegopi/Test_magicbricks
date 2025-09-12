Feature: Get an estimate for Home Interiors Design Services

  Scenario: User can successfully get an estimate for their home interiors
    Given the user is on the "Interior Designers in New Delhi" page
    When the user clicks the "Get Estimate Now" button
    And the user selects "2 BHK" and "Large" for their home
    And they click on the "Next" button
    And they choose a timeline of "Within 2 Months"
    And a tentative budget of "3 to 5 Lakhs"
    And select "Self-use" as the purpose
    And they click the "Continue" button
    Then a personalized estimate for the interior services should be displayed 
    