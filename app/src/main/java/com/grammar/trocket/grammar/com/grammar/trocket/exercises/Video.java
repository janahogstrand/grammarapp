package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

public class Video extends BaseActivityDrawer {

    private String address;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_main);
        super.onCreateDrawer();

        // get video address from previous activity
        Intent intent = getIntent();

        try {
            address = intent.getStringExtra(VideoDialog.VIDEO_ADDRESS);
        } catch (Exception e){
            e.printStackTrace();
        }

        /* IMPORTANT
        - internet permissions must be enabled
        - forced landscape is in manifest - android:screenOrientation="landscape"
        - xml sets view to match parent in height and width for full screen
         */

        // set up video player
        videoView = (VideoView) findViewById(R.id.videoView);
        // get uri from address
        Uri uri = Uri.parse(address);
        // add playback functionality
        videoView.setMediaController(new MediaController(this));
        // play video
        videoView.setVideoURI(uri);
        videoView.start();

    }

}








