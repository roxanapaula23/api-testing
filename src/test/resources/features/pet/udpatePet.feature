Feature: Updating a pet in the store with form data
  As a store owner
  I want to update the details of a pet from store

  Scenario: Successful addition and update of pet with valid ID
   Given The system is operational
   When I submit a request to add a new pet to the store
   Then The system responds with a 'OK' status code
   When I submit a request to update the details of the pet
   Then The system responds with a 'OK' status code

  Scenario: Unsuccessful Update of Pet Details with Invalid ID
    Given The system is operational
    When I submit a request to add a new pet to the store
    Then The system responds with a 'OK' status code
    When I submit a request to update the pet details with an invalid ID
    Then The system responds with a 'Not Found' status code

