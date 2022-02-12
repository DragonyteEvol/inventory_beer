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
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.threads.controllers.ImageController;

import java.util.Hashtable;


public class ProductCard extends LinearLayout {
    private TextView name,presentation,content,stock,priceSell;
    private ImageView image;
    private Context context;
    private LinearLayout layout;

    public ProductCard(Context context, @Nullable AttributeSet attrs,String id) {
        super(context, attrs);
        this.context = context;

        final View inflate = LayoutInflater.from(context).inflate(R.layout.view_card_product, this, true);

        name = (TextView) findViewById(R.id.nameBeer);
        presentation = (TextView) findViewById(R.id.presentationBeer);
        content= (TextView) findViewById(R.id.contentBeer);
        stock= (TextView) findViewById(R.id.stockBeer);
        priceSell= (TextView) findViewById(R.id.priceBeer);
        image = (ImageView) findViewById(R.id.imageBeer);
        layout = (LinearLayout) findViewById(R.id.lCardLayout);

       this.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(context, DetailProductActivity.class);
                    i.putExtra("id",id);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                    Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setParams(Product data){
        this.name.setText(data.getName());
        this.presentation.setText(data.getPresentation());
        this.content.setText(String.valueOf(data.getContent()));
        this.stock.setText(String.valueOf(data.getStock()));
        this.priceSell.setText(String.valueOf(data.getPrice_sell()));
        try{
            ImageController.DecodeImage threadImage = new ImageController.DecodeImage(data.getImage(),this.image);
            threadImage.execute();
        }catch (Exception e){
            Log.e("Error", e.getMessage());
        }
    }

    public void setAmountManager(Product product, Hashtable<String,TextView> views){
        AmountManager amountManager = new AmountManager(context,null,product,views);
        layout.addView(amountManager);
        priceSell.setTextSize(1,16);
    }
}
