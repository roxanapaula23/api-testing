Feature: Update user

  Scenario: Update user personal data
    Given I send a GET request to login
    When I send a PUT request to update user data
    Then I have 200 status code
    And I check the response body is valid
