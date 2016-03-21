package com.grammar.trocket.grammar.com.grammar.trocket.tabs;

import android.content.Intent;
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
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.DictionaryAlphabetAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.AlphabetItem;

import java.util.ArrayList;


public class FragmentTabDictionary extends Fragment {

    View view;
    private DictionaryAlphabetAdapter alphabetAdapter;
    private ArrayList<AlphabetItem> alphabetList;
    private SwipeRefreshLayout swipeContainer;



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

        alphabetList.add(new AlphabetItem("A", true));
        alphabetList.add(new AlphabetItem("B", true));
        alphabetList.add(new AlphabetItem("C", true));
        alphabetList.add(new AlphabetItem("D", true));
        alphabetList.add(new AlphabetItem("E", true));
        alphabetList.add(new AlphabetItem("F", true));
        alphabetList.add(new AlphabetItem("G", true));
        alphabetList.add(new AlphabetItem("H", true));
        alphabetList.add(new AlphabetItem("I", true));
        alphabetList.add(new AlphabetItem("J", true));
        alphabetList.add(new AlphabetItem("K", true));
        alphabetList.add(new AlphabetItem("L", true));
        alphabetList.add(new AlphabetItem("M", true));
        alphabetList.add(new AlphabetItem("N", true));
        alphabetList.add(new AlphabetItem("O", true));
        alphabetList.add(new AlphabetItem("P", true));
        alphabetList.add(new AlphabetItem("Q", true));
        alphabetList.add(new AlphabetItem("R", true));
        alphabetList.add(new AlphabetItem("S", true));
        alphabetList.add(new AlphabetItem("T", true));
        alphabetList.add(new AlphabetItem("U", true));
        alphabetList.add(new AlphabetItem("W", true));
        alphabetList.add(new AlphabetItem("X", true));
        alphabetList.add(new AlphabetItem("Y", true));
        alphabetList.add(new AlphabetItem("Z", true));


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
