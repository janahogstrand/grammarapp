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
    TextToSpeech textToSpeech;
    Locale language = new Locale("es", "ES");
    MediaPlayer player;


    /**
     * Constructor adds on click
     **/
    FestivalTimeViewHolder(View itemView, final List<FestivalTimeItem> data) {
        super(itemView);
        this.data = data;
        festivalPicture = (ImageView) itemView.findViewById(R.id.festival_picture);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);

        view = itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get context
                Context context = v.getContext();
                initTTS(context);

            }

            private void initTTS(Context context) {
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
        });
    }

    /**
     * Plays from audio file if not found
     * then plays textToSpeech
     **/
    private void playAudio() {
        String phrase = data.get(getAdapterPosition()).getSpanishName().toString();
        try {
            assignAudio(phrase);
            //textToSpeech.speak(data.get(getAdapterPosition()).getSpanishName().toString(), TextToSpeech.QUEUE_FLUSH, null);
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
    private void assignAudio(String text) {
        stopAllSound();

        switch (text) {
            case "Son las doce en punto":
                setAudio("https://dl.dropboxusercontent.com/1/view/uv2fi3wdiz978wj/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
                break;
            case "El Indianas":
                setAudio("https://dl.dropboxusercontent.com/1/view/uv2fi3wdiz978wj/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
                break;
            case "Es Test":
                setAudio("https://dl.dropboxusercontent.com/1/view/uv2fi3wdiz978wj/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
                break;
            default:
                player = null;
        }
        player.start();
    }

    /**
     * Stops all current sound
     * If text to speech is running this will stop
     * If media player is running this will stop
     **/
    private void stopAllSound() {
        try {
            if (textToSpeech.isSpeaking()) {
                textToSpeech.stop();
            }
            if (player != null) {
                if (player.isPlaying()) {
                    player.stop();
                    player.reset();
                    player.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Stes audio up
     * */
    private void setAudio(String address) {
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

