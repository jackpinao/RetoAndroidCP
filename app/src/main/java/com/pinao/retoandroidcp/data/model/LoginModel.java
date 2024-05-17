package com.pinao.retoandroidcp.data.model;

import com.google.gson.annotations.SerializedName;
import com.pinao.retoandroidcp.domain.model.LoginItems;

public class LoginModel {
    @SerializedName("name")
    private final String name;
    @SerializedName("email")
    private final String email;
    @SerializedName("password")
    private final String password;
    public LoginModel(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public LoginItems toDomain() {
        return new LoginItems(name,email, password);
    }
    // Getters
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
}
