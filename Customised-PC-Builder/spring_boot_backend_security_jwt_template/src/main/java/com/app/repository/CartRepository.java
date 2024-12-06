package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findByUserId(Long userId);
	
	
	

}
