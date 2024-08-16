package com.app.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.entity.Cart;
import com.app.entity.CartItem;
import com.app.entity.Product;
import com.app.entity.User;
import com.app.exception.CartItemException;
import com.app.exception.UserException;
import com.app.repository.CartItemRepository;
import com.app.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;

	public CartItemServiceImplementation(CartItemRepository cartItemRepository, UserService userService,CartRepository cartRepository) {
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.cartRepository=cartRepository;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		
		BigDecimal price = cartItem.getProduct().getPrice().multiply(new BigDecimal(cartItem.getQuantity()));
		cartItem.setPrice(price.doubleValue());
		
		CartItem createdCartItem = cartItemRepository.save(cartItem);

		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserId());

		if (user.getId().equals(userId)) {

			item.setQuantity(cartItem.getQuantity());
			BigDecimal price = item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity()));
			item.setPrice(price.doubleValue());  // Convert BigDecimal to Double

			return cartItemRepository.save(item);

		} else {
			throw new CartItemException("You can't update  another users cart_item");
		}

	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product,Long userId) {

		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, userId);

		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

		System.out.println("userId- " + userId + " cartItemId " + cartItemId);

		CartItem cartItem = findCartItemById(cartItemId);
		User user = userService.findUserById(cartItem.getUserId());
		
		User reqUser = userService.findUserById(userId);

		if (user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItem.getId());
		} else {
			throw new UserException("you can't remove another users item");
		}

	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("cartItem not found with id : "+cartItemId);
	}
}