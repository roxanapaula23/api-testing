Feature: List of users with array

  Scenario: Create multiple users using array
    Given The service is up
    When I send a POST request with a list of users
    Then I have 200 status code
    And I check the response body is valid
