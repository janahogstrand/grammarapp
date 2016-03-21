package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;

public class SeasonsMain extends BaseActivityDrawer {

    Button springBtn;
    Button summerBtn;
    Button autumnBtn;
    Button winterBtn;


    TextView pageTitle;
    TextView subTitle;
    TextView engSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_main);
        super.onCreateDrawer();

        pageTitle = (TextView) findViewById(R.id.pageTitle);
        pageTitle.setText("Las Estaciones y los Meses del Ano");

        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText("Selecciona cada estacion y\naprende los nombres de los\nmeses del ano.");

        engSubTitle = (TextView) findViewById(R.id.engSubtitle);
        engSubTitle.setText("Select each season and learn the\nnames of the months");


        findAndAssignText();

    }

    /**
     * This method will find the buttons in the MainActivity by using their Ids
     * and then assign texts to them.
     */

    public void findAndAssignText() {
        springBtn = (Button) findViewById(R.id.springBtn);
        summerBtn = (Button) findViewById(R.id.summerBtn);
        autumnBtn = (Button) findViewById(R.id.autumnBtn);
        winterBtn = (Button) findViewById(R.id.winterBtn);

        springBtn.setText("Primavera");
        summerBtn.setText("Verano");
        autumnBtn.setText("Otono");
        winterBtn.setText("Invierno");
    }


    /**
     * This method moves the user to a new activity based on the button they
     * have clicked.
     * @param v
     */
    public void moveToNewActivity(View v){
        String idAsString = v.getResources().getResourceName(v.getId());
        if(idAsString.equals("com.grammar.trocket.grammar:id/springBtn")){
            startActivity(new Intent(SeasonsMain.this, SeasonsFirstActivity.class));
        }
        else if(idAsString.equals("com.grammar.trocket.grammar:id/summerBtn")){
            startActivity(new Intent(SeasonsMain.this, SeasonsSecondActivity.class));
        }
        else if(idAsString.equals("com.grammar.trocket.grammar:id/autumnBtn")){
            startActivity(new Intent(SeasonsMain.this, SeasonsThirdActivity.class));
        }
        else{
            startActivity(new Intent(SeasonsMain.this, SeasonsFourthActivity.class));
        }
    }

}
