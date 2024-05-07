Feature: Logging the user into the system

  Scenario: Successful login with valid credentials
    Given The service is up
    When I send a GET request to login
    Then I have 200 status code
    And I check the response body contains a valid session message
