package com.grammar.trocket.grammar.com.grammar.trocket.exercises.audio_quiz;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.QuizStatisticsActivity;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz.TextQuizMainActivity;

import java.io.IOException;
import java.util.Locale;

public class AudioQuizMainActivity extends Activity {


    public Button answerOption1;
    public Button answerOption2;
    public Button answerOption3;
    public Button answerOption4;
    public Button answerOption5;
    public Button answerOption6;
    public AudioQuizQuestionsList questionsAddresses;
    public AudioQuizAnswerList answersList;
    public String[] questionsAddressesArray;
    public String correctAnswer;
    public String currentQuestionAddress;
    public String[] answerOptionArray;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;

    TextToSpeech textToSpeech;
    Locale language;
    public MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_quiz_main);

        answerOption1 = (Button) findViewById(R.id.answerOption1);
        answerOption2 = (Button) findViewById(R.id.answerOption2);
        answerOption3 = (Button) findViewById(R.id.answerOption3);
        answerOption4 = (Button) findViewById(R.id.answerOption4);
        answerOption5 = (Button) findViewById(R.id.answerOption5);
        answerOption6 = (Button) findViewById(R.id.answerOption6);

        questionsAddresses = new AudioQuizQuestionsList();
        answersList = new AudioQuizAnswerList();
        questionsAddressesArray = questionsAddresses.createArray();
        assignVariables();
        assignTextView();
        assignLanguage();
    }

    /** 
     * This method determines which language will be used by the textToSpeech API/object 
     * and then sets it for the textToSpeech instance/variable. 
     */
    public void assignLanguage(){
        language = new Locale("es", "ES");
        textToSpeech=new TextToSpeech(AudioQuizMainActivity.this, new TextToSpeech.OnInitListener() {
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
        currentQuestionAddress = questionsAddressesArray[questionNumber];
        answerOptionArray = answersList.getAnswerOptions(currentQuestionAddress);
        correctAnswer = answersList.getCorrectAnswer(currentQuestionAddress);
    }

    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignTextView(){
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
        try {
            stopAllSound();
        }catch (Exception e){}

        Log.d("Answer Given:", pressedButton.getText().toString());
        if(correctAnswer.equals(view.getTag().toString())) {
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
     * If the question number is equal to 10 ((indicating that all the questions are over
     * an intent sends the user to the statics screen with message which changes according to
     * how the user performed in the quiz.
     */
    public void checkQuestionNumber(){
        if(questionNumber == 5){
            Intent intent = new Intent(this, QuizStatisticsActivity.class);
            intent.putExtra(TextQuizMainActivity.EXTRA_MESSAGE, ""+successCounter);
            intent.putExtra(TextQuizMainActivity.EXTRA_MESSAGE2, ""+mistakeCounter);
            startActivity(intent);
        }
        else {
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

    /**
     * Colors all the button's background to white thus
     * returning it to its original color and then set the buttons
     * to be clickable.
     */
    public void restoreColor(){
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
            setAudio(currentQuestionAddress);
        } catch (Exception e) {
            textToSpeech.speak(currentQuestionAddress, TextToSpeech.QUEUE_FLUSH, null);
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
