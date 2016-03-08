package com.example.teerachel.imagequizactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public TextView question;
    ImageButton train;
    ImageButton plane;
    ImageButton bus;
    ImageButton car;
    ImageButton bike;
    ImageButton ship;
    public Image_Quiz_Ques questions = new Image_Quiz_Ques();
    public Image_Quiz_Ans answersList = new Image_Quiz_Ans();
    public String[] questionListArr;
    public String correctAns;
    public String[] answerOptionsArr;
    public String currentQuestion;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;

    public final static String EXTRA_MESSAGE = "hello";
    public final static String EXTRA_MESSAGE2 = "hello2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.ques);
        train = (ImageButton) findViewById(R.id.trainImage);
        train.setOnClickListener(this);
        plane = (ImageButton) findViewById(R.id.planeImage);
        plane.setOnClickListener(this);
        bus = (ImageButton) findViewById(R.id.busImage);
        bus.setOnClickListener(this);
        car = (ImageButton) findViewById(R.id.carImage);
        car.setOnClickListener(this);
        bike = (ImageButton) findViewById(R.id.bikeImage);
        bike.setOnClickListener(this);
        ship = (ImageButton) findViewById(R.id.shipImage);
        ship.setOnClickListener(this);

        questionListArr = questions.createArray();
        assignVariables();
        assignTextView();
    }

    /**
     * CurrentQuestion is assigned a question questions list based on the questionNumber.
     * AnswerOptionArray is assigned an array which holds the current question's answer options.
     * CorrectAnswer is assigned the current question's correct answer.
     */
    public void assignVariables(){
        currentQuestion = questionListArr[questionNumber];
        answerOptionsArr = answersList.getAnswerOptions(currentQuestion);
        correctAns = answersList.getCorrectAnswer(currentQuestion);
    }

    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignTextView(){
        question.setText(currentQuestion);
    }

    /**
     * disables all the buttons to prevent the users to click a
     * second button while handler is pausing.
     */
//    public void disableButtons(){
//        train.setClickable(false);
//        plane.setClickable(false);
//        bus.setClickable(false);
//        answerOption4.setClickable(false);
//        answerOption5.setClickable(false);
//        answerOption6.setClickable(false);
//    }


    /**
     * Checks whether the clicked button's text match the
     * correct answer for the current question.
     * If the answer is correct the success counter increase by one.
     * If the answer is incorrect the mistake counter increase by one.
     * After that the question counter is increased thus moving the player to the
     * second question, and the checkQuestionNumber() method is called.
     * @param view
     */
    public void checkResult(View view) {
        ImageButton pressedButton = (ImageButton) view;
        if(correctAns.equals(pressedButton.toString())) {
            Log.d("correct", "correct");
            pressedButton.setBackgroundResource(R.drawable.clear_background);
            successCounter++;
        }
        else {
            Log.d("mistake", pressedButton.toString());
            pressedButton.setBackgroundResource(R.drawable.clear_background);
            mistakeCounter++;
        }

        questionNumber++;
        checkQuestionNumber();
    }


    /**
     * If the question number is equal to 6 ((indicating that all the questions are over
     * an intent sends the user to the statics screen with message which changes according to
     * how the user performed in the quiz.
     */
    public void checkQuestionNumber(){
        if(questionNumber == 6){
            Intent intent = new Intent(this, Image_Quiz_Stats.class);
            intent.putExtra(EXTRA_MESSAGE, ""+successCounter);
            intent.putExtra(EXTRA_MESSAGE2, ""+mistakeCounter);
            startActivity(intent);
        }
        else {
            // Execute run() after 2 seconds have passed
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    assignVariables();
                    assignTextView();
                }
            }, 1000);
        }
    }

    @Override
    public void onClick(View v) {
        checkResult(v);
    }
}
