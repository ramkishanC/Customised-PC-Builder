package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	  List<Product> findByCategory(String category);
	    
	    @Query("SELECT p FROM Product p WHERE p.name LIKE %:query% OR p.description LIKE %:query%")
	    List<Product> searchProduct(String query);
	    
	    List<Product> findTop10ByOrderByCreatedAtDesc();
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    @Query("SELECT p FROM Product p " +
	            "WHERE (:category IS NULL OR p.category = :category) " +
	            "AND (:minPrice IS NULL OR p.price >= :minPrice) " +
	            "AND (:maxPrice IS NULL OR p.price <= :maxPrice) " +
	            "ORDER BY " +
	            "CASE WHEN :sort = 'price_low' THEN p.price END ASC, " +
	            "CASE WHEN :sort = 'price_high' THEN p.price END DESC, " +
	            "p.createdAt DESC")
	     List<Product> filterProducts(
	             @Param("category") String category,
	             @Param("minPrice") Integer minPrice,
	             @Param("maxPrice") Integer maxPrice,
	             @Param("sort") String sort
	     );
}
