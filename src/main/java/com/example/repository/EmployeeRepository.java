package com.example.repository;

import com.example.model.Employee;
import com.example.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByUsername(String username);
    List<Employee> findByDepartment(Department department);
} 