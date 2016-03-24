package com.grammar.trocket.grammar.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grammar.trocket.grammar.com.grammar.trocket.exercises.QuizStatisticsActivity;

/**
 * Created by firasAltayeb on 24/03/2016.
 */
public class TestQuizStatisticsActivity extends ActivityInstrumentationTestCase2<QuizStatisticsActivity> {

    QuizStatisticsActivity quizStatisticsActivity;

    public TestQuizStatisticsActivity() {
        super(QuizStatisticsActivity.class);
    }

    /**
     * This method checks whether the textQuizMainActivity exists.
     */
    public void testActivityExists() {
        quizStatisticsActivity = getActivity();
        assertNotNull(getActivity());
    }


    /**
     * This method checks that initializeComment is able to change the
     * comment String based on the result received from the quizzes
     */
    public void testInitializeComment(){
        quizStatisticsActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                quizStatisticsActivity.message = "6";
                quizStatisticsActivity.initializeComment();
                assertEquals("Good job)", quizStatisticsActivity.comment);
                quizStatisticsActivity.message = "2";
                quizStatisticsActivity.initializeComment();
                assertEquals("Try harder", quizStatisticsActivity.comment);

            }
        });

    }




}
