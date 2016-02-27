package com.grammar.trocket.grammar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jamiemoreland on 27/02/16.
 */
public class Times extends AppCompatActivity {
    private List<FestivalTimeItem> timeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        GridLayoutManager glm = new GridLayoutManager(Times.this, 3);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);



        FestivalTimeAdapter festivalTimeAdapter = new FestivalTimeAdapter(getData());
        rv.setAdapter(festivalTimeAdapter);
    }



    private List<FestivalTimeItem> getData(){
        timeData = new ArrayList<FestivalTimeItem>();
        timeData.add(new FestivalTimeItem("El is a time", "This is a a time", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("El time", "This is a time", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Test", "The Test", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("uber festival", "Wow much festival", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("im trapped in a text view", "much test", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        timeData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        timeData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        timeData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));

        return timeData;
    }
}
