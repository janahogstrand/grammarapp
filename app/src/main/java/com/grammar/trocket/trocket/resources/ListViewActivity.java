package com.grammar.trocket.trocket.resources;

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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.trocket.dialogs.DialectDialog;
import com.grammar.trocket.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.trocket.main.MainMenu;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ListViewActivity extends BaseActivityDrawer {

    private String dialect;
    private Locale language;
    private ListView listView;
    private ProgressBar loadingIcon;
    private ArrayList<NumberCalendarItem> data = new ArrayList<>();
    private LinearLayout linerLayout;
    private TextToSpeech textToSpeech;
    private MediaPlayer player;
    private Handler handler;
    private int callerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);
        super.onCreateDrawer();

        listView = (ListView) findViewById(R.id.listView);
        linerLayout = (LinearLayout)findViewById(R.id.linerLayout);
        loadingIcon = (ProgressBar) findViewById(R.id.loading_icon);

        toggleBusyUi(true);
        Log.d("toggleBusyUi", "true");
        Log.d("toggleBusyUi", "true");
        Log.d("toggleBusyUi", "true");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                assignLanguage();
                assignStringArray();

                toggleBusyUi(false);
                ListViewAdapter adapter = new ListViewAdapter(ListViewActivity.this, data);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(myOnClickListener);

                Log.d("toggleBusyUi", "false");
                Log.d("toggleBusyUi", "false");
                Log.d("toggleBusyUi", "false");
            }
        }, 5000);



    }

    /**
     * Sets the UI to either a "loading" state or a "data" stage, based on {@code busyRefreshing}.
     */
    private void toggleBusyUi(final boolean busy) {
                if (busy) {
                    loadingIcon.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.GONE);
                } else {
                    loadingIcon.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                }
    }

    /**
     * This method assigns a string arrayList with information retried from the database.
     */
    public void assignStringArray(){
        Intent intent = getIntent();
        callerId = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);
        ListViewActivityItems listViewActivityItems = new ListViewActivityItems(ListViewActivity.this, callerId);
        data = listViewActivityItems.getArray();
    }

    /**
     * This method assigns a language and a dialect to a variable according to the
     * language the user has chosen and then assigns the textToSpeech object with
     * the selected language.
     */
    public void assignLanguage(){
        Intent intent = getIntent();

        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
        String dialectCode = MainMenu.dialectsIDCode.get(MainMenu.dialectsNameID.get(dialect));
        String[] dCode = dialectCode.split("_");

        language = new Locale(dCode[0], dCode[1]);

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
