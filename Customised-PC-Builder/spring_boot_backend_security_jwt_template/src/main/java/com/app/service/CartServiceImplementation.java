package com.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Cart;
import com.app.entity.CartItem;
import com.app.entity.Product;
import com.app.entity.User;
import com.app.exception.CartException;
import com.app.repository.CartItemRepository;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;
import com.app.repository.UserRepository;
import com.app.request.AddItemRequest;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setTotalPrice(0.0);
        cart.setTotalItem(0);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest req) throws CartException {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new CartException("User not found with ID: " + userId);
        }

        User user = userOptional.get();
        Cart cart = findUserCart(userId);
        if (cart == null) {
            cart = createCart(user);
        }

        Optional<Product> productOptional = productRepository.findById(req.getProductId());
        if (!productOptional.isPresent()) {
            throw new CartException("Product not found with ID: " + req.getProductId());
        }

        Product product = productOptional.get();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setPrice(req.getPrice());
        cartItem.setUserId(userId);
        cartItem.setCart(cart);

        cart.getCartItems().add(cartItem);
        cart.setTotalPrice(cart.getTotalPrice() + req.getPrice() * req.getQuantity());
        cart.setTotalItem(cart.getTotalItem() + req.getQuantity());

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return "Product added to cart successfully.";
    }

    @Override
    public Cart findUserCart(Long userId) {
    	
    Cart cart = cartRepository.findByUserId(userId);
    
    double totalPrice=0;
    int totalItem=0;
    
    for(CartItem cartItem:cart.getCartItems())
    {
    	totalPrice=totalPrice+cartItem.getPrice();
    	totalItem=totalItem + cartItem.getQuantity();
    }
    
    cart.setTotalItem(totalItem);
    cart.setTotalPrice(totalPrice);
	return cartRepository.save(cart);
    
    	
    	
    }
}
