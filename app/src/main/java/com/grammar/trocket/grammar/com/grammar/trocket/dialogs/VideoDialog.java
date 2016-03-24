package com.grammar.trocket.grammar.com.grammar.trocket.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Video;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.VideoItem;

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
        addListContent(context, options);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param options List of possible videos
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> options) {
        this.setTitle("Select a video");

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
        this.show();
    }


}