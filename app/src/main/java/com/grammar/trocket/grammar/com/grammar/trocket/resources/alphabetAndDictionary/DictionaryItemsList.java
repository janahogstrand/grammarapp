package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 19/03/16.
 * Activity that displays wordss
 */
public class DictionaryItemsList extends BaseActivityDrawer{

    private String dialect;
    private DictionaryItemsAdapter dictionaryAdapter;
    private ArrayList<DictionaryItem> wordList;
    public static TextToSpeech textToSpeech;

    private String letter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();
        getDialect();
        Intent intent = getIntent();

        letter = intent.getStringExtra(Alphabet.LETTER);

        initTTS();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(DictionaryItemsList.this);
        rv.setLayoutManager(llm);


        dictionaryAdapter = new DictionaryItemsAdapter(getData());
        rv.setAdapter(dictionaryAdapter);

        llm.scrollToPositionWithOffset(getIndex(), 35);
    }

    /**
     * Returns index of the first word
     * that matches the letter clicked
     * */
    private int getIndex(){
        int index = 0;
        for(DictionaryItem word: wordList){
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
    private ArrayList<DictionaryItem> getData() {
        wordList = new ArrayList<DictionaryItem>();

        wordList.add(new DictionaryItem("Appleo", "Apple"));
        wordList.add(new DictionaryItem("Antso", "Ants"));
        wordList.add(new DictionaryItem("Bannano", "Bannanas"));
        wordList.add(new DictionaryItem("Bee", "Bee"));
        wordList.add(new DictionaryItem("Cato", "Cat"));
        wordList.add(new DictionaryItem("Cowo", "Cow"));
        wordList.add(new DictionaryItem("Doggo", "Dog"));
        wordList.add(new DictionaryItem("Diggero", "Digger"));
        wordList.add(new DictionaryItem("Eggo", "Eggs"));
        wordList.add(new DictionaryItem("Eeee", "Eee"));
        wordList.add(new DictionaryItem("Flyo", "Fly"));
        wordList.add(new DictionaryItem("Froggo", "Frog"));
        wordList.add(new DictionaryItem("GGgg", "G"));


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
