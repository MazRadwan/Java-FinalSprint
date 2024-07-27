package com.javafinalsprint.service;

import com.javafinalsprint.dao.UserDAO;
import com.javafinalsprint.model.User;

import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDAO;

    public UserService() throws ClassNotFoundException, SQLException {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        // Hash the password before saving
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            // Authentication successful
            return user;
        }
        // Authentication failed
        return null;
    }
}
