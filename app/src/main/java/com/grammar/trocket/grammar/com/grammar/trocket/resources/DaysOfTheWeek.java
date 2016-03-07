package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

import java.util.Locale;


public class DaysOfTheWeek extends BaseActivityDrawer {

    Button mondayBtn;
    Button tuesdayBtn;
    Button wednesdayBtn;
    Button thursdayBtn;
    Button fridayBtn;
    Button saturdayBtn;
    Button sundayBtn;
    MediaPlayer mondayPlayer, tuesdayPlayer, wednesdayPlayer, thursdayPlayer, fridayPlayer,
                saturdayPlayer, sundayPlayer;
    boolean mondayMissing, tuesdayMissing, wednesdayMissing, thursdayMissing, fridayMissing,
            saturdayMissing, sundayMissing;
    TextToSpeech textToSpeech;
    Locale language;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.daysoftheweek_main_menu);
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
        assignBtnSounds();
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
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(DaysOfTheWeek.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    /** 
     * This method determines whether an audio file is found or not when a search is done, 
     * if the audio file is not available the correspondent boolean variable 
     * is assigned as true thus indicating that textToSpeech should be used in its case. 
     */
    public void assignBtnSounds(){
        try {
            mondayPlayer = MediaPlayer.create(this, R.raw.placeholderaudio1); mondayMissing = false;
        }catch (Exception e){ mondayMissing = true; }
        try {
            //tuesdayPlayer = MediaPlayer.create(this, R.raw.tuesday); tuesdayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ tuesdayMissing = true; }
        try {
            //wednesdayPlayer = MediaPlayer.create(this, R.raw.wednesday); wednesdayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ wednesdayMissing = true; }
        try {
            //thursdayPlayer = MediaPlayer.create(this, R.raw.thursday); thursdayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ thursdayMissing = true; }
        try {
            //fridayPlayer = MediaPlayer.create(this, R.raw.friday); fridayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ fridayMissing = true; }
        try {
            //saturdayPlayer = MediaPlayer.create(this, R.raw.saturday); saturdayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ saturdayMissing = true; }
        try {
            //sundayPlayer = MediaPlayer.create(this, R.raw.sunday); sundayMissing = false;
            throw new RuntimeException("exception");
        }catch (Exception e){ sundayMissing = true; }

    }


    //---------------------------------------------------------------------------------------------- 
    // All the BtnClicked methods below share the same comment

    /** 
     * This method is invoked when the associated button is clicked, the methods checks 
     * whether the audio is missing or not, if the audio is missing the speak method plays 
     * the string it received as an argument, if the audio is not missing the audio 
     *  will be played using the start method. 
     *  @param view 
     */
    public void mondayBtnClicked(View view){
        if(mondayMissing){
            textToSpeech.speak("Lunes", TextToSpeech.QUEUE_FLUSH, null);
        }else { mondayPlayer.start();}
    }

    public void tuesdayBtnClicked(View view){
        if(tuesdayMissing){
            textToSpeech.speak("Martes", TextToSpeech.QUEUE_FLUSH, null);
        }else { tuesdayPlayer.start();}
    }

    public void wednesdayBtnClicked(View view){
        if(wednesdayMissing){
            textToSpeech.speak("Miercoles", TextToSpeech.QUEUE_FLUSH, null);
        }else { wednesdayPlayer.start();}
    }

    public void thursdayBtnClicked(View view){
        if(thursdayMissing){
            textToSpeech.speak("Jueves", TextToSpeech.QUEUE_FLUSH, null);
        }else { thursdayPlayer.start();}
    }

    public void fridayBtnClicked(View view){
        if(fridayMissing){
            textToSpeech.speak("Viernes", TextToSpeech.QUEUE_FLUSH, null);
        }else { fridayPlayer.start();}
    }

    public void saturdayBtnClicked(View view){
        if(saturdayMissing){
            textToSpeech.speak("Sabado", TextToSpeech.QUEUE_FLUSH, null);
        }else { saturdayPlayer.start();}
    }

    public void sundayBtnClicked(View view){
        if(sundayMissing){
            textToSpeech.speak("Domingo", TextToSpeech.QUEUE_FLUSH, null);
        }else { sundayPlayer.start();}
    }

    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        //Close the Text to Speech Library

        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");
        }
        super.onDestroy();
    }

}
