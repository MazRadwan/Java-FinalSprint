package main.javafinalsprint.gui;

import main.javafinalsprint.model.User; // Import the User class

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminDashboard extends JFrame {
    private JTable userTable;
    private JTable productTable;
    private JButton deleteUserButton;
    private JButton deleteProductButton;
    private JButton logoutButton;
  

    public AdminDashboard(User user) {
   
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());

        // Placeholder for user table
        userTable = new JTable(new Object[][]{}, new String[]{"Username", "Role", "Email", "Delete"});
        userPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        deleteUserButton = new JButton("Delete User");
        userPanel.add(deleteUserButton, BorderLayout.SOUTH);

        panel.add(userPanel, BorderLayout.NORTH);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        // Placeholder for product table
        productTable = new JTable(new Object[][]{}, new String[]{"Name", "Price", "Quantity", "Seller", "Delete"});
        productPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        deleteProductButton = new JButton("Delete Product");
        productPanel.add(deleteProductButton, BorderLayout.SOUTH);

        panel.add(productPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        logoutButton = new JButton("Logout");

        bottomPanel.add(logoutButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete user action
            }
        });

        deleteProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle delete product action
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginScreen().setVisible(true);
                AdminDashboard.this.dispose();
            }
        });

        add(panel);
    }
}
