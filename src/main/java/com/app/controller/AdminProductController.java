package com.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.request.CreateProductRequest;
import com.app.response.ApiResponse;
import com.app.service.ProductService;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

	private ProductService productService;

	public AdminProductController(ProductService productService) {
		this.productService = productService;
	}

	// working
	@PostMapping("/")
	public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req) throws ProductException {

		Product createdProduct = productService.createProduct(req);

		return new ResponseEntity<Product>(createdProduct, HttpStatus.ACCEPTED);

	}

	// working
	@DeleteMapping("/{productId}/delete")
	public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException {

		System.out.println("delete product controller .... ");
		String msg = productService.deleteProduct(productId);
		System.out.println("delete product controller .... msg " + msg);
		ApiResponse res = new ApiResponse(msg, true);

		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);

	}

	// working
	@GetMapping("/all")
	public ResponseEntity<List<Product>> findAllProduct() {

		List<Product> products = productService.getAllProducts();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// working
	@GetMapping("/recent")
	public ResponseEntity<List<Product>> recentlyAddedProduct() {

		List<Product> products = productService.recentlyAddedProduct();

		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	// working
	@PutMapping("/{productId}/update")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product updateProduct,
			@PathVariable Long productId) throws ProductException {

		Product updatedProduct = productService.updateProduct(productId, updateProduct);

		return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
	}

	// working
	@PostMapping("/creates")
	public ResponseEntity<ApiResponse> createMultipleProduct(@RequestBody CreateProductRequest[] reqs)
			throws ProductException {

		for (CreateProductRequest product : reqs) {
			productService.createProduct(product);
		}

		ApiResponse res = new ApiResponse("products created successfully", true);
		return new ResponseEntity<ApiResponse>(res, HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/find/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
		
		Product product=productService.findProductById(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}

}
