package com.example.service;

import com.example.model.Department;
import java.util.List;

public interface DepartmentService {
    Department save(Department department);
    Department findById(Long id);
    List<Department> findAll();
    void deleteById(Long id);
} 