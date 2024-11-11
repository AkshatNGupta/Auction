# Online Auction System

This project is an implementation of an Online Auction System where users can participate in auctions to bid on various products. The system allows buyers to place bids, update or withdraw their bids, and for sellers to track their profits. It calculates the winner of each auction based on the highest unique bid, along with the profit for the seller.

The system is designed with an object-oriented approach, following principles such as SOLID, separation of concerns, and modularity, ensuring that it's extensible and easy to maintain.

## Features

- **Add Buyer**: Add a buyer to the system.
- **Add Seller**: Add a seller to the system.
- **Create Auction**: Create an auction with a set of bid limits, participation cost, and a seller.
- **Create/Update Bid**: Buyers can place a bid, and they can also update their bid during the auction.
- **Withdraw Bid**: Buyers can withdraw their bid from an ongoing auction.
- **Close Auction**: When the auction closes, the system calculates the highest unique bid and declares a winner.
- **Profit/Loss Calculation**: Calculate and display the seller’s profit based on the winning bid and participation cost.
- **Preferred Buyer**: Buyers who participate in more than two auctions are considered "preferred," and in case of a tie on the highest bid, these buyers will be prioritized.

## Problem Definition

The system is designed for a company FooBar that hosts online auctions for products. Each auction has the following parameters:

- **Lowest Bid Limit**: The minimum amount a buyer can bid.
- **Highest Bid Limit**: The maximum amount a buyer can bid.
- **Participation Cost**: The cost for a buyer to participate in the auction.
- **Seller's Profit Calculation**: The seller earns 20% of the participation costs, and the final auction price is calculated based on the highest unique bid.

The system handles multiple auctions simultaneously, allowing various sellers to list their items and buyers to participate in them.

## Functionalities Expected

- **Add Buyer(name)**: Adds a new buyer to the system.
- **Add Seller(name)**: Adds a new seller to the system.
- **Create Auction(id, lowest bid limit, highest bid limit, participation_cost, seller)**: Creates a new auction with the given details.
- **Create/Update Bid(buyer, auction, amount)**: Allows buyers to create or update their bids on an auction.
- **Withdraw Bid(buyer, auction)**: Allows buyers to withdraw their bids from an auction.
- **Close Auction and Return Winning Bid**: Calculates the winner based on the highest unique bid and closes the auction.
- **Get Profit/Loss(seller, auction)**: Calculates and returns the seller’s profit based on the auction results.

## Key Definitions

- **Highest Unique Bid**: This refers to the highest bid that is unique in the list of all bids placed in an auction. In the case of a tie (multiple buyers bidding the same highest amount), the system selects the one with the highest unique bid.
- **Preferred Buyer**: A buyer who has participated in more than two auctions. When there’s a tie between buyers on the highest bid, the preferred buyer will be given priority.

### Example Scenario

#### Test Case 1:

- Add three buyers and one seller.
- Create an auction (A1) with a bid range between 10 and 50, and a participation cost of 1.
- Buyer1 bids 17, Buyer2 bids 15, and Buyer3 bids 19.
- Buyer2 updates their bid to 19, while Buyer3’s bid remains at 19.
- The auction closes and Buyer1 is declared the winner since their bid was the highest unique bid.
- Seller’s profit will be calculated as `Winning Bid - Average of Low/High Bid Limits + Participation Cost Share`.

#### Test Case 2:

- Create a second auction (A2) with a bid range between 5 and 20, and a participation cost of 2.
- Buyer3 tries to bid 25, but this exceeds the highest bid limit, and the bid fails.
- Buyer2 places a bid of 5, but withdraws it.
- The auction closes with no winner, and the profit for the seller is calculated based on the participation cost alone.