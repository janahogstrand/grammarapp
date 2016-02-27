package com.grammar.trocket.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Festivals extends AppCompatActivity {
    private List<FestivalTimeItem> festData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        GridLayoutManager glm = new GridLayoutManager(Festivals.this, 3);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);



        FestivalTimeAdapter festivalTimeAdapter = new FestivalTimeAdapter(getData());
        rv.setAdapter(festivalTimeAdapter);
    }



    private List<FestivalTimeItem> getData(){
        festData = new ArrayList<FestivalTimeItem>();
        festData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Test", "The Test", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("uber festival", "Wow much festival", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("im trapped in a text view", "much test", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Test", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Test", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));
        festData.add(new FestivalTimeItem("Es Box", "The Box", R.drawable.placeholder));
        festData.add(new FestivalTimeItem("Es Brits", "The Brits", R.drawable.placeholder2));
        festData.add(new FestivalTimeItem("El Indianas", "India", R.drawable.placeholder3));

        return festData;
    }
}
