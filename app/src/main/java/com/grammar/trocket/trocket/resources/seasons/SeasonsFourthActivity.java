package com.grammar.trocket.trocket.resources.seasons;

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

public class SeasonsFourthActivity extends AppCompatActivity {


    Button blueOne;
    Button blueTwo;
    Button blueThree;
    Locale language;
    TextToSpeech textToSpeech;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_fourth);

        blueOne = (Button) findViewById(R.id.option10);
        blueTwo = (Button) findViewById(R.id.option11);
        blueThree = (Button) findViewById(R.id.option12);

        assignText();
        assignLanguage();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        blueOne.setText(SeasonsMain.cSubItemData.get(9).getName());
        blueTwo.setText(SeasonsMain.cSubItemData.get(10).getName());
        blueThree.setText(SeasonsMain.cSubItemData.get(11).getName());
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(SeasonsFourthActivity.this, new TextToSpeech.OnInitListener() {
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
        stopAllSound();
        try {
            if (clickedButton.equals(blueOne))
            {
                if(SeasonsMain.cSubItemData.get(9).getAudioUrl().equals("null")) {
                    String viewTextAsString = clickedButton.getText().toString();
                    textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    setAudio(SeasonsMain.cSubItemData.get(9).getAudioUrl());
                }
            } else if (clickedButton.equals(blueTwo))
            {
                if(SeasonsMain.cSubItemData.get(10).getAudioUrl().equals("null")) {
                    String viewTextAsString = clickedButton.getText().toString();
                    textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    setAudio(SeasonsMain.cSubItemData.get(10).getAudioUrl());
                }
            } else if (clickedButton.equals(blueThree))
            {
                if(SeasonsMain.cSubItemData.get(11).getAudioUrl().equals("null")) {
                    String viewTextAsString = clickedButton.getText().toString();
                    textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
                } else {
                    setAudio(SeasonsMain.cSubItemData.get(11).getAudioUrl());
                }
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