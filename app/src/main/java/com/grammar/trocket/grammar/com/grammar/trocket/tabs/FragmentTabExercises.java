package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.Category;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.CategoryAdapter;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabExercises extends Fragment {

    private List<Category> categories;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    public static Cursor result;

    /**
     * Inflate fragments tab 1
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_exercises, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycleView);

        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setRefreshing(false);


        swipeListener(v);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        try {
            initializeData();
            initializeAdapter();
        }catch (Exception e){

        }

        return v;
    }


    /**
     * Get data here
     * //TODO Make this interact with database
     **/
    private void initializeData() {
        categories = new ArrayList<>();
//        SQLiteDatabase myDatabase = ModuleSelection.db.getWritableDatabase();
//        result = myDatabase.rawQuery(
//                                    "SELECT * " +
//                                    "FROM " + ModuleSelection.db.CATEGORY_TABLE +
//                                    " WHERE " + ModuleSelection.db.CATEGORY_KIND + " = 'exercise' " +
//                                            "AND " + ModuleSelection.db.CATEGORY_PARENTID + " = " + MainMenu.ExerciseID + " ORDER BY " + ModuleSelection.db.CATEGORY_HIERARCHY + " ASC ", null);
//        while(result.moveToNext()) {
//            Log.i("Category",  result.getString(result.getColumnIndex(ModuleSelection.db.CATEGORY_NAME)));
//            Log.i("Category", result.getString(result.getColumnIndex(ModuleSelection.db.CATEGORY_ICONURL)));
//
//            String name = result.getString(result.getColumnIndex(ModuleSelection.db.CATEGORY_NAME));
//            int catId = result.getInt(result.getColumnIndex(ModuleSelection.db.CATEGORY_ID));
//            String icon = result.getString(result.getColumnIndex(ModuleSelection.db.CATEGORY_ICONURL));
//            categories.add(new Category(name, "", getIcon(icon), true, catId));
//        }

        result.move(-1);
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
    }

    private int getIcon(String icon){
        switch (icon){
            case "R.drawable.ic_greetings":
                return R.drawable.ic_greetings;
            case "R.drawable.ic_checking_in":
                return R.drawable.ic_checking_in;
            case "R.drawable.ic_directions":
                return R.drawable.ic_directions;
            case "R.drawable.ic_sightseeing":
                return R.drawable.ic_sightseeing;
            case "R.drawable.ic_eating":
                return R.drawable.ic_eating;
            case "R.drawable.ic_likes":
                return R.drawable.ic_likes;
            case "R.drawable.ic_planning":
                return R.drawable.ic_planning;
            case "R.drawable.ic_dating":
                return R.drawable.ic_dating;
            case "R.drawable.ic_shopping":
                return R.drawable.ic_shopping;
        }
        return 0;
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
                Log.w("Updating.." , "Swiped clicked");
                //ModuleSelection.db.onCreate(ModuleSelection.db.getWritableDatabase());
                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 0);
                view.getContext().startActivity(intent);
            }
        });

    }

}