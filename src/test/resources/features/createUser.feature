Feature: Create User

  Background:
    Given The service is up

  Scenario: Check if user can create an account with valid credentials
    When I send a POST request having valid credentials
    Then I have 200 status code
    And I check the response body is valid

  Scenario: Check status code 405 when the body is empty
    When I send a POST request to create a new account with empty body
    Then I have 405 status code
    And I check if the response message contains "no data"