package main.javafinalsprint.gui;

import main.javafinalsprint.model.Product;
import main.javafinalsprint.model.User;
import main.javafinalsprint.service.ProductService;
import main.javafinalsprint.service.UserService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BuyerDashboard extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JTable productTable;
    private JButton viewDetailsButton;
    private JButton logoutButton;
    private ProductService productService;
    private UserService userService;
    private User currentUser;

    public BuyerDashboard(User user) {
        this.currentUser = user;
        try {
            this.productService = new ProductService();
            this.userService = new UserService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error initializing services: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Buyer Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        layoutComponents();
        addListeners();
        loadAllProducts();
    }

    private void initComponents() {
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        productTable = new JTable();
        viewDetailsButton = new JButton("View Details");
        logoutButton = new JButton("Logout");
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Search Product:"));
        topPanel.add(searchField);
        topPanel.add(searchButton);

        JScrollPane tableScrollPane = new JScrollPane(productTable);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(viewDetailsButton);
        bottomPanel.add(logoutButton);

        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        searchButton.addActionListener(e -> searchProducts());
        viewDetailsButton.addActionListener(e -> viewProductDetails());
        logoutButton.addActionListener(e -> logout());
    }

    private void loadAllProducts() {
        List<Product> products = productService.getAllProducts();
        displayProducts(products);
    }

    private void searchProducts() {
        String keyword = searchField.getText().trim();
        List<Product> allProducts = productService.getAllProducts();
        List<Product> filteredProducts = allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                             p.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        displayProducts(filteredProducts);
    }

    private void displayProducts(List<Product> products) {
        String[] columnNames = {"Name", "Price", "Quantity", "Seller"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Product product : products) {
            User seller = userService.getUserById(product.getSellerId());
            Object[] row = {
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                seller != null ? seller.getUsername() : "Unknown"
            };
            model.addRow(row);
        }

        productTable.setModel(model);
    }

    private void viewProductDetails() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow >= 0) {
            String productName = (String) productTable.getValueAt(selectedRow, 0);
            Product selectedProduct = productService.getAllProducts().stream()
                    .filter(p -> p.getName().equals(productName))
                    .findFirst()
                    .orElse(null);

            if (selectedProduct != null) {
                User seller = userService.getUserById(selectedProduct.getSellerId());
                String details = String.format(
                    "Product Details:\nName: %s\nPrice: $%.2f\nQuantity: %d\nDescription: %s\nSeller: %s",
                    selectedProduct.getName(),
                    selectedProduct.getPrice(),
                    selectedProduct.getQuantity(),
                    selectedProduct.getDescription(),
                    seller != null ? seller.getUsername() : "Unknown"
                );
                JOptionPane.showMessageDialog(this, details, "Product Details", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a product to view details.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to logout?", "Confirm Logout", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            new LoginScreen().setVisible(true);
            this.dispose();
        }
    }
}