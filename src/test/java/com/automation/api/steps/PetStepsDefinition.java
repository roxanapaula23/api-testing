package com.automation.api.steps;

import com.automation.api.model.Pet;
import com.automation.api.utils.HttpManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.automation.api.utils.HttpManager.*;

public class PetStepsDefinition {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @When("I submit a request to add a new pet to the store")
    public void whenISubmitARequestToAddANewPetToTheStore() throws IOException{
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/data/pets.json")));
        List<Pet> pets = objectMapper.readValue(json, new TypeReference<>() {
        });

        Pet pet = pets.get(0);
        pet.setId("1195");

        RequestSpecification request = createRequest();
        request.body(pet);

        Response response = request.post("/v2/pet");
        HttpManager.setResponse(response);
    }

    @And("The response body should contain valid pet details")
    public void theResponseBodyShouldContainValidPetDetails() {
        Response response = HttpManager.getResponse();
        Pet pet = response.getBody().as(Pet.class);

        Assert.assertEquals("Expected pet ID", "1195", pet.getId());
        Assert.assertEquals("Expected pet name", "Sophie", pet.getName());
        Assert.assertEquals("Expected pet status", "available", pet.getStatus());
        Assert.assertEquals("Expected pet category ID", 1, pet.getCategory().getId());
        Assert.assertEquals("Expected pet category name", "Dogs", pet.getCategory().getName());
        Assert.assertEquals("Expected pet tag ID", 1, pet.getTags().get(0).getId());
        Assert.assertEquals("Expected pet tag name", "playful", pet.getTags().get(0).getName());
        Assert.assertEquals("Expected pet photo URL", "https://dogsqueensland.org.au/Breeds/browse-all-breeds/152/Samoyed/", pet.getPhotoUrls().get(0));
    }

    @When("I submit a request to update the details of the pet")
    public void iSubmitARequestToUpdateTheDetailsOfThePet() {
        RequestSpecification request = createFormForUpdateDetailsRequest();
        request.formParam("name", "Bella");
        request.formParam("status", "sold");

        Response response = request.post("v2/pet/1");
        HttpManager.setResponse(response);
    }

    @When("I submit a request to update the pet details with an invalid ID")
    public void iSubmitARequestToUpdateThePetDetailsWithAnInvalidID() {
        int invalidPetId = -1;

        RequestSpecification request = createFormForUpdateDetailsRequest();
        request.formParam("name", "Bella");
        request.formParam("status", "sold");

        Response response = request.post("v2/pet/" + invalidPetId);
        HttpManager.setResponse(response);
    }
}
