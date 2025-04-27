package com.student.vaccine.controller;

import com.student.vaccine.entity.User;
import com.student.vaccine.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean success = authService.login(username, password);
        return success ? "Login successful" : "Invalid credentials";
    }

    @GetMapping("/user")
    public User getUserDetails(@RequestParam String username) {
        return authService.getUser(username);
    }
}
