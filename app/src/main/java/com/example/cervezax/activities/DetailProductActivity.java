package com.example.cervezax.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cervezax.R;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.threads.models.ProductModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ExecutionException;

public class DetailProductActivity extends AppCompatActivity {
    private EditText name,presentation,content,price_buy,price_sell,uid,stock;
    private ImageView image;
    private EditText imageText,id;
    private String idProduct;
    private Button btnUpdate;
    private ImageButton btnDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        Bundle data = getIntent().getExtras();
        idProduct = data.getString("id");

        name = (EditText) findViewById(R.id.etDetailName);
        presentation= (EditText) findViewById(R.id.etDetailPresentation);
        content= (EditText) findViewById(R.id.etDetailContent);
        price_buy= (EditText) findViewById(R.id.etDetailPriceBuy);
        price_sell= (EditText) findViewById(R.id.etDetailPriceSell);
        uid= (EditText) findViewById(R.id.etDetailUid);
        stock= (EditText) findViewById(R.id.etDetailStock);
        imageText = (EditText) findViewById(R.id.tvImageDetail);
        id = (EditText) findViewById(R.id.tvDetailIdProduct);

        image = (ImageView) findViewById(R.id.imgDetailImage);

        btnDelete = (ImageButton) findViewById(R.id.btnDetailDelete);
        btnUpdate= (Button) findViewById(R.id.btnDetailSave);

        ProductModel.FindProductById threadFind = new ProductModel.FindProductById(this,
                Integer.parseInt(idProduct),
                generateHashEditText(),
                image);
        threadFind.execute();
        Product product = threadFind.PRODUCT;

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductModel.DeleteProduct threadDelete = new ProductModel.DeleteProduct(getApplicationContext(),
                        Integer.parseInt(idProduct));
                threadDelete.execute();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    ProductModel.UpdateProduct threadUpdate = new ProductModel.UpdateProduct(getApplicationContext(),genUpdateProduct());
                    threadUpdate.execute();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    private Hashtable<String,EditText> generateHashEditText(){
        Hashtable<String,EditText> listEdit = new Hashtable<>();
        listEdit.put("name",name);
        listEdit.put("presentation",presentation);
        listEdit.put("content",content);
        listEdit.put("price_buy",price_buy);
        listEdit.put("price_sell",price_sell);
        listEdit.put("uid",uid);
        listEdit.put("stock",stock);
        listEdit.put("image",imageText);
        listEdit.put("id",id);
        return listEdit;
    }

    private Product genUpdateProduct(){
        Product updateProduct = new Product();
        updateProduct.setId(Integer.valueOf(id.getText().toString()));
        updateProduct.setName(name.getText().toString());
        updateProduct.setPresentation(presentation.getText().toString());
        updateProduct.setContent(Integer.valueOf(content.getText().toString()));
        updateProduct.setPrice_buy(Integer.valueOf(price_buy.getText().toString()));
        updateProduct.setPrice_sell(Integer.valueOf(price_sell.getText().toString()));
        updateProduct.setUid(Integer.valueOf(uid.getText().toString()));
        updateProduct.setStock(Integer.valueOf(stock.getText().toString()));
        updateProduct.setImage(imageText.getText().toString());
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateToStr = dateFormat.format(date);
        updateProduct.setCreated_at(dateToStr);
        return updateProduct;
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
