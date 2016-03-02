package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeItem;
import com.grammar.trocket.grammar.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by jamiemoreland on 27/02/16.
 * <p/>
 * This class should be used when an instance of the
 * Festival resources activity is needed
 * data can retrieved from the database and
 * views will then be inflated
 *
 * @see BigView
 */
public class Festivals extends AppCompatActivity {
    private List<FestivalTimeItem> festData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        GridLayoutManager glm = new GridLayoutManager(Festivals.this, 3);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);


        FestivalTimeAdapter festivalTimeAdapter = new FestivalTimeAdapter(getData());
        rv.setAdapter(festivalTimeAdapter);
    }


    /**
     * Gets data to pass to adapter
     * //TODO make this interact with database
     *
     * @see FestivalTimeAdapter
     **/
    private List<FestivalTimeItem> getData() {
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
