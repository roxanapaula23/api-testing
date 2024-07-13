Feature: Delete a pet from the store
  As a pet store owner
  I want to delete a pet from my store

  Scenario: Successful deletion of a pet with a valid pet ID
    Given The system is operational
    When I submit a request to add a new pet to the store
    Then The system responds with a 'OK' status code
    When I submit a request to delete the pet with a valid ID
    Then The system responds with a 'OK' status code
    And The response body includes a confirmation message

  Scenario: Unsuccessful deletion of a pet with an invalid pet ID
    Given The system is operational
    When I submit a request to add a new pet to the store
    Then The system responds with a 'OK' status code
    When I submit a request to delete the pet with an invalid ID
    Then The system responds with a 'Not Found' status code
    And The response body includes an error message