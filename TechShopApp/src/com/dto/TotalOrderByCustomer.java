package com.dto;

public class TotalOrderByCustomer {
	private int id;
	private String firstName;
	private int count;
	
	public TotalOrderByCustomer() {}

	public TotalOrderByCustomer(int id, String firstName, int count) {
		this.id = id;
		this.firstName = firstName;
		this.count = count;
	}

	public TotalOrderByCustomer(String firstName, int count) {
		this.firstName = firstName;
		this.count = count;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "TotalOrderByCustomer [id=" + id + ", firstName=" + firstName + ", count=" + count + "]";
	}

	
}
