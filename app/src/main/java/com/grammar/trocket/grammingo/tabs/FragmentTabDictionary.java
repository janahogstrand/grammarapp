package com.grammar.trocket.grammingo.tabs;

import android.app.Activity;
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
import com.grammar.trocket.grammingo.backend.GetJSON;
import com.grammar.trocket.grammingo.backend.TableNames;
import com.grammar.trocket.grammingo.main.MainMenu;
import com.grammar.trocket.grammingo.resources.alphabetAndDictionary.DictionaryAlphabetAdapter;
import com.grammar.trocket.grammingo.resources.alphabetAndDictionary.AlphabetItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

        try {
            alphabetAdapter = new DictionaryAlphabetAdapter(getData(), "DictionaryItemsList");
            rv.setAdapter(alphabetAdapter);

        }catch (Exception e){

        }



        return view;
    }

    //TODO database
    private ArrayList<AlphabetItem> getData() {
        alphabetList = new ArrayList<AlphabetItem>();
        String letterString = "";
        GetJSON getLetters = new GetJSON((Activity) view.getContext(), TableNames.DICTIONARYLETTER_TABLE, "parentId", (MainMenu.DictionaryID + ""));
        try {
            letterString = getLetters.execute().get();
            Log.w("Categories", letterString);

            JSONArray jsonArray = new JSONArray(letterString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);
                String letter = jObject.get(TableNames.DICTIONARYLETTER_LABEL).toString();
                int id = Integer.parseInt(jObject.get(TableNames.DICTIONARYLETTER_ID).toString());
                alphabetList.add(new AlphabetItem(letter, true, id));
                Collections.sort(alphabetList,
                        new Comparator<AlphabetItem>() {
                            public int compare(AlphabetItem letter1, AlphabetItem letter2) {
                                return letter1.getLetter().toUpperCase().compareTo(letter2.getLetter().toUpperCase());
                            }
                        });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                //ModuleSelection.db.onCreate(ModuleSelection.db.getWritableDatabase());
                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 2);
                view.getContext().startActivity(intent);
            }
        });

    }

}
