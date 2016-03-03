package com.grammar.trocket.grammar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        setContentView(R.layout.main_menu_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
