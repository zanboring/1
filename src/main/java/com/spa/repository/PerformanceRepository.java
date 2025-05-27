package com.spa.repository;

import com.spa.entity.Performance;
import com.spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PerformanceRepository extends JpaRepository<Performance, Long> {
    List<Performance> findByUser(User user);
    
    @Query("SELECT p FROM Performance p WHERE p.user.id = :userId")
    List<Performance> findByUserId(Long userId);
    
    List<Performance> findByDateBetween(LocalDate start, LocalDate end);
    
    List<Performance> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
    
    @Query("SELECT SUM(p.serviceCount) FROM Performance p WHERE p.user = ?1 AND p.date BETWEEN ?2 AND ?3")
    Integer calculateTotalServices(User user, LocalDate start, LocalDate end);
    
    @Query("SELECT AVG(p.customerSatisfaction) FROM Performance p WHERE p.user = ?1 AND p.date BETWEEN ?2 AND ?3")
    BigDecimal calculateAverageSatisfaction(User user, LocalDate start, LocalDate end);
    
    @Query("SELECT p FROM Performance p WHERE p.user.id = :userId AND p.date BETWEEN :startDate AND :endDate")
    List<Performance> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);
} 