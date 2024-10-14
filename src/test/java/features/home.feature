
Feature: Home Page feature
 
Background:
Given I launched SFDC login page
And I should be logged in successfully
 And I am on the home page 
  
   Scenario: Select user menu TC05   
    When I click usermenu
    Then I should see usermenu options
    
    
   #Scenario: My Profile Option TC06
    #When I click usermenu
    #And I click My profile option
    #And I should see My profile home page
    #And I click Edit Profile icon
    #Then I should see Popup available
    #And click about tab
    #And Verify About tab operations 
    #And 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  #Scenario: Test data table
    #Given step1 context
    #When step2 action
    #Then step3 outcome
    #
    #|Text		|Number	|
    #|Java		|1			|
    #|Paython|2			|
    #|C++		|3			|