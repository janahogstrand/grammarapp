package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class FourthActivity extends AppCompatActivity {


    Button blueOne;
    Button blueTwo;
    Button blueThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        blueOne = (Button) findViewById(R.id.option10);
        blueTwo = (Button) findViewById(R.id.option11);
        blueThree = (Button) findViewById(R.id.option12);

        assignText();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        blueOne.setText("diciembre");
        blueTwo.setText("enero");
        blueThree.setText("febrero");
    }

}