package com.grammar.trocket.trocket.resources.festivalAndTime;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 * An adapter that inflates cards, binds data and attaches
 * items to the recycle viewers
 */
public class FestivalTimeAdapter extends RecyclerView.Adapter<FestivalTimeViewHolder> {

    private List<FestivalTimeItem> festivalData;

    /**
     * Gives data so a new view can be inflated with this data
     *
     * @param data festival or time objects with data
     * @see FestivalTimeAdapter
     */
    public FestivalTimeAdapter(List<FestivalTimeItem> data) {
        this.festivalData = data;
    }

    /**
     * Inflates card festival which is a layout
     * for the time/festival view
     *
     * @see FestivalTimeViewHolder
     **/
    @Override
    public FestivalTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_festival, parent, false);
        FestivalTimeViewHolder viewHolder = new FestivalTimeViewHolder(v, festivalData);

        return viewHolder;
    }

    /**
     * Binds photo to card view
     **/
    @Override
    public void onBindViewHolder(FestivalTimeViewHolder holder, int position) {
        //holder.festivalPicture.setImageResource();
        ImageView image =  holder.festivalPicture;
        TextView title = holder.title;
        TextView desc = holder.description;
        Context context = image.getContext();
        Picasso.with(context)
                .load(festivalData.get(position).getPhoto())
                .error(android.R.drawable.stat_notify_error)
                .placeholder(R.drawable.loading_animation)
                .into(holder.festivalPicture);


        title.setText(festivalData.get(position).getSpanishName());
        //desc.setText(festivalData.get(position).getEnglishName());
    }

    /**
     * Returns festivalData size
     **/
    @Override
    public int getItemCount() {
        return festivalData.size();
    }

    /**
     * Attach to recycle view
     **/
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
