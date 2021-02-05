Feature: User tries to login with valid and invalid credentials

Scenario: User tries to login
Given User opens Login Page
When User enters username and password
Then User should be logged in 

