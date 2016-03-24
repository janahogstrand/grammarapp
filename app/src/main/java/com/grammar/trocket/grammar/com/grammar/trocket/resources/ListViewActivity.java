package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends BaseActivityDrawer {

    Locale language;
    ListView listView;
    ArrayList<NumberCalendarItem> data = new ArrayList<>();
    LinearLayout linerLayout;
    TextToSpeech textToSpeech;
    MediaPlayer player;
     Handler handler;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        super.onCreateDrawer();

        listView = (ListView) findViewById(R.id.listView);
        linerLayout = (LinearLayout)findViewById(R.id.linerLayout);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                assignLanguage();
                assignStringArray();
                ListViewAdapter adapter = new ListViewAdapter(ListViewActivity.this, data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(myOnClickListener);

            }
        }, 100);




    }

    /**
     * This method assigns a string arrayList with information retried from the database.
     */
    public void assignStringArray(){
        Intent intent = getIntent();
        id = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);
        ListViewActivityItems listViewActivityItems = new ListViewActivityItems(ListViewActivity.this, id);

        Log.w("currentItem.id", Integer.toString(id));

        data = listViewActivityItems.getArray();

    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
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

            String selectedView = ((TextView) view.findViewById(R.id.name)).getText().toString();


            try {
                    setAudio("");

            }catch (Exception e){
                textToSpeech.speak(selectedView, TextToSpeech.QUEUE_FLUSH, null);
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
