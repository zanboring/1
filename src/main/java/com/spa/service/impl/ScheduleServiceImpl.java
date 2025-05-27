package com.spa.service.impl;

import com.spa.entity.Schedule;
import com.spa.entity.ScheduleStatus;
import com.spa.repository.ScheduleRepository;
import com.spa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Schedule> findByUserId(Long userId) {
        return scheduleRepository.findByUserId(userId);
    }

    @Override
    public Schedule save(Schedule schedule) {
        return scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public Schedule findById(Long id) {
        return scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));
    }

    @Override
    public List<Schedule> findByStatus(String status) {
        return scheduleRepository.findByStatus(ScheduleStatus.valueOf(status));
    }
} 