package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class FourthActivity extends AppCompatActivity {


    Button blueOne;
    Button blueTwo;
    Button blueThree;
    Locale language;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

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
        blueOne.setText("diciembre");
        blueTwo.setText("enero");
        blueThree.setText("febrero");
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(FourthActivity.this, new TextToSpeech.OnInitListener() {
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
    public void playAudio(View v){
        Button clickedButton = (Button) v;
        String viewTextAsString = clickedButton.getText().toString();
        textToSpeech.speak(viewTextAsString, TextToSpeech.QUEUE_FLUSH, null);
    }

}