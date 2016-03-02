package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.R;

import java.util.List;


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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
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
    }

    /**
     * Returns categories size
     **/
    @Override
    public int getItemCount() {
        return categorys.size();
    }
}