package com.firasaltayeb.quizactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class Quiz_Statistics_Activity extends Activity {

    public TextView successCounter;
    public TextView mistakeCounter;
    public TextView comment;
    public String message;
    public String message2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.quiz_statistics_activity);

        // Get the message from the intent
        Intent intent = getIntent();
        message = intent.getStringExtra(Quiz_Main_Activity.EXTRA_MESSAGE);
        message2 = intent.getStringExtra(Quiz_Main_Activity.EXTRA_MESSAGE2);

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
