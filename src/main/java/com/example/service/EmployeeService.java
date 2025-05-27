package com.example.service;

import com.example.model.Employee;
import com.example.model.Department;
import java.util.List;

public interface EmployeeService {
    Employee save(Employee employee);
    Employee findById(Long id);
    Employee findByUsername(String username);
    List<Employee> findAll();
    List<Employee> findByDepartment(Department department);
    void deleteById(Long id);
} 