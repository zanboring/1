package com.spa.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "performances")
public class Performance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    @Column(name = "service_count")
    private Integer serviceCount = 0;

    @Column(name = "customer_satisfaction")
    private BigDecimal customerSatisfaction;

    @Column(name = "notes")
    private String notes;
    
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    // 兼容性方法，用于与现有代码配合
    public Integer getScore() {
        return customerSatisfaction != null ? customerSatisfaction.multiply(new BigDecimal(20)).intValue() : 0;
    }
} 