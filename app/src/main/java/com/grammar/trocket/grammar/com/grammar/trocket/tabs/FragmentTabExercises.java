package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseOperations;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.Category;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.CategoryAdapter;
import com.grammar.trocket.grammar.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabExercises extends Fragment {

    private List<Category> categories;
    private RecyclerView rv;

    public static DatabaseOperations db;
    public Cursor c;
    public String categoryId = getArguments().getString("exercisesId");

    /**
     * Inflate fragments tab 1
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_exercises, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycleView);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        db = DatabaseOperations.getInstance(getContext());
        c = db.selectDBTable("Category", "WHERE categoryId='" + categoryId + "'");

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

        while(c.moveToNext()) {
            categories.add(new Category(c.getString(c.getColumnIndex("name")), c.getString(c.getColumnIndex("description")), Integer.parseInt(c.getString(c.getColumnIndex("iconURL"))), Integer.parseInt(c.getString(c.getColumnIndex("hasDialect"))), c.getString(c.getColumnIndex("_id"))));
        }

//        categories.add(new Category("Greetings", "Learn basic greetings!", R.drawable.greetings, true));
//        categories.add(new Category("Checking in", "Learn to check in", R.drawable.checking_in, true));
//        categories.add(new Category("Sightseeing", "Learn to order!", R.drawable.sightseeing, true));
//        categories.add(new Category("Directions", "Learn your way round", R.drawable.directions, false));
//        categories.add(new Category("Eating", "Learn to ask for your favourite dishes!", R.drawable.eating, false));
//        categories.add(new Category("Likes", "Talk about your likes", R.drawable.likes, false));
//        categories.add(new Category("Planning", "Learn to plan", R.drawable.planning, false));
//        categories.add(new Category("Shopping", "Learn about shopping", R.drawable.shopping, false));
//        categories.add(new Category("Dating", "Find love abroad!", R.drawable.dating, false));
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
    }

}