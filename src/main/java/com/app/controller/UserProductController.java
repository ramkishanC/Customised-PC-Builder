package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class UserProductController {

	@Autowired
	private ProductService productService;

	
	//notworking
//	// Get products by category with optional filters
//	@GetMapping("/filter")
//	public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
//			@RequestParam Integer minPrice,
//			@RequestParam Integer maxPrice,@RequestParam String sort, 
//			@RequestParam String stock, @RequestParam Integer pageNumber,@RequestParam Integer pageSize){
//
//		
//		Page<Product> res= productService.getAllProduct(category,minPrice, maxPrice,sort,stock,pageNumber,pageSize);
//		
//		System.out.println("complete products");
//		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
//		
//	}
//	

	//working
	@GetMapping("/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
		
		Product product=productService.findProductById(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}

	
	//working
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct() {

		List<Product> products = productService.getAllProducts();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

}
