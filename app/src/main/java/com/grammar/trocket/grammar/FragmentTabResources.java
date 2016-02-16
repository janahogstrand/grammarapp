package com.grammar.trocket.grammar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FragmentTabResources extends Fragment {
    private List<Category> categories;
    private RecyclerView rv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab1, container, false);
        rv = (RecyclerView)v.findViewById(R.id.recycleView);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();

        return v;
    }


    /**
     * Get data here
     * //TODO Make this interact with database
     * **/
    private void initializeData(){
        categories = new ArrayList<>();
        categories.add(new Category("Numbers", "23 years old", R.drawable.ic_menu_manage));
        categories.add(new Category("Seasons", "25 years old", R.drawable.ic_menu_slideshow));
        categories.add(new Category("Festivals", "35 years old", R.drawable.ic_menu_gallery));
        categories.add(new Category("Calendar", "23 years old", R.drawable.ic_menu_camera));
        categories.add(new Category("Directions", "25 years old", R.drawable.ic_menu_send));
        categories.add(new Category("Food & Drink", "35 years old", R.drawable.ic_menu_gallery));
    }

    /**
     * Set new adapter
     * **/
    private void initializeAdapter(){
        RAdapter adapter = new RAdapter(categories);
        rv.setAdapter(adapter);
    }

}
