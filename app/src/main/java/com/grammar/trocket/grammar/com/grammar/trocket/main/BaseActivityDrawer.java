package com.grammar.trocket.grammar.com.grammar.trocket.main;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleItem;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.Credits;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.VoiceRecording;

/**
 * Created by jamiemoreland on 07/03/16.
 */
public class BaseActivityDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //Voice Recorder Variables
    private VoiceRecording v = new VoiceRecording();
    private boolean startRecording = true;
    private boolean startPlaying = true;

    protected void onCreateDrawer() {
        //super.onCreate(savedInstanceState);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Back press for drawer
     **/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * Getting drawer items
     **/
    @SuppressWarnings("StatementWithEmptyBody")
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_add_module) {
            //closeDrawer();
            Intent intent = new Intent(this, ModuleSelection.class);
            startActivity(intent);
        }

        if (id == R.id.nav_credits) {
            closeDrawer();
        }


        if (id == R.id.nav_record_audio) {
            if (startRecording) {
                v.OnRecord(true, this.getApplicationContext());
                item.setTitle("Stop Recording");
                startRecording = false;
            } else {
                v.OnRecord(false, this.getApplicationContext());
                item.setTitle("Record Audio");
                startRecording = true;
            }
        }

        if (id == R.id.nav_play_audio) {
            if (startPlaying) {
                v.onPlay(true);
                item.setTitle("Stop Playing");
                startPlaying = false;
            } else {
                v.onPlay(false);
                item.setTitle("Play Recording");
                startPlaying = true;
            }
        }

        if (id == R.id.nav_credits){
            Intent intent = new Intent(BaseActivityDrawer.this, Credits.class);
            startActivity(intent);
        }

        return true;
    }

    public void closeDrawer() {
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
    }

}
