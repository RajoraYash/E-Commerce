package com.yash.ecommerce_backend.user.service.Impl;

import com.yash.ecommerce_backend.user.entity.User;
import com.yash.ecommerce_backend.user.repository.UserRepository;
import com.yash.ecommerce_backend.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }
}
