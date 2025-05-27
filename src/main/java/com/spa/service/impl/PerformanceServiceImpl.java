package com.spa.service.impl;

import com.spa.entity.Performance;
import com.spa.repository.PerformanceRepository;
import com.spa.service.PerformanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PerformanceServiceImpl implements PerformanceService {
    private final PerformanceRepository performanceRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public List<Performance> findByUserId(Long userId) {
        return performanceRepository.findByUserId(userId);
    }

    @Override
    public Performance save(Performance performance) {
        return performanceRepository.save(performance);
    }

    @Override
    public void delete(Long id) {
        performanceRepository.deleteById(id);
    }

    @Override
    public Performance findById(Long id) {
        return performanceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Performance not found"));
    }

    @Override
    public double getAverageScore(Long userId) {
        List<Performance> performances = findByUserId(userId);
        if (performances.isEmpty()) {
            return 0.0;
        }
        return performances.stream()
                .mapToInt(Performance::getScore)
                .average()
                .orElse(0.0);
    }

    @Override
    public List<Performance> findByDateRange(Long userId, String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return performanceRepository.findByUserIdAndDateBetween(userId, start, end);
    }
} 