Feature: Login into salesforce application
Background:
   Given User open Salesforce apllication
   
   Scenario: Login with valid username and valid Password
    
      When User on "LoginPage"
      When User enters a usernname and Password
      When User Click on Login button
      When User on "HomePage"
      Then verify the home page text
      
    Scenario: Login with valid username and invalid Password
    
      When User on "LoginPage"
      When User enters a usernname and invalid Password
      When User Click on Login button
      Then verify the error text
      
    Scenario: Login with Rememberme
    
      When User on "LoginPage"
      When User enters a usernname and Password
      When User Click on Login button
      When User on "HomePage"
      When User click Logout
      Then verify Login page with rememberMe
      
    Scenario: Click Forgot password with Invalid username and Invalid password
    
    
      When User on "LoginPage"
      When User enters a invalid usernname and invalid Password
      When User Click on Login button
      Then verify the error text for invalid username,password
      
     Scenario: Click Forgot password with only valid username 
    
    
      When User on "LoginPage"
      When User enters a valid usernname 
      When User Click on Login button
      When User click forgot password
      When User click continue button
      Then verify the error text like forgot password
      
    