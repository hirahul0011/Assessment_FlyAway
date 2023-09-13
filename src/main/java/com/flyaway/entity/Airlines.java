package com.flyaway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Airlines {
	
	@Id
	@Column(name="ID")
	private long ID;
	@Column(name="airlines")
	private String airlines;
	
	public Airlines() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Airlines(long iD, String airlines) {
		super();
		ID = iD;
		this.airlines = airlines;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getAirlines() {
		return airlines;
	}

	public void setAirlines(String airlines) {
		this.airlines = airlines;
	}
	
	

}
