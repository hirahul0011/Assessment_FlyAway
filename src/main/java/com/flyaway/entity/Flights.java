package com.flyaway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Flights {
	
	@Id
	@Column(name="ID")
	private long ID;
	
	@Column(name="Source")
	private String source;
	
	@Column(name="Destination")
	private String destination;
	
	@Column(name="Airline")
	private String airline;
	
	@Column(name="Price")
	private double price;
	
	public Flights() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Flights(long iD, String source, String destination, String airline, double price) {
		super();
		ID = iD;
		this.source = source;
		this.destination = destination;
		this.airline = airline;
		this.price = price;
	}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Flights [ID=" + ID + ", source=" + source + ", destination=" + destination + ", airline=" + airline
				+ ", price=" + price + "]";
	}	
	

}
