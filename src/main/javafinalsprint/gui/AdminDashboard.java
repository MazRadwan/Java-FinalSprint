package main.javafinalsprint.gui;

import main.javafinalsprint.model.User;
import main.javafinalsprint.model.Product;
import main.javafinalsprint.service.UserService;
import main.javafinalsprint.service.ProductService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JTable userTable;
    private JTable productTable;
    private JButton deleteUserButton;
    private JButton logoutButton;
    private UserService userService;
    private ProductService productService;
   

    public AdminDashboard(User user) {
     
        try {
            this.userService = new UserService();
            this.productService = new ProductService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error initializing services: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());

        userTable = new JTable(new DefaultTableModel(new Object[]{"User ID", "Username", "Role", "Email"}, 0));
        userPanel.add(new JScrollPane(userTable), BorderLayout.CENTER);

        deleteUserButton = new JButton("Delete User");
        userPanel.add(deleteUserButton, BorderLayout.SOUTH);

        panel.add(userPanel, BorderLayout.NORTH);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());

        productTable = new JTable(new DefaultTableModel(new Object[]{"Name", "Price", "Quantity", "Seller"}, 0));
        productPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        panel.add(productPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        logoutButton = new JButton("Logout");

        bottomPanel.add(logoutButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        deleteUserButton.addActionListener(e -> deleteUser());
        logoutButton.addActionListener(e -> {
            new LoginScreen().setVisible(true);
            AdminDashboard.this.dispose();
        });

        add(panel);

        loadUsers();
        loadProducts();
    }

    private void loadUsers() {
        try {
            List<User> users = userService.getAllUsers();
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0); // Clear existing rows
            for (User user : users) {
                model.addRow(new Object[]{user.getUserId(), user.getUsername(), user.getRole(), user.getEmail()});
            }
            userTable.removeColumn(userTable.getColumnModel().getColumn(0)); // Hide the User ID column
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading users: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            DefaultTableModel model = (DefaultTableModel) productTable.getModel();
            model.setRowCount(0); // Clear existing rows
            for (Product product : products) {
                User seller = userService.getUserById(product.getSellerId());
                model.addRow(new Object[]{product.getName(), product.getPrice(), product.getQuantity(), seller.getUsername()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteUser() {
        int selectedRow = userTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        int userId = (Integer) model.getValueAt(selectedRow, 0);

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                userService.deleteUser(userId);
                loadUsers(); // Reload the user table
                loadProducts(); // Reload the products table
                JOptionPane.showMessageDialog(this, "User deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error deleting user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}