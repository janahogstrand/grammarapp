package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.recyclerview.FestivalTimeItem;
import com.grammar.trocket.grammar.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


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
public class Festivals extends BaseActivityDrawer {
    private List<FestivalTimeItem> festData;
    String dialect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime_main);
        onCreateDrawer();

        getDialect();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        GridLayoutManager glm = new GridLayoutManager(Festivals.this, 3);
        rv.setLayoutManager(glm);
        rv.setHasFixedSize(true);


        FestivalTimeAdapter festivalTimeAdapter = new FestivalTimeAdapter(getData());
        rv.setAdapter(festivalTimeAdapter);
    }

    public void getDialect(){
        Intent intent = getIntent();
        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
    }

    /**
     * Gets data to pass to adapter
     * //TODO make this interact with database
     *
     * @see FestivalTimeAdapter
     **/
    private List<FestivalTimeItem> getData() {
        festData = new ArrayList<FestivalTimeItem>();
     //   festData.add(new FestivalTimeItem("Uno de enero - Ano nuevo", "New Year's day", R.drawable.festivals3, dialect));
        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", "https://www.dropbox.com/s/5zmf04aoj3hr8ml/festivals0?raw=1", dialect));
        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day","https://www.dropbox.com/s/5zmf04aoj3hr8ml/festivals1.png?raw=1", dialect));
        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", "https://www.dropbox.com/s/5zmf04aoj3hr8ml/festivals2.png?raw=1", dialect));


        return festData;
    }
}
