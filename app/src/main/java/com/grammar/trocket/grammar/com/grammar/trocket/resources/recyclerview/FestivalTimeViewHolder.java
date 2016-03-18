package com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview;

import android.content.Context;
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
                Context context = v.getContext();
                initTTS(context);

            }


        });
    }


    private void initTTS(Context context) {
        //stopAllSound();
        textToSpeech = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(language);
                //For first TTS played
                if (status == TextToSpeech.SUCCESS) {
                    playAudio();
                }
            }
        });
    }

    /**
     * Plays from audio file if not found
     * then plays textToSpeech
     **/
    private void playAudio() {
        String phrase = data.get(getAdapterPosition()).getSpanishName().toString();
        //stopAllSound();
        try {
            assignAudio(phrase);
            //destroyAudio();
        } catch (Exception e) {
            textToSpeech.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
            //destroyAudio();
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
        //stopAllSound();

        switch (text) {
            case "Son las doce en punto":
                setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
                break;
            case "El Indianas":
                setAudio("https://www.dropbox.com/s/7mga5icr0uweph/U01-E05.mp3?raw=1");
                break;
            case "Es Test":
                setAudio("https://www.dropbox.com/s/7mga5icr0uweph/U01-E05.mp3?raw=1");
                break;
            default:
                player = null;
        }
        player.start();
    }

    /**
     * Stops all current sound
     * If media player is running this will stop
     **/
    private void stopAllSound() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.w("Player released", "TTS Shutdown");
        }
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

