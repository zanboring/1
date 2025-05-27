package com.example.repository;

import com.example.model.LeaveApplication;
import com.example.model.Employee;
import com.example.model.Department;
import com.example.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    List<LeaveApplication> findByEmployee(Employee employee);
    List<LeaveApplication> findByEmployeeDepartmentAndStatus(Department department, LeaveStatus status);
} 