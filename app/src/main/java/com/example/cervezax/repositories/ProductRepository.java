package com.example.cervezax.repositories;

import com.example.cervezax.database.entity.Product;

import java.util.List;

public interface ProductRepository {

    List<Product> getAllProducts();
    List<Product> searchProducts(String search);
    Product findProductById(int productId);
    void deleteProductById(int productId);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Product product);

}