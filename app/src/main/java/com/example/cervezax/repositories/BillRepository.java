package com.example.cervezax.repositories;

import com.example.cervezax.database.entity.Bill;

import java.util.List;

public interface BillRepository {

    List<Bill> getAllBill();
    void deleteAllBill();
    void insertBill(Bill bill);
    void updateBill(Bill bill);
    void deleteBill(Bill bill);
    int getTotalSellBill();

}
