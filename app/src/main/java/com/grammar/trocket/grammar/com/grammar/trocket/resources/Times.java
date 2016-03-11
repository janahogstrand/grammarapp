package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.content.Intent;
import android.os.Bundle;
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

/**
 * Created by jamiemoreland on 27/02/16.
 * <p/>
 * This class should be used when an instance of the
 * Times resources activity is needed
 * data can retrieved from the database and
 * views will then be inflated
 *
 * @see BigView
 */
public class Times extends BaseActivityDrawer {
    private List<FestivalTimeItem> timeData;
    private String dialect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime_main);
        onCreateDrawer();

        getDialect();
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
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
        timeData = new ArrayList<FestivalTimeItem>();
        timeData.add(new FestivalTimeItem("Son las doce en punto", "",android.R.drawable.ic_lock_idle_alarm, dialect));
        timeData.add(new FestivalTimeItem("Son las doce y cinco", "",android.R.drawable.ic_lock_idle_alarm, dialect));
        timeData.add(new FestivalTimeItem("Son las doce y cuarto", "",android.R.drawable.ic_lock_idle_alarm, dialect));
        timeData.add(new FestivalTimeItem("Son las doce y media", "",android.R.drawable.ic_lock_idle_alarm, dialect));
        timeData.add(new FestivalTimeItem("Es la una menos cuarto", "",android.R.drawable.ic_lock_idle_alarm, dialect));

        return timeData;
    }
}
