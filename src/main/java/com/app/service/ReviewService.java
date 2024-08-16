package com.app.service;

import java.util.List;

import com.app.entity.Review;
import com.app.entity.User;
import com.app.exception.ProductException;
import com.app.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;

	public List<Review> getAllReview(Long productId);

}
