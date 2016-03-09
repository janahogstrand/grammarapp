package com.grammar.trocket.grammar.com.grammar.trocket.exercises_quiz.audio_quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;

/**
 * Created by Sam on 06/03/2016.
 */
public class AudioQuizStatisticsActivity extends AppCompatActivity {

    public TextView successCounter;
    public TextView mistakeCounter;
    public TextView comment;
    public String message;
    public String message2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_quiz_statistics);

        // Get the message from the intent
        Intent intent = getIntent();
        message = intent.getStringExtra(AudioQuizMainActivity.EXTRA_MESSAGE);
        message2 = intent.getStringExtra(AudioQuizMainActivity.EXTRA_MESSAGE2);

        successCounter = (TextView) findViewById(R.id.successCounter);
        mistakeCounter = (TextView) findViewById(R.id.mistakeCounter);

        comment = (TextView) findViewById(R.id.comment);

        successCounter.setText("Number of successes "+ message);
        mistakeCounter.setText("Number of mistakes "+ message2);

        initializeComment();

    }

    /**
     * Initializes the comment with message that changes according t0 the
     * message received from the Quiz_Main_Activity class.
     */
    public void initializeComment(){
        if(Integer.parseInt(message)>= 5){
            comment.setText("Yatta!!");
        }
        else {
            comment.setText("Try harder");
        }
    }
}
