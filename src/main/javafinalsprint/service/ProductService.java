package main.javafinalsprint.service;

import java.sql.SQLException;

import main.javafinalsprint.dao.ProductDAO;
import main.javafinalsprint.model.Product;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() throws ClassNotFoundException, SQLException{
         this.productDAO = new ProductDAO();
    }

    public void addProduct(Product product) throws SQLException{
        if(product.equals(null)){
            System.out.println("cannot be null");
        }
        productDAO.createProduct(product);
    }

}
