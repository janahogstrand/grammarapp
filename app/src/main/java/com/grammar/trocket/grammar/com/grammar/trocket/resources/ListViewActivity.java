package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.media.AudioManager;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends BaseActivityDrawer {


    String dialect;
    String callerInfo;
    Locale language;
    ListView listView;
    ArrayList<String> data = new ArrayList<String>();
    LinearLayout linerLayout;
    TextToSpeech textToSpeech;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_main_activity);
        super.onCreateDrawer();

        listView = (ListView) findViewById(R.id.listView);
        linerLayout = (LinearLayout)findViewById(R.id.linerLayout);

        assignLanguage();
        assignStringArray();

        ListViewAdapter adapter =  new ListViewAdapter(this, data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(myOnClickListener);

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
     * This method is ran when any item in the listView is clicked. This method checks
     * whether the clicked item has a media player in the same position as it in the mediaPlayer
     * arrayList. If the mediaPlayer does not exist, the textToSpeech object runs the speak()
     * method which plays the clicked item's text.
     */
    public AdapterView.OnItemClickListener myOnClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            stopAllSound();
            String clickedView = String.valueOf(parent.getItemAtPosition(position));

            try {
                if (clickedView.equals("1"))
                {
                    setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
                }
                else if (clickedView.equals("2"))
                {
                    setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
                }
                else {
                    setAudio("");
                }

            }catch (Exception e){
                textToSpeech.speak(clickedView, TextToSpeech.QUEUE_FLUSH, null);
            }

        }
    };


    /**
     * Stops all current sound
     * If media player is running this will stop
     **/
    private void stopAllSound() {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.w("Player released", "Audio Released");
            }
        }
    }

    /**
     * Plays audio for a given address
     * @param address Address of audio URL
     * **/
    private void setAudio(String address) throws IOException {
        player = new MediaPlayer();
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setDataSource(address);
        player.prepare();
        player.start();
    }


    /**
     * This method is called whenever an activity is closed or destroyed.
     * This method stops the textToSpeech object from running and
     * then destroys it inorder for it not to leak information.
     */
    @Override
    protected void onDestroy() {
        if(player != null){
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.d("Player released ", "Player Destroyed");
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
