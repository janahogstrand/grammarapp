package com.grammar.trocket.grammar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by jamiemoreland on 25/02/16.
 * Used for clock and festivals activities
 * Loads an activity where pictures and sentences will be filled
 *
 * @see FestivalTimeViewHolder
 */
public class BigView extends AppCompatActivity {
    ImageView centerImage;
    ImageView nextImage;
    ImageView prevImage;
    TextView nextText;
    TextView prevText;
    TextView nameEnglish;
    TextView nameSpanish;
    FestivalTimeItem prev;
    FestivalTimeItem next;
    FestivalTimeItem current;
    ArrayList<FestivalTimeItem> dataFest;
    int index;
    TextToSpeech textToSpeech;
    Locale language;
    MediaPlayer player = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_view);

        //Get data
        Bundle data = getIntent().getExtras();
        dataFest = data.getParcelableArrayList("data");
        index = data.getInt("current");

        player = new MediaPlayer();
        initViews();
        assignAssets(index);
        makeOnClicks();

        //TODO get language off database
        language = new Locale("es", "ES");


    }

    /**
     * Inits views and init listener for TTS
     * Should only be called once at start of program
     **/
    private void initViews() {
        centerImage = (ImageView) findViewById(R.id.centerImage);
        nextImage = (ImageView) findViewById(R.id.next);
        prevImage = (ImageView) findViewById(R.id.previous);
        nextText = (TextView) findViewById(R.id.textView2);
        prevText = (TextView) findViewById(R.id.textView);
        nameEnglish = (TextView) findViewById(R.id.name_english);
        nameSpanish = (TextView) findViewById(R.id.name_spanish);

        textToSpeech = new TextToSpeech(BigView.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(language);
                //For first TTS played
                if (status == TextToSpeech.SUCCESS) {
                    playAudio();
                }
            }
        });

    }


    /**
     * Setting on click listeners for next and prev
     * buttons
     * nextImage goes to next activity
     * prevImage goes to previous activity
     * centerImage plays sound when clicked
     **/
    private void makeOnClicks() {

        centerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        nextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignAssets(index + 1);
            }
        });
        prevImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignAssets(index - 1);
            }
        });
    }

    /**
     * Will attempted to load images for previous and next
     * if it is the starting or ending button they will
     * become invisible
     *
     * @param newIndex Index of page so correct page
     *                 can be loaded
     **/
    private void assignAssets(int newIndex) {
        index = newIndex;
        current = dataFest.get(index);
        centerImage.setImageResource(current.getPhoto());
        nameEnglish.setText(current.getEnglishName());
        nameSpanish.setText(current.getSpanishName());
        try {
            next = dataFest.get(index + 1);
            nextImage.setImageResource(next.getPhoto());
            nextImage.setVisibility(View.VISIBLE);
            nextText.setVisibility(View.VISIBLE);
        } catch (IndexOutOfBoundsException e) {
            nextImage.setVisibility(View.INVISIBLE);
            nextText.setVisibility(View.INVISIBLE);
        }
        try {
            prev = dataFest.get(index - 1);
            prevImage.setImageResource(prev.getPhoto());
            prevImage.setVisibility(View.VISIBLE);
            prevText.setVisibility(View.VISIBLE);
        } catch (ArrayIndexOutOfBoundsException e) {
            prevImage.setVisibility(View.INVISIBLE);
            prevText.setVisibility(View.INVISIBLE);
        }

        playAudio();

    }

    /**
     * Plays from audio file if not found
     * then plays textToSpeech
     **/
    private void playAudio() {
        try {
            assignAudio(nameSpanish.getText().toString());
        } catch (Exception e) {
            textToSpeech.speak(nameSpanish.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    /**
     * Database will assign correct audio files, for now I have used
     * if statements to simulate this
     * TODO database implementation
     *
     * @throws NullPointerException When no audio is found
     */
    private void assignAudio(String text) {
        stopAllSound();

        switch (text) {
            case "Es Brits":
                player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio1);
                break;
            case "El Indianas":
                player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio2);
                break;
            case "Es Test":
                player = MediaPlayer.create(BigView.this, R.raw.placeholderaudio3);
                break;
            default:
                player = null;
        }
        player.start();
    }

    /**
     * Stops all current sound
     * If text to speech is running this will stop
     * If media player is running this will stop
     **/
    private void stopAllSound() {
        try {
            if (textToSpeech.isSpeaking()) {
                textToSpeech.stop();
            }
            if (player != null) {
                if (player.isPlaying()) {
                    player.stop();
                    player.reset();
                    player.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
