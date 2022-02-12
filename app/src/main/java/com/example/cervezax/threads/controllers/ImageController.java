package com.example.cervezax.threads.controllers;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageController {

    public static class DecodeImage extends AsyncTask<Void,Integer,Bitmap>{

        private String photoPath;
        private ImageView imageView;

        public static Bitmap rotateImage(Bitmap source, float angle) {
            Matrix matrix = new Matrix();
            matrix.postRotate(angle);
            return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
        }

        public Bitmap decodeImage(String currentPhotoPath){
            Bitmap imageBitmap = BitmapFactory.decodeFile(currentPhotoPath);
            Bitmap resizedImage = Bitmap.createScaledBitmap(imageBitmap,135,100,false);
            return rotateImage(resizedImage,90);
        }

        public DecodeImage(String photoPath, ImageView imageView) {
            this.photoPath = photoPath;
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap bitmap = decodeImage(photoPath);
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
