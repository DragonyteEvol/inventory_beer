package com.example.cervezax.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;

import com.example.cervezax.BuildConfig;
import com.example.cervezax.R;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.threads.controllers.ImageController;
import com.example.cervezax.threads.models.ProductModel;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class AddProductActivity extends AppCompatActivity {

    private EditText name,presentation,content,price_buy,price_sell,uid,stock;
    private ImageView image;
    private String routeImage;
    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.etAddName);
        presentation= (EditText) findViewById(R.id.etAddPresentation);
        content= (EditText) findViewById(R.id.etAddContent);
        price_buy= (EditText) findViewById(R.id.etAddPriceBuy);
        price_sell= (EditText) findViewById(R.id.etAddPriceSell);
        uid= (EditText) findViewById(R.id.etAddUid);
        stock= (EditText) findViewById(R.id.etAddStock);
        image = (ImageView) findViewById(R.id.imgAdd);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    showCamera();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

       btnSave = (Button) findViewById(R.id.btnAddSave);
       btnSave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               insertProduct();
           }
       });
    }

    private void insertProduct(){
        ProductModel.InsertProduct newProduct = new ProductModel.InsertProduct(this,genProduct());
        newProduct.execute();
    }

    private Product genProduct(){
        Product product = new Product();
        product.setName(name.getText().toString());
        product.setPresentation(presentation.getText().toString());
        product.setContent(Integer.parseInt(content.getText().toString()));
        product.setPrice_buy(Integer.parseInt(price_buy.getText().toString()));
        product.setPrice_sell(Integer.parseInt(price_sell.getText().toString()));
        product.setUid(Integer.parseInt(uid.getText().toString()));
        product.setStock(Integer.parseInt(stock.getText().toString()));
        product.setImage(routeImage);
        return  product;
    }


    ///IMAGE/////

    private void showCamera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File img = null;
        try{
           img = createImage();
        }catch (IOException e){

        }
        if(img != null){
            Uri imgUri = FileProvider.getUriForFile(Objects.requireNonNull(getApplicationContext()),
                    BuildConfig.APPLICATION_ID + ".provider", img);
            i.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
            startActivityForResult(i,1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            ImageController.DecodeImage threadImage =new ImageController.DecodeImage(routeImage,image);
            threadImage.execute();
        }
    }

    private File createImage() throws IOException {
        String imageName = "img_";
        File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File img = File.createTempFile(imageName,".jpg",directory);
        routeImage = img.getAbsolutePath();
        return img;
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
