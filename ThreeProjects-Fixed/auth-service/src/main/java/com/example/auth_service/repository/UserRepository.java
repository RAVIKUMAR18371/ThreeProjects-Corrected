package com.example.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.auth_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom method required by Spring security later to find users
    Optional<User> findByUsername(String username);
    
}
