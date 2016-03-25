package com.grammar.trocket.grammar.com.grammar.trocket.exercises;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class MultipleQuizMainActivity extends Activity {

    public CheckBox answerOption1;
    public CheckBox answerOption2;
    public CheckBox answerOption3;
    public CheckBox answerOption4;
    public CheckBox answerOption5;
    public CheckBox answerOption6;
    public CheckBox changeBackground;

    public QuizzesQuestions QuizzesQuestions;
    public ArrayList<Question> questionsList;
    public QuizzesAnswers answersList;


    public Question currentQuestion;
    public String[] answerOptionArray;
    public ArrayList<String> correctAnswerList;

    public String selectedQuizType;
    public int selectedQuizPosition;


    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;
    public ArrayList<String> selectedAnswers;

    public TextToSpeech textToSpeech;
    public Locale language;
    public MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_multiple_quiz_main);

        findAllViews();
        getSelectedQuizPosition();

        selectedAnswers = new ArrayList<>();
        correctAnswerList = new ArrayList<>();

        QuizzesQuestions = new QuizzesQuestions(MultipleQuizMainActivity.this, selectedQuizPosition, selectedQuizType);
        questionsList = QuizzesQuestions.getQuizQuestions();
        answersList = new QuizzesAnswers(MultipleQuizMainActivity.this, questionsList, selectedQuizType);

        assignVariables();
        assignViews();
        assignLanguage();
    }


    /**
     * This method gets the position and the type of the chosen quiz.
     */
    public void getSelectedQuizPosition(){
        Intent intent = getIntent();
        selectedQuizPosition = QuizDialog.SELECTED_QUIZ_POSITION;
        selectedQuizType = intent.getStringExtra(QuizDialog.SELECTED_QUIZ_TYPE);
    }

    public void findAllViews(){
        answerOption1 = (CheckBox) findViewById(R.id.answerOption1);
        answerOption2 = (CheckBox) findViewById(R.id.answerOption2);
        answerOption3 = (CheckBox) findViewById(R.id.answerOption3);
        answerOption4 = (CheckBox) findViewById(R.id.answerOption4);
        answerOption5 = (CheckBox) findViewById(R.id.answerOption5);
        answerOption6 = (CheckBox) findViewById(R.id.answerOption6);
    }


    /** 
     * This method determines which language will be used by the textToSpeech API/object 
     * and then sets it for the textToSpeech instance/variable. 
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(MultipleQuizMainActivity.this, new TextToSpeech.OnInitListener() {
            @Override public void onInit(int status) {
                textToSpeech.setLanguage(language);
            }
        });
    }

    /**
     * CurrentQuestion is assigned a question questions list based on the questionNumber.
     * AnswerOptionArray is assigned an array which holds the current question's answer options.
     * CorrectAnswer is assigned the current question's correct answer.
     */
    public void assignVariables(){
        correctAnswerList.clear();
        selectedAnswers.clear();

        currentQuestion = questionsList.get(questionNumber);
        answerOptionArray = answersList.getAnswerOptions(currentQuestion);
        correctAnswerList = answersList.getCorrectAnswerList(currentQuestion, answerOptionArray);


    }

    /**
     * This method is called when the user clicks on the check boxes.
     * This method adds the checked boxes to the selectedAnswers arrayList, but
     * if the check box text is already in the selectedAnswers arrayList this
     * method removes it.
     * @param view
     */
    public void checkboxUse(View view)
    {
        CheckBox clickedBox = (CheckBox) view;
        if(!selectedAnswers.contains(clickedBox.getText().toString()))
        {
            selectedAnswers.add(clickedBox.getText().toString());
        }
        else
        {
            selectedAnswers.remove(clickedBox.getText().toString());
        }

    }

    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignViews(){
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
     * @param  view
     */

    public void checkResult(View view)
    {
        try {
            stopAllSound();
        }catch (Exception e){}

        for (int i = 0; i < selectedAnswers.size(); i++) {
            if (correctAnswerList.contains(selectedAnswers.get(i))) {
                Log.d("correct", "correct");
                changeBackground = checkBoxForBackgroundChange(selectedAnswers.get(i));
                changeBackground.setBackgroundResource(R.drawable.quiz_rounded_button_green);
                successCounter++;
            } else {
                Log.d("mistake", selectedAnswers.get(i));
                changeBackground = checkBoxForBackgroundChange(selectedAnswers.get(i));
                changeBackground.setBackgroundResource(R.drawable.quiz_rounded_button_red);
                mistakeCounter++;
            }
        }

        showCorrectAnswer();
        disableCheckboxes();
        questionNumber++;
        checkQuestionNumber();
    }

    /**
     * This method is called in the check results. It's designed to find which checkbox
     * was called clicked by comparing the selectedAnswer with the checkboxes strings.
     * @return
     */
    public CheckBox checkBoxForBackgroundChange(String selectedAnswer)
    {
        CheckBox value = null;

        if(selectedAnswer.equals(answerOption1.getText().toString()))
        {
            value = answerOption1;
        }
        if(selectedAnswer.equals(answerOption2.getText().toString()))
        {
            value = answerOption2;
        }
        if(selectedAnswer.equals(answerOption3.getText().toString()))
        {
            value = answerOption3;
        }
        if(selectedAnswer.equals(answerOption4.getText().toString()))
        {
            value = answerOption4;
        }
        if(selectedAnswer.equals(answerOption5.getText().toString()))
        {
            value = answerOption5;
        }
        if (selectedAnswer.equals(answerOption6.getText().toString()))
        {
            value = answerOption6;
        }

        return value;
    }


    /**
     * This method is called by checkResult() when a user clicks a button holding the wrong answer.
     * This method shows the user the button holding the correct answer by changing its color to green.
     */
    public void showCorrectAnswer(){
        if(correctAnswerList.contains(answerOption1.getText().toString())){
            answerOption1.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        if (correctAnswerList.contains(answerOption2.getText().toString())){
            answerOption2.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        if (correctAnswerList.contains(answerOption3.getText().toString())){
            answerOption3.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        if (correctAnswerList.contains(answerOption4.getText().toString())){
            answerOption4.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        if (correctAnswerList.contains(answerOption5.getText().toString())){
            answerOption5.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
        if (correctAnswerList.contains(answerOption6.getText().toString())){
            answerOption6.setBackgroundResource(R.drawable.quiz_rounded_button_green);
        }
    }


    /**
     * disables all the buttons to prevent the users to click a
     * second button while handler is pausing.
     */
    public  void disableCheckboxes(){
        answerOption1.setClickable(false);
        answerOption2.setClickable(false);
        answerOption3.setClickable(false);
        answerOption4.setClickable(false);
        answerOption5.setClickable(false);
        answerOption6.setClickable(false);
        answerOption1.setChecked(false);
        answerOption2.setChecked(false);
        answerOption3.setChecked(false);
        answerOption4.setChecked(false);
        answerOption5.setChecked(false);
        answerOption6.setChecked(false);
    }


    /**
     * If the question number is equal to 10 ((indicating that all the questions are over
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
            // Execute run() after 1 seconds have passed
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


    public void playAudio(View v) {
        try {
            stopAllSound();
        }catch (Exception e){}

        try {
            setAudio(currentQuestion.getName());
        } catch (Exception e) {
            textToSpeech.speak(currentQuestion.getName(), TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    /**
     * Stops all current sound
     * If media player is running this will stop
     **/
    public void stopAllSound() {
        if (player != null) {
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.w("Player released", "Audio Released");
            }
        }
    }



    public void setAudio(String address)throws IOException {
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
        this.finish();
        try {
        if(player != null){
            if (player.isPlaying()) {
                player.stop();
                player.reset();
                player.release();
                Log.d("Player released ", "Player Destroyed");
            }
        }}catch (Exception e){}
        //Close the Text to Speech Library
        if(textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");
        }
        super.onDestroy();
    }


}
