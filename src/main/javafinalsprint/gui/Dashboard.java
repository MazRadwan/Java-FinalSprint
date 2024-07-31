package main.javafinalsprint.gui;

import main.javafinalsprint.model.User;
import main.javafinalsprint.util.ScreenUtils;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private User currentUser;
    private JButton sellerDashboardButton;
    private JButton buyerDashboardButton;
    private JButton adminDashboardButton;
    private JButton logoutButton;

    public Dashboard(User user) {
        this.currentUser = user;
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        ScreenUtils.centerOnScreen(this);

        initComponents();
    }

    private void initComponents() {
        sellerDashboardButton = new JButton("Seller Dashboard");
        buyerDashboardButton = new JButton("Buyer Dashboard");
        adminDashboardButton = new JButton("Admin Dashboard");
        logoutButton = new JButton("Logout");

        sellerDashboardButton.addActionListener(e -> openSellerDashboard());
        buyerDashboardButton.addActionListener(e -> openBuyerDashboard());
        adminDashboardButton.addActionListener(e -> openAdminDashboard());
        logoutButton.addActionListener(e -> logout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(sellerDashboardButton);
        panel.add(buyerDashboardButton);
        panel.add(adminDashboardButton);
        panel.add(logoutButton);

        add(panel, BorderLayout.CENTER);
    }

    private void openSellerDashboard() {
        new SellerDashboard(currentUser).setVisible(true); // Pass currentUser to the constructor
        Dashboard.this.dispose();
    }

    private void openBuyerDashboard() {
        // Implement opening Buyer Dashboard
    }

    private void openAdminDashboard() {
        // Implement opening Admin Dashboard
    }

    private void logout() {
        dispose();
        new LoginScreen().setVisible(true);
    }
}
