package com.grammar.trocket.grammingo.resources.festivalAndTime;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.grammar.trocket.grammar.R;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by jamiemoreland on 24/02/16.
 * Manages the festival and time on click
 *
 * @see FestivalTimeAdapter
 */
public class FestivalTimeViewHolder extends RecyclerView.ViewHolder {

    public ImageView festivalPicture;
    public TextView title;
    public TextView description;
    private View view;
    private List<FestivalTimeItem> data;
    public static TextToSpeech textToSpeech;
    Locale language = new Locale("es", "ES");
    public static MediaPlayer player;


    /**
     * Constructor adds on click
     **/
    public FestivalTimeViewHolder(View itemView, final List<FestivalTimeItem> data) {
        super(itemView);
        this.data = data;
        festivalPicture = (ImageView) itemView.findViewById(R.id.festival_picture);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.desc);

        view = itemView;


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get context
                stopAllSound();
                playAudio();
            }


        });
    }

    /**
     * Stops all current sound
     * If media player is running this will stop
     **/
    private void stopAllSound() {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.w("Player released", "Audio Released");
            }
        }
    }

    /**
     * Plays from audio file if not found
     * then plays textToSpeech
     **/
    private void playAudio() {
        String phrase = data.get(getAdapterPosition()).getSpanishName().toString();
        Log.i("Audio phrase", phrase);
        try {
            assignAudio(phrase);
        } catch (Exception e) {
            textToSpeech.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    /**
     * Database will assign correct audio files, for now I have used
     * if statements to simulate this
     * TODO database implementation
     *
     * @throws NullPointerException When no audio is found
     */
    private void assignAudio(String text) throws IOException {
        player = null;
        player.start();
    }


    /**
     * Plays audio for a given address
     *
     * @param address Address of audio URL
     **/
    private void setAudio(String address) throws IOException {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(address);
        player.prepare();
        player.start();
    }


}

