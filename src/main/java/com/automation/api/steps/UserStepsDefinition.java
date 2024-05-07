package com.automation.api.steps;

import com.automation.api.model.UserModel;
import com.automation.api.utils.HttpManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.runner.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static com.automation.api.utils.HttpManager.createRequest;
import static io.restassured.RestAssured.baseURI;

public class UserStepsDefinition {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("The service is up")
    public void theServiceIsUp() {
        Response response = RestAssured.get(baseURI);
        Assert.assertEquals("Status code might be 503- Service Unavailable", 200, response.getStatusCode());
    }

    @When("I send a POST request having valid credentials")
    public void iSendAPOSTRequestHavingValidCredentials() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/data/user.json")));
        UserModel user = objectMapper.readValue(json, UserModel.class);

        RequestSpecification request = createRequest();
        request.body(user);

        Response response = request.post("/v2/user");
        HttpManager.setResponse(response);
    }

    @Then("I have {int} status code")
    public void iHaveStatusCode(int statusCode) {
        Response response = HttpManager.getResponse();
        Assert.assertEquals("Expected status code is " + statusCode, statusCode, response.getStatusCode());


    }

    @And("I check the response body is valid")
    public void iCheckTheResponseBodyIsValid() {
        Response response = HttpManager.getResponse();
        Assert.assertEquals("Expected status code is 200", 200, response.getStatusCode());
        Assert.assertEquals("Expected content type is application/json", "application/json", response.getContentType());
        Assert.assertNotNull("Response body should not be null", response.getBody());
    }

    @When("I send a POST request to create a new account with empty body")
    public void iSendAPOSTRequestToCreateANewAccountWithEmptyBody() {
        Response response = HttpManager.post("/v2/user", null);
        HttpManager.setResponse(response);
    }

    @And("I check if the response message contains {string}")
    public void iCheckIfTheResponseMessageContains(String message) {
        Response response = HttpManager.getResponse();
        Assert.assertTrue(response.getBody().asString().contains(message));
    }

    @When("I send a POST request with a list of users")
    public void iSendAPOSTRequestWithAListOfUsers() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/data/users.json")));
        List<UserModel> users = objectMapper.readValue(json, new TypeReference<>() {});
        Response response = HttpManager.post("/v2/user/createWithArray", users);
        HttpManager.setResponse(response);
    }

    @When("I send a GET request to login")
    public void iSendAGETRequestToLogin() throws IOException {
        String json = new String(Files.readAllBytes(Paths.get("src/test/resources/data/users.json")));
        List<UserModel> users = objectMapper.readValue(json, new TypeReference<>() {});
        String username = users.get(0).getUsername();
        String password = users.get(0).getPassword();

        String url = "/v2/user/login?username=" + username + "&password=" + password;
        Response response = HttpManager.get(url);
        HttpManager.setResponse(response);
    }

    @And("I check the response body contains a valid session message")
    public void iCheckTheResponseBodyContainsAValidSessionMessage() {
        Response response = HttpManager.getResponse();
        String message = response.getBody().jsonPath().getString("message");
        Assert.assertTrue("Expected message to start with 'logged in user session:', but was: " + message, message.startsWith("logged in user session:"));
    }
}
