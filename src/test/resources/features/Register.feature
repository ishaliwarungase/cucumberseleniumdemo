@Register
Feature: User tries to register

Scenario: User tries to register
Given User opens Registration Page
When User enters information for registration
Then User should register
