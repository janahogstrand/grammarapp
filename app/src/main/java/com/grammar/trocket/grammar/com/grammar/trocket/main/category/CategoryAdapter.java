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
        CategoryViewHolder cvh = new CategoryViewHolder(v, categorys, null);

        if(!categorys.get(i).isResource){
            int catID = categorys.get(i).id;
            //If an exercise rename buttons

            String catString;
            GetJSON getButtonCategories = new GetJSON((Activity) v.getContext(), TableNames.CATEGORY_TABLE, "parentId", (catID + ""));
            try {
                catString = getButtonCategories.execute().get();
                ArrayList<CardButton> cardButtonArrayList = new ArrayList<CardButton>();

                JSONArray jsonArray = new JSONArray(catString);


                for (int k = 0; k < 3; ++k) {
                    JSONObject button = jsonArray.getJSONObject(k);
                    String name = button.get(TableNames.CATEGORY_NAME).toString();
                    int order;
                    int id;
                    int contentId;
                    try {
                        order = Integer.parseInt(button.get(TableNames.CATEGORY_HIERARCHY).toString());
                        id = Integer.parseInt(button.get(TableNames.CATEGORY_ID).toString());
                        contentId = Integer.parseInt(button.get(TableNames.CATEGORY_CONTENT).toString());
                    } catch (NumberFormatException e) {
                        order = 0;
                        id = 0;
                        contentId = 0;
                    }

                    cardButtonArrayList.add(new CardButton(name, order, id, contentId));
                }

                Collections.sort(cardButtonArrayList);



                cvh = new CategoryViewHolder(v, categorys, cardButtonArrayList);

                for (int j = 0; j < cardButtonArrayList.size(); ++j) {
                    Log.w("Card order", cardButtonArrayList.get(j).getOrder() + ""  + "-" + cardButtonArrayList.get(j).getName());
                    //JSONObject jObject = jsonArray.getJSONObject(j);
                    String name = cardButtonArrayList.get(j).getName();
                    if (j == 0) {
                        cvh.observe.setText(name);
                        cvh.observe.setVisibility(View.VISIBLE);
                    } else if (j == 1) {
                        cvh.reflect.setText(name);
                        cvh.reflect.setVisibility(View.VISIBLE);
                    } else if (j == 2) {
                        cvh.experiment.setText(name);
                        cvh.experiment.setVisibility(View.VISIBLE);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            if (categorys.get(i).isResource) {
                cvh.observe.setVisibility(View.INVISIBLE);
                cvh.reflect.setVisibility(View.INVISIBLE);
                cvh.experiment.setVisibility(View.INVISIBLE);
            }
        }

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
        categoryViewHolder.contentType = categorys.get(i).contentId;

    }

    /**
     * Returns categories size
     **/
    @Override
    public int getItemCount() {
        return categorys.size();
    }
}