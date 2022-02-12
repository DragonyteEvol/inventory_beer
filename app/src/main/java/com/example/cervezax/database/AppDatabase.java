package com.example.cervezax.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cervezax.database.dao.ProductDao;
import com.example.cervezax.database.entity.Product;

@Database(entities = {
        Product.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static AppDatabase INSTANCE;

    public abstract ProductDao productDao();


    public static AppDatabase getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"beer.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    };
}
