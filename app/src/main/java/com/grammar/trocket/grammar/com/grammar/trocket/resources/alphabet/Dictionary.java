package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 19/03/16.
 * Activity that displays wordss
 */
public class Dictionary extends BaseActivityDrawer{

    private String dialect;
    private DictionaryAdapter dictionaryAdapter;
    private ArrayList<WordItem> wordList;
    public static TextToSpeech textToSpeech;

    private String letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main);
        super.onCreateDrawer();
        getDialect();
        Intent intent = getIntent();

        letter = intent.getStringExtra(Alphabet.LETTER);

        initTTS();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(Dictionary.this);
        rv.setLayoutManager(llm);


        dictionaryAdapter = new DictionaryAdapter(getData());
        rv.setAdapter(dictionaryAdapter);

        llm.scrollToPositionWithOffset(getIndex(), 35);
    }

    /**
     * Returns index of the first word
     * that matches the letter clicked
     * */
    private int getIndex(){
        int index = 0;
        for(WordItem word: wordList){
            String firstLetter = (word.getForeignWord().charAt(0) + "").toUpperCase();
            if(firstLetter.equals(letter)){
                break;
            }
            ++index;
            if(wordList.size() == index){
                Toast.makeText(this, "No words that begin with this letter", Toast.LENGTH_SHORT).show();
            }
        }
        return index;
    }

    //TODO database ORDER BY ASCENDING
    private ArrayList<WordItem> getData() {
        wordList = new ArrayList<WordItem>();

        wordList.add(new WordItem("Appleo", "Apple"));
        wordList.add(new WordItem("Antso", "Ants"));
        wordList.add(new WordItem("Bannano", "Bannanas"));
        wordList.add(new WordItem("Bee", "Bee"));
        wordList.add(new WordItem("Cato", "Cat"));
        wordList.add(new WordItem("Cowo", "Cow"));
        wordList.add(new WordItem("Doggo", "Dog"));
        wordList.add(new WordItem("Diggero", "Digger"));
        wordList.add(new WordItem("Eggo", "Eggs"));
        wordList.add(new WordItem("Eeee", "Eee"));
        wordList.add(new WordItem("Flyo", "Fly"));
        wordList.add(new WordItem("Froggo", "Frog"));
        wordList.add(new WordItem("GGgg", "G"));


        return wordList;
    }

    /**
     * Get dialect selected
     * **/
    public void getDialect(){
        //Intent intent = getIntent();
        //dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
    }


    /**
     * Sets textToSpeech language
     * **/
    private void initTTS(){
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(new Locale("Es", "es"));
            }
        });
    }

    /**
     * Destroy textToSpeech
     * **/
    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
