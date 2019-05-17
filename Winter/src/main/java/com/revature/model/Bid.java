package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Bid {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bidId;
	
	private double currentBidPrice;
	
	private double maxBidPrice;
	@ManyToOne
	private User bidder;
	
	private long saleItemId;
	
	public Bid() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Bid(double currentBidPrice, double maxBidPrice, User bidder, long saleItemId) {
		super();
		this.currentBidPrice = currentBidPrice;
		this.maxBidPrice = maxBidPrice;
		this.bidder = bidder;
		this.setSaleItemId(saleItemId);
	}
	public Bid(long bidId, double currentBidPrice, double maxBidPrice, User bidder, long saleItemId) {
		super();
		this.bidId = bidId;
		this.currentBidPrice = currentBidPrice;
		this.maxBidPrice = maxBidPrice;
		this.bidder = bidder;
		this.setSaleItemId(saleItemId);
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
	public long getSaleItemId() {
		return saleItemId;
	}
	public void setSaleItemId(long saleItemId) {
		this.saleItemId = saleItemId;
	}
	@Override
	public String toString() {
		return "Bid [bidId=" + bidId + ", currentBidPrice=" + currentBidPrice
				+ ", maxBidPrice=" + maxBidPrice + ", bidder=" + bidder
				+ ", saleItemId=" + saleItemId + "]";
	}
}
