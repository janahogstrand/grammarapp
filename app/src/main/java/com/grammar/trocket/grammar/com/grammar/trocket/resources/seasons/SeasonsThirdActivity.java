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

public class SeasonsThirdActivity extends AppCompatActivity {


    Button redOne;
    Button redTwo;
    Button redThree;
    Locale language;
    TextToSpeech textToSpeech;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_third);

        redOne = (Button) findViewById(R.id.option7);
        redTwo = (Button) findViewById(R.id.option8);
        redThree = (Button) findViewById(R.id.option9);

        assignText();
        assignLanguage();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        redOne.setText("septiembre");
        redTwo.setText("octubre");
        redThree.setText("noviembre");
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(SeasonsThirdActivity.this, new TextToSpeech.OnInitListener() {
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
            setAudio("");
            stopAllSound();
            switch (clickedButton.getText().toString()) {
                case "septiembre":
                    //player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio1);
                    setAudio("https://dl.dropboxusercontent.com/1/view/55452sookpv6m84/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
                    break;
                case "octubre":
                    setAudio("https://dl.dropboxusercontent.com/1/view/55452sookpv6m84/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
                    break;
                case "noviembre":
                    setAudio("https://dl.dropboxusercontent.com/1/view/55452sookpv6m84/uploads/tap_item/audio/1/Evil_Laugh_1-Timothy-64737261.mp3");
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
     * Plays audio for a given address
     * @param address Address of audio URL
     * **/
    private void setAudio(String address){
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

