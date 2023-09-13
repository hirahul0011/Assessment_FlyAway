package com.flyaway.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Admin {
	
	@Id
	@Column(name="FirstName")
	private String firstName;
	@Column(name="LastName")
	private String lastName;
	@Column(name="Password")
	private String password;
	@Column(name="ContactNo")
	private String contactNo;
	@Column(name="EmailID")
	private String emailID;
	@Column(name="CardNo")
	private String cardNo;
	@Column(name="CardExpiry")
	private Date cardExpiry;
	
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String firstName, String lastName, String password, String contactNo, String emailID, String cardNo,
			Date cardExpiry) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.contactNo = contactNo;
		this.emailID = emailID;
		this.cardNo = cardNo;
		this.cardExpiry = cardExpiry;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Date getCardExpiry() {
		return cardExpiry;
	}

	public void setCardExpiry(Date cardExpiry) {
		this.cardExpiry = cardExpiry;
	}		
	

}
