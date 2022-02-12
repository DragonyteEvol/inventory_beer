package com.example.cervezax.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cervezax.R;
import com.example.cervezax.threads.models.BillModel;

public class BillActivity extends AppCompatActivity {
    LinearLayout scrollLayout;
    TextView tvFinalSell;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        scrollLayout = (LinearLayout) findViewById(R.id.lnBillScroll);
        tvFinalSell = (TextView) findViewById(R.id.tvBillFinalPrice);

        BillModel.GetAllSell threadSell = new BillModel.GetAllSell(this,tvFinalSell);
        threadSell.execute();

        try{
            BillModel.ShowAllBill threadShowBill = new BillModel.ShowAllBill(this,scrollLayout);
            threadShowBill.execute();
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    //ToolBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuAdd:
                Intent i = new Intent(this, AddProductActivity.class);
                startActivity(i);
                return true;
            case R.id.menuSell:
                Intent s = new Intent(this, AddBillActivity.class);
                startActivity(s);
                return true;
            case R.id.menuBill:
                Intent b = new Intent(this, BillActivity.class);
                startActivity(b);
                return true;
            case R.id.menuInfo:
                Intent f = new Intent(this, InfoActivity.class);
                startActivity(f);
                return true;
        }
        return true;
    }
}
