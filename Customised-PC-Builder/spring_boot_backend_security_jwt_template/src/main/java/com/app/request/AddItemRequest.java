package com.app.request;

public class AddItemRequest {
	
	private Long productId;
	private int quantity;
	private Double price;
	
	
	public AddItemRequest() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	

}
