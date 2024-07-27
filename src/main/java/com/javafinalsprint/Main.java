package com.javafinalsprint;

import java.sql.SQLException;

import com.javafinalsprint.model.User;
import com.javafinalsprint.service.UserService;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserService();

        // Create a new user
        User newUser = new User();
        newUser.setUsername("testuser");
        newUser.setEmail("testuser@example.com");
        newUser.setPassword("password123");
        newUser.setRole("seller"); // or "buyer", "admin"

        // Register the user
        userService.registerUser(newUser);

        System.out.println("User registered successfully!");
    }
}
