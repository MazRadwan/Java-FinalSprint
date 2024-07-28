package main.javafinalsprint.service;

import main.javafinalsprint.dao.ProductDAO;
import main.javafinalsprint.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() throws ClassNotFoundException, SQLException {
        this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) {
        productDAO.createProduct(product);
    }

    public Product getProductById(int productId) {
        return productDAO.getProductById(productId);
    }

    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) {
        productDAO.deleteProduct(productId);
    }

    public List<Product> getProductsBySellerId(int sellerId) {
        return productDAO.getProductsBySellerId(sellerId);
    }
}

