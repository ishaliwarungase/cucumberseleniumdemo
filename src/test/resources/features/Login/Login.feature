Feature: User tries to login with valid and invalid credentials

Scenario: Valid login
Given User enters URL
When User logs in using valid credentials
Then User should be logged in 

Scenario: Invalid login
Given User enters URL
When User logs in using invalid credentials
Then User should not be logged in
