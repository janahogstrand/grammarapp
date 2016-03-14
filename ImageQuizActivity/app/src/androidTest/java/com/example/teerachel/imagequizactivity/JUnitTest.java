package com.example.teerachel.imagequizactivity;

import junit.framework.TestCase;

/**
 * Created by ShristiM on 10/03/16.
 */
public class JUnitTest extends TestCase{

    MainActivity mainActivity;
    Image_Quiz_Ans imageans;
    Image_Quiz_Question imageques;
    Image_Quiz_Stats imagestats;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        mainActivity = new MainActivity();
        imageans = new Image_Quiz_Ans();
        imageques = new Image_Quiz_Question();
        imagestats = new Image_Quiz_Stats();
    }

    public void testFetch(){

    }

    @Override
    protected void tearDown() throws  Exception {
        super.tearDown();

    }
}
