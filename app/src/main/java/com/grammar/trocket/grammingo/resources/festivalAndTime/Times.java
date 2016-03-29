package com.grammar.trocket.grammingo.resources.festivalAndTime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.grammar.trocket.grammingo.backend.GetJSON;
import com.grammar.trocket.grammingo.backend.TableNames;
import com.grammar.trocket.grammingo.dialogs.DialectDialog;
import com.grammar.trocket.grammingo.main.BaseActivityDrawer;
import com.grammar.trocket.grammingo.main.MainMenu;
import com.grammar.trocket.grammar.R;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by jamiemoreland on 27/02/16.
 * <p/>
 * This class should be used when an instance of the
 * Times resources activity is needed
 * data can retrieved from the database and
 * views will then be inflated
 *
 */
public class Times extends BaseActivityDrawer {

    List<FestivalTimeItem> timeData;
    String dialect;
    Handler handler;
    Activity activity;
    TextView textView;
    GridLayoutManager gridLayoutManager;
    int id;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime_main);
        onCreateDrawer();
        activity = Times.this;

        textView = (TextView) findViewById(R.id.festTimeTxtView);

        Intent intent = getIntent();
        id = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);

        getDialect();

        rv = (RecyclerView) findViewById(R.id.rv);
        gridLayoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(gridLayoutManager);
        rv.setHasFixedSize(true);

        toggleBusyUi(true);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {

                FestivalTimeAdapter festivalTimeAdapter = new FestivalTimeAdapter(getData());
                rv.setAdapter(festivalTimeAdapter);

                toggleBusyUi(false);
            }
        }, 100);

    }


    /**
     * Sets the UI to either a "loading" state or a "data" stage, based on {@code busyRefreshing}.
     */
    private void toggleBusyUi(boolean busy) {
        if (busy) {
            textView.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }



    private List<FestivalTimeItem> getData() {

        timeData = new ArrayList<FestivalTimeItem>();

        String timeString = "";
        GetJSON getTime = new GetJSON(activity, TableNames.THUMBNAILTAP_TABLE, "parentId", (id + ""));
        try {
            timeString = getTime.execute().get();
            Log.w("Categories", timeString);

            JSONArray jsonArray = new JSONArray(timeString);
            JSONObject time = jsonArray.getJSONObject(0);
            String timeId = time.get(TableNames.THUMBNAILTAP_ID).toString();

            String timeItems = "";
            GetJSON getTimes = new GetJSON(activity, TableNames.THUMBNAILTAPITEM_TABLE, "parentId", timeId);
            timeItems = getTimes.execute().get();
            Log.w("Times", timeItems);
            JSONArray timeArray = new JSONArray(timeItems);
            for(int i = 0; i< timeArray.length(); ++i){
                JSONObject jObject = timeArray.getJSONObject(i);
                String foreign = jObject.get(TableNames.THUMBNAILTAPITEM_NAME).toString();
                String english = jObject.get(TableNames.THUMBNAILTAPITEM_TRANSLATION).toString();
                String url = jObject.get(TableNames.THUMBNAILTAPITEM_FULLIMAGEURL).toString();
                String id = jObject.get(TableNames.THUMBNAILTAPITEM_ID).toString();
                timeData.add(new FestivalTimeItem(foreign,english,url,id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return timeData;
    }


    public void getDialect(){
        Intent intent = getIntent();
        dialect = intent.getStringExtra(DialectDialog.DIALECT_INFO);
        String dialectCode = MainMenu.dialectsIDCode.get(MainMenu.dialectsNameID.get(dialect));
        String[] dCode = dialectCode.split("_");
        initTTS(dCode[0], dCode[1]);
    }

    private void initTTS(final String lang1, final String lang2){
        FestivalTimeViewHolder.textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                FestivalTimeViewHolder.textToSpeech .setLanguage(new Locale(lang1, lang2));
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(FestivalTimeViewHolder.textToSpeech != null){
            FestivalTimeViewHolder.textToSpeech.shutdown();
            Log.d("-------------------", "TTS Destroyed");

        }
        super.onDestroy();
    }

}
