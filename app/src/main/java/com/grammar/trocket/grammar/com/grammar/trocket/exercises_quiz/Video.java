package com.grammar.trocket.grammar.com.grammar.trocket.exercises_quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoObserveDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoReflectDialog;

public class Video extends YouTubeBaseActivity {

    private YouTubePlayerView player;
    private YouTubePlayer.OnInitializedListener listener;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        // get video address from previous activity
        Intent intent = getIntent();

        try {
            address = intent.getStringExtra(VideoReflectDialog.VIDEO_ADDRESS);
        } catch (Exception e){
            address = intent.getStringExtra(VideoObserveDialog.VIDEO_ADDRESS2);
        }

        // initialise player and listener
        player = (YouTubePlayerView) findViewById(R.id.view);
        listener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                // set fullscreen, load video address, and play video
                youTubePlayer.setFullscreen(true);
                youTubePlayer.loadVideo(address);
                youTubePlayer.play();
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                // Toast to notify user in case of initialisation failure
                Context context = getApplicationContext();
                CharSequence message = "Initialisation failed";
                int duration = Toast.LENGTH_LONG;

                Toast toast = Toast.makeText(context, message, duration);
                toast.show();
            }
        };

        // actually initialise  player
        player.initialize("AIzaSyCAJ0JmTuIlhQN68Zy44rzKbzJu5CKNEus",listener);

    }
}
