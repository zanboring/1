package com.spa.controller;

import com.spa.entity.LeaveRequest;
import com.spa.entity.LeaveStatus;
import com.spa.service.LeaveRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leave")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestService leaveRequestService;

    @GetMapping("/view")
    public String viewLeaveRequests(@RequestParam Long userId, Model model) {
        model.addAttribute("leaveRequests", leaveRequestService.findByUserId(userId));
        return "leave/view";
    }

    @GetMapping("/manage")
    public String manageLeaveRequests(Model model) {
        model.addAttribute("leaveRequests", leaveRequestService.findByStatus(LeaveStatus.PENDING.name()));
        return "leave/manage";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveLeaveRequest(@RequestBody LeaveRequest leaveRequest) {
        try {
            leaveRequestService.save(leaveRequest);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/approve/{id}")
    @ResponseBody
    public String approveLeaveRequest(@PathVariable Long id) {
        try {
            leaveRequestService.approve(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @PostMapping("/reject/{id}")
    @ResponseBody
    public String rejectLeaveRequest(@PathVariable Long id, @RequestParam String comment) {
        try {
            leaveRequestService.reject(id, comment);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
} 