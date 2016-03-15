package com.mycompany.videoviewtest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    private String address = "https://dl.dropboxusercontent.com/1/view/6mdpypt5nxb3b7u/uploads/cluster_item/image/2/videoplaceholder.mp4";
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
