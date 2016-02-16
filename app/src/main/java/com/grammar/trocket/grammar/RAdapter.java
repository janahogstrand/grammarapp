package com.grammar.trocket.grammar;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.CategoryViewHolder> {

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView catName;
        TextView desc;
        ImageView icon;

        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            catName = (TextView)itemView.findViewById(R.id.category_name);
            desc = (TextView)itemView.findViewById(R.id.desc);
            icon = (ImageView)itemView.findViewById(R.id.icon);
        }
    }

    List<Category> categorys;

    RAdapter(List<Category> categorys){
        this.categorys = categorys;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        CategoryViewHolder pvh = new CategoryViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder categoryViewHolder, int i) {
        categoryViewHolder.catName.setText(categorys.get(i).name);
        categoryViewHolder.desc.setText(categorys.get(i).desc);
        categoryViewHolder.icon.setImageResource(categorys.get(i).icon);
    }

    @Override
    public int getItemCount() {
        return categorys.size();
    }
}