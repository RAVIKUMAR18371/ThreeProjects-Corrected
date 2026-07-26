package com.example.auth_service.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.auth_service.dto.JWTTokenResponseDTO;
import com.example.auth_service.dto.UserDTO;
import com.example.auth_service.model.User;
import com.example.auth_service.repository.UserRepository;
import com.example.auth_service.util.JwtUtil;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserDTO registerUser(User user) {
        // Check if username already exists
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists: " + user.getUsername());
        }
        
        // Set default role if not provided
        if (user.getRoles() == null || user.getRoles().trim().isEmpty()) {
            user.setRoles("ROLE_USER");
        }
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);

        return convertUserToDTO(savedUser);
    }

    public JWTTokenResponseDTO generateToken(String username) {
        String token = jwtUtil.generateToken(username);

        return new JWTTokenResponseDTO(token, "Bearer", 30 * 60); // 30 minutes in seconds
    }
    
    private UserDTO convertUserToDTO(User user) {
        return new UserDTO(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRoles()
        );
    }
}