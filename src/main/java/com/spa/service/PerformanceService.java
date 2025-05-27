package com.spa.service;

import com.spa.entity.Performance;
import java.util.List;

public interface PerformanceService {
    List<Performance> findByUserId(Long userId);
    Performance save(Performance performance);
    void delete(Long id);
    Performance findById(Long id);
    double getAverageScore(Long userId);
    List<Performance> findByDateRange(Long userId, String startDate, String endDate);
} 