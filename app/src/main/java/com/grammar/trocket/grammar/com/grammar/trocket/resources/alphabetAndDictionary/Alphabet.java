package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class Alphabet extends BaseActivityDrawer{

    private DictionaryAlphabetAdapter alphabetAdapter;
    private ArrayList<AlphabetItem> alphabetList;
    public static String dialect;
    public static TextToSpeech textToSpeech;
    public static final String LETTER = "com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet.LETTER";
    public static Cursor letters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_main);
        super.onCreateDrawer();
        getDialect();

        initTTS();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(Alphabet.this, 2);
        rv.setLayoutManager(glm);


        alphabetAdapter = new DictionaryAlphabetAdapter(getData(), "Alphabet");
        rv.setAdapter(alphabetAdapter);
    }

    public void getDialect(){
        Intent intent = getIntent();
        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
    }

    //TODO database
    private ArrayList<AlphabetItem> getData() {
        alphabetList = new ArrayList<AlphabetItem>();

//        alphabetList.add(new AlphabetItem("A", false));
//        alphabetList.add(new AlphabetItem("B", false));
//        alphabetList.add(new AlphabetItem("C", false));
//        alphabetList.add(new AlphabetItem("D", false));
//        alphabetList.add(new AlphabetItem("E", false));
//        alphabetList.add(new AlphabetItem("F", false));
//        alphabetList.add(new AlphabetItem("G", false));
//        alphabetList.add(new AlphabetItem("H", false));
//        alphabetList.add(new AlphabetItem("I", false));
//        alphabetList.add(new AlphabetItem("J", false));
//        alphabetList.add(new AlphabetItem("K", false));
//        alphabetList.add(new AlphabetItem("L", false));
//        alphabetList.add(new AlphabetItem("M", false));
//        alphabetList.add(new AlphabetItem("N", false));
//        alphabetList.add(new AlphabetItem("O", false));
//        alphabetList.add(new AlphabetItem("P", false));
//        alphabetList.add(new AlphabetItem("Q", false));
//        alphabetList.add(new AlphabetItem("R", false));
//        alphabetList.add(new AlphabetItem("S", false));
//        alphabetList.add(new AlphabetItem("T", false));
//        alphabetList.add(new AlphabetItem("U", false));
//        alphabetList.add(new AlphabetItem("W", false));
//        alphabetList.add(new AlphabetItem("X", false));
//        alphabetList.add(new AlphabetItem("Y", false));
//        alphabetList.add(new AlphabetItem("Z", false));

        SQLiteDatabase myDatabase = ModuleSelection.db.getWritableDatabase();
        letters = myDatabase.rawQuery("SELECT * FROM " + ModuleSelection.db.DICTIONARYLETTER_TABLE + " WHERE " +  ModuleSelection.db.DICTIONARYLETTER_DICTIONARYID + " = " + MainMenu.DictionaryID
                + " ORDER BY "  + ModuleSelection.db.DICTIONARYLETTER_TABLE + "." + ModuleSelection.db.DICTIONARYLETTER_LABEL + " ASC", null);
        while(letters.moveToNext()) {
            Log.i("Letter1", letters.getString(letters.getColumnIndex(ModuleSelection.db.DICTIONARYLETTER_COURSEID)));
            Log.i("Letter2", letters.getString(letters.getColumnIndex(ModuleSelection.db.DICTIONARYLETTER_LABEL)));

            String letter = letters.getString(letters.getColumnIndex(ModuleSelection.db.DICTIONARYLETTER_LABEL));
            alphabetList.add(new AlphabetItem(letter, false));
        }
        letters.move(-1);
        return alphabetList;
    }

    private void initTTS(){
        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Alphabet.textToSpeech.setLanguage(new Locale("Es", "es"));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
