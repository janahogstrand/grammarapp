package com.grammar.trocket.grammar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalAdapter extends RecyclerView.Adapter<FestivalViewHolder>  {

    private List<FestivalItem> festivalData;

    FestivalAdapter(List<FestivalItem> festData){this.festivalData = festData;}


    @Override
    public FestivalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_festival, parent, false);
        FestivalViewHolder viewHolder = new FestivalViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FestivalViewHolder holder, int position) {
        //holder.festivalName.setText(festivalData.get(position).getName());
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
