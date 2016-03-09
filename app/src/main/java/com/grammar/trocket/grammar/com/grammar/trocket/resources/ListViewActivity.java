package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends Activity {


    String dialect;
    String callerInfo;
    Locale language;
    ListView listView;
    ArrayList<String> data = new ArrayList<String>();
    // mediaPlayers arrayList will hold mediaPlayers who each are
    // assigned with a unique audio file
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

    /**
     * This method assigns a string arrayList with information retried from the database.
     */
    public void assignStringArray(){
        Intent intent = getIntent();
        ListViewActivityItems listViewActivityItems = new ListViewActivityItems();

        callerInfo = intent.getStringExtra(DialectDialog.CALLER_INFO);
        if(callerInfo.equals("El Calendario")){
            data = listViewActivityItems.getCalendarArray();
        }
        else {
            data = listViewActivityItems.getNummberArray();
        }

    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        Intent intent = getIntent();

        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
        if(dialect.equals("Spanish")){
            language = new Locale("es", "ES");
        }
        else {
            language = new Locale("es", "US");
        }

        textToSpeech=new TextToSpeech(ListViewActivity.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    /**
     *This method creates a mediaPlayers object for every item in listView and then
     * adds it to the mediaPlayer arrayList.
     */
    public void assignMediaPlayers() {
        for(int i = 2;i<data.size();i++){
            if (i%2 == 0){
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.placeholderaudio1);
                mediaPlayers.add(mediaPlayer);
            }else {
                MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.placeholderaudio2);
                mediaPlayers.add(mediaPlayer);
            }

        }
    }

    /**
     * This method is ran when any item in the listView is clicked. This method checks
     * whether the clicked item has a media player in the same position as it in the mediaPlayer
     * arrayList. If the mediaPlayer does not exist, the textToSpeech object runs the speak()
     * method which plays the clicked item's text.
     */
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

    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        this.finish();

        for( MediaPlayer mediaPlayer : mediaPlayers ){
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    Log.d("mediaPlayer released ", " Player Destroyed");
                }
            }
        }


        //Close the Text to Speech Library
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");
        }
        super.onDestroy();
    }




}
