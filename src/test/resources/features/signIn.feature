Feature: Log In
  As a user
  I want to be able to log in to my account
  So that I can access my team information

  Background:
    Given the user is on the log in page

  Scenario: Entering valid login credentials allows a user to login
    When the user attempts to login with valid credentials
    Then the user is logged in successfully

  Scenario Outline: Entering invalid email address does not allow a user to login
    When the user attempts to login with an invalid <credential>
    Then the user receives an error message
    And the log in button is disabled

    Examples:
      | credential |
      | EMAIL      |
      | PASSWORD   |

  Scenario Outline: Not entering required <credential> prevents user from logging in
    When the user attempts to log in without a <credential>
    Then the user receives an error message
    And the log in button is disabled

    Examples:
      | credential |
      | email      |
      | password   |

  Scenario: User can log back in after logging out
    Given the user has logged in
    When the user logs out
    Then the user can log in again

  Scenario: User can log in after entering invalid credentials
    Given the user attempts to login with an invalid PASSWORD
    When the user enters a correct password
    Then the user is logged in successfully