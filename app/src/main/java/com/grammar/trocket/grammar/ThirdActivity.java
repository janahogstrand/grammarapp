package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {


    Button redOne;
    Button redTwo;
    Button redThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        redOne = (Button) findViewById(R.id.option7);
        redTwo = (Button) findViewById(R.id.option8);
        redThree = (Button) findViewById(R.id.option9);

        assignText();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        redOne.setText("septiembre");
        redTwo.setText("octubre");
        redThree.setText("noviembre");
    }

}

