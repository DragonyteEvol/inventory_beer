package com.example.cervezax.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.cervezax.R;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.threads.models.ProductModel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class AmountManager extends LinearLayout {

    private Button btnLess,btnPlus,btnAdd;
    private EditText etAmount;
    private Hashtable<String,TextView> textViews;
    private Product product;
    private static List<String> listProduct = new ArrayList();
    Context context;
    private static List<Integer> listPrice = new ArrayList<>();
    public AmountManager(Context context, @Nullable AttributeSet attrs, Product product, Hashtable<String,TextView> textViews) {
        super(context, attrs);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.view_amount_manager, this, true);

        this.product = product;
        this.textViews = textViews;
        this.context = context;

        btnLess = (Button) findViewById(R.id.btnAmountLess);
        btnPlus = (Button) findViewById(R.id.btnAmountPlus);
        btnAdd = (Button) findViewById(R.id.btnAmountAdd);
        etAmount = (EditText) findViewById(R.id.etAmountText);

        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                int currentlyValue = Integer.parseInt(etAmount.getText().toString());
                if (currentlyValue == product.getStock()){
                    return;
                }else{
                    int finalValue = currentlyValue + 1;
                    etAmount.setText(String.valueOf(finalValue));
                }
            }
        });

        btnLess.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                int currentlyValue = Integer.parseInt(etAmount.getText().toString());
                if (currentlyValue == 0){
                    return;
                }else{
                    int finalValue = currentlyValue - 1;
                    etAmount.setText(String.valueOf(finalValue));
                }

            }
        });

        btnAdd.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setDataBill();
                try{
                    ProductModel.ReStock threadStock = new ProductModel.ReStock(context,product.getId(),
                            Integer.parseInt(etAmount.getText().toString()));
                    threadStock.execute();
                }catch (Exception e ){
                    Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
                }
                etAmount.setText("0");
            }
        });
    }

    private void setDataBill(){
        try {
        //Products
        String mount = etAmount.getText().toString();
        String currentlyListProduct = textViews.get("tvListProduct").getText().toString();
        textViews.get("tvListProduct").setText(currentlyListProduct + "\n *" + mount + this.product.getName());
        listProduct.add(product.getName());

        //Prices
        String currentlyListPrice = textViews.get("tvListPrice").getText().toString();
        int groupPrice = this.product.getPrice_sell() * Integer.parseInt(mount);
        textViews.get("tvListPrice").setText(currentlyListPrice + "\n"+ groupPrice);
        listPrice.add(product.getPrice_sell() * Integer.parseInt(mount));

        //Final Price
        textViews.get("tvTotal").setText("Total: ");
            int finalPrice = 0;
            for (int i : listPrice){
                finalPrice = finalPrice + i;
            }
            textViews.get("tvFinalPrice").setText(String.valueOf(finalPrice));
        }catch (Exception e){
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
