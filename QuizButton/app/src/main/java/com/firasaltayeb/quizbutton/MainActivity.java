package com.firasaltayeb.quizbutton;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    public TextView question;
    public Button answerOption1;
    public Button answerOption2;
    public Button answerOption3;
    public Button answerOption4;
    public Button answerOption5;
    public Button answerOption6;
    public QuestionsList questionsList = new QuestionsList();
    public AnswersList answersList = new AnswersList();
    public String[] questionsListArray;
    public String correctAnswer;
    public String currentQuestion;
    public String[] answerOptionArray;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;

    public final static String EXTRA_MESSAGE = "com.firasaltayeb.quizbutton.MESSAGE";
    public final static String EXTRA_MESSAGE2 = "com.firasaltayeb.quizbutton.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = (TextView) findViewById(R.id.question);
        answerOption1 = (Button) findViewById(R.id.answerOption1);
        answerOption2 = (Button) findViewById(R.id.answerOption2);
        answerOption3 = (Button) findViewById(R.id.answerOption3);
        answerOption4 = (Button) findViewById(R.id.answerOption4);
        answerOption5 = (Button) findViewById(R.id.answerOption5);
        answerOption6 = (Button) findViewById(R.id.answerOption6);

        questionsListArray = questionsList.createArray();
        assignVariables();
        assignTextView();
    }

    /**
     * CurrentQuestion is assigned a question questions list based on the questionNumber.
     * AnswerOptionArray is assigned an array which holds the current question's answer options.
     * CorrectAnswer is assigned the current question's correct answer.
     */
    public void assignVariables(){
        currentQuestion = questionsListArray[questionNumber];
        answerOptionArray = answersList.getAnswerOptions(currentQuestion);
        correctAnswer = answersList.getCorrectAnswer(currentQuestion);
    }

    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignTextView(){
        question.setText(currentQuestion);
        answerOption1.setText(answerOptionArray[0]);
        answerOption2.setText(answerOptionArray[1]);
        answerOption3.setText(answerOptionArray[2]);
        answerOption4.setText(answerOptionArray[3]);
        answerOption5.setText(answerOptionArray[4]);
        answerOption6.setText(answerOptionArray[5]);
    }

    /**
     * Checks whether the clicked button's text match the
     * correct answer for the current question.
     * If the answer is correct the clicked button is colored green and the
     * success counter increase by one. If the answer is incorrect the button
     * is colored with red and mistake counter increase by one.
     * After that the question counter is increased thus moving the player to the
     * second question, and the checkQuestionNumber() method is called.
     * @param view
     */
    public void checkResult(View view) {
        Button pressedButton = (Button) view;
        if(correctAnswer.equals(pressedButton.getText().toString())) {
            Log.d("correct", "correct");
            pressedButton.setBackgroundColor(Color.GREEN);
            successCounter++;
        }
        else {
            Log.d("mistake", pressedButton.getText().toString());
            pressedButton.setBackgroundColor(Color.RED);
            mistakeCounter++;
        }
        disableButtons();
        questionNumber++;
        checkQuestionNumber();
    }

    /**
     * disables all the buttons to prevent the users to click a
     * second button while handler is pausing.
     */
    public  void disableButtons(){
        answerOption1.setClickable(false);
        answerOption2.setClickable(false);
        answerOption3.setClickable(false);
        answerOption4.setClickable(false);
        answerOption5.setClickable(false);
        answerOption6.setClickable(false);
    }
    /**
     * Colors all the button's background to white thus
     * returning it to its original color and then set the buttons
     * to be clickable.
     */
    public void restoreColor(){
        answerOption1.setBackgroundColor(Color.WHITE);
        answerOption2.setBackgroundColor(Color.WHITE);
        answerOption3.setBackgroundColor(Color.WHITE);
        answerOption4.setBackgroundColor(Color.WHITE);
        answerOption5.setBackgroundColor(Color.WHITE);
        answerOption6.setBackgroundColor(Color.WHITE);
        answerOption1.setClickable(true);
        answerOption2.setClickable(true);
        answerOption3.setClickable(true);
        answerOption4.setClickable(true);
        answerOption5.setClickable(true);
        answerOption6.setClickable(true);
    }

    /**
     * If the question number is equal to 10 ((indicating that all the questions are over
     * an intent sends the user to the statics screen with message which changes according to
     * how the user performed in the quiz.
     */
    public void checkQuestionNumber(){
        if(questionNumber == 10){
            Intent intent = new Intent(this, StaticsActivity.class);
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
                    restoreColor();
                }
            }, 1000);
        }
    }
}
