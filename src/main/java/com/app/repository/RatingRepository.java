package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {
	

	@Query("Select r From Rating r where r.product.id=:productId")
	public List<Rating> getAllProductsRating(@Param("productId") Long productId);
	
	
	@Query("SELECT r FROM Rating r WHERE r.product.id = :productId AND r.user.id = :userId")
    Rating findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);
	
	
}


