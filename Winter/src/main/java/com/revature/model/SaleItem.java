package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SaleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long saleId;
	@ManyToOne()
	private User seller;
	@ManyToOne()
	private Img itemImg;
	@OneToOne()
	private Bid currentBid;
	private double minPrice;
	private long endDate;
	private String title;
	private String description;
	@ManyToOne()
	private Category category;
	private boolean Alerted;
	public SaleItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleItem(User seller, Img itemImg, Bid currentBid,
			double minPrice, long endDate, String title, String description) {
		super();
		this.seller = seller;
		this.itemImg = itemImg;
		this.currentBid = currentBid;
		this.minPrice = minPrice;
		this.endDate = endDate;
		this.title = title;
		this.description = description;
		this.Alerted = false;
	}

	public SaleItem(int saleId, User seller, Img itemImg, Bid currentBid,
			double minPrice, long endDate, String title, String description) {
		super();
		this.saleId = saleId;
		this.seller = seller;
		this.itemImg = itemImg;
		this.currentBid = currentBid;
		this.minPrice = minPrice;
		this.endDate = endDate;
		this.title = title;
		this.description = description;
		this.Alerted = false;
	}

	public long getSaleId() {
		return saleId;
	}

	public void setSaleId(long saleId) {
		this.saleId = saleId;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Img getItemImg() {
		return itemImg;
	}

	public void setItemImg(Img itemImg) {
		this.itemImg = itemImg;
	}

	public Bid getCurrentBid() {
		return currentBid;
	}

	public void setCurrentBid(Bid currentBid) {
		this.currentBid = currentBid;
	}

	public double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "SaleItem [saleId=" + saleId + ", minPrice=" + minPrice
				+ ", endDate=" + endDate + ", title=" + title + ", description=" + description
				+ "]";
	}

	public boolean isAlerted() {
		return Alerted;
	}

	public void setAlerted(boolean alerted) {
		Alerted = alerted;
	}
}
