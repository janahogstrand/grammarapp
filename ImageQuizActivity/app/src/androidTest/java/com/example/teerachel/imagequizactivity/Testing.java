package com.example.teerachel.imagequizactivity;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by teerachel on 10/03/2016.
 */
public class Testing  extends ActivityInstrumentationTestCase2<MainActivity>{

    MainActivity mActivity;
    private Button train;
    private int successCounter;

    public Testing(){
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        setActivityInitialTouchMode(true);

        mActivity = getActivity();
        assertNotNull(mActivity);

    }

    public void testTextViewNotNull(){
        TextView question = (TextView) mActivity.findViewById(R.id.ques);
        assertNotNull(question);
    }

    public void testButtonsNotNull(){
        Button train = (Button) mActivity.findViewById(R.id.trainImage);
        assertNotNull(train);
        Button plane = (Button) mActivity.findViewById(R.id.planeImage);
        assertNotNull(plane);
        Button bus = (Button) mActivity.findViewById(R.id.busImage);
        assertNotNull(bus);
        Button car = (Button) mActivity.findViewById(R.id.carImage);
        assertNotNull(car);
        Button bike = (Button) mActivity.findViewById(R.id.bikeImage);
        assertNotNull(bike);
        Button ship = (Button) mActivity.findViewById(R.id.shipImage);
        assertNotNull(ship);
    }

//    public void testButtonClick(){
//        mActivity.runOnUiThread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        train.performClick();
//                        successCounter++;
//                        assertEquals(successCounter, MainActivity.class);
//                    }
//                }
//        );
//    }


}
