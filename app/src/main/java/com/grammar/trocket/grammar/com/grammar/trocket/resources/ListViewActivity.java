package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends AppCompatActivity {


    Locale language;
    ListView listView;
    String dialect;
    ArrayList<String> data = new ArrayList<String>();
    ArrayList<MediaPlayer> mediaPlayers = new ArrayList<MediaPlayer>();
    LinearLayout linerLayout;
    TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_activity);

         listView = (ListView) findViewById(R.id.listView);
         linerLayout = (LinearLayout)findViewById(R.id.linerLayout);


        assignLanguage();
        assignStringArray();

        ListViewAdapter adapter =  new ListViewAdapter(this, data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(myOnClickListener);

        assignMediaPlayers();

    }

    public void assignStringArray(){
        for(int i = 0;i<31;i++){
            String myString = new String("Place holder");
            data.add(myString);
        }
    }

    public void assignLanguage(){
        Intent intent = getIntent();
        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
        if(dialect.equals("Spanish")){
            language = new Locale("es", "ES");
        }else { language = new Locale("es", "MX");}
        textToSpeech=new TextToSpeech(ListViewActivity.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    public void assignMediaPlayers() {
        for(int i = 0;i<29;i++){
            if (i%2 == 0){
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.placeholderaudio1);
                mediaPlayers.add(mediaPlayer);
            }else {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.placeholderaudio2);
                mediaPlayers.add(mediaPlayer);
            }

        }
    }

    public AdapterView.OnItemClickListener myOnClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("position", position+"");
            try {
                    mediaPlayers.get(position).start();
            }catch (Exception e){
                String clickedView = String.valueOf(parent.getItemAtPosition(position));
                textToSpeech.speak(clickedView, TextToSpeech.QUEUE_FLUSH, null);
            }
        }
    };


}
