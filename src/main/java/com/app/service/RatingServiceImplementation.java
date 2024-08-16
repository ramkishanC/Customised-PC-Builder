package com.app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.entity.Rating;
import com.app.entity.User;
import com.app.exception.ProductException;
import com.app.repository.RatingRepository;
import com.app.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private ProductService productService;

	
	//working
	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		if (req == null || req.getProductId() == null || req.getRating() == null) {
	        throw new IllegalArgumentException("Invalid rating request parameters.");
	    }

	    Product product = productService.findProductById(req.getProductId());
	    if (product == null) {
	        throw new ProductException("Product not found with ID: " + req.getProductId());
	    }

	    // Check if a rating already exists for the user and product
	    Rating existingRating = ratingRepository.findByProductIdAndUserId(req.getProductId(), user.getId());
	    if (existingRating != null) {
	        // Update the existing rating
	        existingRating.setRating(req.getRating());
	        existingRating.setCreatedAt(LocalDateTime.now());
	        // Initialize the User to avoid LazyInitializationException
	        return ratingRepository.save(existingRating);
	    } else {
	        // Create a new rating
	        Rating rating = new Rating();
	        rating.setProduct(product);
	        rating.setUser(user);
	        rating.setRating(req.getRating());
	        rating.setCreatedAt(LocalDateTime.now());
	        return ratingRepository.save(rating);
	    }
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		// TODO Auto-generated method stub
		return ratingRepository.getAllProductsRating(productId);
	}

}
