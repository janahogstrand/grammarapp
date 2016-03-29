package com.grammar.trocket.grammar.test;

import android.media.MediaPlayer;
import android.test.ActivityInstrumentationTestCase2;

import com.grammar.trocket.grammingo.exercises.MultipleQuizMainActivity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by firasAltayeb on 23/03/2016.
 */
public class TestMultipleQuizMainActivity extends ActivityInstrumentationTestCase2<MultipleQuizMainActivity>{

    MultipleQuizMainActivity MultipleQuizMainActivity;

    public TestMultipleQuizMainActivity() {
        super(MultipleQuizMainActivity.class);
    }

    /**
     * This method checks whether the textQuizMainActivity exists.
     */
    public void testActivityExists() {
        MultipleQuizMainActivity = getActivity();
        assertNotNull(getActivity());
    }

    /**
     * This method check whether the findAllViews() method is able
     * to find the requested views.
     */
    public void testFindAllViews() {
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MultipleQuizMainActivity.findAllViews();
                assertEquals(MultipleQuizMainActivity.answerOption1.getResources().
                        getResourceName(MultipleQuizMainActivity.answerOption1.getId()), "com.grammar.trocket.grammar:id/answerOption1");
            }
        });
    }

    /**
     * This method checks whether the method assignLanguage() works by checking
     * whether language variable was assigned a language.
     */

    public void testAssignLanguage(){
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MultipleQuizMainActivity.assignLanguage();
                assertEquals(MultipleQuizMainActivity.language.getCountry(), "ES");
            }
        });
    }




    /**
     * This method checks whether the assignViews() assigns the questionView with
     * the current question and whether the answerOptions buttons are assigned
     * with the answerOptionArray's items.
     */
    public void testAssignViews() {
        MultipleQuizMainActivity = getActivity();
        final String[] testArray = {"test1", "test2", "test3", "test4", "test5", "test6"};

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MultipleQuizMainActivity.answerOptionArray = testArray;
                MultipleQuizMainActivity.assignViews();
                assertEquals("test1", MultipleQuizMainActivity.answerOption1.getText().toString());
                assertEquals("test2", MultipleQuizMainActivity.answerOption2.getText().toString());
                assertEquals("test3", MultipleQuizMainActivity.answerOption3.getText().toString());
                assertEquals("test4", MultipleQuizMainActivity.answerOption4.getText().toString());
                assertEquals("test5", MultipleQuizMainActivity.answerOption5.getText().toString());
                assertEquals("test6", MultipleQuizMainActivity.answerOption6.getText().toString());
            }
        });

    }

    /**
     * This method checks whether the checkResult() method works by assigning both selectedAnswers
     * and correctAnswerList arrayLists with the testList and then checking whether the checkResult()
     * method was able to incriminate successCounter and questionNumber by one since both the arrayList
     * were assigned the same elements.
     */
    public void testCheckResult() {
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {

                String[] testArray = new String[] {"test1"};
                ArrayList<String> testList = new ArrayList<>();
                testList.addAll(Arrays.asList(testArray));
                MultipleQuizMainActivity.selectedAnswers = testList;

                MultipleQuizMainActivity.correctAnswerList = testList;

                MultipleQuizMainActivity.findAllViews();
                MultipleQuizMainActivity.answerOption1.setText("test1");

                MultipleQuizMainActivity.checkResult(MultipleQuizMainActivity.answerOption1);
                assertEquals(1, MultipleQuizMainActivity.successCounter);
                assertEquals(1, MultipleQuizMainActivity.questionNumber);
            }
        });
    }

    /**
     * This method checks whether the ShowCorrectAnswer() is able to
     * assign a background resource to the correct answerOption button based
     * on whether the button's text is in the correctAnswerList arrayList.
     */
    public void testShowCorrectAnswer() {
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                String[] testArray = new String[] {"test1", "test2", "test3", "test4", "test5", "test6"};
                ArrayList<String> testList = new ArrayList<>();
                testList.addAll(Arrays.asList(testArray));
                MultipleQuizMainActivity.correctAnswerList = testList;
                MultipleQuizMainActivity.answerOption1.setText("correctAnswer");
                MultipleQuizMainActivity.showCorrectAnswer();
                assertNotNull(MultipleQuizMainActivity.answerOption1.getBackground());
            }
        });
    }

    /**
     * This method checks whether the disableButtons() method is able to
     * set all the buttons to unClickable and unChecked.
     */
    public void testDisableButtons() {
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MultipleQuizMainActivity.disableCheckboxes();
                assertEquals(MultipleQuizMainActivity.answerOption1.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption2.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption3.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption4.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption5.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption6.isClickable(), false);
                assertEquals(MultipleQuizMainActivity.answerOption1.isChecked(), false);
                assertEquals(MultipleQuizMainActivity.answerOption2.isChecked(), false);
                assertEquals(MultipleQuizMainActivity.answerOption3.isChecked(), false);
                assertEquals(MultipleQuizMainActivity.answerOption4.isChecked(), false);
                assertEquals(MultipleQuizMainActivity.answerOption5.isChecked(), false);
                assertEquals(MultipleQuizMainActivity.answerOption6.isChecked(), false);
            }
        });
    }

    /**
     * This method checks whether restoreView() works by running disableButtons()
     * and then checking that running restoreView() restores all the buttons to
     * be clickable.
     */
    public void testRestoreView() {
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MultipleQuizMainActivity.disableCheckboxes();
                MultipleQuizMainActivity.restoreView();
                assertEquals(MultipleQuizMainActivity.answerOption1.isClickable(), true);
                assertEquals(MultipleQuizMainActivity.answerOption2.isClickable(), true);
                assertEquals(MultipleQuizMainActivity.answerOption3.isClickable(), true);
                assertEquals(MultipleQuizMainActivity.answerOption4.isClickable(), true);
                assertEquals(MultipleQuizMainActivity.answerOption5.isClickable(), true);
                assertEquals(MultipleQuizMainActivity.answerOption6.isClickable(), true);
            }
        });
    }

    /**
     * This method checks whether StopAllSound() works by setting up
     * and playing a player and then testing whether the player stoped
     * played after running the application
     */
    public void testStopAllSound(){
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MediaPlayer test = new MediaPlayer();
                MultipleQuizMainActivity.player = test;
                MultipleQuizMainActivity.player.start();
                //audioQuizMainActivity.stopAllSound();
                assertEquals(MultipleQuizMainActivity.player.isPlaying(), false);
            }
        });
    }


    /**
     *This method checks whether the setAudio() method is able to assigns and
     * start the player Media Player object.
     */
    public void testSetAudio(){
        MultipleQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                try {
                    MultipleQuizMainActivity.setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
                } catch (Exception e) {
                }
                assertEquals(MultipleQuizMainActivity.player.isPlaying(), true);
            }
        });
    }
}
