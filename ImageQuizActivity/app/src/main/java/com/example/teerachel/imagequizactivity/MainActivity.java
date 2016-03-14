package com.example.teerachel.imagequizactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    public Button firstButton;
    public Button secondButton;
    public Button thirdButton;
    public Button fourthButton;
    public Button fifthButton;
    public Button sixthButton;
    public TextView question;
    public Image_Quiz_Ques questions;
    ArrayList<Integer> drawableList;
    public String[] questionList;
    public String correctAns;
    public String currentQuestion;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;
    public final static String EXTRA_MESSAGE = "com.example.teerachel.imagequizactivity.message";
    public final static String EXTRA_MESSAGE2 = "com.example.teerachel.imagequizactivity.message2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//            train = (Button) findViewById(obj.get(0));
//            train.setBackground(getDrawable(R.drawable.train));
//            train.setOnClickListener(this);
//            train.setText("train");

        questions = new Image_Quiz_Ques();
        drawableList = new ArrayList<>();
        questionList = questions.createArray();

        findDrawables();
        findViewByIds();
        assignTextView();

    }

    public void findDrawables(){
        drawableList.add(R.drawable.aeroplane);
        drawableList.add(R.drawable.airportseat);
        drawableList.add(R.drawable.bankcard);
        drawableList.add(R.drawable.bed);
        drawableList.add(R.drawable.car);
        drawableList.add(R.drawable.train);
        drawableList.add(R.drawable.holiday);
        drawableList.add(R.drawable.phone);
        drawableList.add(R.drawable.reserved);
        drawableList.add(R.drawable.restaurant);
    }

    public void findViewByIds(){
        question = (TextView) findViewById(R.id.questionBtn);
        firstButton = (Button) findViewById(R.id.firstBtn);
        secondButton = (Button) findViewById(R.id.secondBtn);
        thirdButton = (Button) findViewById(R.id.thirdBtn);
        fourthButton = (Button) findViewById(R.id.fourthBtn);
        fifthButton = (Button) findViewById(R.id.fifthBtn);
        sixthButton = (Button) findViewById(R.id.sixthBtn);
    }


    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignTextView(){
        currentQuestion = questionList[questionNumber];
        question.setText(currentQuestion);
        firstButton.setBackground(getDrawable(drawableList.get(0)));
        secondButton.setBackground(getDrawable(drawableList.get(1)));
        thirdButton.setBackground(getDrawable(drawableList.get(2)));
        fourthButton.setBackground(getDrawable(drawableList.get(3)));
        fifthButton.setBackground(getDrawable(drawableList.get(4)));
        sixthButton.setBackground(getDrawable(drawableList.get(5)));
    }


    /**
     * Checks whether the clicked button's text match the
     * correct answer for the current question.
     * If the answer is correct the success counter increase by one.
     * If the answer is incorrect the mistake counter increase by one.
     * After that the question counter is increased thus moving the player to the
     * second question, and the checkQuestionNumber() method is called.
     * The buttons also changes opacity onClick to indicate that the
     * button is clicked for user's purposes.
     * @param view
     */
    public void checkResult(View view) {
        final Button pressedButton = (Button) view;
        if(correctAns.equals(pressedButton.getText().toString())) {
            Log.d("correct", "correct");
            successCounter++;
            Toast.makeText(MainActivity.this,
                    "You're correct!", Toast.LENGTH_LONG).show();

            pressedButton.getBackground().mutate().setAlpha(128);
            pressedButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pressedButton.getBackground().setAlpha(255);
                }
            }, 1000);

        }
        else {
            mistakeCounter++;
            Toast.makeText(MainActivity.this,
                    "Sorry, try again :(", Toast.LENGTH_LONG).show();

            pressedButton.getBackground().mutate().setAlpha(128);
            pressedButton.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pressedButton.getBackground().setAlpha(255);
                }
            }, 1000);

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
                    assignTextView();
                }
            }, 1000);
        }
    }


}
