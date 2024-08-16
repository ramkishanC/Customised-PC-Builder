package com.app.service;

import com.app.entity.Cart;
import com.app.entity.User;
import com.app.exception.CartException;
import com.app.request.AddItemRequest;

public interface CartService {
	
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId , AddItemRequest req)throws CartException;
	
	public Cart findUserCart(Long userId);
	
	
	

}
