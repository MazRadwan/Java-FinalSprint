package main.javafinalsprint.gui;

import main.javafinalsprint.model.User;
import main.javafinalsprint.service.UserService;
import main.javafinalsprint.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class RegistrationScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JComboBox<String> roleComboBox;
    private JButton registerButton;
    private JButton backButton;
    private UserService userService;

    public RegistrationScreen() {
        initializeComponents();
        setupLayout();
        addActionListeners();
        initializeUserService();
    }

    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        emailField = new JTextField(20);
        String[] roles = {"buyer", "seller", "admin"}; // Ensure these match your database enum values
        roleComboBox = new JComboBox<>(roles);
        registerButton = new JButton("Register");
        backButton = new JButton("Back to Login");
    }

    private void setupLayout() {
        setTitle("E-Commerce Registration");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        ScreenUtils.centerOnScreen(this);

        JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Role:"));
        panel.add(roleComboBox);
        panel.add(registerButton);
        panel.add(backButton);

        add(panel);
    }

    private void addActionListeners() {
        registerButton.addActionListener(e -> handleRegistration());
        backButton.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            this.dispose();
        });
    }

    private void initializeUserService() {
        try {
            userService = new UserService();
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(this, "Error initializing UserService: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    private void handleRegistration() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();
        String role = ((String) roleComboBox.getSelectedItem()).toLowerCase();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);

        try {
            // Call registerUser method
            userService.registerUser(newUser);
            JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.", "Success", JOptionPane.INFORMATION_MESSAGE);
            new LoginScreen().setVisible(true);
            this.dispose();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Registration failed: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistrationScreen().setVisible(true));
    }
}