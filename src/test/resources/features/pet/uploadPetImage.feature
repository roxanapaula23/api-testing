Feature: Uploading image to a pet
  As a store owner
  I want to upload an image to a pet
  So that I can see the image of the pet

  Scenario: Check if user can upload an image to a pet
    Given The system is operational
    When I submit a request to upload an image for a pet
    Then The system responds with a 'OK' status code
    And The response body should contain a valid confirmation message