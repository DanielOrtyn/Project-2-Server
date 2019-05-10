package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Director")
public class Director {
	@Id
	@Column(name="director_id")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int directorId;

	@Column(name="name")
	private String name;
	
	@Column(name="gender")
	private String gender;

	public Director() {
		super();
	}
	public Director(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
	}
	
	public int getDirectorId() {
		return directorId;
	}

	public void setDirectorId(int directorId) {
		this.directorId = directorId;
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
		return "Director \n\tdirectorId=" + directorId + ", \n\tname=" + name + ", \n\tgender=" + gender + "]";
	}
}
