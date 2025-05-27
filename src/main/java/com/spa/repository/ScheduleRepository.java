package com.spa.repository;

import com.spa.entity.Schedule;
import com.spa.entity.ScheduleStatus;
import com.spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("SELECT s FROM Schedule s WHERE s.user = :user")
    List<Schedule> findByUser(User user);
    
    @Query("SELECT s FROM Schedule s WHERE s.user.id = :userId")
    List<Schedule> findByUserId(Long userId);
    
    @Query("""
        SELECT s FROM Schedule s 
        WHERE s.user = :user 
        AND s.startTime >= :start 
        AND s.endTime <= :end
        """)
    List<Schedule> findByUserAndStartTimeBetween(User user, LocalDateTime start, LocalDateTime end);
    
    @Query("""
        SELECT s FROM Schedule s 
        WHERE s.user = :user 
        AND s.startTime <= :now 
        AND s.endTime >= :now
        """)
    List<Schedule> findCurrentSchedules(User user, LocalDateTime now);
    
    @Query("""
        SELECT s FROM Schedule s 
        WHERE s.startTime >= :start 
        AND s.endTime <= :end
        """)
    List<Schedule> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);
    
    @Query("SELECT s FROM Schedule s WHERE s.status = :status")
    List<Schedule> findByStatus(ScheduleStatus status);
} 