package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import java.io.IOException;
import java.util.Locale;


public class DaysOfTheWeek extends BaseActivityDrawer {


    String dialect;
    Button mondayBtn;
    Button tuesdayBtn;
    Button wednesdayBtn;
    Button thursdayBtn;
    Button fridayBtn;
    Button saturdayBtn;
    Button sundayBtn;
    TextToSpeech textToSpeech;
    Locale language;
    MediaPlayer player;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daysoftheweek_main_menu);
        super.onCreateDrawer();

        mondayBtn = (Button) findViewById(R.id.mondayBtn);
        tuesdayBtn = (Button) findViewById(R.id.tuesdayBtn);
        wednesdayBtn = (Button) findViewById(R.id.wednesdayBtn);
        thursdayBtn = (Button) findViewById(R.id.thursdayBtn);
        fridayBtn = (Button) findViewById(R.id.fridayBtn);
        saturdayBtn = (Button) findViewById(R.id.saturdayBtn);
        sundayBtn = (Button) findViewById(R.id.sundayBtn);


        assignBtnText();
        assignLanguage();
   }


    /** 
     * This method assigns text to the View/Buttons with the target 
     * language word in the first line and the English version 
     * in the second line. 
     */
    public void assignBtnText(){
        mondayBtn.setText("Lunes\nMonday");
        tuesdayBtn.setText("Martes\nTuesday");
        wednesdayBtn.setText("Miercoles\nWednesday");
        thursdayBtn.setText("Jueves\nThursday");
        fridayBtn.setText("Viernes\nFriday");
        saturdayBtn.setText("Sabado\nSaturday");
        sundayBtn.setText("Domingo\nSunday");
    }

    /** 
     * This method determines which language will be used by the textToSpeech API/object 
     * and then sets it for the textToSpeech instance/variable. 
     */
    public void assignLanguage(){
        Intent intent = getIntent();

        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
        if(dialect.equals("Spanish")){
            language = new Locale("es", "ES");
        }
        else {
            language = new Locale("es", "US");
        }

        textToSpeech=new TextToSpeech(DaysOfTheWeek.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }



    /** 
     * This method is invoked when the associated button is clicked, the methods checks 
     * whether the audio is missing or not, if the audio is missing the speak method plays 
     * the string it received as an argument, if the audio is not missing the audio 
     *  will be played using the start method. 
     *  @param view 
     */
    public void weekBtnClicked(View view) throws IOException{
        Button clickedButton = (Button) view;
        stopAllSound();
        try {
            if (view.getTag().toString().equals("mondayBtn"))
            {
                setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
            } else if (view.getTag().toString().equals("tuesdayBtn"))
            {
                setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
            } else if (view.getTag().toString().equals("wednesdayBtn"))
            {
                setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
            } else if (view.getTag().toString().equals("thursdayBtn"))
            {
                setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
            } else if (view.getTag().toString().equals("fridayBtn"))
            {
                setAudio("");
            } else if (view.getTag().toString().equals("saturdayBtn"))
            {
                setAudio("");
            } else if (view.getTag().toString().equals("sundayBtn"))
            {
                setAudio("");
            }
        }
        catch (Exception e)
        {
            String viewTextAsString = clickedButton.getText().toString();
            textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
        }
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

    private void setAudio(String address) throws IOException {
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
