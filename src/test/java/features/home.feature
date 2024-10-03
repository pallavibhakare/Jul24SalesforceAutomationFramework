
Feature: Home Page feature
  

  Scenario: Select user menu
    Given I should be logged in successfully 
    And I am on the home page
    When I click usermenu
    Then I should see usermenu options
    

  Scenario: Test data table
    Given step1 context
    When step2 action
    Then step3 outcome
    
    |Text		|Number	|
    |Java		|1			|
    |Paython|2			|
    |C++		|3			|