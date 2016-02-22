package com.grammar.trocket.grammar;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RAdapter extends RecyclerView.Adapter<RAdapter.CategoryViewHolder> {

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView catName;
        TextView desc;
        ImageView icon;
        public View view;
        public Category currentItem;

        /**
         * Get ids
         * Set on click listener
         **/
        CategoryViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            catName = (TextView) itemView.findViewById(R.id.category_name);
            desc = (TextView) itemView.findViewById(R.id.desc);
            icon = (ImageView) itemView.findViewById(R.id.icon);
            view = itemView;
            view.setOnClickListener(new View.OnClickListener(){
                @Override public void onClick(View v){
                    //Card clicked

                    //TODO make this adapter get information from database
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            view.getContext(),
                            android.R.layout.select_dialog_singlechoice);
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");
                    
                    //Load dialog with adapter
                    DialectDialog d = new DialectDialog(view.getContext(), arrayAdapter);
                }
            });
        }
    }

    //List of data, in this case categories
    List<Category> categorys;

    RAdapter(List<Category> categorys) {
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
        CategoryViewHolder cvh = new CategoryViewHolder(v);
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

    @Override
    public int getItemCount() {
        return categorys.size();
    }
}