package com.spa.service;

import com.spa.entity.User;
import com.spa.entity.UserRole;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(String username, String password, UserRole role);
    User createUser(User user);
    void updateUser(Long id, User user);
    void deleteUser(Long id);
    Optional<User> getUserById(Long id);
    Optional<User> getUserByUsername(String username);
    List<User> getAllUsers();
    List<User> getUsersByRole(UserRole role);
    boolean validateUser(String username, String password);
    boolean existsByUsername(String username);
    User findByUsername(String username);
} 