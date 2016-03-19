package com.grammar.trocket.grammar.com.grammar.trocket.exercises.image_quiz;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import android.content.Intent;
import android.widget.TextView;
import android.widget.ImageView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.QuizStatisticsActivity;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz.TextQuizMainActivity;
import com.squareup.picasso.Picasso;


public class Image_Quiz_Main extends Activity {

    public ImageView firstView;
    public ImageView secondView;
    public ImageView thirdView;
    public ImageView fourthView;
    public ImageView fifthView;
    public ImageView sixthView;
    public TextView question;
    public Image_Quiz_Question questions;
    public Image_Quiz_Answers answers;
    public String[] questionList;
    public String correctAns;
    public String currentQuestion;
    public int successCounter = 0;
    public int mistakeCounter = 0;
    public int questionNumber = 0;
    public String imageAddress1;
    public String imageAddress2;
    public String imageAddress3;
    public String imageAddress4;
    public String imageAddress5;
    public String imageAddress6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_quiz_main);

        questions = new Image_Quiz_Question();
        answers = new Image_Quiz_Answers();
        questionList = questions.createArray();


        findAndAdjustAddresses();
        findViewByIds();
        assignViews();
        setImages();

    }

    public void findAndAdjustAddresses(){
        imageAddress1 = "https://www.dropbox.com/s/na7kxqo36le8elu/aeroplane.png?dl=0";
        imageAddress2 = "https://www.dropbox.com/s/dnc4e8jppzlmw72/car.png?dl=0";
        imageAddress3 = "https://www.dropbox.com/s/6om2t6p7zmcyk65/train.png?dl=0";
        imageAddress4 = "https://www.dropbox.com/s/sriywah61eygdnb/bus.png?dl=0";
        imageAddress5 = "https://www.dropbox.com/s/qu3njgt1nbetw5j/cycle.png?dl=0";
        imageAddress6 = "https://www.dropbox.com/s/dywmen629grh3wm/ship.png?dl=0";

        imageAddress1 = imageAddress1.substring(0, imageAddress1.length()-4) + "raw=1";
        imageAddress2 = imageAddress2.substring(0, imageAddress2.length()-4) + "raw=1";
        imageAddress3 = imageAddress3.substring(0, imageAddress3.length()-4) + "raw=1";
        imageAddress4 = imageAddress4.substring(0, imageAddress4.length()-4) + "raw=1";
        imageAddress5 = imageAddress5.substring(0, imageAddress5.length()-4) + "raw=1";
        imageAddress6 = imageAddress6.substring(0, imageAddress6.length()-4) + "raw=1";

    }

    public void findViewByIds(){
        question = (TextView) findViewById(R.id.questionBtn);

        firstView = (ImageView) findViewById(R.id.firstView);
        secondView = (ImageView) findViewById(R.id.secondView);
        thirdView = (ImageView) findViewById(R.id.thirdView);
        fourthView = (ImageView) findViewById(R.id.fourthView);
        fifthView = (ImageView) findViewById(R.id.fifthView);
        sixthView = (ImageView) findViewById(R.id.sixthView);
    }


    /**
     * The TextView is assigned the current question and the each of the button
     * are assigned an answer option for the current question.
     * */
    public void assignViews(){
        currentQuestion = questionList[questionNumber];
        correctAns = answers.getCorrectAnswer(currentQuestion);
        question.setText(currentQuestion);
    }


    public void setImages(){
        Picasso.with(firstView.getContext()).load(imageAddress1).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(firstView);

        Picasso.with(secondView.getContext()).load(imageAddress2).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(secondView);

        Picasso.with(thirdView.getContext()).load(imageAddress3).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(thirdView);

        Picasso.with(fourthView.getContext()).load(imageAddress4).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(fourthView);

        Picasso.with(fifthView.getContext()).load(imageAddress5).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(fifthView);

        Picasso.with(sixthView.getContext()).load(imageAddress6).placeholder(R.drawable.loading_animation)
                .error(android.R.drawable.stat_notify_error).into(sixthView);
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

        if(correctAns.equals(view.getTag().toString())) {
            successCounter++;
            Toast.makeText(Image_Quiz_Main.this,
                    "You're correct!", Toast.LENGTH_LONG).show();
        }
        else {
            mistakeCounter++;
            Toast.makeText(Image_Quiz_Main.this,
                    "Sorry, try again :(", Toast.LENGTH_LONG).show();
        }

        Log.d("pressedView", view.getTag().toString() );
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
                    assignViews();
                }
            }, 1000);
        }
    }


}
