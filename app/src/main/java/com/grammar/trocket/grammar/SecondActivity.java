package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    Button OrgOne;
    Button OrgTwo;
    Button OrgThree;
    Locale language;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

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
        textToSpeech=new TextToSpeech(SecondActivity.this, new TextToSpeech.OnInitListener() {
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
