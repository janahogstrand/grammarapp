package com.grammar.trocket.grammar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 * Manages the festival and time on click
 *
 * @see FestivalTimeAdapter
 */
public class FestivalTimeViewHolder extends RecyclerView.ViewHolder {

    public ImageView festivalPicture;
    public TextView festivalName;
    private View view;
    private List<FestivalTimeItem> data;


    /**
     * Constructor adds on click
     **/
    FestivalTimeViewHolder(View itemView, final List<FestivalTimeItem> data) {
        super(itemView);
        this.data = data;
        festivalPicture = (ImageView) itemView.findViewById(R.id.festival_picture);

        view = itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get context
                Context context = v.getContext();
                //New intent
                Intent bigView = new Intent(context, BigView.class);
                Bundle b = new Bundle();
                //Current position
                int current = getAdapterPosition();
                //All items
                ArrayList<FestivalTimeItem> dataFest = new ArrayList<FestivalTimeItem>(data);
                //Send items over
                bigView.putParcelableArrayListExtra("data", dataFest);
                //Current position
                b.putInt("current", current);

                //Start activity with data
                bigView.putExtras(b);
                context.startActivity(bigView);

            }
        });

    }

}
