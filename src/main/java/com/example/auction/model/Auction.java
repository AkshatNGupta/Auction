package com.example.auction.model;

import com.example.auction.exceptions.AuctionClosedException;
import com.example.auction.exceptions.AuctionException;
import com.example.auction.exceptions.InvalidBidException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Auction {
    private final String id;
    private final int lowestBidLimit;
    private final int highestBidLimit;
    private final int participationCost;
    private final Seller seller;
    private boolean isClosed;
    private final List<Bid> bids;
    private final Set<Buyer> participants;

    public Auction(String id, int lowestBidLimit, int highestBidLimit, int participationCost, Seller seller) {
        this.id = id;
        this.lowestBidLimit = lowestBidLimit;
        this.highestBidLimit = highestBidLimit;
        this.participationCost = participationCost;
        this.seller = seller;
        this.isClosed = false;
        this.bids = new ArrayList<>();
        this.participants = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public Seller getSeller() {
        return seller;
    }

    public int getParticipationCost() {
        return participationCost;
    }

    public void addBid(Bid bid) throws AuctionException {
        if (isClosed) {
            throw new AuctionClosedException("Auction is closed.");
        }
        if (bid.getAmount() < lowestBidLimit || bid.getAmount() > highestBidLimit) {
            throw new InvalidBidException("Bid amount is out of range.");
        }
        bids.add(bid);
        participants.add(bid.getBuyer());
    }

    public void updateBid(Buyer buyer, int newAmount) throws AuctionException {
        for (Bid bid : bids) {
            if (bid.getBuyer().equals(buyer)) {
                if (newAmount < lowestBidLimit || newAmount > highestBidLimit) {
                    throw new InvalidBidException("Bid amount is out of range.");
                }
                bid.setAmount(newAmount);
                return;
            }
        }
        throw new InvalidBidException("Bid not found.");
    }

    public void withdrawBid(Buyer buyer) {
        bids.removeIf(bid -> bid.getBuyer().equals(buyer));
    }

    public void closeAuction() {
        isClosed = true;
    }

    public Bid getWinningBid() {
        Map<Integer, List<Bid>> bidMap = bids.stream()
                .collect(Collectors.groupingBy(Bid::getAmount));

        List<Integer> uniqueBids = bidMap.entrySet().stream()
                .filter(entry -> entry.getValue().size() == 1) // Filter unique bids
                .map(Map.Entry::getKey)
                .sorted(Collections.reverseOrder()) // Sort in descending order
                .collect(Collectors.toList());

        if (uniqueBids.isEmpty()) return null;

        int highestUniqueBid = uniqueBids.get(0);
        List<Bid> highestBidders = bidMap.get(highestUniqueBid);

        List<Bid> preferredBidders = highestBidders.stream()
                .filter(bid -> bid.getBuyer().isPreferred())
                .collect(Collectors.toList());

        return preferredBidders.isEmpty() ? highestBidders.get(0) : preferredBidders.get(0);
    }


    public double calculateSellerProfit() {
        double avgBidLimit = (lowestBidLimit + highestBidLimit) / 2.0;
        double participationProfit = participationCost * participants.size() * 0.2;
        Bid winningBid = getWinningBid();

        if (winningBid == null) {
            return participationProfit; // Only participation profit if no winner
        } else {
            return winningBid.getAmount() + participationProfit - avgBidLimit;
        }
    }
}