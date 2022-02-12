package com.example.cervezax.repositories;

import com.example.cervezax.database.dao.BillDao;
import com.example.cervezax.database.entity.Bill;

import java.util.List;

public class BillImplement implements BillRepository{

    BillDao dao;

    public BillImplement(BillDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Bill> getAllBill() {
        return dao.getAll();
    }

    @Override
    public void deleteAllBill() {
        dao.deleteAll();
    }

    @Override
    public void insertBill(Bill bill) {
       dao.insert(bill);
    }

    @Override
    public void updateBill(Bill bill) {
        dao.update(bill);
    }

    @Override
    public void deleteBill(Bill bill) {
        dao.delete(bill);
    }

    @Override
    public int getTotalSellBill() {
       return dao.getTotalSell();
    }
}
