Feature: Sign out from the system

  Scenario: Successful logout from the system
    Given The system is operational
    When A logout action is initiated
    Then The system responds with a 'OK' status code
    And The response message contains 'ok'