package com.yash.ecommerce_backend.product.entity;
import com.yash.ecommerce_backend.category.entity.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.math.BigDecimal;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 150)
    private String name;
    @Column(length = 2000)
    private String description;
    @Column(nullable = false,unique = true,length = 100)
    private String sku;
    @Column(nullable = false,precision = 12,scale = 2)
    private BigDecimal price;
    @Column(nullable = false)
    private boolean active = true;
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }
    @PreUpdate
    protected void onUpdate(){
    updatedAt = LocalDateTime.now();
    }
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
