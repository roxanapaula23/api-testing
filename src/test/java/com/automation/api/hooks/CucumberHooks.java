package com.automation.api.hooks;

import com.automation.api.utils.PropsManager;
import io.cucumber.java.Before;
import io.restassured.RestAssured;

import java.util.Objects;

public class CucumberHooks {
    @Before
    public void setUpRestAssured() {
        RestAssured.baseURI = Objects.requireNonNull(PropsManager.props()).getProperty("api.url");
    }
}
