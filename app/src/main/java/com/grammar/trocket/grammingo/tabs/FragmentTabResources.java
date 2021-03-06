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
import com.grammar.trocket.grammingo.main.category.Category;
import com.grammar.trocket.grammingo.main.category.CategoryAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class FragmentTabResources extends Fragment {
    private List<Category> categories;
    private RecyclerView rv;
    private SwipeRefreshLayout swipeContainer;
    private View view;

    /**
     * Inflate fragement tab 2
     * Set recycle view for card view
     **/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tab_resources, container, false);
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

        }
        return v;
    }


    /**
     * Get data here
     **/
    private void initializeData() {
        categories = new ArrayList<>();
        String cardsString = "";
        //Get exercise and resource ids
        GetJSON getExerciseCards = new GetJSON((Activity) view.getContext(), TableNames.CATEGORY_TABLE, "parentId", (MainMenu.ResourcesID + ""));
        try {
            cardsString = getExerciseCards.execute().get();
            Log.w("Categories", cardsString);

            JSONArray jsonArray = new JSONArray(cardsString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);

                int id = Integer.parseInt(jObject.get("id").toString());
                int contentId = Integer.parseInt(jObject.get("content").toString());
                String name = jObject.get("name").toString();
                int icon = getIcon(jObject.get("iconUrl").toString());
                int order = Integer.parseInt(jObject.get("hierarchy").toString());
                categories.add(new Category(name, "", icon, true, true, id, contentId, order));

                Collections.sort(categories);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set new adapter
     **/
    private void initializeAdapter() {
        CategoryAdapter adapter = new CategoryAdapter(categories, null);
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
        return R.drawable.ic_menu_send;
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
//                ModuleSelection.db.onCreate(ModuleSelection.db.getWritableDatabase());

                Intent intent = new Intent(view.getContext(), MainMenu.class);
                intent.putExtra(MainMenu.TAB_SELECT, 1);
                view.getContext().startActivity(intent);
            }
        });

    }

}
