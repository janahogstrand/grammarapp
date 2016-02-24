package com.grammar.trocket.grammar;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

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
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Card clicked
                //Check if dialog is needed
                if(currentItem.hasDialect){
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

            }
        });
    }
}