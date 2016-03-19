package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseOperations;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeItem;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleSelection extends BaseActivityDrawer {

    private ArrayList<ModuleItem> moduleData;
    private ModuleAdapter moduleAdapter;
    public static String LANGUAGE = "com.grammar.trocket.grammar.com.grammar.trocket.main.language";

    public static DatabaseOperations db;
    public Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main);
        super.onCreateDrawer();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager glm = new LinearLayoutManager(ModuleSelection.this);
        rv.setLayoutManager(glm);

        db = DatabaseOperations.getInstance(getApplicationContext());
        c = db.selectDBTable("Course");

        moduleAdapter = new ModuleAdapter(getData());
        rv.setAdapter(moduleAdapter);
    }

    //TODO database
    private ArrayList<ModuleItem> getData(){
        moduleData = new ArrayList<ModuleItem>();

        while(c.moveToNext()) {
            moduleData.add(new ModuleItem(c.getString(c.getColumnIndex("name")), Integer.parseInt(c.getString(c.getColumnIndex("_id")))));
        }

        return moduleData;
    }
}
