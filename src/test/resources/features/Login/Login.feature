Feature: Customer Login

  Scenario: Successfull Login

    Given User enters URL
    When User logged in using username as "username" and password as "password"
    Then Home page should be displayed
