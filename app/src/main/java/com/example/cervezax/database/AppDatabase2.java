package com.example.cervezax.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cervezax.database.dao.BillDao;
import com.example.cervezax.database.entity.Bill;

@Database(entities = {
        Bill.class
}, version = 1)
public abstract class AppDatabase2 extends RoomDatabase {

    public static AppDatabase2 INSTANCE;

    public abstract BillDao billDao();


    public static AppDatabase2 getInstance(Context context){
        if (INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase2.class,"bills.db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    };
}
