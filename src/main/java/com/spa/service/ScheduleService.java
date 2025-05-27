package com.spa.service;

import com.spa.entity.Schedule;
import java.util.List;

public interface ScheduleService {
    List<Schedule> findByUserId(Long userId);
    Schedule save(Schedule schedule);
    void delete(Long id);
    Schedule findById(Long id);
    List<Schedule> findByStatus(String status);
} 