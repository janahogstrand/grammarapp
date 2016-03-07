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


public class FragmentTabResources extends Fragment {
    private List<Category> categories;
    private RecyclerView rv;

    /**
     * Inflate fragement tab 2
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_resources, container, false);
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
        categories.add(new Category("El Alfabeto", "Learn the alphabet", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("Los Numeros", "Learn the numbers", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("Los Dias", "Learn the days of the week", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("El Calendario", "Learn how to read a calendar", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("Festividades", "Learn about festivals", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("Estaciones y Meses", "Learn about the seasons", R.drawable.ic_menu_send, true, true));
        categories.add(new Category("La Hora", "Learn to tell the time", R.drawable.ic_menu_send, true, true));
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories);
        rv.setAdapter(adapter);
    }

}
