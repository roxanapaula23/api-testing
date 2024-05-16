Feature:  Get User Details by Username

  Background:
    Given The system is operational

  Scenario: Retrieve information for a specific username
    Given A list of users is registered in the system
    When I request information for a specific username
    Then The system responds with a 'OK' status code
    And The information matches the user details

  Scenario: Non-existent username cannot be retrieved
    Given A list of users is registered in the system
    When I attempt to retrieve an username that does not exist
    Then The system responds with a 'Not Found' status code
    And The response message contains 'User not found'
