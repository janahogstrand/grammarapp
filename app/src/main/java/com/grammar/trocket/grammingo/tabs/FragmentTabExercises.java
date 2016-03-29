package com.grammar.trocket.grammingo.tabs;

import android.app.Activity;
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

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammingo.backend.GetJSON;
import com.grammar.trocket.grammingo.backend.TableNames;
import com.grammar.trocket.grammingo.main.MainMenu;
import com.grammar.trocket.grammingo.main.category.CardButton;
import com.grammar.trocket.grammingo.main.category.Category;
import com.grammar.trocket.grammingo.main.category.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FragmentTabExercises extends Fragment {

    private List<Category> categories;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private View view;
    public ArrayList<CardButton> cardButtonArrayList;

    /**
     * Inflate fragments tab 1
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_exercises, container, false);
        rv = (RecyclerView) v.findViewById(R.id.recycleView);
        view = v;
        swipeContainer = (SwipeRefreshLayout) v.findViewById(R.id.swipeContainer);
        swipeContainer.setRefreshing(false);


        swipeListener(v);

        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);

        try {
            initializeData();
            initializeAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }


    /**
     * Get data here
     * //TODO Make this interact with database
     **/
    private void initializeData() {
        categories = new ArrayList<>();
        String cardsString = "";
        //Get exercise and resource ids
        GetJSON getExerciseCards = new GetJSON((Activity) view.getContext(), TableNames.CATEGORY_TABLE, "parentId", (MainMenu.ExerciseID + ""));
        try {
            cardsString = getExerciseCards.execute().get();
            Log.w("Categories", cardsString);

            JSONArray jsonArray = new JSONArray(cardsString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);

                int id = Integer.parseInt(jObject.get("id").toString());
                String name = jObject.get("name").toString();
                int icon = getIcon(jObject.get("iconUrl").toString());
                Log.w("Exercise card: ", name + "  " + id + "  " + icon);
                int order = Integer.parseInt(jObject.get("hierarchy").toString());
                categories.add(new Category(name, "", icon, true, id, order));

                Collections.sort(categories);




                String catString;
                GetJSON getButtonCategories = new GetJSON((Activity) view.getContext(), TableNames.CATEGORY_TABLE, "parentId", (id + ""));
                try {
                    catString = getButtonCategories.execute().get();
                    cardButtonArrayList = new ArrayList<CardButton>();

                    JSONArray buttonArray = new JSONArray(catString);

                    JSONObject button2;
                    for (int k = 0; k < 3; ++k) {
                        try {
                            button2 = buttonArray.getJSONObject(k);
                            String myid = button2.get("id").toString();
                            Log.w("But id", myid);

                        } catch (JSONException e) {
                            Log.w("Could not find button", "Observe,reflect,experiment");
                            break;
                        }

                        String name2 = button2.get(TableNames.CATEGORY_NAME).toString();
                        int contentId = Integer.parseInt(button2.get(TableNames.CATEGORY_CONTENT).toString());
                        int myid = Integer.parseInt(button2.get("id").toString());
                        Log.w("But name", name2);
//                        int order2;
//                        int id2;
//                        int contentId;
//                        try {
//                            order2 = Integer.parseInt(button2.get(TableNames.CATEGORY_HIERARCHY).toString());
//                            id2 = Integer.parseInt(button2.get(TableNames.CATEGORY_ID).toString());
//                            contentId = Integer.parseInt(button2.get(TableNames.CATEGORY_CONTENT).toString());
//                        } catch (NumberFormatException e) {
//                            order2 = 0;
//                            id2 = 0;
//                            contentId = 0;
//                        }

                        cardButtonArrayList.add(new CardButton(name2, 0, myid, contentId));
                    }
                }catch (Exception e){

                }



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories, cardButtonArrayList);
        rv.setAdapter(adapter);
    }

    private int getIcon(String icon) {
        switch (icon) {
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
            case "R.drawable.ic_home":
                return R.drawable.ic_home;
            case "R.drawable.ic_travel":
                return R.drawable.ic_travel;
            case "R.drawable.ic_world":
                return R.drawable.ic_world;
        }
        return 0;
    }

    /**
     * Reloads main activity this time updating database
     **/
    private void swipeListener(final View view) {
        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.w("Updating..", "Swiped clicked");
                //ModuleSelection.db.onCreate(ModuleSelection.db.getWritableDatabase());
                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 0);
                view.getContext().startActivity(intent);
            }
        });

    }

}