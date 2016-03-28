package com.grammar.trocket.grammar.test;

import android.test.ActivityInstrumentationTestCase2;

import com.grammar.trocket.trocket.exercises.Question;
import com.grammar.trocket.trocket.exercises.TextQuizMainActivity;

/**
 * Created by firasAltayeb on 22/03/2016.
 */
public class TestTextQuizMainActivity extends ActivityInstrumentationTestCase2<TextQuizMainActivity> {

    TextQuizMainActivity textQuizMainActivity;

    public TestTextQuizMainActivity() {
        super(TextQuizMainActivity.class);
    }

    /**
     * This method checks whether the textQuizMainActivity exists.
     */
    public void testActivityExists() {
        textQuizMainActivity = getActivity();
        assertNotNull(getActivity());
    }

    /**
     * This method check whether the findAllViews() method is able
     * to find the requested views.
     */
    public void testFindAllViews() {
        textQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                textQuizMainActivity.findAllViews();
                assertEquals(textQuizMainActivity.questionView.getResources().
                        getResourceName(textQuizMainActivity.questionView.getId()), "com.grammar.trocket.grammar:id/question");
            }
        });
    }

    /**
     * This method checks whether the assignViews() assigns the questionView with
     * the current question and whether the answerOptions buttons are assigned
     * with the answerOptionArray's items.
     */
    public void testAssignViews() {
        textQuizMainActivity = getActivity();
        final String[] testArray = {"test1", "test2", "test3", "test4", "test5", "test6"};

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Question test = new Question("test",10);
                textQuizMainActivity.currentQuestion = test;
                textQuizMainActivity.answerOptionArray = testArray;
                textQuizMainActivity.assignViews();
                assertEquals("currentQuestion", textQuizMainActivity.questionView.getText().toString());
                assertEquals("test1", textQuizMainActivity.answerOption1.getText().toString());
                assertEquals("test2", textQuizMainActivity.answerOption2.getText().toString());
                assertEquals("test3", textQuizMainActivity.answerOption3.getText().toString());
                assertEquals("test4", textQuizMainActivity.answerOption4.getText().toString());
                assertEquals("test5", textQuizMainActivity.answerOption5.getText().toString());
                assertEquals("test6", textQuizMainActivity.answerOption6.getText().toString());
            }
        });

    }

    /**
     * By setting answerOption1 button as pressed and assigning it with the correct answer
     * this method is able to test that checkResult() runs correctly as both successCounter
     * and questionNumber were incriminated by 1.
     */
    public void testCheckResult() {
        textQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                textQuizMainActivity.correctAnswer = "correct answer";
                textQuizMainActivity.answerOption1.setText("correct answer");
                textQuizMainActivity.answerOption1.setPressed(true);
                textQuizMainActivity.checkResult(textQuizMainActivity.answerOption1);
                assertEquals(1, textQuizMainActivity.successCounter);
                assertEquals(1, textQuizMainActivity.questionNumber);
            }
        });
    }


    /**
     * This method checks whether the ShowCorrectAnswer() is able to
     * assign a background resource to the correct answerOption button based
     * on whether the button's text is the same as the correct answer.
     */
    public void testShowCorrectAnswer() {
        textQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                textQuizMainActivity.correctAnswer = "correctAnswer";
                textQuizMainActivity.answerOption1.setText("correctAnswer");
                textQuizMainActivity.showCorrectAnswer();
                assertNotNull(textQuizMainActivity.answerOption1.getBackground());
            }
        });
    }


    /**
     * This method checks whether the disableButtons() method is able to
     * set all the buttons to unClickable.
     */
    public void testDisableButtons() {
        textQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                textQuizMainActivity.disableButtons();
                assertEquals(textQuizMainActivity.answerOption1.isClickable(), false);
                assertEquals(textQuizMainActivity.answerOption2.isClickable(), false);
                assertEquals(textQuizMainActivity.answerOption3.isClickable(), false);
                assertEquals(textQuizMainActivity.answerOption4.isClickable(), false);
                assertEquals(textQuizMainActivity.answerOption5.isClickable(), false);
                assertEquals(textQuizMainActivity.answerOption6.isClickable(), false);
            }
        });
    }

    /**
     * This method checks whether restoreView() works by running disableButtons()
     * and then checking that running restoreView() restores all the buttons to
     * be clickable.
     */
    public void testRestoreView() {
        textQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                textQuizMainActivity.disableButtons();
                textQuizMainActivity.restoreView();
                assertEquals(textQuizMainActivity.answerOption1.isClickable(), true);
                assertEquals(textQuizMainActivity.answerOption2.isClickable(), true);
                assertEquals(textQuizMainActivity.answerOption3.isClickable(), true);
                assertEquals(textQuizMainActivity.answerOption4.isClickable(), true);
                assertEquals(textQuizMainActivity.answerOption5.isClickable(), true);
                assertEquals(textQuizMainActivity.answerOption6.isClickable(), true);
            }
        });
    }
}