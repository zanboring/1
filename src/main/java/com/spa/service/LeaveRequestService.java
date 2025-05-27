package com.spa.service;

import com.spa.entity.LeaveRequest;
import java.util.List;

public interface LeaveRequestService {
    List<LeaveRequest> findByUserId(Long userId);
    LeaveRequest save(LeaveRequest leaveRequest);
    void delete(Long id);
    LeaveRequest findById(Long id);
    List<LeaveRequest> findByStatus(String status);
    void approve(Long id);
    void reject(Long id, String comment);
} 