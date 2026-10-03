package com.yash.ecommerce_backend.user;

import com.yash.ecommerce_backend.user.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Role role;
}
