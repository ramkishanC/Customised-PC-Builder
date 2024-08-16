package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Rating;
import com.app.entity.User;
import com.app.exception.ProductException;
import com.app.exception.UserException;
import com.app.request.RatingRequest;
import com.app.service.RatingService;
import com.app.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	
	private UserService userService;
	private RatingService ratingServices;
	
	public RatingController(UserService userService,RatingService ratingServices) {
		this.ratingServices=ratingServices;
		this.userService=userService;
		// TODO Auto-generated constructor stub
	}

	
	//working
	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req,@RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		Rating rating=ratingServices.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
	
	
	 // Get all ratings for a product
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getAllRatings(@PathVariable Long productId) throws ProductException {
        List<Rating> ratings = ratingServices.getProductsRating(productId);
        return new ResponseEntity<>(ratings, HttpStatus.OK);
    }
	
	
}
