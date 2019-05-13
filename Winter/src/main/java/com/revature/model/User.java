package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	private String username;
	private String password;
	private String name;
	private String email;
	private String postal;
	@ManyToOne()
	private Img profileImg;
	private double rating;
	private long ratingCount;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, String name,
			String email, String postal, Img profileImg, double rating,
			long ratingCount) {
		super();
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.postal = postal;
		this.profileImg = profileImg;
		this.rating = rating;
		this.ratingCount = ratingCount;
	}
	
	public User(int userId, String username, String password, String name,
			String email, String postal, Img profileImg, double rating,
			long ratingCount) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.postal = postal;
		this.profileImg = profileImg;
		this.rating = rating;
		this.ratingCount = ratingCount;
	}
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public Img getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(Img profileImg) {
		this.profileImg = profileImg;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public long getRatingCount() {
		return ratingCount;
	}
	public void setRatingCount(long ratingCount) {
		this.ratingCount = ratingCount;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", name=" + name + ", email="
				+ email + ", postal=" + postal + ", rating=" + rating
				+ ", ratingCount=" + ratingCount + "]";
	}
}
