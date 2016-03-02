package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.com.grammar.trocket.main.category.Category;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.CategoryAdapter;
import com.grammar.trocket.grammar.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabExercises extends Fragment {
    private List<Category> categories;
    private RecyclerView rv;

    /**
     * Inflae fragement tab 1
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_exercises, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycleView);

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
        categories.add(new Category("Greetings", "Learn basic greetings!", R.drawable.ic_menu_share, true));
        categories.add(new Category("Directions", "Learn you're way round", R.drawable.ic_menu_send, true));
        categories.add(new Category("Food & Drink", "Learn to order!", R.drawable.ic_menu_gallery, true));
        categories.add(new Category("Greetings", "Super super long sentence as a test to see how long the sentences should be roughly (In case clinet want a really really " +
                "long description)", R.drawable.ic_menu_share, true));
        categories.add(new Category("Directions", "25 years old", R.drawable.ic_menu_send, true));
        categories.add(new Category("Food & Drink", "35 years old", R.drawable.ic_menu_gallery, true));
        categories.add(new Category("Greetings", "Learn basic greetings!", R.drawable.ic_menu_share, true));
        categories.add(new Category("Directions", "Learn you're way round", R.drawable.ic_menu_send, true));
        categories.add(new Category("Food & Drink", "Learn to order!", R.drawable.ic_menu_gallery, true));
        categories.add(new Category("Greetings", "Super super long sentence as a test to see how long the sentences should be roughly (In case clinet want a really really " +
                "long description)", R.drawable.ic_menu_share, true));
        categories.add(new Category("Directions", "25 years old", R.drawable.ic_menu_send, true));
        categories.add(new Category("Food & Drink", "35 years old", R.drawable.ic_menu_gallery, true));
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
    }

}
