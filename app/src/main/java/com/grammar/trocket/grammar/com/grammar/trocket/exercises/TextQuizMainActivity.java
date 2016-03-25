package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;

import java.util.ArrayList;


public class TextQuizMainActivity extends Activity {

    public TextView questionView;
    public Button answerOption1;
    public Button answerOption2;
    public Button answerOption3;
    public Button answerOption4;
    public Button answerOption5;
    public Button answerOption6;

    public QuizzesQuestions QuizzesQuestions;
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

    public final static String EXTRA_MESSAGE = "com.firasaltayeb.quizbutton.MESSAGE";
    public final static String EXTRA_MESSAGE2 = "com.firasaltayeb.quizbutton.MESSAGE2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_text_quiz_main);

        findAllViews();
        getSelectedQuizPosition();

        QuizzesQuestions = new QuizzesQuestions(TextQuizMainActivity.this,selectedQuizPosition,selectedQuizType);
        questionsList = QuizzesQuestions.getQuizQuestions();
        answersList = new QuizzesAnswers(TextQuizMainActivity.this, questionsList, selectedQuizType);

        assignVariables();
        assignViews();
    }


    public void findAllViews(){
        questionView = (TextView) findViewById(R.id.question);
        answerOption1 = (Button) findViewById(R.id.answerOption1);
        answerOption2 = (Button) findViewById(R.id.answerOption2);
        answerOption3 = (Button) findViewById(R.id.answerOption3);
        answerOption4 = (Button) findViewById(R.id.answerOption4);
        answerOption5 = (Button) findViewById(R.id.answerOption5);
        answerOption6 = (Button) findViewById(R.id.answerOption6);
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
     * CurrentQuestion is assigned a question questions list based on the questionNumber.
     * AnswerOptionArray is assigned an array which holds the current question's answer options.
     * CorrectAnswer is assigned the current question's correct answer.
     */
    public void assignVariables(){
        currentQuestion = questionsList.get(questionNumber);
        correctAnswer = answersList.getCorrectAnswer(currentQuestion);
        answerOptionArray = answersList.getAnswerOptions(currentQuestion);
    }

    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignViews(){
        questionView.setText(currentQuestion.getName());
        answerOption1.setText(answerOptionArray[0]);
        answerOption2.setText(answerOptionArray[1]);
        answerOption3.setText(answerOptionArray[2]);
        answerOption4.setText(answerOptionArray[3]);
        answerOption5.setText(answerOptionArray[4]);
        answerOption6.setText(answerOptionArray[5]);
        if(answerOptionArray[0] == null){answerOption1.setVisibility(View.INVISIBLE);}
        if(answerOptionArray[1] == null){answerOption2.setVisibility(View.INVISIBLE);}
        if(answerOptionArray[2] == null){answerOption3.setVisibility(View.INVISIBLE);}
        if(answerOptionArray[3] == null){answerOption4.setVisibility(View.INVISIBLE);}
        if(answerOptionArray[4] == null){answerOption5.setVisibility(View.INVISIBLE);}
        if(answerOptionArray[5] == null){answerOption6.setVisibility(View.INVISIBLE);}
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
            pressedButton.setBackgroundResource(R.drawable.quiz_rounded_button_green);
            successCounter++;
        }
        else {
            Log.d("mistake", pressedButton.getText().toString());
            pressedButton.setBackgroundResource(R.drawable.quiz_rounded_button_red);
            showCorrectAnswer();
            mistakeCounter++;
        }
        disableButtons();
        questionNumber++;
        checkQuestionNumber();
    }


    /**
     * This method is called by checkResult() when a user clicks a button holding the wrong answer.
     * This method shows the user the button holding the correct answer by changing its color to green.
     */
    public void showCorrectAnswer(){
        if(correctAnswer.equals(answerOption1.getText().toString())){
            answerOption1.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        else if (correctAnswer.equals(answerOption2.getText().toString())){
            answerOption2.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        else if (correctAnswer.equals(answerOption3.getText().toString())){
            answerOption3.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        else if (correctAnswer.equals(answerOption4.getText().toString())){
            answerOption4.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        else if (correctAnswer.equals(answerOption5.getText().toString())){
            answerOption5.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        else if (correctAnswer.equals(answerOption6.getText().toString())){
            answerOption6.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
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
    public void restoreView(){
        answerOption1.setBackgroundResource(R.drawable.quiz_rounded_button_primary);
        answerOption2.setBackgroundResource(R.drawable.quiz_rounded_button_secondary);
        answerOption3.setBackgroundResource(R.drawable.quiz_rounded_button_primary);
        answerOption4.setBackgroundResource(R.drawable.quiz_rounded_button_secondary);
        answerOption5.setBackgroundResource(R.drawable.quiz_rounded_button_primary);
        answerOption6.setBackgroundResource(R.drawable.quiz_rounded_button_secondary);
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
        if(questionNumber == questionsList.size()){
            Intent intent = new Intent(this, QuizStatisticsActivity.class);
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
                    assignViews();
                    restoreView();
                }
            }, 1000);
        }
    }
}
