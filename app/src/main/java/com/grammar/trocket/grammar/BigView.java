package com.grammar.trocket.grammar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 25/02/16.
 * Used for clock and festivals activities
 */
public class BigView extends AppCompatActivity {
    ImageView centerImage;
    ImageView nextImage;
    ImageView prevImage;
    TextView nextText;
    TextView prevText;
    TextView nameEnglish;
    TextView nameSpanish;
    FestivalTimeItem prev;
    FestivalTimeItem next;
    FestivalTimeItem current;
    ArrayList<FestivalTimeItem> dataFest;
    int index;
    TextToSpeech textToSpeech;
    Locale language;
    MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);

        Bundle data = getIntent().getExtras();
        dataFest = data.getParcelableArrayList("data");
        index = data.getInt("current");

        initViews();
        assignAssets();
        //assignLanguage();
        makeOnClicks();

        language = new Locale("es", "ES");


    }

    public void makeOnClicks() {
        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAssets(index + 1);
            }
        });
        prevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAssets(index - 1);
            }
        });
    }

    public void resetAssets(int newIndex){
        index = newIndex;
        assignAssets();
        testAudio();

    }

    public void assignAssets(){
        current = dataFest.get(index);
        centerImage.setImageResource(current.getPhoto());
        nameEnglish.setText(current.getEnglishName());
        nameSpanish.setText(current.getSpanishName());
        try {
            next = dataFest.get(index + 1);
            nextImage.setImageResource(next.getPhoto());
            nextImage.setVisibility(View.VISIBLE);
            nextText.setVisibility(View.VISIBLE);
        } catch (IndexOutOfBoundsException e) {
            nextImage.setVisibility(View.INVISIBLE);
            nextText.setVisibility(View.INVISIBLE);
        }
        try {
            prev = dataFest.get(index-1);
            prevImage.setImageResource(prev.getPhoto());
            prevImage.setVisibility(View.VISIBLE);
            prevText.setVisibility(View.VISIBLE);
        } catch (ArrayIndexOutOfBoundsException e) {
            prevImage.setVisibility(View.INVISIBLE);
            prevText.setVisibility(View.INVISIBLE);
        }
        testAudio();



    }

    public void initViews(){
        centerImage = (ImageView) findViewById(R.id.centerImage);
        nextImage = (ImageView) findViewById(R.id.next);
        prevImage = (ImageView) findViewById(R.id.previous);
        nextText = (TextView) findViewById(R.id.textView2);
        prevText = (TextView) findViewById(R.id.textView);
        nameEnglish = (TextView) findViewById(R.id.name_english);
        nameSpanish = (TextView) findViewById(R.id.name_spanish);
    }

    public void testAudio(){
        try{
            assignAudio(nameSpanish.getText().toString());
        }catch (Exception e){
            textToSpeech = new TextToSpeech(BigView.this, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    textToSpeech.setLanguage(language);
                    textToSpeech.speak(nameSpanish.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
                }
            });
        }
    }

    /** 
     * Database will assign correct audio files, for now I have used
     * if statements to simulate this
     */
    public void assignAudio(String text){
        try{
            textToSpeech.stop();
        }catch (Exception e){

        }
        try{
            player.stop();
        }catch (Exception e){

        }
        switch (text){
            case "Es Brits":  player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio1);
                break;
            case "El Indianas": player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio2);
                break;
            case "Es Test":  player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio3);
                break;
            default: player = null;
        }
        player.start();
    }


}
