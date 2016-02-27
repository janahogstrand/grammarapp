package com.grammar.trocket.grammar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalTimeViewHolder extends RecyclerView.ViewHolder {

    public ImageView festivalPicture;
    public TextView festivalName;
    private View view;
    private List<FestivalTimeItem> data;

    FestivalTimeViewHolder(View itemView, final List<FestivalTimeItem> data){
        super(itemView);
        this.data = data;
        festivalPicture = (ImageView) itemView.findViewById(R.id.festival_picture);

        view = itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked Country Position = " + data.get(getAdapterPosition()).getSpanishName(), Toast.LENGTH_SHORT).show();



                Context context = v.getContext();
                Intent bigView = new Intent(context, BigView.class);

                Bundle b = new Bundle();

                int current = getAdapterPosition();

                ArrayList<FestivalTimeItem> dataFest = new ArrayList<FestivalTimeItem>(data);
                bigView.putParcelableArrayListExtra("data", dataFest);
                b.putInt("current", current);


                bigView.putExtras(b);

                context.startActivity(bigView);

            }
        });

    }

}
