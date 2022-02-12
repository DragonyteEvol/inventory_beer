package com.example.cervezax.database.dao;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cervezax.database.entity.Product;

import java.util.List;

@Dao
public interface ProductDao {

    @Query("SELECT * FROM product")
    List<Product> getAll();

    @Query("SELECT * FROM product WHERE id = :productId")
    Product findById(int productId);

    @Query("SELECT * FROM product WHERE name LIKE '%' || :search ||'%'")
    List<Product> searchProducts(String search);

    @Query("DELETE FROM product WHERE id = :productId")
    void deleteProductById(int productId);

    @Insert
    void insert(Product product);

    @Update
    void update(Product product);

    @Delete
    void delete(Product product);

}