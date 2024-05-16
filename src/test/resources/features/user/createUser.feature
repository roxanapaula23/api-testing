Feature: Create User

  Scenario: Validate user registration with correct credentials
    Given The system is operational
    When I submit valid credentials for registration
    Then The system responds with a 'OK' status code
    And The response body confirms user creation

  Scenario: Verify response when no data is provided
    Given The system is operational
    When I attempt to register a new user without providing any data
    Then The system responds with a 'Method Not Allowed' status code
    And The response message contains "no data"