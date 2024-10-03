#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Login Feature
  
Background:
Given I launched SGDC login page

  Scenario: Validate valid login    
    When I enter valid username
    And enter valid password
    And clicked login button
    Then I should see Home page
    
   Scenario: Validate invalid login
    When I enter invalid username
    And enter invalid password
    And clicked login button
    Then I should see Error message


  #Scenario Outline: Login to the app
    #Given I want to go to the "<url>"
    #When I enter "<username>" and "<password>"
    #Then I click on the login button
#
    #Examples: 
      #| url  												 | username 				| password  |
      #| https://login.salesforce.com | pallavi@qa.com 	| Admin@123 |
      #| https://login.salesforce.com |    123 					| 123123    |
