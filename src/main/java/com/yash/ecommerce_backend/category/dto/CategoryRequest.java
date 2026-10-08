package com.yash.ecommerce_backend.category.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Category name is Required")
    @Size(max = 100 , message = "Name must not exceed 100 characters")
    private String name;
    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;
}
