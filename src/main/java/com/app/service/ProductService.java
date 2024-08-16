package com.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.exception.ProductExcpetion;
import com.app.request.CreateProductRequest;

public interface ProductService {

	// For admin
    Product createProduct(CreateProductRequest req) throws ProductException;
    
    String deleteProduct(Long productId) throws ProductException;
    
    Product updateProduct(Long productId, Product product) throws ProductException;
    
    List<Product> getAllProducts();
    
    // For user and admin
    Product findProductById(Long id) throws ProductException;
    
    List<Product> findProductByCategory(String category);
    
    List<Product> searchProduct(String query);
    
    Page<Product> getAllProduct(String category,Integer minPrice, Integer maxPrice, String sort, String stock, Integer pageNumber, Integer pageSize);
    
    List<Product> recentlyAddedProduct();
	
}
