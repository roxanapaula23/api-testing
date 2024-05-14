Feature: Sign out from the system

  Scenario: Successful logout from the system
    Given I send a GET request to login
    When I send a GET request to logout
    Then I have 200 status code
    And I check the response body contains "ok" message