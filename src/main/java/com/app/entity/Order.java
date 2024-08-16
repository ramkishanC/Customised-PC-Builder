package com.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_order")  // Custom table name
public class Order {

	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Long id;
	
	@Column(name="order_id")
	private String orderId;
	
	@ManyToOne
	private User user;
	
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();
	
	
	private LocalDateTime orderDate;
	
	private LocalDateTime deliveryDate;
	
	@OneToOne
	private Address shippingAddress;
	
	@Embedded
	private PaymentDetails paymentDetails = new PaymentDetails();
	
	private Double totalPrice;
	
	private String orderStatus;
	
	private int totalItems;
	
	private LocalDateTime createdAt;

	
	
	
	
}
