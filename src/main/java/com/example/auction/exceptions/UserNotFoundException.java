package com.example.auction.exceptions;

public class UserNotFoundException extends AuctionException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
