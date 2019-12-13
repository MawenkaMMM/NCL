Feature: NCL homepage

  Scenario: Guests filters shore excursion result using price range
    // URL:https://www.ncl.com/
    //Price range:$0-$30
    
    Givena a Guest

    And I am on the homepage
    And navigated to Shore Excursion page
    And I click find excursion
    And Shore Excursion page is present
    When price range is filtered to $0-$30
    Then Only shore excursions within range are displayed
