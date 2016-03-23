package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseHelper;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleSelection extends BaseActivityDrawer {

    private ArrayList<ModuleItem> moduleData;
    private ModuleAdapter moduleAdapter;
    public static Cursor result;
    public static String LANGUAGE = "com.grammar.trocket.grammar.com.grammar.trocket.main.language";
    public static String COURSE = "com.grammar.trocket.grammar.com.grammar.trocket.main.COURSE";

    public static DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();

        db = DatabaseHelper.getInstance(getApplicationContext());
        db.getWritableDatabase();

        Cursor result = db.selectDBTable(db.COURSE_TABLE);

        while (result.moveToNext()) {
            Log.i("Cursor", result.getString(result.getColumnIndex(db.COURSE_NAME)));
        }



        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager glm = new LinearLayoutManager(ModuleSelection.this);
        rv.setLayoutManager(glm);


        moduleAdapter = new ModuleAdapter(getData(),ModuleSelection.this);
        rv.setAdapter(moduleAdapter);

        //db.insertIntoTable(ModuleSelection.this);
    }

    private ArrayList<ModuleItem> getData() {
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
                moduleData = new ArrayList<ModuleItem>();
                result = db.selectDBTable(db.COURSE_TABLE);
                while (result.moveToNext()) {
                    moduleData.add(new ModuleItem(result.getString(result.getColumnIndex(db.COURSE_NAME)), result.getString(result.getColumnIndex(db.COURSE_CREATOR)), result.getInt(result.getColumnIndex(DatabaseHelper.COURSE_ID))));
                }
//            }
//        });
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        return moduleData;
    }
}
