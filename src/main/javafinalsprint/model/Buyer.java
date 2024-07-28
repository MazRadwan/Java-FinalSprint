package main.javafinalsprint.model;

import java.util.List;

public class Buyer extends User {
    private List<Product> cart;

    public List<Product> getCart() {
        return cart;
    }

    public void setCart(List<Product> cart) {
        this.cart = cart;
    }

    public void viewProducts() {
        // Implementation to view products
    }

    public void searchProducts(String query) {
        // Implementation to search products
    }

    public void addProductToCart(Product product) {
        // Implementation to add product to cart
        if (cart != null) {
            cart.add(product);
        }
    }
}
