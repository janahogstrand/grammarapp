package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class FragmentTabResources extends Fragment {
    private List<Category> categories;
    private RecyclerView rv;

    /**
     * Inflae fragement tab 2
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab2, container, false);
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
        categories.add(new Category("Numbers", "Learn to count", R.drawable.ic_menu_manage, true));
        categories.add(new Category("Seasons", "Learn the seasons", R.drawable.ic_menu_slideshow, false));
        categories.add(new Category("Festivals", "Learn the festivals", R.drawable.ic_menu_gallery, true));
        categories.add(new Category("Calendar", "Learn how to read a calendar", R.drawable.ic_menu_camera, false));
        categories.add(new Category("Directions", "Learn to ask for directons", R.drawable.ic_menu_send, true));
        categories.add(new Category("Food & Drink", "Learn to ask for food and drinks", R.drawable.ic_menu_gallery, false));
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        RAdapter adapter = new RAdapter(categories);
        rv.setAdapter(adapter);
    }

}
