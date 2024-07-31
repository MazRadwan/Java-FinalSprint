package main.javafinalsprint.gui;

import main.javafinalsprint.model.Product;
import main.javafinalsprint.service.ProductService;
import main.javafinalsprint.util.ScreenUtils;
import main.javafinalsprint.model.User;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SellerDashboard extends JFrame {
    private JTable productTable;
    private JButton addProductButton;
    private JButton updateProductButton;
    private JButton deleteProductButton;
    private JButton logoutButton;
    private ProductService productService;
    private User currentUser;

    public SellerDashboard(User user) {
        this.currentUser = user;
        try {
            this.productService = new ProductService();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error initializing services: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Seller Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setLocationRelativeTo(null);
        ScreenUtils.centerOnScreen(this);

        initComponents();
        loadSellerProducts();
    }

    private void initComponents() {
        productTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(productTable);
        addProductButton = new JButton("Add New Product");
        updateProductButton = new JButton("Edit Product");
        deleteProductButton = new JButton("Delete Product");
        logoutButton = new JButton("Logout");

        addProductButton.addActionListener(e -> openAddProductDialog());
        updateProductButton.addActionListener(e -> editProduct());
        deleteProductButton.addActionListener(e -> deleteProduct());
        logoutButton.addActionListener(e -> logout());

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addProductButton);
        buttonPanel.add(updateProductButton);
        buttonPanel.add(deleteProductButton);
        buttonPanel.add(logoutButton);

        add(panel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadSellerProducts() {
        try {
            List<Product> products = productService.getProductsBySellerId(currentUser.getUserId());
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(new String[]{"Name", "Price", "Quantity", "Description", "Product ID"});
            for (Product product : products) {
                model.addRow(new Object[]{product.getName(), product.getPrice(), product.getQuantity(), product.getDescription(), product.getProductId()});
            }
            productTable.setModel(model);
            productTable.removeColumn(productTable.getColumnModel().getColumn(4)); // Hide the Product ID column
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading products: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    private void openAddProductDialog() {
        JDialog dialog = new JDialog(this, "Add New Product", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JTextField nameField = new JTextField(20);
        JTextField priceField = new JTextField(20);
        JTextField quantityField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Name:"), gbc);
        
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Price:"), gbc);
        
        gbc.gridx = 1;
        dialog.add(priceField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Quantity:"), gbc);
        
        gbc.gridx = 1;
        dialog.add(quantityField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("Description:"), gbc);
        
        gbc.gridx = 1;
        dialog.add(descriptionField, gbc);
        
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, gbc);
        
        okButton.addActionListener(e -> {
            String name = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            String description = descriptionField.getText();
            
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);
            product.setSellerId(currentUser.getUserId());
            
            productService.addProduct(product);
            loadSellerProducts();
            dialog.dispose();
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        dialog.pack();
        ScreenUtils.centerOnScreen(dialog);
        dialog.setVisible(true);
    }
    
    

    private void editProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to edit.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        String name = (String) model.getValueAt(selectedRow, 0);
        double price = (Double) model.getValueAt(selectedRow, 1);
        int quantity = (Integer) model.getValueAt(selectedRow, 2);
        String description = (String) model.getValueAt(selectedRow, 3);
        int productId = (Integer) model.getValueAt(selectedRow, 4);
    
        JDialog dialog = new JDialog(this, "Edit Product", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
    
        JTextField nameField = new JTextField(name, 20);
        JTextField priceField = new JTextField(String.valueOf(price), 20);
        JTextField quantityField = new JTextField(String.valueOf(quantity), 20);
        JTextField descriptionField = new JTextField(description, 20);
    
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(new JLabel("Name:"), gbc);
    
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(new JLabel("Price:"), gbc);
    
        gbc.gridx = 1;
        dialog.add(priceField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(new JLabel("Quantity:"), gbc);
    
        gbc.gridx = 1;
        dialog.add(quantityField, gbc);
    
        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(new JLabel("Description:"), gbc);
    
        gbc.gridx = 1;
        dialog.add(descriptionField, gbc);
    
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
    
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(okButton);
        dialog.add(buttonPanel, gbc);
    
        okButton.addActionListener(e -> {
            String updatedName = nameField.getText();
            double updatedPrice = Double.parseDouble(priceField.getText());
            int updatedQuantity = Integer.parseInt(quantityField.getText());
            String updatedDescription = descriptionField.getText();
    
            Product product = new Product();
            product.setProductId(productId);
            product.setName(updatedName);
            product.setPrice(updatedPrice);
            product.setQuantity(updatedQuantity);
            product.setDescription(updatedDescription);
            product.setSellerId(currentUser.getUserId());
    
            productService.updateProduct(product);
            loadSellerProducts();
            dialog.dispose();
            JOptionPane.showMessageDialog(this, "Product updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });
    
        cancelButton.addActionListener(e -> dialog.dispose());
    
        dialog.pack();
        ScreenUtils.centerOnScreen(dialog);
        dialog.setVisible(true);
    }
    

    private void deleteProduct() {
        int selectedRow = productTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a product to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        DefaultTableModel model = (DefaultTableModel) productTable.getModel();
        int productId = (Integer) model.getValueAt(selectedRow, 4);
    
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this product?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            productService.deleteProduct(productId);
            loadSellerProducts();
        }
    }
    

    private void logout() {
        dispose();
        new LoginScreen().setVisible(true);
    }
}
