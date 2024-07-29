package main.javafinalsprint.service;

import main.javafinalsprint.dao.UserDAO;
import main.javafinalsprint.model.User;

import java.sql.SQLException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

public class UserService {
    private UserDAO userDAO;

    public UserService() throws ClassNotFoundException, SQLException {
        this.userDAO = new UserDAO();
    }

    public void registerUser(User user) {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        userDAO.createUser(user);
    }

    public User loginUser(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return user; // Authentication successful
        }
        return null; // Authentication failed
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }
}
