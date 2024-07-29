package com.automation.api.steps;

import com.automation.api.model.Pet;
import com.automation.api.utils.HttpManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;
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

    @When("I submit a request to upload an image for a pet")
    public void iSubmitARequestToUploadAnImageForAPet()  throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/data/pets.json")));
        List<Pet> pets = objectMapper.readValue(json, new TypeReference<>() {
        });
        Pet pet = pets.get(0);
        String petId = pet.getId();

        String additionalMetadata = "image";

        File file = new File("src/test/resources/images/samoyed.jpg");

        RequestSpecification request = createFormRequest();
        request.multiPart("file", file);
        request.formParam("additionalMetadata", additionalMetadata);

        Response response = request.post("/v2/pet/" + petId + "/uploadImage");
        HttpManager.setResponse(response);
    }

    @And("The response body should contain a valid confirmation message")
    public void theResponseBodyShouldContainAValidConfirmationMessage() {
        String expectedMessage = "additionalMetadata: image\nFile uploaded to ./samoyed.jpg, 58489 bytes";
        Response response = HttpManager.getResponse();
        String actualMessage = response.getBody().jsonPath().getString("message");
        Assert.assertEquals("Expected message does not match the actual message", expectedMessage, actualMessage);
    }

    @When("I submit a request to delete the pet with a valid ID")
    public void iSubmitARequestToDeleteThePetWithAValidID() {
        Response response = HttpManager.getResponse();
        String petId = response.getBody().jsonPath().getString("id");

        RequestSpecification request = createRequest();

        response = request.delete("/v2/pet/" + petId);
        HttpManager.setResponse(response);
    }

    @And("The response body includes a confirmation message")
    public void theResponseBodyIncludesAConfirmationMessage() {
        Response response = HttpManager.getResponse();
        String actualMessage = response.getBody().jsonPath().getString("message");
        Assert.assertEquals("Expected message does not match the actual message", "1195", actualMessage);
    }

    @When("I submit a request to delete the pet with an invalid ID")
    public void iSubmitARequestToDeleteThePetWithAnInvalidID() {
        String invalidPetId = "pet1";

        RequestSpecification request = RestAssured.given();
        request.header("Accept", "application/json");

        Response response = request.delete("/v2/pet/" + invalidPetId);
        HttpManager.setResponse(response);
    }

    @And("The response body includes an error message")
    public void theResponseBodyIncludesAnErrorMessage() {
        Response response = HttpManager.getResponse();
        String actualMessage = response.getBody().jsonPath().getString("message");
        String expectedMessage = "java.lang.NumberFormatException: For input string: \"pet1\"";
        Assert.assertEquals("Expected message does not match the actual message", expectedMessage, actualMessage);
    }
}
