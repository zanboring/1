package com.spa.controller;

import com.spa.entity.Schedule;
import com.spa.entity.ScheduleStatus;
import com.spa.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/view")
    public String viewSchedule(@RequestParam Long userId, Model model) {
        model.addAttribute("schedules", scheduleService.findByUserId(userId));
        return "schedule/view";
    }

    @GetMapping("/manage")
    public String manageSchedule(Model model) {
        model.addAttribute("schedules", scheduleService.findByStatus(ScheduleStatus.PENDING.name()));
        return "schedule/manage";
    }

    @PostMapping("/save")
    @ResponseBody
    public String saveSchedule(@RequestBody Schedule schedule) {
        try {
            scheduleService.save(schedule);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteSchedule(@PathVariable Long id) {
        try {
            scheduleService.delete(id);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
} 