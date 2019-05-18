package com.revature.model;

public class BidWithSale {

	private long bidId;

	private double currentBidPrice;

	private double maxBidPrice;

	private User bidder;

	private SaleItem saleItem;

	public BidWithSale() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BidWithSale(double currentBidPrice, double maxBidPrice, User bidder, SaleItem saleItem) {
		super();
		this.bidId = 0;
		this.currentBidPrice = currentBidPrice;
		this.maxBidPrice = maxBidPrice;
		this.bidder = bidder;
		this.saleItem = saleItem;
	}

	public BidWithSale(long bidId, double currentBidPrice, double maxBidPrice, User bidder, SaleItem saleItem) {
		super();
		this.bidId = bidId;
		this.currentBidPrice = currentBidPrice;
		this.maxBidPrice = maxBidPrice;
		this.bidder = bidder;
		this.saleItem = saleItem;
	}
	
	public BidWithSale(Bid bid, SaleItem saleItem) {
		super();
		this.bidId = bid.getBidId();
		this.currentBidPrice = bid.getCurrentBidPrice();
		this.maxBidPrice = bid.getMaxBidPrice();
		this.bidder = bid.getBidder();
		this.saleItem = saleItem;
	}

	public long getBidId() {
		return bidId;
	}

	public void setBidId(long bidId) {
		this.bidId = bidId;
	}

	public double getCurrentBidPrice() {
		return currentBidPrice;
	}

	public void setCurrentBidPrice(double currentBidPrice) {
		this.currentBidPrice = currentBidPrice;
	}

	public double getMaxBidPrice() {
		return maxBidPrice;
	}

	public void setMaxBidPrice(double maxBidPrice) {
		this.maxBidPrice = maxBidPrice;
	}

	public User getBidder() {
		return bidder;
	}

	public void setBidder(User bidder) {
		this.bidder = bidder;
	}

	public SaleItem getSaleItem() {
		return saleItem;
	}

	public void setSaleItem(SaleItem saleItem) {
		this.saleItem = saleItem;
	}

	@Override
	public String toString() {
		return "BidWithSale [bidId=" + bidId + ", currentBidPrice=" + currentBidPrice + ", maxBidPrice=" + maxBidPrice
				+ ", bidder=" + bidder + ", saleItem=" + saleItem + "]";
	}

}
