package com.spa.service.impl;

import com.spa.entity.LeaveRequest;
import com.spa.entity.LeaveStatus;
import com.spa.repository.LeaveRequestRepository;
import com.spa.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveRequestServiceImpl implements LeaveRequestService {
    private final LeaveRequestRepository leaveRequestRepository;

    @Override
    public List<LeaveRequest> findByUserId(Long userId) {
        return leaveRequestRepository.findByUserId(userId);
    }

    @Override
    public LeaveRequest save(LeaveRequest leaveRequest) {
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public void delete(Long id) {
        leaveRequestRepository.deleteById(id);
    }

    @Override
    public LeaveRequest findById(Long id) {
        return leaveRequestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave request not found"));
    }

    @Override
    public List<LeaveRequest> findByStatus(String status) {
        return leaveRequestRepository.findByStatus(LeaveStatus.valueOf(status));
    }

    @Override
    public void approve(Long id) {
        LeaveRequest request = findById(id);
        request.setStatus(LeaveStatus.APPROVED);
        save(request);
    }

    @Override
    public void reject(Long id, String comment) {
        LeaveRequest request = findById(id);
        request.setStatus(LeaveStatus.REJECTED);
        request.setComment(comment);
        save(request);
    }
} 