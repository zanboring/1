package com.spa.repository;

import com.spa.entity.LeaveRequest;
import com.spa.entity.LeaveStatus;
import com.spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {
    @Query("SELECT l FROM LeaveRequest l WHERE l.user = :user")
    List<LeaveRequest> findByUser(User user);
    
    @Query("SELECT l FROM LeaveRequest l WHERE l.status = :status")
    List<LeaveRequest> findByStatus(LeaveStatus status);
    
    @Query("SELECT l FROM LeaveRequest l WHERE l.user = :user AND l.status = :status")
    List<LeaveRequest> findByUserAndStatus(User user, LeaveStatus status);
    
    @Query("""
        SELECT l FROM LeaveRequest l 
        WHERE l.user = :user 
        AND l.startTime >= :startTime 
        AND l.endTime <= :endTime
        """)
    List<LeaveRequest> findByUserAndTimeRange(User user, LocalDateTime startTime, LocalDateTime endTime);
    
    @Query("""
        SELECT l FROM LeaveRequest l 
        WHERE l.status = :status 
        AND l.startTime >= :startTime 
        AND l.endTime <= :endTime
        """)
    List<LeaveRequest> findByStatusAndTimeRange(LeaveStatus status, LocalDateTime startTime, LocalDateTime endTime);

    @Query("""
        SELECT l FROM LeaveRequest l 
        WHERE l.user = :user 
        AND l.startTime >= :start 
        AND l.endTime <= :end
        """)
    List<LeaveRequest> findByUserAndStartTimeBetween(User user, LocalDateTime start, LocalDateTime end);

    @Query("SELECT l FROM LeaveRequest l WHERE l.user.id = :userId")
    List<LeaveRequest> findByUserId(Long userId);
    
    @Query("""
        SELECT l FROM LeaveRequest l 
        WHERE l.startTime >= :start 
        AND l.endTime <= :end
        """)
    List<LeaveRequest> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
} 