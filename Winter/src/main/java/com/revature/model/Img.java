package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Img {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long imgId;
	
	private String url;
	
	private String title;

	public Img() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Img(String url, String title) {
		super();
		this.url = url;
		this.title = title;
	}

	public Img(long imgId, String url, String title) {
		super();
		this.imgId = imgId;
		this.url = url;
		this.title = title;
	}

	public long getImgId() {
		return imgId;
	}

	public void setImgId(long imgId) {
		this.imgId = imgId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
