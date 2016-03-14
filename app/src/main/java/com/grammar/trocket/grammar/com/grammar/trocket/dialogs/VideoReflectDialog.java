package com.grammar.trocket.grammar.com.grammar.trocket.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Video;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class VideoReflectDialog extends AlertDialog.Builder {

    public final static String VIDEO_ADDRESS = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE2";

    Context context;
    Intent intent;


    public VideoReflectDialog(Context context, final ArrayAdapter<String> reflectOptions) {
        super(context);
        this.context = context;
        addListContent(context, reflectOptions);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param reflectOptions List of possible videos
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> reflectOptions) {
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
                reflectOptions,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        //intent = new Intent(context, Video.class);
                        // start new youtube activity and pass the video address to it

                        intent = new Intent(context, Video.class);
                        String address = "qyXTgqJtoGM";
                        intent.putExtra(VIDEO_ADDRESS, address);
                        context.startActivity(intent);
                    }
                });
        //Show dialog
        this.show();
    }


}