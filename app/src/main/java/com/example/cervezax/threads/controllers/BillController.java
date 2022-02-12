package com.example.cervezax.threads.controllers;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.cervezax.database.AppDatabase;
import com.example.cervezax.database.entity.Bill;
import com.example.cervezax.repositories.BillImplement;

import java.util.List;

public class BillController {
    public static class InsertBill extends AsyncTask<Void,Integer,Void>{
        Context context;
        Bill bill;

        public InsertBill(Context context, Bill bill) {
            this.context = context;
            this.bill = bill;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Factura Generada.", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ShowAllBill extends AsyncTask<Void,Integer, List<Bill>>{
        Context context;

        @Override
        protected List<Bill> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            return null;
        }

        @Override
        protected void onPostExecute(List<Bill> bills) {
            super.onPostExecute(bills);
        }
    }

}
