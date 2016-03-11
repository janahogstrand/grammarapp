package com.mycompany.audiotest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private ImageView image;
    private String imageAddress = "https://www.dropbox.com/s/5zmf04aoj3hr8ml/placeholder.png?raw=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //////////////// IMPORTANT /////////////////////
        // for Picasso to work, the jar must be in the libs folder
        // the file dependency must also be logged in the project structure
        // the image address needs to have dl=0 replaced with raw=1, the same as with the audio files
        image = (ImageView) findViewById(R.id.imageView);
        Context context = image.getContext();
        Picasso.with(context).load(imageAddress).into(image);

    }

    public void startPlayer(View view) {

        // to get dropbox address, take sharing url, and replace dl=0 with raw=1
        String address = "https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1";

        // create media player, set source, and start playing
        player = new MediaPlayer();
        try {
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(address);
            player.prepare();
            player.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
