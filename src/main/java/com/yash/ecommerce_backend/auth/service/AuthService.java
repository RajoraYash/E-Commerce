package com.yash.ecommerce_backend.auth.service;
import com.yash.ecommerce_backend.auth.service.JwtService;
import com.yash.ecommerce_backend.auth.dto.LoginRequest;
import com.yash.ecommerce_backend.auth.dto.LoginResponse;
import com.yash.ecommerce_backend.user.entity.User;
import com.yash.ecommerce_backend.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    public AuthService(AuthenticationManager authenticationManager,
                       UserRepository userRepository,
                       JwtService jwtService){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }
    public LoginResponse login(LoginRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user  = userRepository.findByEmail(request.getEmail())
                .orElseThrow(()->new RuntimeException(("User Not Found")));
        String token = jwtService.generateToken(user);
        return new LoginResponse(token);
    }
}
