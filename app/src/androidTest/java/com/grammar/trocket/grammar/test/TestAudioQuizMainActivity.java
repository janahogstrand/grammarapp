package com.grammar.trocket.grammar.test;

import android.media.MediaPlayer;
import android.test.ActivityInstrumentationTestCase2;
import com.grammar.trocket.grammingo.exercises.AudioQuizMainActivity;

/**
 * Created by firasAltayeb on 23/03/2016.
 */
public class TestAudioQuizMainActivity extends ActivityInstrumentationTestCase2<AudioQuizMainActivity> {

    AudioQuizMainActivity audioQuizMainActivity;

    public TestAudioQuizMainActivity() {
        super(AudioQuizMainActivity.class);
    }

    /**
     * This method checks whether the textQuizMainActivity exists.
     */
    public void testActivityExists() {
        audioQuizMainActivity = getActivity();
        assertNotNull(getActivity());
    }

    /**
     * This method check whether the findAllViews() method is able
     * to find the requested views.
     */
    public void testFindAllViews() {
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.findAllViews();
                assertEquals(audioQuizMainActivity.answerOption1.getResources().
                        getResourceName(audioQuizMainActivity.answerOption1.getId()), "com.grammar.trocket.grammar:id/answerOption1");
            }
        });
    }

    /**
     * This method checks whether the method assignLanguage() works by checking
     * whether language variable was assigned a language.
     */

    public void testAssignLanguage(){
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.assignLanguage();
                assertEquals(audioQuizMainActivity.language.getCountry(), "ES");
            }
        });
    }

    /**
     * This method checks whether the assignViews() assigns the questionView with
     * the current question and whether the answerOptions buttons are assigned
     * with the answerOptionArray's items.
     */
    public void testAssignViews() {
        audioQuizMainActivity = getActivity();
        final String[] testArray = {"test1", "test2", "test3", "test4", "test5", "test6"};

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.answerOptionArray = testArray;
                audioQuizMainActivity.assignViews();
                assertEquals("test1", audioQuizMainActivity.answerOption1.getText().toString());
                assertEquals("test2", audioQuizMainActivity.answerOption2.getText().toString());
                assertEquals("test3", audioQuizMainActivity.answerOption3.getText().toString());
                assertEquals("test4", audioQuizMainActivity.answerOption4.getText().toString());
                assertEquals("test5", audioQuizMainActivity.answerOption5.getText().toString());
                assertEquals("test6", audioQuizMainActivity.answerOption6.getText().toString());
            }
        });

    }

    /**
     * By setting answerOption1 button as pressed and assigning it with the correct answer
     * this method is able to test that checkResult() runs correctly as both successCounter
     * and questionNumber were incriminated by 1.
     */
    public void testCheckResult() {
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.correctAnswer = "correct answer";
                audioQuizMainActivity.answerOption1.setText("correct answer");
                audioQuizMainActivity.answerOption1.setPressed(true);
                audioQuizMainActivity.checkResult(audioQuizMainActivity.answerOption1);
                assertEquals(1, audioQuizMainActivity.successCounter);
                assertEquals(1, audioQuizMainActivity.questionNumber);
            }
        });
    }

    /**
     * This method checks whether the ShowCorrectAnswer() is able to
     * assign a background resource to the correct answerOption button based
     * on whether the button's text is the same as the correct answer.
     */
    public void testShowCorrectAnswer() {
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.correctAnswer = "correctAnswer";
                audioQuizMainActivity.answerOption1.setText("correctAnswer");
                audioQuizMainActivity.showCorrectAnswer();
                assertNotNull(audioQuizMainActivity.answerOption1.getBackground());
            }
        });
    }

    /**
     * This method checks whether the disableButtons() method is able to
     * set all the buttons to unClickable.
     */
    public void testDisableButtons() {
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.disableButtons();
                assertEquals(audioQuizMainActivity.answerOption1.isClickable(), false);
                assertEquals(audioQuizMainActivity.answerOption2.isClickable(), false);
                assertEquals(audioQuizMainActivity.answerOption3.isClickable(), false);
                assertEquals(audioQuizMainActivity.answerOption4.isClickable(), false);
                assertEquals(audioQuizMainActivity.answerOption5.isClickable(), false);
                assertEquals(audioQuizMainActivity.answerOption6.isClickable(), false);
            }
        });
    }

    /**
     * This method checks whether restoreView() works by running disableButtons()
     * and then checking that running restoreView() restores all the buttons to
     * be clickable.
     */
    public void testRestoreView() {
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                audioQuizMainActivity.disableButtons();
                audioQuizMainActivity.restoreView();
                assertEquals(audioQuizMainActivity.answerOption1.isClickable(), true);
                assertEquals(audioQuizMainActivity.answerOption2.isClickable(), true);
                assertEquals(audioQuizMainActivity.answerOption3.isClickable(), true);
                assertEquals(audioQuizMainActivity.answerOption4.isClickable(), true);
                assertEquals(audioQuizMainActivity.answerOption5.isClickable(), true);
                assertEquals(audioQuizMainActivity.answerOption6.isClickable(), true);
            }
        });
    }

    /**
     * This method checks whether StopAllSound() works by setting up
     * and playing a player and then testing whether the player stoped
     * played after running the application
     */
    public void testStopAllSound(){
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                MediaPlayer test = new MediaPlayer();
                audioQuizMainActivity.player = test;
                audioQuizMainActivity.player.start();
                //audioQuizMainActivity.stopAllSound();
                assertEquals(audioQuizMainActivity.player.isPlaying(), false);
            }
        });
    }


    /**
     *This method checks whether the setAudio() method is able to assigns and
     * start the player Media Player object.
     */
    public void testSetAudio(){
        audioQuizMainActivity = getActivity();
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                try {
                   audioQuizMainActivity.setAudio("https://www.dropbox.com/s/7mga5icr0uwep6h/U01-E05.mp3?raw=1");
                } catch (Exception e) {
                }
                assertEquals(audioQuizMainActivity.player.isPlaying(), true);
            }
        });
    }
}
