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

Feature: One-click job apply  

Feature: One-click job apply
  Scenario: Login and update resume on LinkedIn
    Given I open the browser
    When I login to LinkedIn
    And I delete my resume on LinkedIn
    And I update my resume on LinkedIn
   

    #When I login to Naukri with username "yourNaukriUser" and password "yourNaukriPass"  
    #And I update my resume on Naukri  
    #And I apply for a job on Naukri  

    #When I login to Instahyre with username "yourInstahyreUser" and password "yourInstahyrePass"  
    #And I update my resume on Instahyre  
    #And I apply for a job on Instahyre  

    Then I close the browser  
