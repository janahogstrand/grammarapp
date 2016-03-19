package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

import java.io.IOException;
import java.util.ArrayList;
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
    // mediaPlayers arrayList will hold mediaPlayers who each are
    // assigned with a unique audio file
    ArrayList<MediaPlayer> mediaPlayers;
    boolean mondayMissing, tuesdayMissing, wednesdayMissing, thursdayMissing, fridayMissing,
            saturdayMissing, sundayMissing = false;
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

        mediaPlayers = new ArrayList<MediaPlayer>();
        mediaPlayers.add(mondayPlayer);  mediaPlayers.add(tuesdayPlayer);
        mediaPlayers.add(wednesdayPlayer);  mediaPlayers.add(thursdayPlayer);
        mediaPlayers.add(fridayPlayer);  mediaPlayers.add(saturdayPlayer);
        mediaPlayers.add(sundayPlayer);

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
            mondayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mondayPlayer.setDataSource("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
            mondayPlayer.prepare();
        }catch (Exception e){
            //mondayMissing = true;
            Log.d("111111111", "mondayMissing");
            Log.d("111111111", "mondayMissing");
            Log.d("111111111", "mondayMissing");
            Log.d("111111111", "mondayMissing");
            Log.d("111111111", "mondayMissing");
        }
        try {
            tuesdayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            tuesdayPlayer.setDataSource("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
//            tuesdayPlayer.prepare();
        }catch (Exception e){ tuesdayMissing = true; }
        try {
            wednesdayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            wednesdayPlayer.setDataSource("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
//            wednesdayPlayer.prepare();
        }catch (Exception e){ wednesdayMissing = true; }
        try {
            thursdayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            thursdayPlayer.setDataSource("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
//            thursdayPlayer.prepare();
        }catch (Exception e){ thursdayMissing = true; }
        try {
            fridayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            fridayPlayer.setDataSource("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
//            fridayPlayer.prepare();
        }catch (Exception e){ fridayMissing = true; }
        try {
            saturdayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            saturdayPlayer.setDataSource("");
//            saturdayPlayer.prepare();
        }catch (Exception e){ saturdayMissing = true; }
        try {
            sundayPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            sundayPlayer.setDataSource("");
//            sundayPlayer.prepare();
        }catch (Exception e){ sundayMissing = true; }

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
        if(!mondayMissing && view.getTag().toString().equals("mondayBtn")){
           // mondayPlayer.prepare();
            //mondayPlayer.start();
            Log.d("111111111", "mondayMissing");
            Log.d("111111111", "mondayMissing");
            Log.d(view.getTag().toString() , "mondayMissing");
            Log.d(view.getTag().toString() , "mondayMissing");
            Log.d("111111111", "mondayMissing");

        }
        else if(!tuesdayMissing && view.getTag().toString().equals("tuesdayBtn")){
//            tuesdayPlayer.prepare();
//            tuesdayPlayer.start();
        }
        else if(!wednesdayMissing && view.getTag().toString().equals("wednesdayBtn")){
//             wednesdayPlayer.prepare();
//             wednesdayPlayer.start();
        }
        else if(!thursdayMissing && view.getTag().toString().equals("thursdayBtn")){
//             thursdayPlayer.prepare();
//             tuesdayPlayer.start();
        }
        else if(!fridayMissing && view.getTag().toString().equals("fridayBtn")){
//            fridayPlayer.prepare();
//            fridayPlayer.start();
        }
        else if(!saturdayMissing && view.getTag().toString().equals("saturdayBtn")){
//            saturdayPlayer.prepare();
//            saturdayPlayer.start();
        }
        else if(!sundayMissing && view.getTag().toString().equals("sundayBtn")){
//            sundayPlayer.prepare();
//            sundayPlayer.start();
        }
        else {
            Log.d("111111111", "textToSpeech");
            Log.d("111111111", "textToSpeech");
            Log.d("111111111", "textToSpeech");
            Log.d("111111111", "textToSpeech");
            Log.d("111111111", "textToSpeech");
            String viewTextAsString = clickedButton.getText().toString();
            textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        for( MediaPlayer mediaPlayer : mediaPlayers ){
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    Log.d("mediaPlayer released ", " Player Destroyed");
                }
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
