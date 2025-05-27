package com.example.service.impl;

import com.example.model.LeaveApplication;
import com.example.model.Employee;
import com.example.model.Department;
import com.example.model.LeaveStatus;
import com.example.repository.LeaveApplicationRepository;
import com.example.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {

    @Autowired
    private LeaveApplicationRepository leaveApplicationRepository;

    @Override
    public LeaveApplication save(LeaveApplication leaveApplication) {
        return leaveApplicationRepository.save(leaveApplication);
    }

    @Override
    public LeaveApplication findById(Long id) {
        return leaveApplicationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave application not found"));
    }

    @Override
    public List<LeaveApplication> findByEmployee(Employee employee) {
        return leaveApplicationRepository.findByEmployee(employee);
    }

    @Override
    public List<LeaveApplication> findPendingByDepartment(Department department) {
        return leaveApplicationRepository.findByEmployeeDepartmentAndStatus(department, LeaveStatus.PENDING);
    }
} 