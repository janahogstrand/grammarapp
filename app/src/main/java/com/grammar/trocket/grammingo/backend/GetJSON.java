package com.grammar.trocket.grammingo.backend;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

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

    public GetJSON(Activity activity, String table, String field ,String id){
        this.activity = activity;
        this.table = table;
        this.field = field;
        this.id = id;

    }

    public GetJSON(Activity activity, String table, String field){
        this.activity = activity;
        this.table = table;
        this.field = field;
        this.id = "";
    }

    public GetJSON(Activity activity, String table){
        this.activity = activity;
        this.table = table;
        this.field = "";
        this.id = "";
    }

        //private ProgressDialog progressDialog = new ProgressDialog(activity);
        InputStream inputStream = null;
        String result = "";

//    private String processJSON(){
//        String jsonQueryResult = null;
//        if(!jsonData.equals("[]")) {
//            try {
//                JSONArray jsonArray = new JSONArray(jsonData);
//                Log.i("Columns", table);
//                Log.i("JSON", (Integer.toString(jsonArray.getJSONObject(0).length())));
//
//                for (int j = 0; j < jsonArray.length(); ++j) {
//                    JSONObject jObject = jsonArray.getJSONObject(j);
//                    Log.w("JSON: ", jObject.toString());
//                    //Log.w("JSON name: ", jObject.get("name").toString());
//                    jsonQueryResult = jObject.toString();
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//                Log.i("Result", "No JSON data at api link");
//                return null;
//            }
//        } else {
//            Log.i("Result", "No JSON data at api link");
//            return null;
//        }
//        return jsonQueryResult;
//    }

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
        } catch (Exception e){
            e.printStackTrace();
        }
        //result = processJSON();
            return jsonData;

    }
}
