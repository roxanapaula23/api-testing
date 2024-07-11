package com.automation.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class HttpManager {
    private static Response response;

    public static Response getResponse() {
        return response;
    }

    public static void setResponse(Response response) {
        HttpManager.response = response;
    }

    public static Response get(String path) {
        String url = RestAssured.baseURI + path;

        return RestAssured.given()
                .contentType("application/json")
                .get(url);
    }

    public static Response post(String path, Object requestBody) {
        String url = RestAssured.baseURI + path;

        Response response;
        if (requestBody == null) {
            response = RestAssured.given()
                    .contentType("application/json")
                    .post(url);
        } else {
            response = RestAssured.given()
                    .contentType("application/json")
                    .body(requestBody)
                    .post(url);
        }
        return response;
    }

    public static RequestSpecification createRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        return request;
    }

    public static RequestSpecification createFormForUpdateDetailsRequest() {
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/x-www-form-urlencoded");
        return request;
    }
}
