package com.example.auction.model;

import com.example.auction.enums.UserRole;

public class Seller extends User {
    public Seller(String name) {
        super(name, UserRole.SELLER);
    }
}