package main.javafinalsprint.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellerDashboard extends JFrame {
    private JButton addProductButton;
    private JTable productTable;
    private JButton editProductButton;
    private JButton deleteProductButton;
    private JButton logoutButton;
   

    public SellerDashboard() {
     
        setTitle("Seller Dashboard");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        addProductButton = new JButton("Add New Product");

        topPanel.add(addProductButton);

        panel.add(topPanel, BorderLayout.NORTH);

        // Placeholder for product table
        productTable = new JTable(new Object[][]{}, new String[]{"Name", "Price", "Quantity", "Edit", "Delete"});
        panel.add(new JScrollPane(productTable), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        editProductButton = new JButton("Edit Product");
        deleteProductButton = new JButton("Delete Product");
        logoutButton = new JButton("Logout");

        bottomPanel.add(editProductButton);
        bottomPanel.add(deleteProductButton);
        bottomPanel.add(logoutButton);

        panel.add(bottomPanel, BorderLayout.SOUTH);

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle add product action
            }
        });

        editProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle edit product action
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
                SellerDashboard.this.dispose();
            }
        });

        add(panel);
    }
}
