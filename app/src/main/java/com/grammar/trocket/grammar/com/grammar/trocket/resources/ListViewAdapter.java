package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<NumberCalendarItem>  data;

    public ListViewAdapter(Activity activity, ArrayList<NumberCalendarItem> data) {
        this.activity = activity;
        this.data = data;
    }

    public void setData(ArrayList<NumberCalendarItem> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }

    @Override
    public NumberCalendarItem getItem(int position) {
        if (data == null) return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    /**
     * This method creates a view for every object/item in the data arrayList.
     * The view's design is based on the view in the "list_view_row" layout file.
     * After the view is found in the "list_view_row" layout file, the view's
     * gravity and background will change to match the requested design.
     * @param position
     * @param recycledView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        // try to use the recycled view, if there is one
        View output = recycledView;
        if (output == null) {
            // there wasn't a recycled view to work with, so create one from scratch
            LayoutInflater inflater = activity.getLayoutInflater();
            output = inflater.inflate(R.layout.activity_list_view_row, parent, false);
        }
        // get hold of the TextView
        TextView textView = (TextView) output.findViewById(R.id.name);

        // get the name to display
        NumberCalendarItem numCalItem = getItem(position);
        String name = numCalItem.getLabel();
        String pronunciation = numCalItem.getPronunciation();

        // put the name in the TextView
        textView.setText(name + "\n" + pronunciation);
        textView.setGravity(Gravity.CENTER);

        if (position%2==0) {
            textView.setBackgroundResource(R.drawable.quiz_rounded_button_primary);
        } else {
            textView.setBackgroundResource(R.drawable.quiz_rounded_button_secondary);
        }

        return output;
    }
}





















