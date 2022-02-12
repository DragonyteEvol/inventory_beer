package com.example.cervezax.views;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cervezax.R;
import com.example.cervezax.activities.DetailProductActivity;
import com.example.cervezax.database.entity.Bill;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.threads.controllers.ImageController;

import java.util.Hashtable;


public class BillCard extends LinearLayout {
    private TextView tvListProduct,tvListPrice,tvFinalPrice,tvCreatedAt;

    public BillCard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.view_card_bill, this, true);


        tvListPrice = (TextView) findViewById(R.id.tvViewBillListPrice);
        tvListProduct= (TextView) findViewById(R.id.tvViewBillListProduct);
        tvFinalPrice= (TextView) findViewById(R.id.tvViewBillFinalPrice);
        tvCreatedAt= (TextView) findViewById(R.id.tvViewBillCreatedAt);
    }

    public void setParams(Bill bill){
       tvListPrice.setText(bill.getPrice());
       tvListProduct.setText(bill.getProduct());
       tvFinalPrice.setText(bill.getFinal_price());
       tvCreatedAt.setText(bill.getCreated_at());
    }

}
