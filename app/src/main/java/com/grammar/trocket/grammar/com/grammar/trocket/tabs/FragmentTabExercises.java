package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.content.Intent;
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
        categories.add(new Category("Greetings", "Learn basic greetings!", R.drawable.greetings, true));
        categories.add(new Category("Checking in", "Learn to check in", R.drawable.checking_in, true));
        categories.add(new Category("Sightseeing", "Learn to order!", R.drawable.sightseeing, true));
        categories.add(new Category("Directions", "Learn your way round", R.drawable.directions, false));
        categories.add(new Category("Eating", "Learn to ask for your favourite dishes!", R.drawable.eating, false));
        categories.add(new Category("Likes", "Talk about your likes", R.drawable.likes, false));
        categories.add(new Category("Planning", "Learn to plan", R.drawable.planning, false));
        categories.add(new Category("Shopping", "Learn about shopping", R.drawable.shopping, false));
        categories.add(new Category("Dating", "Find love abroad!", R.drawable.dating, false));
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