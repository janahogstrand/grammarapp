package com.mycompany.videotesttwo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button, button2;
    public final static String EXTRA_MESSAGE = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialise buttons
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);

    }

    public void onButtonClick(View view) {

        // start new youtube activity and pass the video address to it
        Intent intent = new Intent(this, Video.class);
        String address = "xLSgm06J6sM";

        intent.putExtra(EXTRA_MESSAGE, address);
        startActivity(intent);

    }

    public void onButton2Click(View view) {

        // start new youtube activity and pass the video address to it
        Intent intent = new Intent(this, Video.class);
        String address = "qyXTgqJtoGM";

        intent.putExtra(EXTRA_MESSAGE, address);
        startActivity(intent);

    }

}
