package com.grammar.trocket.grammar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity {

    Button firstButton;
    Button secButton;
    Button thirdButton;
    Button fourthButton;

    TextView pageTitle;
    TextView subTitle;
    TextView engSubTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        /**
         * TextViews
         * TextViews for pageTitle, subTitle and engSubTitle
         * pageTitle and subTitle consists of multiple lines.
         * The engSubTitle will be displayed in blue.
         */
        pageTitle = (TextView) findViewById(R.id.pageTitle);
        pageTitle.setText("Las Estaciones y los Meses del Ano");

        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText("Selecciona cada estacion y\naprende los nombres de los\nmeses del ano.");

        engSubTitle = (TextView) findViewById(R.id.engSubtitle);
        engSubTitle.setText("Select each season and learn the\nnames of the months");


        /**
         *  Buttons
         *  Buttons to direct respective seasons to their months
         *  opening new intents
         */
        firstButton = (Button) findViewById(R.id.springBtn);
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, FirstActivity.class));
            }
        });

        secButton = (Button) findViewById(R.id.summerBtn);
        secButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, SecondActivity.class));
            }
        });

        thirdButton = (Button) findViewById(R.id.autumnBtn);
        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, ThirdActivity.class));
            }
        });

        fourthButton = (Button) findViewById(R.id.winterBtn);
        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu.this, FourthActivity.class));
            }
        });

        assignText();

    }

    /**
     * This method will assign texts to the four main buttons
     * in the Main content.
     */

    public void assignText() {
        firstButton.setText("Primavera");
        secButton.setText("Verano");
        thirdButton.setText("Otono");
        fourthButton.setText("Invierno");
    }

}

