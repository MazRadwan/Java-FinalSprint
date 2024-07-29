package main.javafinalsprint.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Dashboard extends JFrame {
    private JButton buyerButton;
    private JButton sellerButton;
    private JButton adminButton;

    public Dashboard() {
        setTitle("Dashboard");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        buyerButton = new JButton("Buyer Dashboard");
        sellerButton = new JButton("Seller Dashboard");
        adminButton = new JButton("Admin Dashboard");

        buyerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BuyerDashboard(null).setVisible(true);
                Dashboard.this.dispose();
            }
        });

        sellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SellerDashboard().setVisible(true);
                Dashboard.this.dispose();
            }
        });

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminDashboard(null).setVisible(true);
                Dashboard.this.dispose();
            }
        });

        panel.add(buyerButton);
        panel.add(sellerButton);
        panel.add(adminButton);

        add(panel);
    }
}