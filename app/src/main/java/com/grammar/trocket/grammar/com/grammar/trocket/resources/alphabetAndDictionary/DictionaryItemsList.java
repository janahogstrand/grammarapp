package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;

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
    public static Cursor words;

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

        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
        words = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.DICTIONARYLETTER_TABLE +
                " JOIN " + MainMenu.db.DICTIONARYWORD_TABLE + " ON " + MainMenu.db.DICTIONARYLETTER_TABLE +"." + MainMenu.db.DICTIONARYLETTER_ID + " = " +  MainMenu.db.DICTIONARYWORD_TABLE + "." + MainMenu.db.DICTIONARYWORD_DICTIONARYLETTERID +
                " WHERE " +  MainMenu.db.DICTIONARYLETTER_DICTIONARYID + " = " + MainMenu.DictionaryID + " ORDER BY "  + MainMenu.db.DICTIONARYWORD_TABLE + "." + MainMenu.db.DICTIONARYWORD_LABEL + " ASC", null);


        while(words.moveToNext()) {
            Log.i("Letter1", words.getString(words.getColumnIndex(MainMenu.db.DICTIONARYLETTER_COURSEID)));
            Log.i("Letter2", words.getString(words.getColumnIndex(MainMenu.db.DICTIONARYLETTER_LABEL)));

            String word = words.getString(words.getColumnIndex(MainMenu.db.DICTIONARYWORD_LABEL));
            ;
            wordList.add(new DictionaryItem(word, ""));
        }
        words.move(-1);


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
