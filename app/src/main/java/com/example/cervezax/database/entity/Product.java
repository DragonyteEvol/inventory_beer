package com.example.cervezax.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product")
public class Product {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getPrice_buy() {
        return price_buy;
    }

    public void setPrice_buy(int price_buy) {
        this.price_buy = price_buy;
    }

    public int getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(int price_sell) {
        this.price_sell = price_sell;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "presentation")
    String presentation;

    @ColumnInfo(name = "content")
    int content;

    @ColumnInfo(name = "price_buy")
    int price_buy;

    @ColumnInfo(name = "price_sell")
    int price_sell;

    @ColumnInfo(name = "uid")
    int uid;

    @ColumnInfo(name = "stock")
    int stock;

    @ColumnInfo(name = "created_at")
    String created_at;

    @ColumnInfo(name = "image")
    String image;


}