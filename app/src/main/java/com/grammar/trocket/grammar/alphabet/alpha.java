package com.grammar.trocket.grammar.alphabet;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class alpha extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alpha);

        Button btnA = (Button) this.findViewById(R.id.btnA);
        final MediaPlayer A = MediaPlayer.create(this, R.raw.A);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                A.start();
            }
        });

    }
}
