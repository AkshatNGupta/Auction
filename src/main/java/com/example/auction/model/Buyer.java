package com.example.auction.model;

import com.example.auction.enums.UserRole;

public class Buyer extends User {
    private int auctionParticipationCount = 0;
    private boolean isPreferred = false;

    public Buyer(String name) {
        super(name, UserRole.BUYER);
    }

    public void incrementParticipation() {
        auctionParticipationCount++;
        if (auctionParticipationCount > 2) {
            isPreferred = true;
        }
    }

    public boolean isPreferred() {
        return isPreferred;
    }
}