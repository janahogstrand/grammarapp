package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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
        setContentView(R.layout.seasons_main_activity);
        super.onCreateDrawer();

        pageTitle = (TextView) findViewById(R.id.pageTitle);
        pageTitle.setText("Las Estaciones y los Meses del Ano");

        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText("Selecciona cada estacion y\naprende los nombres de los\nmeses del ano.");

        engSubTitle = (TextView) findViewById(R.id.engSubtitle);
        engSubTitle.setText("Select each season and learn the\nnames of the months");


        findAndAssignText();

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
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
//
//    @Override
//    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//
//    }
//
//    @SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        }
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }

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
