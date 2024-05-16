Feature: Logging the user into the system

  Scenario: Successful login with valid credentials
    Given The system is operational
    When I provide the login details of a user
    Then The system responds with a 'OK' status code
    And The response message contains a valid session message
