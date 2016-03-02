package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {

    Button OrgOne;
    Button OrgTwo;
    Button OrgThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        OrgOne = (Button) findViewById(R.id.option4);
        OrgTwo = (Button) findViewById(R.id.option5);
        OrgThree = (Button) findViewById(R.id.option6);

        assignText();
    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */
    public void assignText() {
        OrgOne.setText("junio");
        OrgTwo.setText("julio");
        OrgThree.setText("agosto");
    }

}
