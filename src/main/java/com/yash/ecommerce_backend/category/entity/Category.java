package com.yash.ecommerce_backend.category.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
    @Column
    private String description;
    @Column(nullable = false,updatable = false)
    private LocalDateTime createdAt;
    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }
}

