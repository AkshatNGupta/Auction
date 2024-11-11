package com.example.auction.service;

import com.example.auction.exceptions.AuctionException;
import com.example.auction.exceptions.UserNotFoundException;
import com.example.auction.model.Auction;
import com.example.auction.model.Bid;
import com.example.auction.model.Buyer;
import com.example.auction.model.Seller;

import java.util.HashMap;
import java.util.Map;

public class AuctionSystem {
    public static final String SELLER_NOT_FOUND = "Seller not found";
    public static final String AUCTION_NOT_FOUND = "Auction not found";
    public static final String BUYER_NOT_FOUND = "Buyer not found";
    private final Map<String, Buyer> buyers = new HashMap<>();
    private final Map<String, Seller> sellers = new HashMap<>();
    private final Map<String, Auction> auctions = new HashMap<>();

    public void addBuyer(String name) {
        buyers.put(name, new Buyer(name));
    }

    public void addSeller(String name) {
        sellers.put(name, new Seller(name));
    }

    public void createAuction(String id, int lowestBiddingPrice, int highestBiddingPrice, int participationCost, String sellerName) throws AuctionException {
        Seller seller = sellers.get(sellerName);
        if (seller == null) {
            throw new UserNotFoundException(SELLER_NOT_FOUND);
        }
        auctions.put(id, new Auction(id, lowestBiddingPrice, highestBiddingPrice, participationCost, seller));
    }

    public void placeBid(String auctionId, String buyerName, int bidAmount) throws AuctionException {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new AuctionException(AUCTION_NOT_FOUND);
        }
        Buyer buyer = buyers.get(buyerName);
        if (buyer == null) {
            throw new UserNotFoundException(BUYER_NOT_FOUND);
        }
        buyer.incrementParticipation();
        auction.addBid(new Bid(buyer, bidAmount));
    }

    public void updateBid(String auctionId, String buyerName, int bidAmount) throws AuctionException {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new AuctionException(AUCTION_NOT_FOUND);
        }
        Buyer buyer = buyers.get(buyerName);
        if (buyer == null) {
            throw new UserNotFoundException(BUYER_NOT_FOUND);
        }
        auction.updateBid(buyer, bidAmount);
    }

    public void withdrawBid(String auctionId, String buyerName) throws AuctionException {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new AuctionException(AUCTION_NOT_FOUND);
        }
        Buyer buyer = buyers.get(buyerName);
        if (buyer == null) {
            throw new UserNotFoundException(BUYER_NOT_FOUND);
        }
        auction.withdrawBid(buyer);
    }

    public void closeAuction(String auctionId) throws AuctionException {
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new AuctionException(AUCTION_NOT_FOUND);
        }
        auction.closeAuction();
        Bid winningBid = auction.getWinningBid();

        if (winningBid != null) {
            System.out.printf("Auction %s closed. Winning bid is %d by %s%n",
                    auctionId, winningBid.getAmount(), winningBid.getBuyer().getName());
        } else {
            System.out.printf("Auction %s closed. No unique highest bids; no winner.%n", auctionId);
        }
    }


    public void getProfit(String sellerName, String auctionId) throws AuctionException {
        Seller seller = sellers.get(sellerName);
        if (seller == null) {
            throw new UserNotFoundException(SELLER_NOT_FOUND);
        }
        Auction auction = auctions.get(auctionId);
        if (auction == null) {
            throw new AuctionException(AUCTION_NOT_FOUND);
        }
        double profit = auction.calculateSellerProfit();
        System.out.printf("Profit for seller %s from auction %s is %.2f%n", sellerName, auctionId, profit);
    }
}
