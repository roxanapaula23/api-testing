Feature: List of users creation

  Scenario: Create multiple users
    Given The system is operational
    When A list of users is registered in the system
    Then The system responds with a 'OK' status code
    And The response body confirms user creation

