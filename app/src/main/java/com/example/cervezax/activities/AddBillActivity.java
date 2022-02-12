package com.example.cervezax.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cervezax.R;
import com.example.cervezax.database.entity.Bill;
import com.example.cervezax.threads.models.BillModel;
import com.example.cervezax.threads.models.ProductModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class AddBillActivity extends AppCompatActivity {
    private EditText etSearch;
    private Hashtable<String,TextView> views;
    private Button btnGenerateBill;
    private Integer finalPriceBill;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bill);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        this.views = generateTableViews();
        this.btnGenerateBill = (Button) findViewById(R.id.btnAddBillGenerate);

        btnGenerateBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    BillModel.InsertBill threadNewBill = new BillModel.InsertBill(getApplicationContext(),genBill());
                    threadNewBill.execute();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        this.views.get("tvFinalPrice").addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                btnGenerateBill.setVisibility(View.VISIBLE);
            }
        });



        LinearLayout layout = (LinearLayout) findViewById(R.id.lnAddBillScroll);
        try {
            ProductModel.ShowAllProductWhitManager thread = new ProductModel.ShowAllProductWhitManager(this, layout,views);
            thread.execute();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        etSearch = (EditText) findViewById(R.id.etAddBilSearch);
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
                ProductModel.SearchProductsWhitManager threadSearch = new ProductModel.SearchProductsWhitManager(getApplicationContext(), etSearch.getText().toString(), layout,views);
                threadSearch.execute();
            }
        });
    }

    private Hashtable<String,TextView> generateTableViews(){
        TextView tvListProduct = (TextView) findViewById(R.id.tvAddBillListProduct);
        TextView tvListPrice = (TextView) findViewById(R.id.tvAddBillListPrice);
        TextView tvTotal= (TextView) findViewById(R.id.tvAddBillTotal);
        TextView tvFinalPrice= (TextView) findViewById(R.id.tvAddBillFinalPrice);

        Hashtable<String,TextView> views = new Hashtable<>();
        views.put("tvListProduct",tvListProduct);
        views.put("tvListPrice",tvListPrice);
        views.put("tvTotal",tvTotal);
        views.put("tvFinalPrice",tvFinalPrice);
        return  views;
    }

    private Bill genBill(){
        Bill newBill = new Bill();
        newBill.setPrice(views.get("tvListPrice").getText().toString());
        newBill.setProduct(views.get("tvListProduct").getText().toString());
        newBill.setFinal_price(views.get("tvFinalPrice").getText().toString());
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateStr = dateFormat.format(date);
        newBill.setCreated_at(dateStr);
        return newBill;
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
