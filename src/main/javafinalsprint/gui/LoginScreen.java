package main.javafinalsprint.gui;

import main.javafinalsprint.model.User;

import main.javafinalsprint.service.UserService;
import main.javafinalsprint.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class LoginScreen extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private UserService userService;

    public LoginScreen() {
        initializeComponents();
        setupLayout();
        addActionListeners();
        initializeUserService();
          // Use ScreenUtils to center the frame on the selected screen
          ScreenUtils.centerOnScreen(this);
        
          // Print the actual location of the frame
          System.out.println("Frame location after centering: " + getLocation());
    }

    private void initializeComponents() {
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");
    }

    private void setupLayout() {
        setTitle("E-Commerce Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(passwordField, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(new JLabel("Don't have an account?"), gbc);

        gbc.gridy = 4;
        panel.add(registerButton, gbc);

        add(panel);
    }

    private void addActionListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationScreen().setVisible(true);
                LoginScreen.this.dispose();
            }
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

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user = userService.loginUser(username, password);

        if (user != null) {
            switch (user.getRole().toLowerCase()) {
                case "buyer":
                    new BuyerDashboard(user).setVisible(true);
                    break;
                    case "seller":
                    SellerDashboard sellerDashboard = new SellerDashboard(user); // Pass user to the constructor
                    sellerDashboard.setVisible(true);
                    break;
                
                case "admin":
                    new AdminDashboard(user).setVisible(true);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Invalid user role", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Print information about all screens
        ScreenUtils.printScreenInfo();

        // Set the preferred screen to the second monitor (index 1)
        // You can change this index based on your setup
        ScreenUtils.setPreferredScreen(1);

        SwingUtilities.invokeLater(() -> {
            LoginScreen loginScreen = new LoginScreen();
            loginScreen.setVisible(true);

            // Print the final location of the frame
            System.out.println("Final frame location: " + loginScreen.getLocation());
        });
    }
}