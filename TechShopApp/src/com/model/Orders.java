package com.model;

import java.time.LocalDate;

public class Orders {
	
	private int id;
	private int customerId;
	private int productId;
	private LocalDate date;
	private double totalAmount;
	private int quantity;
	private String status;
	
	public Orders() {}
	
	public Orders(int id, int customerId, int productId, LocalDate date, double totalAmount, int quantity,
			String status) {
		this.id = id;
		this.customerId = customerId;
		this.productId = productId;
		this.date = date;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.status = status;
	}
	public Orders(int customerId, int productId, LocalDate date, double totalAmount, int quantity, String status) {
        this.customerId = customerId;
		this.productId = productId;
		this.date = date;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Orders [id=" + id + ", customerId=" + customerId + ", productId=" + productId + ", date=" + date
				+ ", totalAmount=" + totalAmount + ", quantity=" + quantity + ", status=" + status + "]";
	}
	
	
	

}
