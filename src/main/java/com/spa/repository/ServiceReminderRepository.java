package com.spa.repository;

import com.spa.entity.ServiceReminder;
import com.spa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ServiceReminderRepository extends JpaRepository<ServiceReminder, Long> {
    List<ServiceReminder> findByUser(User user);
    List<ServiceReminder> findByUserAndReminderDateBetween(User user, LocalDateTime start, LocalDateTime end);
    List<ServiceReminder> findByCompletedFalseAndReminderDateBefore(LocalDateTime time);
} 