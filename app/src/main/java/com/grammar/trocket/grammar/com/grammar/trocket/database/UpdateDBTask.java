package com.grammar.trocket.grammar.com.grammar.trocket.database;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by ran on 16/03/16.
 */
public class UpdateDBTask extends AsyncTask<String, Void, String> {

        private Activity activity;
        public UpdateDBTask(Activity activity) {
            this.activity = activity;
        }

        private DatabaseOperations dbOps = new DatabaseOperations(activity);
//        ProgressDialog progDailog = new ProgressDialog(activity.getApplicationContext());

//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progDailog.setMessage("Loading...");
//            progDailog.setIndeterminate(false);
//            progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progDailog.setCancelable(true);
//            progDailog.show();
//        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);

                int data = isr.read();

                while(data != -1) {
                    result += (char) data;
                    data = isr.read();
                }

                dbOps.insertIntoDB(result, params[1]);

                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return result;
        }

//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            progDailog.dismiss();
//        }
    }
