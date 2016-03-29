package com.grammar.trocket.grammingo.main.module_selection;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammingo.backend.GetJSON;
import com.grammar.trocket.grammingo.backend.TableNames;
import com.grammar.trocket.grammingo.main.BaseActivityDrawer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleSelection extends BaseActivityDrawer {

    private ArrayList<ModuleItem> moduleData;
    private ModuleAdapter moduleAdapter;
    public static Cursor result;
    public static String LANGUAGE = "com.grammar.trocket.grammar.com.grammar.trocket.main.language";
    public static String COURSE = "com.grammar.trocket.grammar.com.grammar.trocket.main.COURSE";
    private  String modulesString;
    //public static TableNames db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();

        GetJSON getModules = new GetJSON(ModuleSelection.this, TableNames.COURSE_TABLE);
        try {
            modulesString = getModules.execute().get();
            Log.w("Modules" , modulesString);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager glm = new LinearLayoutManager(ModuleSelection.this);
        rv.setLayoutManager(glm);


        moduleAdapter = new ModuleAdapter(getData(),ModuleSelection.this);
        rv.setAdapter(moduleAdapter);
    }

    private ArrayList<ModuleItem> getData() {
        moduleData = new ArrayList<ModuleItem>();

        try {
            JSONArray jsonArray = new JSONArray(modulesString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);
//                Log.w("JSON: ", jObject.toString());
//                Log.w("JSON name: ", jObject.get("name").toString());
                String name = jObject.get("name").toString();
                String creator = jObject.get("creator").toString();
                int id = Integer.parseInt(jObject.get("id").toString());
                moduleData.add(new ModuleItem(name, creator, id));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moduleData;
    }
}
