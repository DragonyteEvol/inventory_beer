package com.example.cervezax.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cervezax.database.entity.Bill;

import java.util.List;

@Dao
public interface BillDao {

    @Query("SELECT * FROM bill")
    List<Bill> getAll();

    @Query("DELETE FROM bill")
    void deleteAll();

    @Query("SELECT SUM(final_price) as total FROM bill")
    int getTotalSell();

    @Insert
    void insert(Bill bill);

    @Update
    void update(Bill bill);

    @Delete
    void delete(Bill bill);
}
