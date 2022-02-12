package com.example.cervezax.repositories;

import com.example.cervezax.database.dao.ProductDao;
import com.example.cervezax.database.entity.Product;

import java.util.List;

public class ProductImplement implements ProductRepository{

    ProductDao dao;

    public ProductImplement(ProductDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Product> getAllProducts() {
        return dao.getAll();
    }

    @Override
    public Product findProductById(int productId) {
        return dao.findById(productId);
    }

    @Override
    public void insertProduct(Product product) {
        dao.insert(product);
    }

    @Override
    public void updateProduct(Product product) {
        dao.update(product);
    }

    @Override
    public void deleteProduct(Product product) {
        dao.delete(product);
    }

    @Override
    public List<Product> searchProducts(String search){
        return dao.searchProducts(search);
    }

    @Override
    public void deleteProductById(int productId) {
        dao.deleteProductById(productId);
    }
}