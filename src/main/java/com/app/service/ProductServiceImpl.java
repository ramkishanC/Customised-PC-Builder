package com.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.entity.Product;
import com.app.exception.ProductException;
import com.app.repository.ProductRepository;
import com.app.request.CreateProductRequest;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	 @Override
	    public Product createProduct(CreateProductRequest req) {
	        Product product = new Product();
	        product.setName(req.getName());
	        product.setDescription(req.getDescription());
	        product.setBrand(req.getBrand());
	        product.setPrice(req.getPrice());
	        product.setQuantity(req.getQuantity());
	        product.setCategory(req.getCategory());
	        product.setCreatedAt(LocalDateTime.now());

	        return productRepository.save(product);
	    }

	    @Override
	    public String deleteProduct(Long productId) throws ProductException {
	        Product product = findProductById(productId);
	        productRepository.delete(product);
	        return "Product deleted successfully";
	    }

	    @Override
	    public Product updateProduct(Long productId, Product updatedProduct) throws ProductException {
	        Product product = findProductById(productId);

	        if(updatedProduct.getName()!=null) {
	        	product.setName(updatedProduct.getName());
	        }
	        
	        if (updatedProduct.getDescription() != null) {
	            product.setDescription(updatedProduct.getDescription());
	        }
	        
	        if(updatedProduct.getPrice()!=null) {
	        	product.setPrice(updatedProduct.getPrice());
	        }
	        
	        if (updatedProduct.getQuantity() != null) {
	            product.setQuantity(updatedProduct.getQuantity());
	        }
	        
	        if (updatedProduct.getBrand() != null) {
	            product.setBrand(updatedProduct.getBrand());
	        }
	       
	        if (updatedProduct.getCategory() != null) {
	            product.setCategory(updatedProduct.getCategory());
	        }
	        
	       

	        return productRepository.save(product);
	    }

	    @Override
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @Override
	    public Product findProductById(Long id) throws ProductException {
	        return productRepository.findById(id).orElseThrow(() -> new ProductException("Product not found with id " + id));
	    }

	    @Override
	    public List<Product> findProductByCategory(String category) {
	        return productRepository.findByCategory(category);
	    }

	    @Override
	    public List<Product> searchProduct(String query) {
	        return productRepository.searchProduct(query);
	    }

	    @Override
		public Page<Product> getAllProduct(String category,Integer minPrice, Integer maxPrice, String sort, String stock, Integer pageNumber, Integer pageSize) {

			Pageable pageable = PageRequest.of(pageNumber, pageSize);
			
			List<Product> products = productRepository.filterProducts(category, minPrice, maxPrice, sort);
			
			
			

			if(stock!=null) {

				if(stock.equals("in_stock")) {
					products=products.stream().filter(p->p.getQuantity()>0).collect(Collectors.toList());
				}
				else if (stock.equals("out_of_stock")) {
					products=products.stream().filter(p->p.getQuantity()<1).collect(Collectors.toList());				
				}
					
						
			}
			int startIndex = (int) pageable.getOffset();
			int endIndex = Math.min(startIndex + pageable.getPageSize(), products.size());

			List<Product> pageContent = products.subList(startIndex, endIndex);
			Page<Product> filteredProducts = new PageImpl<>(pageContent, pageable, products.size());
		    return filteredProducts; 
			
			
		}

	    @Override
	    public List<Product> recentlyAddedProduct() {
	        return productRepository.findTop10ByOrderByCreatedAtDesc();
	    }

}
