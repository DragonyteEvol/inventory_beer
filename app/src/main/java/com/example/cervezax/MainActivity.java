package com.example.cervezax;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.example.cervezax.activities.AddBillActivity;
import com.example.cervezax.activities.AddProductActivity;
import com.example.cervezax.activities.BillActivity;
import com.example.cervezax.activities.DetailProductActivity;
import com.example.cervezax.activities.InfoActivity;
import com.example.cervezax.database.AppDatabase;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.repositories.ProductImplement;
import com.example.cervezax.threads.models.ProductModel;
import com.example.cervezax.views.ProductCard;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        LinearLayout layout = (LinearLayout) findViewById(R.id.lnMainScroll);
        try {
            ProductModel.ShowAllProduct thread = new ProductModel.ShowAllProduct(this, layout);
            thread.execute();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        etSearch = (EditText) findViewById(R.id.etMainSearch);
        etSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                etSearch.setText("");
            }
        });

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                ProductModel.SearchProductsMain threadSearch = new ProductModel.SearchProductsMain(getApplicationContext(), etSearch.getText().toString(), layout);
                threadSearch.execute();
            }
        });
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