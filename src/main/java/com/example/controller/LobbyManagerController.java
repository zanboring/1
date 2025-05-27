package com.example.controller;

import com.example.model.Department;
import com.example.model.Employee;
import com.example.service.DepartmentService;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lobby-manager")
public class LobbyManagerController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        List<Employee> employees = employeeService.findAll();
        List<Department> departments = departmentService.findAll();
        
        model.addAttribute("employees", employees);
        model.addAttribute("departments", departments);
        return "lobby_manager";
    }

    @PostMapping("/employee/add")
    @ResponseBody
    public String addEmployee(@RequestBody Employee employee) {
        try {
            employeeService.save(employee);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/employee/{id}/update")
    @ResponseBody
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        try {
            Employee existingEmployee = employeeService.findById(id);
            existingEmployee.setName(employee.getName());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setPosition(employee.getPosition());
            employeeService.save(existingEmployee);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/employee/{id}/delete")
    @ResponseBody
    public String deleteEmployee(@PathVariable Long id) {
        try {
            employeeService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/department/add")
    @ResponseBody
    public String addDepartment(@RequestBody Department department) {
        try {
            departmentService.save(department);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/department/{id}/update")
    @ResponseBody
    public String updateDepartment(@PathVariable Long id, @RequestBody Department department) {
        try {
            Department existingDepartment = departmentService.findById(id);
            existingDepartment.setName(department.getName());
            existingDepartment.setManager(department.getManager());
            departmentService.save(existingDepartment);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/department/{id}/delete")
    @ResponseBody
    public String deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteById(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
} 