package com.spa.controller;

import com.spa.entity.User;
import com.spa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;

    @GetMapping({"/", "/login"})
    public String home() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        if (userService.validateUser(username, password)) {
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
            
            switch (user.getRole()) {
                case LOBBY_MANAGER:
                    return "redirect:/lobby_manager?username=" + username;
                case DEPARTMENT_MANAGER:
                    return "redirect:/department_manager?username=" + username;
                case TECHNICIAN:
                    return "redirect:/technician?username=" + username;
                case STAFF:
                    return "redirect:/staff?username=" + username;
                default:
                    return "redirect:/?error";
            }
        }
        return "redirect:/?error";
    }
} 