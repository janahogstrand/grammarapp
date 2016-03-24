package com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.main.BaseActivityDrawer;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.festivalAndTime.FestivalTimeItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SeasonsMain extends BaseActivityDrawer {

    public List<ClusterItem> cItemData;
    public List<ClusterItem> cSubItemData;
    private String dialect;
    Activity activity;
    int id;

    Button springBtn;
    Button summerBtn;
    Button autumnBtn;
    Button winterBtn;


    TextView pageTitle;
    TextView subTitle;
    TextView engSubTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons_main);
        super.onCreateDrawer();

        Intent intent = getIntent();
        id = intent.getIntExtra(DialectDialog.CALLER_INFO, -1);

        pageTitle = (TextView) findViewById(R.id.pageTitle);
        pageTitle.setText("Las Estaciones y los Meses del Ano");

        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText("Selecciona cada estacion y\naprende los nombres de los\nmeses del ano.");

        engSubTitle = (TextView) findViewById(R.id.engSubtitle);
        engSubTitle.setText("Select each season and learn the\nnames of the months");


        findAndAssignText();

    }

    /**
     * This method will find the buttons in the MainActivity by using their Ids
     * and then assign texts to them.
     */

    public void findAndAssignText() {
        springBtn = (Button) findViewById(R.id.springBtn);
        summerBtn = (Button) findViewById(R.id.summerBtn);
        autumnBtn = (Button) findViewById(R.id.autumnBtn);
        winterBtn = (Button) findViewById(R.id.winterBtn);

        springBtn.setText("Primavera");
        summerBtn.setText("Verano");
        autumnBtn.setText("Otono");
        winterBtn.setText("Invierno");
    }


    /**
     * This method moves the user to a new activity based on the button they
     * have clicked.
     * @param v
     */
    public void moveToNewActivity(View v){
        String idAsString = v.getResources().getResourceName(v.getId());
        if(idAsString.equals("com.grammar.trocket.grammar:id/springBtn")){
            Intent intent = new Intent(SeasonsMain.this, SeasonsFirstActivity.class);
            startActivity(intent);
        }
        else if(idAsString.equals("com.grammar.trocket.grammar:id/summerBtn")){
            Intent intent = new Intent(SeasonsMain.this, SeasonsSecondActivity.class);
            startActivity(intent);
        }
        else if(idAsString.equals("com.grammar.trocket.grammar:id/autumnBtn")){
            Intent intent = new Intent(SeasonsMain.this, SeasonsThirdActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(SeasonsMain.this, SeasonsFourthActivity.class);
            startActivity(intent);
        }
    }


    /**
     * Gets data to pass to adapter
     *
     *
     **/
    private List<ClusterItem> getData() {
        cItemData = new ArrayList<ClusterItem>();

        String clusterString = "";
        GetJSON getcluster = new GetJSON(activity, TableNames.CLUSTER_TABLE, "parentId", (id + ""));
        try {
            clusterString = getcluster.execute().get();
            Log.w("Seasons", clusterString);

            JSONArray jsonArray = new JSONArray(clusterString);
            JSONObject cluster = jsonArray.getJSONObject(0);
            String clusterId = cluster.get(TableNames.CLUSTER_ID).toString();

            String cItems = "";
            GetJSON getCItems = new GetJSON(activity, TableNames.CLUSTERITEM_TABLE, "parentId", clusterId);
            cItems = getCItems.execute().get();
            Log.w("Festivals", cItems);
            JSONArray cItemArray = new JSONArray(cItems);
            for(int i = 0; i< cItemArray.length(); ++i){
                JSONObject jObject = cItemArray.getJSONObject(i);
                String foreign = jObject.get(TableNames.THUMBNAILTAPITEM_NAME).toString();
                String english = jObject.get(TableNames.THUMBNAILTAPITEM_TRANSLATION).toString();
                String url = jObject.get(TableNames.THUMBNAILTAPITEM_FULLIMAGEURL).toString();
                String id = jObject.get(TableNames.THUMBNAILTAPITEM_ID).toString();
                //cItemData.add(new FestivalTimeItem(foreign,english,url,id));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cItemData;
    }

}
