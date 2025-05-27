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
import java.util.List;

@Controller
@RequestMapping("/department-manager")
public class DepartmentManagerController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveApplicationService leaveApplicationService;

    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication, Model model) {
        Employee manager = employeeService.findByUsername(authentication.getName());
        List<Employee> departmentEmployees = employeeService.findByDepartment(manager.getDepartment());
        List<LeaveApplication> leaveApplications = leaveApplicationService.findPendingByDepartment(manager.getDepartment());
        
        model.addAttribute("departmentEmployees", departmentEmployees);
        model.addAttribute("leaveApplications", leaveApplications);
        return "department_manager";
    }

    @PostMapping("/leave/{id}/approve")
    @ResponseBody
    public String approveLeave(@PathVariable Long id, Authentication authentication) {
        try {
            Employee manager = employeeService.findByUsername(authentication.getName());
            LeaveApplication leaveApplication = leaveApplicationService.findById(id);
            
            if (leaveApplication.getEmployee().getDepartment().equals(manager.getDepartment())) {
                leaveApplication.setStatus(LeaveStatus.APPROVED);
                leaveApplication.setApprover(manager);
                leaveApplicationService.save(leaveApplication);
                return "success";
            }
            return "error: Unauthorized";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/leave/{id}/reject")
    @ResponseBody
    public String rejectLeave(@PathVariable Long id, Authentication authentication) {
        try {
            Employee manager = employeeService.findByUsername(authentication.getName());
            LeaveApplication leaveApplication = leaveApplicationService.findById(id);
            
            if (leaveApplication.getEmployee().getDepartment().equals(manager.getDepartment())) {
                leaveApplication.setStatus(LeaveStatus.REJECTED);
                leaveApplication.setApprover(manager);
                leaveApplicationService.save(leaveApplication);
                return "success";
            }
            return "error: Unauthorized";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
} 