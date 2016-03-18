package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.grammar.trocket.grammar.R;

import java.io.IOException;
import java.util.Locale;

public class SeasonsSecondActivity extends AppCompatActivity {

    Button OrgOne;
    Button OrgTwo;
    Button OrgThree;
    Locale language;
    TextToSpeech textToSpeech;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_second);

        OrgOne = (Button) findViewById(R.id.option4);
        OrgTwo = (Button) findViewById(R.id.option5);
        OrgThree = (Button) findViewById(R.id.option6);

        assignText();
        assignLanguage();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        OrgOne.setText("junio");
        OrgTwo.setText("julio");
        OrgThree.setText("agosto");
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(SeasonsSecondActivity.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    /**
     * This method is ran when a button in this activity is clicked.
     * This method runs textToSpeech object's speak()
     * method which plays the clicked button's text.
     */
    //TODO Make database interact with this
    public void playAudio(View v){
        Button clickedButton = (Button) v;

        try {
            stopAllSound();
            switch (clickedButton.getText().toString()) {
                case "junio":
                    //player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio1);
                    setAudio("https://www.dropbox.com/s/7mga5icr0uweph/U01-E05.mp3?raw=1");
                    break;
                case "julio":
                    setAudio("https://www.dropbox.com/s/7mga5icr0uweph/U01-E05.mp3?raw=1");
                    break;
                case "agosto":
                    setAudio("https://www.dropbox.com/s/7mga5icr0uweph/U01-E05.mp3?raw=1");
                    break;
                default:
                    player = null;
            }
        } catch (Exception e) {
            String viewTextAsString = clickedButton.getText().toString();
            textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
        }


    }

    /**
     * Stops all current sound
     * If text to speech is running this will stop
     * If media player is running this will stop
     **/
    private void stopAllSound() {
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
    }

    /**
     * Plays audio for a given address
     * @param address Address of audio URL
     * **/
    private void setAudio(String address) throws IOException{
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(address);
        player.prepare();
        player.start();
    }

    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        this.finish();
        //Close the Text to Speech Library
        if(player != null){
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.d("Player released ", "Player Destroyed");
            }
        }
        //Close the Text to Speech Library
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");
        }
        super.onDestroy();
    }

}