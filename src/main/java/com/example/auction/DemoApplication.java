package com.example.auction;

import com.example.auction.exceptions.AuctionException;
import com.example.auction.service.AuctionSystem;

public class DemoApplication {
    public static void main(String[] args) {
        AuctionSystem auctionSystem = new AuctionSystem();

        auctionSystem.addBuyer("Buyer 1");
        System.out.println("Buyer 1 added successfully.");

        auctionSystem.addBuyer("Buyer 2");
        System.out.println("Buyer 2 added successfully.");

        auctionSystem.addBuyer("Buyer 3");
        System.out.println("Buyer 3 added successfully.");

        auctionSystem.addSeller("Seller1");
        System.out.println("Seller1 added successfully.");

        try {
            auctionSystem.createAuction("A1", 10, 50, 1, "Seller1");
            System.out.println("Auction A1 created successfully.");
        } catch (AuctionException e) {
            System.err.println("Error creating auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.placeBid("A1", "Buyer 1", 17);
            System.out.println("Bid placed by Buyer 1 on Auction A1.");
        } catch (AuctionException e) {
            System.err.println("Error placing bid by Buyer 1 on Auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.placeBid("A1", "Buyer 2", 15);
            System.out.println("Bid placed by Buyer 2 on Auction A1.");
        } catch (AuctionException e) {
            System.err.println("Error placing bid by Buyer 2 on Auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.updateBid("A1", "Buyer 2", 19);
            System.out.println("Bid updated by Buyer 2 on Auction A1.");
        } catch (AuctionException e) {
            System.err.println("Error updating bid by Buyer 2 on Auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.placeBid("A1", "Buyer 3", 19);
            System.out.println("Bid placed by Buyer 3 on Auction A1.");
        } catch (AuctionException e) {
            System.err.println("Error placing bid by Buyer 3 on Auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.closeAuction("A1");
            System.out.println("Auction A1 closed successfully.");
        } catch (AuctionException e) {
            System.err.println("Error closing auction A1: " + e.getMessage());
        }

        try {
            auctionSystem.getProfit("Seller1", "A1");
        } catch (AuctionException e) {
            System.err.println("Error getting profit for Seller1 on Auction A1: " + e.getMessage());
        }

        System.out.println("=================================================");

        auctionSystem.addSeller("Seller2");
        System.out.println("Seller2 added successfully.");

        try {
            auctionSystem.createAuction("A2", 5, 20, 2, "Seller2");
            System.out.println("Auction A2 created successfully.");
        } catch (AuctionException e) {
            System.err.println("Error creating auction A2: " + e.getMessage());
        }

        try {
            auctionSystem.placeBid("A2", "Buyer 3", 25);
            System.out.println("Bid placed by Buyer 3 on Auction A2.");
        } catch (AuctionException e) {
            System.err.println("Error placing bid by Buyer 3 on Auction A2: " + e.getMessage());
        }

        try {
            auctionSystem.placeBid("A2", "Buyer 2", 5);
            System.out.println("Bid placed by Buyer 2 on Auction A2.");
        } catch (AuctionException e) {
            System.err.println("Error placing bid by Buyer 2 on Auction A2: " + e.getMessage());
        }

        try {
            auctionSystem.withdrawBid("A2", "Buyer 2");
            System.out.println("Bid withdrawn by Buyer 2 on Auction A2.");
        } catch (AuctionException e) {
            System.err.println("Error withdrawing bid by Buyer 2 on Auction A2: " + e.getMessage());
        }

        try {
            auctionSystem.closeAuction("A2");
            System.out.println("Auction A2 closed successfully.");
        } catch (AuctionException e) {
            System.err.println("Error closing auction A2: " + e.getMessage());
        }

        try {
            auctionSystem.getProfit("Seller2", "A2");
        } catch (AuctionException e) {
            System.err.println("Error getting profit for Seller2 on Auction A2: " + e.getMessage());
        }
    }
}