package com.firasaltayeb.listviewactivity;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String>  data;

    public ListViewAdapter(Activity activity, ArrayList<String> data) {
        this.activity = activity;
        this.data = data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if (data == null) return 0;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        if (data == null) return null;
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View recycledView, ViewGroup parent) {
        // try to use the recycled view, if there is one
        View output = recycledView;
        if (output == null) {
            // there wasn't a recycled view to work with, so create one from scratch
            LayoutInflater inflater = activity.getLayoutInflater();
            output = inflater.inflate(com.firasaltayeb.listviewactivity.R.layout.list_view_row, parent, false);
        }

        // get hold of the TextView
        TextView textView = (TextView) output.findViewById(com.firasaltayeb.listviewactivity.R.id.name);

        // get the name to display
        String name = (String) getItem(position);

        // put the name in the TextView
        textView.setText(name);

        if (position%2==0) {
            textView.setBackgroundColor(Color.RED);
        } else {
            textView.setBackgroundColor(Color.TRANSPARENT);
        }


        return output;
    }
}





















