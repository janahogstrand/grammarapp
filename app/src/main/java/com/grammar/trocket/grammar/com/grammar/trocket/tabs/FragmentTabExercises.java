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

        initializeData();
        initializeAdapter();

        return v;
    }


    /**
     * Get data here
     * //TODO Make this interact with database
     **/
    private void initializeData() {
        categories = new ArrayList<>();
        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
        result = myDatabase.rawQuery(
                                    "SELECT * " +
                                    "FROM " + MainMenu.db.CATEGORY_TABLE +
                                    " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'exercise' " +
                                            "AND " + MainMenu.db.CATEGORY_PARENTID + " = " + MainMenu.ExerciseID + " ORDER BY " + MainMenu.db.CATEGORY_HIERARCHY + " ASC ", null);
        while(result.moveToNext()) {
            Log.i("Category",  result.getString(result.getColumnIndex(MainMenu.db.CATEGORY_NAME)));
            Log.i("Category", result.getString(result.getColumnIndex(MainMenu.db.CATEGORY_ICONURL)));

            String name = result.getString(result.getColumnIndex(MainMenu.db.CATEGORY_NAME));
            int catId = result.getInt(result.getColumnIndex(MainMenu.db.CATEGORY_ID));
            categories.add(new Category(name, "", R.drawable.ic_likes, true, catId));

        }
        result.move(-1);
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
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
                MainMenu.db.onCreate(MainMenu.db.getWritableDatabase());
                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 0);
                view.getContext().startActivity(intent);
            }
        });

    }

}