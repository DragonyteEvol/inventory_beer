package com.example.cervezax.threads;

import android.os.AsyncTask;

public class TaskAsync extends AsyncTask<Void,Integer,String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(Void... voids) {
        return null;
    }
}
