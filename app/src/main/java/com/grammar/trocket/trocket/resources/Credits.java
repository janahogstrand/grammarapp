package com.grammar.trocket.trocket.resources;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.trocket.main.MainMenu;

/**
 * Created by Sam on 24/03/2016.
 */
public class Credits extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Credits.this, MainMenu.class);
        startActivity(intent);
    }
}
