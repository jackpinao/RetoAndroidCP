package com.pinao.retoandroidcp.domain.model;

import com.pinao.retoandroidcp.data.database.entity.LoginEntity;

public class LoginItems {
    private final String name;
    private final String email;
    private final String password;

    public LoginItems(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public LoginEntity toDatabase() {
        return new LoginEntity(name,email, password);
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
