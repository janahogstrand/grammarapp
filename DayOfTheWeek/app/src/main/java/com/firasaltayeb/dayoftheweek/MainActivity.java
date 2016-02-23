package com.firasaltayeb.dayoftheweek;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

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

    public void assignBtnText(){
        mondayBtn.setText("Lunes\nMonday");
        tuesdayBtn.setText("Martes\nTuesday");
        wednesdayBtn.setText("Miercoles\nWednesday");
        thursdayBtn.setText("Jueves\nThursday");
        fridayBtn.setText("Viernes\nFriday");
        saturdayBtn.setText("Sabado\nSaturday");
        sundayBtn.setText("Domingo\nSunday");
    }

    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(MainActivity.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    public void assignBtnSounds(){
        try {
            mondayPlayer = MediaPlayer.create(this, R.raw.monday); mondayMissing = false;
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

    public void mondayBtnClicked(View view){
        if(mondayMissing){
            textToSpeech.speak("Lunes", TextToSpeech.QUEUE_FLUSH, null, "");
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






}
