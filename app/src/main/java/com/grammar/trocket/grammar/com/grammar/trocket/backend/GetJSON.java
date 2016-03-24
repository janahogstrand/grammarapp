package com.grammar.trocket.grammar.com.grammar.trocket.backend;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jamiemoreland on 23/03/16.
 */
public class GetJSON extends AsyncTask<String, String, String> {

    private Activity activity;
    private String table;
    private String field;
    private String id;
    private ProgressDialog progressDialog;

    public GetJSON(Activity activity, String table, String field ,String id){
        this.activity = activity;
        this.table = table;
        this.field = field;
        this.id = id;
        this.progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        //progressDialog.show();
    }

    public GetJSON(Activity activity, String table, String field){
        this.activity = activity;
        this.table = table;
        this.field = field;
        this.id = "";
        this.progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        //progressDialog.show();
    }

    public GetJSON(Activity activity, String table){
        this.activity = activity;
        this.table = table;
        this.field = "";
        this.id = "";
        this.progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Fetching data...");
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        //progressDialog.show();
    }

        //private ProgressDialog progressDialog = new ProgressDialog(activity);
        InputStream inputStream = null;
        String result = "";


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        this.progressDialog = new ProgressDialog(activity);
//        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        Log.w("Progess Dialog", "...");



    }

    @Override
    protected String doInBackground(String... params) {
        URL url;
        HttpURLConnection urlConnection = null;
        String result = null;
        String jsonData = null;
        try {
            jsonData = "";
            if(field == ""){
                url = new URL("http://grammarapp.herokuapp.com/api/" + table + "/" + id);
                Log.w("Searching for :", "http://grammarapp.herokuapp.com/api/" + table + "/" + id);
            }else {
                url = new URL("http://grammarapp.herokuapp.com/api/" + table + "?" + field + "=" + id);
                Log.w("Searching for :", "http://grammarapp.herokuapp.com/api/" + table + "?" + field + "=" + id);
            }

            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = urlConnection.getInputStream();
            InputStreamReader isr = new InputStreamReader(in);

            int data = isr.read();

            while (data != -1) {
                jsonData += (char) data;
                data = isr.read();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //result = processJSON();
        //if(progressDialog.isShowing()){
            progressDialog.dismiss();
        //}
            return jsonData;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//        if(progressDialog.isShowing()){
//            progressDialog.dismiss();
//        }
    }
}
