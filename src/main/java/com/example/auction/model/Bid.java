package com.example.auction.model;

public class Bid {
    private final Buyer buyer;
    private int amount;

    public Bid(Buyer buyer, int amount) {
        this.buyer = buyer;
        this.amount = amount;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
