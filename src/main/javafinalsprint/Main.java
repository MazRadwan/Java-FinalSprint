package main.javafinalsprint;

import java.sql.SQLException;

import main.javafinalsprint.model.Product;
import main.javafinalsprint.model.User;
import main.javafinalsprint.service.ProductService;
import main.javafinalsprint.service.UserService;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserService();

        // Create a new user
        User newUser = new User();
        newUser.setUsername("testuser");
        newUser.setEmail("testuser@example.com");
        newUser.setPassword("password123");
        newUser.setRole("seller"); // or "buyer", "admin"

        // Register the user
        userService.registerUser(newUser);

        System.out.println("User registered successfully!");

        ProductService productService = new ProductService();

        Product newProduct = new Product();
        newProduct.setName("test");
        newProduct.setPrice(1);
        newProduct.setQuantity(12);
        newProduct.setSellerId(1);

        productService.addProduct(newProduct);
        System.out.println("Product added successfully!");
    }
}
