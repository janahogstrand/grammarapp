package com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.grammar.trocket.grammar.R;

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
     * @param festData festival or time objects with data
     * @see FestivalTimeAdapter
     */
    public FestivalTimeAdapter(List<FestivalTimeItem> festData) {
        this.festivalData = festData;
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
        holder.festivalPicture.setImageResource(festivalData.get(position).getPhoto());
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
