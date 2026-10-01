package com.yash.ecommerce_backend.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
        @NotBlank(message = "Name is required")
        private String name;
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "password must contain atleast 8 characters")
        private String password;
    }

