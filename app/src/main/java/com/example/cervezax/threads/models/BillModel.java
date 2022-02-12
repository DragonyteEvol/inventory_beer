package com.example.cervezax.threads.models;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cervezax.MainActivity;
import com.example.cervezax.database.AppDatabase;
import com.example.cervezax.database.AppDatabase2;
import com.example.cervezax.database.entity.Bill;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.repositories.BillImplement;
import com.example.cervezax.views.BillCard;
import com.example.cervezax.views.ProductCard;

import java.util.List;

public class BillModel {
    public static class InsertBill extends AsyncTask<Void,Integer,Void> {
        Context context;
        Bill bill;

        public InsertBill(Context context, Bill bill) {
            this.context = context;
            this.bill = bill;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase2 db = AppDatabase2.getInstance(context);
            BillImplement resource = new BillImplement(db.billDao());
            resource.insertBill(bill);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Factura Generada.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public static class ShowAllBill extends AsyncTask<Void,Integer, List<Bill>>{
        Context context;
        LinearLayout layout,objectiveLayout;

        public ShowAllBill(Context context,LinearLayout objectiveLayout) {
            this.context = context;
            this.layout = new LinearLayout(context);
            this.layout.setOrientation(LinearLayout.VERTICAL);
            this.objectiveLayout = objectiveLayout;
        }

        @Override
        protected List<Bill> doInBackground(Void... voids) {
            AppDatabase2 db = AppDatabase2.getInstance(context);
            BillImplement resource = new BillImplement(db.billDao());

            return resource.getAllBill();
        }

        @Override
        protected void onPostExecute(List<Bill> bills) {
            super.onPostExecute(bills);
            for (Bill i : bills){
                BillCard view = new BillCard(context,null);
                view.setParams(i);
                objectiveLayout.addView(view);
            }
        }
    }

    public static class GetAllSell extends AsyncTask<Void,Integer,Integer>{
        private Context context;
        private TextView finalSell;

        public GetAllSell(Context context,TextView finalSell) {
            this.context = context;
            this.finalSell = finalSell;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            AppDatabase2 db = AppDatabase2.getInstance(context);
            BillImplement resource = new BillImplement(db.billDao());
            return  resource.getTotalSellBill();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            finalSell.setText(String.valueOf(integer));
        }
    }
}
