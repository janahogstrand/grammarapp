package com.grammar.trocket.grammar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalTimeAdapter extends RecyclerView.Adapter<FestivalTimeViewHolder>  {

    private List<FestivalTimeItem> festivalData;

    FestivalTimeAdapter(List<FestivalTimeItem> festData){this.festivalData = festData;}


    @Override
    public FestivalTimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_festival, parent, false);
        FestivalTimeViewHolder viewHolder = new FestivalTimeViewHolder(v, festivalData);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FestivalTimeViewHolder holder, int position) {
        holder.festivalPicture.setImageResource(festivalData.get(position).getPhoto());
    }

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
