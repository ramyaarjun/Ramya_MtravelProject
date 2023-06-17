Feature: Testing the Home page


  Scenario: Verify booking flow


    Given User is on the home page

    When User searches for "India"

    Then User should be able to select a date

    And User should see the correct ticket price

    And User should be able to proceed to booking

    And User should be able to register and click on the continue button

    And User should see the booking page