package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class FirstActivity extends AppCompatActivity {
    Button pinkOne;
    Button pinkTwo;
    Button pinkThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        pinkOne = (Button) findViewById(R.id.option1);
        pinkTwo = (Button) findViewById(R.id.option2);
        pinkThree = (Button) findViewById(R.id.option3);

        assignText();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        pinkOne.setText("marzo");
        pinkTwo.setText("abril");
        pinkThree.setText("mayo");
    }

}

