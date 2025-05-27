package com.example.controller;

import com.example.model.Employee;
import com.example.model.LeaveApplication;
import com.example.model.LeaveStatus;
import com.example.service.EmployeeService;
import com.example.service.LeaveApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @GetMapping("/profile")
    public String viewProfile(Authentication authentication, Model model) {
        Employee employee = employeeService.findByUsername(authentication.getName());
        model.addAttribute("employee", employee);
        model.addAttribute("myLeaveApplications", leaveApplicationService.findByEmployee(employee));
        return "employee";
    }

    @PostMapping("/profile/update")
    @ResponseBody
    public String updateProfile(@RequestBody Employee employee, Authentication authentication) {
        try {
            Employee currentEmployee = employeeService.findByUsername(authentication.getName());
            currentEmployee.setName(employee.getName());
            currentEmployee.setEmail(employee.getEmail());
            currentEmployee.setPhone(employee.getPhone());
            employeeService.save(currentEmployee);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/leave/apply")
    @ResponseBody
    public String applyLeave(@RequestBody LeaveApplication leaveApplication, Authentication authentication) {
        try {
            Employee employee = employeeService.findByUsername(authentication.getName());
            leaveApplication.setEmployee(employee);
            leaveApplication.setStatus(LeaveStatus.PENDING);
            leaveApplicationService.save(leaveApplication);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
} 