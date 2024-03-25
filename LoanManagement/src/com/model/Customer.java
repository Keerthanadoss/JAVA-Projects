package com.model;

public class Customer {
	private int id;
	private String name;
	private String emailId;
	private String phoneNumber;
	private String address;
	private double creditScore;
	
	public Customer() {}

	public Customer(int id, String name, String emailId, String phoneNumber, String address, double creditScore) {		 
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.creditScore = creditScore;
	}

	public Customer(String name, String emailId, String phoneNumber, String address, double creditScore) {		 
		this.name = name;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.creditScore = creditScore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(double creditScore) {
		this.creditScore = creditScore;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", emailId=" + emailId + ", phoneNumber=" + phoneNumber
				+ ", address=" + address + ", creditScore=" + creditScore + "]";
	}
	
	
	
	
	
	

}
