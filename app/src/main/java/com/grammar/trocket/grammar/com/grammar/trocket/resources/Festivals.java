package com.grammar.trocket.grammar.com.grammar.trocket.resources;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.AlphabetItem;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeAdapter;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeItem;
import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeViewHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
        initTTS();
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

//            for (int j = 0; j < jsonArray.length(); ++j) {
//                JSONObject jObject = jsonArray.getJSONObject(j);

//                String letter = jObject.get(TableNames.DICTIONARYLETTER_LABEL).toString();
//                int id = Integer.parseInt(jObject.get(TableNames.DICTIONARYLETTER_ID).toString());
//                alphabetList.add(new AlphabetItem(letter, true, id));
//                Collections.sort(alphabetList,
//                        new Comparator<AlphabetItem>() {
//                            public int compare(AlphabetItem letter1, AlphabetItem letter2) {
//                                return letter1.getLetter().toUpperCase().compareTo(letter2.getLetter().toUpperCase());
//                            }
//                        });
           // }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?ra=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?rw=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?aw=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?ra=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?ra=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?rw=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?aw=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));
//        festData.add(new FestivalTimeItem("Uno de noviembre - Dia de Todos los Santos", "All Saints Day", fixString("https://www.dropbox.com/s/60o0jnj9ynmei8w/festivals0.png?raw=") , dialect));
//        festData.add(new FestivalTimeItem("12 de octubre", "Columbus Day",fixString("https://www.dropbox.com/s/idh6b3ngfzpf1no/festivals1.png?ra=1"), dialect));
//        festData.add(new FestivalTimeItem("marzo o abril, semana santa", "Easter (March or April)", fixString("https://www.dropbox.com/s/rs97sn34kjr7h6e/festivals2.png?raw="), dialect));

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

    private void initTTS(){
        FestivalTimeViewHolder.textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                FestivalTimeViewHolder.textToSpeech .setLanguage(new Locale("Es", "es"));
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
