package com.grammar.trocket.grammingo.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammingo.exercises.Video;
import com.grammar.trocket.grammingo.exercises.VideoItem;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class VideoDialog extends AlertDialog.Builder {

    public final static String VIDEO_ADDRESS = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE2";

    Context context;
    Intent intent;
    ArrayList<VideoItem> videoList;
    JSONArray allVideoDetails;

    public VideoDialog(Context context, final ArrayAdapter<String> options, ArrayList<VideoItem> videoList, JSONArray allVideoDetails) {
        super(context);
        this.context = context;
        this.videoList = videoList;
        this.allVideoDetails = allVideoDetails;
        addTitle("Select a video");
        addListContent(context, options);
    }

    private void addTitle(String text){
        TextView title = new TextView(context);
        title.setText(text);
        title.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(40);
        title.setTypeface(null, Typeface.BOLD);
        setCustomTitle(title);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param options List of possible videos
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> options) {

        //Set button
        this.setNegativeButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        //New dialog once finished
        this.setAdapter(
                options,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        //intent = new Intent(context, Video.class);
                        // start new youtube activity and pass the video address to it

                        intent = new Intent(context, Video.class);
                        String address = videoList.get(position).getUrl();
                        intent.putExtra(VIDEO_ADDRESS, address);
                        context.startActivity(intent);
                    }
                });
        //Show dialog
        //this.show();
    }


}