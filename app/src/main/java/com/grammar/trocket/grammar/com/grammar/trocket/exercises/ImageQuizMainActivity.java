package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ImageQuizMainActivity extends Activity {

    public TextView questionView;
    public ImageView answerOption1;
    public ImageView answerOption2;
    public ImageView answerOption3;
    public ImageView answerOption4;
    public ImageView answerOption5;
    public ImageView answerOption6;


    public QuizzesQuestions quizzesQuestions;
    public ArrayList<Question> questionsList;
    public QuizzesAnswers answersList;

    public String correctAnswer;
    public Question currentQuestion;
    public String[] answerOptionArray;

    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;
    public String selectedQuizType;
    public int selectedQuizPosition;
    public Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_quiz_main);

        findAllViews();
        getSelectedQuizPosition();
        questionView.setText("Loading Questions");

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                quizzesQuestions = new QuizzesQuestions(ImageQuizMainActivity.this,selectedQuizPosition,selectedQuizType);
                questionsList = quizzesQuestions.getQuizQuestions();
                answersList = new QuizzesAnswers(ImageQuizMainActivity.this, questionsList, selectedQuizType);
                assignVariables();
                assignViews();

            }
        }, 100);

    }

    public void findAllViews(){
        questionView = (TextView) findViewById(R.id.questionBtn);
        answerOption1 = (ImageView) findViewById(R.id.firstView);
        answerOption2 = (ImageView) findViewById(R.id.secondView);
        answerOption3 = (ImageView) findViewById(R.id.thirdView);
        answerOption4 = (ImageView) findViewById(R.id.fourthView);
        answerOption5 = (ImageView) findViewById(R.id.fifthView);
        answerOption6 = (ImageView) findViewById(R.id.sixthView);
    }

    /**
     * This method gets the position and the type of the chosen quiz.
     */
    public void getSelectedQuizPosition(){
        Intent intent = getIntent();
        selectedQuizPosition = QuizDialog.SELECTED_QUIZ_POSITION;
        selectedQuizType = intent.getStringExtra(QuizDialog.SELECTED_QUIZ_TYPE);
    }


    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignVariables(){
        currentQuestion = questionsList.get(questionNumber);
        correctAnswer = answersList.getCorrectAnswer(currentQuestion);
        answerOptionArray = answersList.getAnswerOptions(currentQuestion);
    }


    public void assignViews(){
        questionView.setText(currentQuestion.getName());
        Picasso.with(answerOption1.getContext()).load(answerOptionArray[0]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption1);

        Picasso.with(answerOption2.getContext()).load(answerOptionArray[1]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption2);

        Picasso.with(answerOption3.getContext()).load(answerOptionArray[2]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption3);

        Picasso.with(answerOption4.getContext()).load(answerOptionArray[3]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption4);

        Picasso.with(answerOption5.getContext()).load(answerOptionArray[4]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption5);

        Picasso.with(answerOption6.getContext()).load(answerOptionArray[5]).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(answerOption6);
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
    public void checkResult(final View view) {
        final ImageView variableName = (ImageView) findViewById(view.getId());

        String pressedView = getPressedViewsUrl(view);

        if(correctAnswer.equals(pressedView)) {
            successCounter++;
            variableName.setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP);
            variableName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    variableName.clearColorFilter();
                }
            }, 1000);
        }
        else {
            mistakeCounter++;
            variableName.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            variableName.postDelayed(new Runnable() {
                @Override
                public void run() {
                    variableName.clearColorFilter();
                }
            }, 1000);
        }

        Log.d("pressedView", view.getTag().toString());
        questionNumber++;
        checkQuestionNumber();
    }


    public String getPressedViewsUrl(View view){

        String pressedView = "";
        if ((view.getTag().toString()).equals("answerOption1")){
            pressedView = answerOptionArray[0];
        }
        else if ((view.getTag().toString()).equals("answerOption2")){
            pressedView = answerOptionArray[1];
        }
        else if ((view.getTag().toString()).equals("answerOption3")){
            pressedView = answerOptionArray[2];
        }
        else if ((view.getTag().toString()).equals("answerOption4")){
            pressedView = answerOptionArray[3];
        }
        else if ((view.getTag().toString()).equals("answerOption5")){
            pressedView = answerOptionArray[4];
        }
        else if ((view.getTag().toString()).equals("answerOption6")){
            pressedView = answerOptionArray[5];
        }

        return pressedView;
    }

    /**
     * If the question number is equal to 6 ((indicating that all the questions are over
     * an intent sends the user to the statics screen with message which changes according to
     * how the user performed in the quiz.
     */
    public void checkQuestionNumber(){
        if(questionNumber == questionsList.size()){
            Intent intent = new Intent(this, QuizStatisticsActivity.class);
            intent.putExtra(TextQuizMainActivity.EXTRA_MESSAGE, ""+successCounter);
            intent.putExtra(TextQuizMainActivity.EXTRA_MESSAGE2, ""+mistakeCounter);
            startActivity(intent);
        }
        else {
            // Execute run() after 2 seconds have passed
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    assignVariables();
                    assignViews();
                }
            }, 1000);
        }
    }


}
