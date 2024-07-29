package main.javafinalsprint.gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductDetailsScreen extends JFrame {
    private JLabel nameLabel;
    private JLabel priceLabel;
    private JLabel quantityLabel;
    private JLabel sellerLabel;
    private JButton backButton;

    public ProductDetailsScreen(String productName, String productPrice, String productQuantity, String sellerName) {
        setTitle("Product Details");
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        nameLabel = new JLabel("Product Name: " + productName);
        priceLabel = new JLabel("Price: " + productPrice);
        quantityLabel = new JLabel("Quantity: " + productQuantity);
        sellerLabel = new JLabel("Seller: " + sellerName);

        backButton = new JButton("Back to Dashboard");

        panel.add(nameLabel);
        panel.add(priceLabel);
        panel.add(quantityLabel);
        panel.add(sellerLabel);
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        add(panel);
    }
}

