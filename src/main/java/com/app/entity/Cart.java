package com.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name="cart_items")
	private Set<CartItem> cartItems = new HashSet<>();
	
	@Column(name="total_price")
	private Double totalPrice;
	
	@Column(name="total_item")
	private int totalItem;
	
	
	
	
	
}
