package main.javafinalsprint;

import main.javafinalsprint.model.User;
import main.javafinalsprint.model.Product;
import main.javafinalsprint.service.UserService;
import main.javafinalsprint.service.ProductService;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;

public class ConsoleApp {
    private UserService userService;
    private ProductService productService;
    private Scanner scanner;

    public ConsoleApp() throws ClassNotFoundException, SQLException {
        this.userService = new UserService();
        this.productService = new ProductService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("Welcome to the E-Commerce Platform");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = getValidMenuChoice(1, 3);

            if (choice == 1) {
                login();
            } else if (choice == 2) {
                register();
            } else if (choice == 3) {
                System.out.println("Exiting... Goodbye!");
                break;
            }
        }
    }

    private void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userService.loginUser(username, password);

        if (user != null) {
            System.out.println("Login successful!");
            switch (user.getRole()) {
                case "seller":
                    displaySellerMenu(user);
                    break;
                case "buyer":
                    displayBuyerMenu(user);
                    break;
                case "admin":
                    displayAdminMenu(user);
                    break;
                default:
                    System.out.println("Unknown role. Please contact support.");
            }
        } else {
            System.out.println("Login failed. Please check your credentials and try again.");
        }
    }

    private void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        String email;
        while (true) {
            System.out.print("Enter email: ");
            email = scanner.nextLine();
            if (isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Please enter a valid email address.");
            }
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String role;
        while (true) {
            System.out.print("Enter role (seller/buyer/admin): ");
            role = scanner.nextLine().toLowerCase();
            if (role.equals("seller") || role.equals("buyer") || role.equals("admin")) {
                break;
            } else {
                System.out.println("Invalid role. Please enter 'seller', 'buyer', or 'admin'.");
            }
        }

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(role);

        userService.registerUser(newUser);

        System.out.println("Registration successful! You can now log in.");
    }

    private void displaySellerMenu(User user) {
        while (true) {
            System.out.println("Welcome, Seller " + user.getUsername());
            System.out.println("1. Add Product");
            System.out.println("2. Update Product");
            System.out.println("3. Delete Product");
            System.out.println("4. View My Products");
            System.out.println("5. Logout");
            int choice = getValidMenuChoice(1, 5);

            if (choice == 1) {
                addProduct(user);
            } else if (choice == 2) {
                updateProduct(user);
            } else if (choice == 3) {
                deleteProduct(user);
            } else if (choice == 4) {
                viewMyProducts(user);
            } else if (choice == 5) {
                break;
            }
        }
    }

    private void addProduct(User user) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setSellerId(user.getUserId());

        productService.addProduct(product);

        System.out.println("Product added successfully!");
    }

    private void updateProduct(User user) {
        System.out.print("Enter product ID to update: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = productService.getProductById(productId);
        if (product != null && product.getSellerId() == user.getUserId()) {
            System.out.print("Enter new product name: ");
            String name = scanner.nextLine();
            System.out.print("Enter new product price: ");
            double price = scanner.nextDouble();
            System.out.print("Enter new product quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter new product description: ");
            String description = scanner.nextLine();

            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);

            productService.updateProduct(product);

            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found or you do not have permission to update this product.");
        }
    }

    private void deleteProduct(User user) {
        System.out.print("Enter product ID to delete: ");
        int productId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Product product = productService.getProductById(productId);
        if (product != null && product.getSellerId() == user.getUserId()) {
            productService.deleteProduct(productId);
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found or you do not have permission to delete this product.");
        }
    }

    private void viewMyProducts(User user) {
        List<Product> products = productService.getProductsBySellerId(user.getUserId());
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Your Products:");
            for (Product product : products) {
                System.out.println("ID: " + product.getProductId() + ", Name: " + product.getName() +
                        ", Price: " + product.getPrice() + ", Quantity: " + product.getQuantity() +
                        ", Description: " + product.getDescription());
            }
        }
    }

    private int getValidMenuChoice(int min, int max) {
        int choice = 0;
        while (true) {
            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                if (choice >= min && choice <= max) {
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between " + min + " and " + max + ".");
                scanner.nextLine(); // Clear the invalid input
            }
        }
        return choice;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void displayBuyerMenu(User user) {
        System.out.println("Welcome, Buyer " + user.getUsername());
        // Display buyer-specific options
        // Handle buyer menu actions
    }

    private void displayAdminMenu(User user) {
        System.out.println("Welcome, Admin " + user.getUsername());
        // Display admin-specific options
        // Handle admin menu actions
    }
}
