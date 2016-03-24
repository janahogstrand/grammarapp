package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * An adapter that inflates cards, binds data and attaches
 * items to the recycle viewers
 */
public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    //List of data, in this case categories
    List<Category> categorys;

    public CategoryAdapter(List<Category> categorys) {
        this.categorys = categorys;
    }

    /**
     * Attach to recycle view
     **/
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    /**
     * Apply card view with ViewHolder
     **/
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_category, viewGroup, false);
        CategoryViewHolder cvh = new CategoryViewHolder(v, categorys);
        return cvh;
    }


    /**
     * Add values
     **/
    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.catName.setText(categorys.get(i).name);
        categoryViewHolder.desc.setText(categorys.get(i).desc);
        categoryViewHolder.icon.setImageResource(categorys.get(i).icon);
        categoryViewHolder.currentItem = categorys.get(i);

        //If an exercise rename buttons
        if (!categoryViewHolder.categories.get(i).isResource) {
            int catID = categorys.get(i).id;

            String catString;
            GetJSON getButtonCategories = new GetJSON((Activity) categoryViewHolder.view.getContext(), TableNames.CATEGORY_TABLE, "parentId", (catID + ""));
            try {
                catString = getButtonCategories.execute().get();
                //Log.w("buttons", catString);

                //SET INVISIBLE
                categoryViewHolder.observe.setVisibility(View.INVISIBLE);
                categoryViewHolder.reflect.setVisibility(View.INVISIBLE);
                categoryViewHolder.experiment.setVisibility(View.INVISIBLE);

                ArrayList<CardButton> cardButtonArrayList = new ArrayList<CardButton>();

                JSONArray jsonArray = new JSONArray(catString);


                for (int k = 0; k < 3; ++k) {
                    JSONObject button = jsonArray.getJSONObject(k);
                    String name = button.get(TableNames.CATEGORY_NAME).toString();
                    int order;
                    try {
                        order = Integer.parseInt(button.get(TableNames.CATEGORY_HIERARCHY).toString());
                    } catch (NumberFormatException e) {
                        order = 0;
                    }

                    cardButtonArrayList.add(new CardButton(name, order));
                }

                Collections.sort(cardButtonArrayList);

                for (int j = 0; j < cardButtonArrayList.size(); ++j) {
                    //JSONObject jObject = jsonArray.getJSONObject(j);
                    String name = cardButtonArrayList.get(j).getName();
                    if (j == 0) {
                        categoryViewHolder.observe.setText(name);
                        categoryViewHolder.observe.setVisibility(View.VISIBLE);
                    } else if (j == 1) {
                        categoryViewHolder.reflect.setText(name);
                        categoryViewHolder.reflect.setVisibility(View.VISIBLE);
                    } else if (j == 2) {
                        categoryViewHolder.experiment.setText(name);
                        categoryViewHolder.experiment.setVisibility(View.VISIBLE);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Returns categories size
     **/
    @Override
    public int getItemCount() {
        return categorys.size();
    }
}