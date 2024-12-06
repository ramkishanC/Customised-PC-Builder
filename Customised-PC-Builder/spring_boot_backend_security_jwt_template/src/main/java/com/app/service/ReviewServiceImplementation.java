package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.entity.Review;
import com.app.entity.User;
import com.app.exception.ProductException;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;
import com.app.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {

		// TODO Auto-generated method stub

		if (req == null || req.getProductId() == null) {
			throw new IllegalArgumentException("Invalid review request or product ID.");
		}

		Product product = productService.findProductById(req.getProductId());
		if (product == null) {
			throw new ProductException("Product not found with ID: " + req.getProductId());
		}
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());

//		product.getReviews().add(review);
		 // Save the review and product
		productRepository.save(product);
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {

		return reviewRepository.getAllProductsReview(productId);
	}

}
