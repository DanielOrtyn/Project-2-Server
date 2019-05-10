package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SaleItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int saleId;
	@ManyToOne()
	private User seller;
	@ManyToOne()
	private Image itemImg;
	@OneToMany()
	private Bid currentBid;
	private double minPrice;
	private long endDate;
	private String title;
	private String desc;

	public SaleItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SaleItem(int saleId, User seller, Image itemImg, Bid currentBid,
			double minPrice, long endDate, String title, String desc) {
		super();
		this.saleId = saleId;
		this.seller = seller;
		this.itemImg = itemImg;
		this.currentBid = currentBid;
		this.minPrice = minPrice;
		this.endDate = endDate;
		this.title = title;
		this.desc = desc;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}

	public User getSeller() {
		return seller;
	}

	public void setSeller(User seller) {
		this.seller = seller;
	}

	public Image getItemImg() {
		return itemImg;
	}

	public void setItemImg(Image itemImg) {
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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "SaleItem [saleId=" + saleId + ", minPrice=" + minPrice
				+ ", endDate=" + endDate + ", title=" + title + ", desc=" + desc
				+ "]";
	}
}
