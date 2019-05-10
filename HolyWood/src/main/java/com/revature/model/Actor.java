package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Actor")

public class Actor {
	@Id
	@Column(name = "act_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int actId;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	public Actor() {
		// TODO Auto-generated constructor stub
	}

	public Actor(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}

	public int getActId() {
		return actId;
	}

	public void setActId(int actId) {
		this.actId = actId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Actor \n\tactId=" + actId + ", \n\tname=" + name + ", \n\tgender=" + gender
				+ "]";
	}

}
