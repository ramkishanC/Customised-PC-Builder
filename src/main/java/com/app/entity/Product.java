package com.app.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
   
    private String name;
    
    private String description;

    private BigDecimal price;

    @Column(name = "quantity")
    private Integer quantity;
    
    private String brand;

    @JsonIgnore
    @OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    
    @Column(name="num_ratings")
    private int numRating;
    
    private String category;
    
    
    private LocalDateTime createdAt;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
