package com.grammar.trocket.grammar.test;

import android.test.ActivityInstrumentationTestCase2;
import com.grammar.trocket.grammingo.exercises.ImageQuizMainActivity;
import com.grammar.trocket.grammingo.exercises.Question;

/**
 * Created by firasAltayeb on 24/03/2016.
 */
public class TestImageQuizMainActivity extends ActivityInstrumentationTestCase2<ImageQuizMainActivity> {

    ImageQuizMainActivity imageQuizMainActivity;

    public TestImageQuizMainActivity() {
        super(ImageQuizMainActivity.class);
    }
    /**
     * This method checks whether the textQuizMainActivity exists.
     */
    public void testActivityExists() {
        imageQuizMainActivity = getActivity();
        assertNotNull(getActivity());
    }

    /**
     * This method check whether the findAllViews() method is able
     * to find the requested views.
     */
    public void testFindAllViews() {
        imageQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                imageQuizMainActivity.findAllViews();
                assertEquals(imageQuizMainActivity.questionView.getResources().
                        getResourceName(imageQuizMainActivity.questionView.getId()), "com.grammar.trocket.grammar:id/question");
            }
        });
    }

    /**
     * This method checks whether the assignViews() assigns the questionView with
     * the current question and whether the answerOptions buttons are assigned
     * with the answerOptionArray's items.
     */
    public void testAssignViews() {
        imageQuizMainActivity = getActivity();
        final String[] testArray = {"test1", "test2", "test3", "test4", "test5", "test6"};

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Question test = new Question("test",10);
                imageQuizMainActivity.currentQuestion = test;
                imageQuizMainActivity.answerOptionArray = testArray;
                imageQuizMainActivity.assignViews();
                assertEquals("currentQuestion", imageQuizMainActivity.questionView.getText().toString());
            }
        });

    }

    /**
     * By setting answerOption1 button as pressed and assigning it with the correct answer
     * this method is able to test that checkResult() runs correctly as both successCounter
     * and questionNumber were incriminated by 1.
     */
    public void testCheckResult() {
        imageQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                imageQuizMainActivity.correctAnswer = "correct answer";
                imageQuizMainActivity.answerOption1.setPressed(true);
                imageQuizMainActivity.checkResult(imageQuizMainActivity.answerOption1);
                assertEquals(1, imageQuizMainActivity.successCounter);
                assertEquals(1, imageQuizMainActivity.questionNumber);
            }
        });
    }


}