package com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabet;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleItem;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 18/03/16.
 */
public class Alphabet extends BaseActivityDrawer{

    private AlphabetAdapter alphabetAdapter;
    private ArrayList<AlphabetItem> alphabetList;
    public static String dialect;
    public static TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_main);
        super.onCreateDrawer();
        getDialect();



        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                Alphabet.textToSpeech.setLanguage(new Locale("Es", "es"));
                //For first TTS played
//                if (status == TextToSpeech.SUCCESS) {
//                    playAudio();
//                }
            }
        });

        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        rv.setHasFixedSize(true);

        GridLayoutManager glm = new GridLayoutManager(Alphabet.this, 2);
        rv.setLayoutManager(glm);


        alphabetAdapter = new AlphabetAdapter(getData());
        rv.setAdapter(alphabetAdapter);
    }

    public void getDialect(){
        Intent intent = getIntent();
        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
    }

    //TODO database
    private ArrayList<AlphabetItem> getData() {
        alphabetList = new ArrayList<AlphabetItem>();

        alphabetList.add(new AlphabetItem("A", false));
        alphabetList.add(new AlphabetItem("B", false));
        alphabetList.add(new AlphabetItem("C", false));
        alphabetList.add(new AlphabetItem("D", false));
        alphabetList.add(new AlphabetItem("E", false));
        alphabetList.add(new AlphabetItem("F", false));
        alphabetList.add(new AlphabetItem("G", false));
        alphabetList.add(new AlphabetItem("H", false));
        alphabetList.add(new AlphabetItem("I", false));
        alphabetList.add(new AlphabetItem("J", false));
        alphabetList.add(new AlphabetItem("K", false));
        alphabetList.add(new AlphabetItem("L", false));
        alphabetList.add(new AlphabetItem("M", false));
        alphabetList.add(new AlphabetItem("N", false));
        alphabetList.add(new AlphabetItem("O", false));
        alphabetList.add(new AlphabetItem("P", false));
        alphabetList.add(new AlphabetItem("Q", false));
        alphabetList.add(new AlphabetItem("R", false));
        alphabetList.add(new AlphabetItem("S", false));
        alphabetList.add(new AlphabetItem("T", false));
        alphabetList.add(new AlphabetItem("U", false));
        alphabetList.add(new AlphabetItem("W", false));
        alphabetList.add(new AlphabetItem("X", false));
        alphabetList.add(new AlphabetItem("Y", false));
        alphabetList.add(new AlphabetItem("Z", false));


        return alphabetList;
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
