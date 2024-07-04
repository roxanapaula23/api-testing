Feature: Adding a New Pet to the Store
  In order to sell a pet
  As a store owner
  I want to add a new pet to the catalog

  Scenario: Confirming successful addition of a new pet to the store
    Given The system is operational
    When I submit a request to add a new pet to the store
    Then The system responds with a 'OK' status code
    And The response body should contain valid pet details