package com.example.teerachel.imagequizactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements OnClickListener{

    public TextView question;
    Button train;
    Button plane;
    Button bus;
    Button car;
    Button bike;
    Button ship;
    public Image_Quiz_Ques questions = new Image_Quiz_Ques();
    public Image_Quiz_Ans answersList = new Image_Quiz_Ans();
    public String[] questionListArr;
    public String correctAns;
    public Button[] answerOptionsArr;
    public String currentQuestion;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;
    public final static String EXTRA_MESSAGE = "com.example.teerachel.imagequizactivity.message";
    public final static String EXTRA_MESSAGE2 = "com.example.teerachel.imagequizactivity.message2";

    ArrayList<Integer> obj = new ArrayList<>();

    private static MainActivity instance;
    public static MainActivity getInstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instance = this;

        question = (TextView) findViewById(R.id.ques);

        obj.add(R.id.trainImage);
        obj.add(R.id.planeImage);
        obj.add(R.id.bikeImage);
        obj.add(R.id.carImage);
        obj.add(R.id.busImage);
        obj.add(R.id.shipImage);
        Collections.shuffle(obj);

            train = (Button) findViewById(obj.get(0));
            train.setBackground(getDrawable(R.drawable.train));
            train.setOnClickListener(this);
            train.setText("train");

            plane = (Button) findViewById(obj.get(1));
            plane.setBackground(getDrawable(R.drawable.aeroplane));
            plane.setOnClickListener(this);
            plane.setText("plane");

            bus = (Button) findViewById(obj.get(2));
            bus.setBackground(getDrawable(R.drawable.bus));
            bus.setOnClickListener(this);
            bus.setText("bus");

            car = (Button) findViewById(obj.get(3));
            car.setBackground(getDrawable(R.drawable.car));
            car.setOnClickListener(this);
            car.setText("car");

            bike = (Button) findViewById(obj.get(4));
            bike.setBackground(getDrawable(R.drawable.airportseat));
            bike.setOnClickListener(this);
            bike.setText("bike");

            ship = (Button) findViewById(obj.get(5));
            ship.setBackground(getDrawable(R.drawable.ship));
            ship.setOnClickListener(this);
            ship.setText("ship");

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
            Log.d("mistake", pressedButton.toString());
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
