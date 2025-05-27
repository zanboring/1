package com.spa.controller;

import com.spa.entity.User;
import com.spa.entity.UserRole;
import com.spa.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final UserService userService;

    @GetMapping("/init-users")
    public String initUsers() {
        try {
            // 检查用户是否已存在
            if (!userService.existsByUsername("zanboring")) {
                User user = new User();
                user.setUsername("zanboring");
                user.setPassword("123456");
                user.setName("张博仁");
                user.setPhone("13800138000");
                user.setRole(UserRole.LOBBY_MANAGER);
                user.setActive(true);
                userService.createUser(user);
            }

            if (!userService.existsByUsername("tyh")) {
                User user = new User();
                user.setUsername("tyh");
                user.setPassword("123456");
                user.setName("唐雨涵");
                user.setPhone("13800138001");
                user.setRole(UserRole.DEPARTMENT_MANAGER);
                user.setActive(true);
                userService.createUser(user);
            }

            if (!userService.existsByUsername("lc")) {
                User user = new User();
                user.setUsername("lc");
                user.setPassword("123456");
                user.setName("李创");
                user.setPhone("13800138002");
                user.setRole(UserRole.TECHNICIAN);
                user.setActive(true);
                userService.createUser(user);
            }

            if (!userService.existsByUsername("jjking")) {
                User user = new User();
                user.setUsername("jjking");
                user.setPassword("123456");
                user.setName("李喆");
                user.setPhone("13800138003");
                user.setRole(UserRole.STAFF);
                user.setActive(true);
                userService.createUser(user);
            }

            return "用户初始化成功!";
        } catch (Exception e) {
            return "用户初始化失败: " + e.getMessage();
        }
    }
} 