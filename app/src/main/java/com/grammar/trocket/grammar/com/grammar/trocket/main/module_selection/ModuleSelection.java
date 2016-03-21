package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class ModuleSelection extends BaseActivityDrawer {

    private ArrayList<ModuleItem> moduleData;
    private ModuleAdapter moduleAdapter;
    public static String LANGUAGE = "com.grammar.trocket.grammar.com.grammar.trocket.main.language";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager glm = new LinearLayoutManager(ModuleSelection.this);
        rv.setLayoutManager(glm);


        moduleAdapter = new ModuleAdapter(getData());
        rv.setAdapter(moduleAdapter);
    }

    //TODO database
    private ArrayList<ModuleItem> getData() {
        moduleData = new ArrayList<ModuleItem>();

        moduleData.add(new ModuleItem("Spanish", "Spanish", 0));
        moduleData.add(new ModuleItem("German", "German", 1));
        moduleData.add(new ModuleItem("French", "French", 2));

        return moduleData;
    }
}
