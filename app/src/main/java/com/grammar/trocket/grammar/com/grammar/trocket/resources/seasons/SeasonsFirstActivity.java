package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.grammar.trocket.grammar.R;

import java.util.Locale;

public class SeasonsFirstActivity extends AppCompatActivity {

    Button pinkOne;
    Button pinkTwo;
    Button pinkThree;
    Locale language;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_first);

        pinkOne = (Button) findViewById(R.id.option1);
        pinkTwo = (Button) findViewById(R.id.option2);
        pinkThree = (Button) findViewById(R.id.option3);

        assignText();
        assignLanguage();

    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        pinkOne.setText("marzo");
        pinkTwo.setText("abril");
        pinkThree.setText("mayo");
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(SeasonsFirstActivity.this, new TextToSpeech.OnInitListener() {
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

    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        this.finish();
        //Close the Text to Speech Library
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");
        }
        super.onDestroy();
    }

}
