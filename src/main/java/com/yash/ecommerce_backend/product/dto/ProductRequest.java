package com.yash.ecommerce_backend.product.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    @NotBlank(message = "Name is required")
    @Size(max = 100 , message = "Name must not exceed 100 characters")
    private String name;
    @Size(max = 2000 , message = "Description must noty exceed 2000 characters" )
    private String description;
    @NotBlank(message = "SKU is required")
    @Size(max = 100, message = "SKU must not exceed 100 characters")
    private String sku;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    private BigDecimal price;
    @NotNull(message = "CategoryId is required")
    private Long categoryId;

}
