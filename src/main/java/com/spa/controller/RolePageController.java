package com.spa.controller;

import com.spa.entity.User;
import com.spa.entity.UserRole;
import com.spa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class RolePageController {
    private final UserService userService;

    @GetMapping("/lobby_manager")
    public String lobbyManager(@RequestParam String username, Model model) {
        try {
            User user = userService.findByUsername(username);
            if (user.getRole() != UserRole.LOBBY_MANAGER) {
                return "redirect:/?error=unauthorized";
            }
            model.addAttribute("user", user);
            return "lobby_manager";
        } catch (Exception e) {
            return "redirect:/?error=user_not_found";
        }
    }

    @GetMapping("/department_manager")
    public String departmentManager(@RequestParam String username, Model model) {
        try {
            User user = userService.findByUsername(username);
            if (user.getRole() != UserRole.DEPARTMENT_MANAGER) {
                return "redirect:/?error=unauthorized";
            }
            model.addAttribute("user", user);
            return "department_manager";
        } catch (Exception e) {
            return "redirect:/?error=user_not_found";
        }
    }

    @GetMapping("/technician")
    public String technician(@RequestParam String username, Model model) {
        try {
            User user = userService.findByUsername(username);
            if (user.getRole() != UserRole.TECHNICIAN) {
                return "redirect:/?error=unauthorized";
            }
            model.addAttribute("user", user);
            return "technician";
        } catch (Exception e) {
            return "redirect:/?error=user_not_found";
        }
    }

    @GetMapping("/staff")
    public String staff(@RequestParam String username, Model model) {
        try {
            User user = userService.findByUsername(username);
            if (user.getRole() != UserRole.STAFF) {
                return "redirect:/?error=unauthorized";
            }
            model.addAttribute("user", user);
            return "staff";
        } catch (Exception e) {
            return "redirect:/?error=user_not_found";
        }
    }
} 