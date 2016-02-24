package com.grammar.trocket.grammar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Festivals extends AppCompatActivity {
    private List<FestivalItem> festData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivals);
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);

        GridLayoutManager glm = new GridLayoutManager(Festivals.this, 3);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);



        FestivalAdapter festivalAdapter = new FestivalAdapter(getData());
        rv.setAdapter(festivalAdapter);
    }

    private List<FestivalItem> getData(){
        festData = new ArrayList<FestivalItem>();
        festData.add(new FestivalItem("Name", R.drawable.placeholder));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));
        festData.add(new FestivalItem("Name", R.drawable.placeholder));
        festData.add(new FestivalItem("Name", R.drawable.placeholder2));
        festData.add(new FestivalItem("Name", R.drawable.placeholder3));

        return festData;
    }
}
