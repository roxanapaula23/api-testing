Feature: Update user

  Scenario: Update user personal data
    Given I provide the login details of a user
    When I update the user data
    Then The system responds with a 'OK' status code

