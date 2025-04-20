package com.student.vaccine.service;


import com.student.vaccine.entity.User;
import com.student.vaccine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password) {

        String trimmedUsername = username.trim();
        String trimmedPassword = password.trim();

//        return userRepository.findByUsername(trimmedUsername)
//                .map(user -> user.getPassword().equals(trimmedPassword))
//                .orElse(false);

        Optional<User> userOpt = userRepository.findByUsername(trimmedUsername);

        if (userOpt.isEmpty()) {
            System.out.println("User not found: " + trimmedUsername);
            return false;
        }

        User user = userOpt.get();
        boolean passwordMatch = user.getPassword().equals(trimmedPassword);
        System.out.println("Password match: " + passwordMatch);

        return passwordMatch;
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
}
