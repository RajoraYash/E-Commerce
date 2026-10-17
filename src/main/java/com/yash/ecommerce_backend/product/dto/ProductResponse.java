package com.yash.ecommerce_backend.product.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private String sku;
    private BigDecimal price;
    private Long categoryId;
    private String categoryName;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
