package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.entity.Rating;
import com.app.entity.User;
import com.app.exception.ProductException;
import com.app.request.RatingRequest;

@Service
public interface RatingService {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
 