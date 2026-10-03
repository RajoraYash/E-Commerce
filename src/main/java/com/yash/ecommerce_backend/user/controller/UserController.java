package com.yash.ecommerce_backend.user.controller;

import com.yash.ecommerce_backend.user.UserResponse;
import com.yash.ecommerce_backend.user.dto.UserRequest;
import com.yash.ecommerce_backend.user.entity.User;
import com.yash.ecommerce_backend.user.enums.Role;
import com.yash.ecommerce_backend.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")

public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request){
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRole(Role.CUSTOMER);

        User savedUser = userService.createUser(user);
        UserResponse response = new UserResponse();
        response.setId(savedUser.getId());
        response.setName(savedUser.getName());
        response.setEmail(savedUser.getEmail());
        response.setRole(savedUser.getRole());
        return response;
    }
    @GetMapping("/profile")
    public String profile() {
        return "You are authenticated!";
    }

}
