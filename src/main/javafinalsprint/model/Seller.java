package main.javafinalsprint.model;

import java.util.List;

public class Seller extends User {
    private String storeName;
    private List<Product> products;

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        // Implementation to add product
        if (products != null) {
            products.add(product);
        }
    }

    public void updateProduct(Product product) {
        // Implementation to update product
        if (products != null) {
            for (Product p : products) {
                if (p.getProductId() == product.getProductId()) {
                    p.setName(product.getName());
                    p.setPrice(product.getPrice());
                    p.setQuantity(product.getQuantity());
                    break;
                }
            }
        }
    }

    public void deleteProduct(int productId) {
        // Implementation to delete product
        if (products != null) {
            products.removeIf(product -> product.getProductId() == productId);
        }
    }

    public void viewMyProducts() {
        // Implementation to view seller's products
    }
}
