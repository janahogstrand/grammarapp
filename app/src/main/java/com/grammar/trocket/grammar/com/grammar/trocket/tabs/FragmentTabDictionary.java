package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.Category;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.DictionaryAlphabetAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.AlphabetItem;

import java.util.ArrayList;


public class FragmentTabDictionary extends Fragment {

    View view;
    private DictionaryAlphabetAdapter alphabetAdapter;
    private ArrayList<AlphabetItem> alphabetList;
    private SwipeRefreshLayout swipeContainer;
    public static Cursor letters;


    /**
     * Inflate fragment tab 3
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tab_dictionary, container, false);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setRefreshing(false);

        swipeListener(view);

        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(view.getContext(), 2);
        rv.setLayoutManager(glm);


        alphabetAdapter = new DictionaryAlphabetAdapter(getData(), "DictionaryItemsList");
        rv.setAdapter(alphabetAdapter);

        return view;
    }

    //TODO database
    private ArrayList<AlphabetItem> getData() {
        alphabetList = new ArrayList<AlphabetItem>();

//        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
//        letters = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'resource'", null);
//        //letters = MainMenu.db.selectDBTable(MainMenu.db.COURSE_TABLE);
//        while(letters.moveToNext()) {
//            //Log.i("Cursor2", letters.getString(letters.getColumnIndex(MainMenu.db.COURSE_NAME)) + letters.getColumnIndex(MainMenu.db.COURSE_NAME) + "" + letters.getColumnIndex(DatabaseHelper.COURSE_CREATOR) + "" + letters.getColumnIndex(DatabaseHelper.COURSE_ID) + "" );
//            //moduleData.add(new ModuleItem(letters.getString(letters.getColumnIndex(MainMenu.db.COURSE_NAME)), letters.getString(letters.getColumnIndex(MainMenu.db.COURSE_CREATOR)), letters.getColumnIndex(DatabaseHelper.COURSE_ID)));
//            Log.i("Category",  letters.getString(letters.getColumnIndex(MainMenu.db.CATEGORY_NAME)));
//
//        }

        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
        letters = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.DICTIONARYLETTER_TABLE + " WHERE " +  MainMenu.db.DICTIONARYLETTER_DICTIONARYID + " = " + MainMenu.DictionaryID
                + " ORDER BY "  + MainMenu.db.DICTIONARYLETTER_TABLE + "." + MainMenu.db.DICTIONARYLETTER_LABEL + " ASC", null);
        while(letters.moveToNext()) {
            Log.i("Letter1",  letters.getString(letters.getColumnIndex(MainMenu.db.DICTIONARYLETTER_COURSEID)));
            Log.i("Letter2", letters.getString(letters.getColumnIndex(MainMenu.db.DICTIONARYLETTER_LABEL)));

            String letter = letters.getString(letters.getColumnIndex(MainMenu.db.DICTIONARYLETTER_LABEL));
            alphabetList.add(new AlphabetItem(letter, true));
        }
        letters.move(-1);
        return alphabetList;
    }

    /**
     * Reloads main activity this time updating database
     * **/
    private void swipeListener(final View view){
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.w("Updating..", "Swiped clicked");
                MainMenu.db.onCreate(MainMenu.db.getWritableDatabase());
                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 2);
                view.getContext().startActivity(intent);
            }
        });

    }

}
