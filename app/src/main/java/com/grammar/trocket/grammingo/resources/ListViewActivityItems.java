package com.grammar.trocket.grammingo.resources;

import android.app.Activity;
import android.util.Log;

import com.grammar.trocket.grammingo.backend.GetJSON;
import com.grammar.trocket.grammingo.backend.TableNames;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by firasAltayeb on 09/03/2016.
 */
public class ListViewActivityItems {

    ArrayList<NumberCalendarItem> arrayList = new ArrayList<>();
    Activity activity;
    int callerId;

    public ListViewActivityItems(Activity activity, int callerId) {
        this.activity = activity;
        this.callerId = callerId;
    }

    public ArrayList<NumberCalendarItem> getArray(){

        String tapString = "";
        GetJSON getTap = new GetJSON(activity, TableNames.TAP_TABLE, "parentId", (callerId + ""));
        try {
            tapString = getTap.execute().get();
            Log.w("Categories", tapString);

            JSONArray jsonArray = new JSONArray(tapString);
            JSONObject tap = jsonArray.getJSONObject(0);
            String tapId = tap.get(TableNames.TAP_ID).toString();

            String tapItems = "";
            GetJSON getTaps = new GetJSON(activity, TableNames.TAPITEM_TABLE, "parentId", tapId);
            tapItems = getTaps.execute().get();
            Log.w("Festivals", tapItems);
            JSONArray tapArray = new JSONArray(tapItems);
            for(int i = 0; i< tapArray.length(); ++i){
                JSONObject jObject = tapArray.getJSONObject(i);
                String label = jObject.get(TableNames.TAPITEM_LABEL).toString();
                String pronunciation = jObject.get(TableNames.TAPITEM_PRONUNCIATION).toString();
                String url = jObject.get(TableNames.TAPITEM_AUDIOURL).toString();
                arrayList.add(new NumberCalendarItem(label,pronunciation,url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;
    }

}
