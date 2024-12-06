package com.app.request;

public class RatingRequest {

	private Long productId;
	private Double rating;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
}
