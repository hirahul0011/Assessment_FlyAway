package com.flyaway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Places {
	
	@Id
	@Column(name="ID")
	private long ID;
	@Column(name="Places")
	private String places;
	
	public Places() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Places(long iD, String places) {
		super();
		ID = iD;
		this.places = places;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getPlaces() {
		return places;
	}

	public void setPlaces(String places) {
		this.places = places;
	}
	
	
	

}
