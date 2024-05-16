package com.automation.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
    @JsonProperty("id")
    private int id;

    @JsonProperty("username")
    private String username;

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("userStatus")
    private int userStatus;

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public Object getId() {
        return this.id;
    }

    public Object getPhone() {
        return this.phone;
    }

    public Object getUserStatus() {
        return this.userStatus;
    }
}
