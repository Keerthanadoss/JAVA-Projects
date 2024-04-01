package com.dto;

public class QuantityOfProductInStock {
	private int productId;
	private String productName;
	private int quantityInStock;
	
	public QuantityOfProductInStock() {}

	public QuantityOfProductInStock(int productId, String productName, int quantityInStock) {
	 
		this.productId = productId;
		this.productName = productName;
		this.quantityInStock = quantityInStock;
	}

	public QuantityOfProductInStock(String productName, int quantityInStock) {
		 
		this.productName = productName;
		this.quantityInStock = quantityInStock;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	@Override
	public String toString() {
		return "QuantityOfProductInStock [productId=" + productId + ", productName=" + productName
				+ ", quantityInStock=" + quantityInStock + "]";
	}
	
	
	
	
	

}
