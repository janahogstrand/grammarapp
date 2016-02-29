package com.grammar.trocket.grammar;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 * Adds on clicks for Categories
 * Opens DialectDialog with data
 * provided here
 *
 * @see DialectDialog
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView catName;
    TextView desc;
    ImageView icon;
    Button observe;
    Button reflect;
    Button experiment;
    public View view;
    public Category currentItem;
    List<Category> categories;
    int index;

    /**
     * Init views
     * Set on click listener
     **/
    CategoryViewHolder(View itemView, List<Category> categories) {
        super(itemView);
        this.categories = categories;
        cv = (CardView) itemView.findViewById(R.id.cv);
        catName = (TextView) itemView.findViewById(R.id.category_name);
        desc = (TextView) itemView.findViewById(R.id.desc);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        observe = (Button) itemView.findViewById(R.id.observe);
        reflect = (Button) itemView.findViewById(R.id.reflect);
        experiment = (Button) itemView.findViewById(R.id.experiment);
        view = itemView;
        checkResource();
        makeOnClicks();

    }

    private void checkResource(){
        if(categories.get(0).isResource) {
            observe.setVisibility(View.INVISIBLE);
            reflect.setVisibility(View.INVISIBLE);
            experiment.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * When card is clicked dialogs are opened
     **/
    private void makeOnClicks() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Card clicked
                //Check if dialog is needed
                if (currentItem.hasDialect) {
                    //TODO make this adapter get information from database
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            view.getContext(),
                            android.R.layout.select_dialog_singlechoice);
                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");

                    Context c = view.getContext();
                    Intent intent = new Intent();

                    //Load dialog with adapter
                    if(currentItem.name.equals("Times")){
                        intent = new Intent(c, Times.class);
                    }else{
                        intent = new Intent(c, Festivals.class);
                    }
                    DialectDialog d = new DialectDialog(view.getContext(), arrayAdapter, c, intent);

                }

            }
        });





//        Button observe = (Button) view.findViewById(R.id.observe);
//        observe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Times times = new Times();
//                Intent times = new Intent(v.getContext(), Times.class);
//                v.getContext().startActivity(times);
//            }
//        });
    }
    public void setIndex(int index){
        this.index = index;
    }

}