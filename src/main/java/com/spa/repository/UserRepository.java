package com.spa.repository;

import com.spa.entity.User;
import com.spa.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    
    List<User> findByRole(UserRole role);
    
    @Query("SELECT u FROM User u WHERE u.role = :role AND u.department.name = :department")
    List<User> findByRoleAndDepartment(UserRole role, String department);
    
    boolean existsByUsername(String username);
    
    @Query("""
        SELECT u FROM User u 
        WHERE u.role = :role 
        AND LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))
        """)
    List<User> findByRoleAndNameContaining(UserRole role, String name);
    
    @Query("""
        SELECT u FROM User u 
        WHERE LOWER(u.username) LIKE LOWER(CONCAT('%', :keyword, '%')) 
        OR LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))
        """)
    List<User> searchByKeyword(String keyword);
} 