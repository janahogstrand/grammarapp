package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    private Activity activity;

    private String letter;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();
        this.activity = DictionaryItemsList.this;
        getDialect();
        Intent intent = getIntent();

        letter = intent.getStringExtra(Alphabet.LETTER);
        id = intent.getIntExtra("getletterid", -1);

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

//        SQLiteDatabase myDatabase = ModuleSelection.db.getWritableDatabase();
//        words = myDatabase.rawQuery("SELECT * FROM " + ModuleSelection.db.DICTIONARYLETTER_TABLE +
//                " JOIN " + ModuleSelection.db.DICTIONARYWORD_TABLE + " ON " + ModuleSelection.db.DICTIONARYLETTER_TABLE +"." + ModuleSelection.db.DICTIONARYLETTER_ID + " = " +  ModuleSelection.db.DICTIONARYWORD_TABLE + "." + ModuleSelection.db.DICTIONARYWORD_DICTIONARYLETTERID +
//                " WHERE " +  ModuleSelection.db.DICTIONARYLETTER_DICTIONARYID + " = " + MainMenu.DictionaryID + " ORDER BY "  + ModuleSelection.db.DICTIONARYWORD_TABLE + "." + ModuleSelection.db.DICTIONARYWORD_LABEL + " ASC", null);
//
//
//        while(words.moveToNext()) {
//            Log.i("Letter1", words.getString(words.getColumnIndex(ModuleSelection.db.DICTIONARYLETTER_COURSEID)));
//            Log.i("Letter2", words.getString(words.getColumnIndex(ModuleSelection.db.DICTIONARYLETTER_LABEL)));
//
//            String word = words.getString(words.getColumnIndex(ModuleSelection.db.DICTIONARYWORD_LABEL));
//            wordList.add(new DictionaryItem(word, ""));
//        }
        String wordString = "";
        GetJSON getWords = new GetJSON(activity, TableNames.DICTIONARYWORD_TABLE, "parentId", (id + ""));
        try {
            wordString = getWords.execute().get();
            Log.w("Categories", wordString);

            JSONArray jsonArray = new JSONArray(wordString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);
                String word = jObject.get(TableNames.DICTIONARYLETTER_LABEL).toString();
                wordList.add(new DictionaryItem(word, ""));
                Collections.sort(wordList, new Comparator<DictionaryItem>() {
                    @Override
                    public int compare(DictionaryItem one, DictionaryItem two) {
                        return one.getForeignWord().toUpperCase().compareTo(two.getForeignWord().toUpperCase());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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
