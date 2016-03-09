package com.grammar.trocket.grammar;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public Button btnA;
    public Button btnB;
    public Button btnChe; public Button btnc;
    public Button btnD;
    public Button btnE;
    public Button btnF;
    public Button btnG;
    public Button btnH;
    public Button btnI;
    public Button btnJ;
    public Button btnK;
    public Button btnL; public Button btnLL;
    public Button btnM;
    public Button btnN;  public Button btnn;
    public Button btnO;
    public Button btnP;
    public Button btnQ;
    public Button btnR;
    public Button btnS;
    public Button btnT;
    public Button btnU;
    public Button btnV;
    public Button btnW;
    public Button btnX;
    public Button btnY;
    public Button btnZ;
    public Button btninfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button one;
        one = (Button) this.findViewById(R.id.btnA);
        final MediaPlayer Amusic;
        Amusic = MediaPlayer.create(this, R.raw.abcd);

        one.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Amusic.start();

            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
    public void assignTextView(){

        btnA.setText("A a");
        btnB.setText("B b");
        btnChe.setText("C c");
        btnc.setText("C Che");
        btnD.setText("D d");
        btnE.setText("E e");
        btnF.setText("F f");
        btnG.setText("G g");
        btnH.setText("H h");
        btnI.setText("I i");
        btnJ.setText("J j");
        btnK.setText("K k");
        btnL.setText("L l");
        btnLL.setText("L ll");

       btnM.setText("M m");
        btnN.setText("N n");
        btnn.setText("Nn o");
        btnO.setText("O o");
        btnP.setText("btnP");
        btnQ.setText("btnQ");
        btnR.setText("btnR");
        btnS.setText("btnS");
        btnT.setText("btnT");
        btnU.setText("btnU");
        btnV.setText("btnV");
        btnW.setText("btnW");
        btnX.setText("btnX");
        btnY.setText("btnY");
        btnZ.setText("btnZ");
        btninfo.setText("btnInfo");



    }
}
