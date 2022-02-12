package com.example.cervezax.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "bill")
public class Bill {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "product")
    String product;

    @ColumnInfo(name = "price")
    String price;

    @ColumnInfo(name = "final_price")
    String final_price;

    @ColumnInfo(name = "created_at")
    String created_at;
}
