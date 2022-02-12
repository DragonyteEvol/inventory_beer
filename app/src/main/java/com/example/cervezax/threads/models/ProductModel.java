package com.example.cervezax.threads.models;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cervezax.MainActivity;
import com.example.cervezax.activities.DetailProductActivity;
import com.example.cervezax.database.AppDatabase;
import com.example.cervezax.database.entity.Product;
import com.example.cervezax.repositories.ProductImplement;
import com.example.cervezax.threads.controllers.ImageController;
import com.example.cervezax.views.AmountManager;
import com.example.cervezax.views.ProductCard;

import java.util.Hashtable;
import java.util.List;

public class ProductModel extends Activity {

    public static class ShowAllProduct extends AsyncTask<Void,Integer, List<Product>> {

        Context context;
        LinearLayout layout;
        LinearLayout objectiveLayout;

        public ShowAllProduct(Context context, LinearLayout layout) {
            this.context = context;
            this.layout = new LinearLayout(context);
            this.layout.setOrientation(LinearLayout.VERTICAL);
            objectiveLayout= layout;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resources = new ProductImplement(db.productDao());
            List<Product> allProducts = resources.getAllProducts();

            return allProducts;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            for (Product i : products){
                ProductCard view = new ProductCard(context,null,String.valueOf(i.getId()));
                view.setParams(i);
                layout.addView(view);
            }
            objectiveLayout.addView(layout);
        }
    }

    public static class ShowAllProductWhitManager extends AsyncTask<Void,Integer, List<Product>> {

        Context context;
        LinearLayout layout;
        LinearLayout objectiveLayout;
        Hashtable<String,TextView> views;

        public ShowAllProductWhitManager(Context context, LinearLayout layout,Hashtable<String,TextView> views) {
            this.context = context;
            this.layout = new LinearLayout(context);
            this.views = views;
            this.layout.setOrientation(LinearLayout.VERTICAL);
            objectiveLayout= layout;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resources = new ProductImplement(db.productDao());
            List<Product> allProducts = resources.getAllProducts();

            return allProducts;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            for (Product i : products){
                ProductCard view = new ProductCard(context,null,String.valueOf(i.getId()));
                view.setParams(i);
                view.setAmountManager(i,this.views);
                layout.addView(view);
            }
            objectiveLayout.addView(layout);
        }
    }

    public static class InsertProduct extends AsyncTask<Void,Integer,Void>{

        Product product;
        Context context;

        public InsertProduct(Context context,Product product) {
            this.product = product;
            this.context = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resource = new ProductImplement(db.productDao());
            resource.insertProduct(product);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context, "Producto Registrado", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public static class SearchProductsMain extends AsyncTask<Void,Integer,List<Product>>{

        Context context;
        String search;
        LinearLayout objectiveLayout,layout;

        public SearchProductsMain(Context context, String search, LinearLayout objectiveLayout) {
            this.context = context;
            this.search = search;
            this.objectiveLayout = objectiveLayout;

            this.layout = new LinearLayout(context);
            this.layout.setOrientation(LinearLayout.VERTICAL);
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            if(search == "" || search == null || search == "Buscar"){
               return null;
            }else{
                AppDatabase db = AppDatabase.getInstance(context);
                ProductImplement repositories = new ProductImplement(db.productDao());
                return repositories.searchProducts(search);
            }
        }


        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if(products ==null){
                return;
            }else {
                for (Product i : products){
                    ProductCard view = new ProductCard(context,null,String.valueOf(i.getId()));
                    view.setParams(i);
                    layout.addView(view);
                }
                objectiveLayout.removeAllViews();
                objectiveLayout.addView(layout);
            }
        }
    }

    public static class SearchProductsWhitManager extends AsyncTask<Void,Integer,List<Product>>{

        Context context;
        String search;
        LinearLayout objectiveLayout,layout;
        Hashtable<String,TextView> views;
        public SearchProductsWhitManager(Context context, String search, LinearLayout objectiveLayout,Hashtable<String,TextView> views) {
            this.context = context;
            this.search = search;
            this.objectiveLayout = objectiveLayout;
            this.views = views;
            this.layout = new LinearLayout(context);
            this.layout.setOrientation(LinearLayout.VERTICAL);
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            if(search == "" || search == null || search == "Buscar"){
                return null;
            }else{
                AppDatabase db = AppDatabase.getInstance(context);
                ProductImplement repositories = new ProductImplement(db.productDao());
                return repositories.searchProducts(search);
            }
        }


        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if(products ==null){
                return;
            }else {
                for (Product i : products){
                    ProductCard view = new ProductCard(context,null,String.valueOf(i.getId()));
                    view.setParams(i);
                    view.setAmountManager(i,this.views);
                    layout.addView(view);
                }
                objectiveLayout.removeAllViews();
                objectiveLayout.addView(layout);
            }
        }
    }

    public static class FindProductById extends AsyncTask<Void,Integer,Product>{

        public Product PRODUCT;

        private Context context;
        private int idProduct;
        private Hashtable<String, EditText> views;
        private ImageView image;

        public FindProductById(Context context, int idProduct, Hashtable<String,EditText> views, ImageView image) {
            this.context = context;
            this.idProduct = idProduct;
            this.views = views;
            this.image= image;
        }

        @Override
        protected Product doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resource = new ProductImplement(db.productDao());
            return resource.findProductById(idProduct);
        }

        @Override
        protected void onPostExecute(Product product) {
            super.onPostExecute(product);
            PRODUCT = product;
            views.get("name").setText(product.getName());
            views.get("presentation").setText(product.getPresentation());
            views.get("content").setText(String.valueOf(product.getContent()));
            views.get("price_buy").setText(String.valueOf(product.getPrice_buy()));
            views.get("price_sell").setText(String.valueOf(product.getPrice_sell()));
            views.get("uid").setText(String.valueOf(product.getUid()));
            views.get("stock").setText(String.valueOf(product.getStock()));
            views.get("id").setText(String.valueOf(product.getId()));
            views.get("image").setText(product.getImage());
            ImageController.DecodeImage decode = new ImageController.DecodeImage(product.getImage(),image);
            decode.execute();
        }
    }

    public static class DeleteProduct extends  AsyncTask<Void,Integer,Void>{

        private Context context;
        private int productId;

        public DeleteProduct(Context context, int productId) {
            this.context = context;
            this.productId = productId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resources = new ProductImplement(db.productDao());

            resources.deleteProductById(productId);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            try {
                Intent i = new Intent(context, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(context,"Producto Eliminado", Toast.LENGTH_LONG).show();
        }
    }

    public static class UpdateProduct extends AsyncTask<Void,Integer,Void>{
        private Context context;
        private Product product;

        public UpdateProduct(Context context, Product product) {
            this.context = context;
            this.product = product;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resources = new ProductImplement(db.productDao());
            resources.updateProduct(product);
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            Toast.makeText(context,"Producto Actualizado",Toast.LENGTH_LONG).show();
            Intent i = new Intent(context,MainActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    public static class ReStock extends AsyncTask<Void,Integer,Void>{
        private Context context;
        private int productId;
        private int stock;

        public ReStock(Context context, int productId, int stock) {
            this.context = context;
            this.productId = productId;
            this.stock = stock;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = AppDatabase.getInstance(context);
            ProductImplement resources = new ProductImplement(db.productDao());
            Product product = resources.findProductById(productId);
            int currentlyStock = product.getStock();
            product.setStock(currentlyStock - stock);
            resources.updateProduct(product);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(context,"OK",Toast.LENGTH_LONG).show();
        }
    }
}
