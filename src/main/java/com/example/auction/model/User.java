package com.example.auction.model;

import com.example.auction.enums.UserRole;

public abstract class User {
    protected String name;
    protected UserRole role;

    protected User(String name, UserRole role) {
        this.name = name;
        this.role = role;
    }

    public String getName() {
        return name;
    }
}
