package com.grammar.trocket.trocket.resources;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.trocket.backend.GetJSON;
import com.grammar.trocket.trocket.backend.TableNames;
import com.grammar.trocket.trocket.dialogs.DialectDialog;
import com.grammar.trocket.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.trocket.main.MainMenu;
import com.grammar.trocket.trocket.resources.festivalAndTime.FestivalTimeAdapter;
import com.grammar.trocket.trocket.resources.festivalAndTime.FestivalTimeItem;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.trocket.resources.festivalAndTime.FestivalTimeViewHolder;

import org.json.JSONArray;
import org.json.JSONObject;

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
 */
public class  Festivals extends BaseActivityDrawer {
    private List<FestivalTimeItem> festData;
    String dialect;
    Activity activity;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_festivalstime_main);
        onCreateDrawer();
        activity = Festivals.this;

        Intent intent = getIntent();
        id = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);

        getDialect();
        String dialectCode = MainMenu.dialectsIDCode.get(MainMenu.dialectsNameID.get(dialect));
        String[] dCode = dialectCode.split("_");
        initTTS(dCode[0], dCode[1]);
        RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        LinearLayoutManager glm = new LinearLayoutManager(Festivals.this);
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

        String festString = "";
        GetJSON getFest = new GetJSON(activity, TableNames.THUMBNAILTAP_TABLE, "parentId", (id + ""));
        try {
            festString = getFest.execute().get();
            Log.w("Categories", festString);

            JSONArray jsonArray = new JSONArray(festString);
            JSONObject festival = jsonArray.getJSONObject(0);
            String festId = festival.get(TableNames.THUMBNAILTAP_ID).toString();

            String festItems = "";
            GetJSON getFests = new GetJSON(activity, TableNames.THUMBNAILTAPITEM_TABLE, "parentId", festId);
            festItems = getFests.execute().get();
            Log.w("Festivals", festItems);
            JSONArray festArray = new JSONArray(festItems);
            for(int i = 0; i< festArray.length(); ++i){
                JSONObject jObject = festArray.getJSONObject(i);
                String foreign = jObject.get(TableNames.THUMBNAILTAPITEM_NAME).toString();
                String english = jObject.get(TableNames.THUMBNAILTAPITEM_TRANSLATION).toString();
                String url = jObject.get(TableNames.THUMBNAILTAPITEM_FULLIMAGEURL).toString();
                String id = jObject.get(TableNames.THUMBNAILTAPITEM_ID).toString();
                festData.add(new FestivalTimeItem(foreign,english,url,id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return festData;
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
