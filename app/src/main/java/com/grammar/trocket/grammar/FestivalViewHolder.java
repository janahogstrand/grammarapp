package com.grammar.trocket.grammar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalViewHolder extends RecyclerView.ViewHolder {

    public ImageView festivalPicture;
    public TextView festivalName;
    private View view;

    FestivalViewHolder(View itemView){
        super(itemView);
        festivalPicture = (ImageView) itemView.findViewById(R.id.festival_picture);

        view = itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//        //Toast.make
//        Toast.makeText(view.getContext(), "Clicked Country Position = " + getPosition(), Toast.LENGTH_SHORT).show();
//    }
}
