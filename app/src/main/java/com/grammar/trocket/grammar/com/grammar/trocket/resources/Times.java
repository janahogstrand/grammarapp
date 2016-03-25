package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeItem;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeViewHolder;

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
    private List<FestivalTimeItem> timeData;
    private String dialect;
    Activity activity;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime_main);
        onCreateDrawer();
        activity = Times.this;

        Intent intent = getIntent();
        id = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);

        getDialect();
        String dialectCode = MainMenu.dialectsIDCode.get(MainMenu.dialectsNameID.get(dialect));
        String[] dCode = dialectCode.split("_");
        initTTS(dCode[0], dCode[1]);
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

    /**
     * Makes String correct format
     * To be used when making new FestivalTimeItem for url
     * */
    private String fixString(String imageAddress){
        imageAddress = imageAddress.substring(0, imageAddress.length()-4) + "raw=1";
        return imageAddress;
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
