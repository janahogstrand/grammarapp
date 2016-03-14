package com.example.teerachel.imagequizactivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;


/**
 * Created by teerachel on 10/03/2016.
 */
public class Testing  extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity mActivity;
    Image_Quiz_Ques Ques;
    private int successCounter = 0;
    public String correctAns;

    public Testing() {
        super(MainActivity.class);
        // super(Image_Quiz_Ques.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);

        mActivity = getActivity();


        assertNotNull(mActivity);


    }

    public void testTextViewNotNull() {
        TextView question = (TextView) mActivity.findViewById(R.id.questionBtn);
        assertNotNull(question);
    }

    public void testButtonsNotNull() {
        Button train = (Button) mActivity.findViewById(R.id.firstBtn);
        assertNotNull(train);
        Button plane = (Button) mActivity.findViewById(R.id.planeImage);
        assertNotNull(plane);
        Button bus = (Button) mActivity.findViewById(R.id.thirdBtn);
        assertNotNull(bus);
        Button car = (Button) mActivity.findViewById(R.id.fourthBtn);
        assertNotNull(car);
        Button bike = (Button) mActivity.findViewById(R.id.bikeImage);
        assertNotNull(bike);
        Button ship = (Button) mActivity.findViewById(R.id.shipImage);
        assertNotNull(ship);
    }

    /**
     * This tests the button clicked
     */
    public void testClickActionModeItem() {
        onView(withId(R.id.firstBtn)).perform(click());
        onView(withId(R.id.firstBtn)).check(matches(withText("train")));


    }

    public void testEspresso() {
        onView(withId(R.id.questionBtn))
                .check(matches(allOf(withText(not(startsWith("Tren"))), withText(containsString("1.")))));

    }
}