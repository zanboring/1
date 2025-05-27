package com.example.service;

import com.example.model.LeaveApplication;
import com.example.model.Employee;
import com.example.model.Department;
import java.util.List;

public interface LeaveApplicationService {
    LeaveApplication save(LeaveApplication leaveApplication);
    LeaveApplication findById(Long id);
    List<LeaveApplication> findByEmployee(Employee employee);
    List<LeaveApplication> findPendingByDepartment(Department department);
} 