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

    public static List<ClusterItem> cItemData;
    public static List<ClusterSubItem> cSubItemData;
    public Cluster season;
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

        getData();

        Log.w(cItemData.get(0).getId(), cItemData.get(0).getName());
        Log.w(cSubItemData.get(0).getId(), cSubItemData.get(0).getName());
        for(int i = 0; i < cItemData.size(); ++i) {
            Log.d(cItemData.get(i).getId(), cItemData.get(i).getName());
        }

        for(int i = 0; i < cSubItemData.size(); ++i) {
            Log.i(cSubItemData.get(i).getId(), cSubItemData.get(i).getName());
        }

        pageTitle = (TextView) findViewById(R.id.pageTitle);
        pageTitle.setText(season.getTitle());

        subTitle = (TextView) findViewById(R.id.subTitle);
        subTitle.setText(season.getInstruction());

        engSubTitle = (TextView) findViewById(R.id.engSubtitle);
        engSubTitle.setText(season.getHelp());


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

        springBtn.setText(cItemData.get(0).getName());
        summerBtn.setText(cItemData.get(1).getName());
        autumnBtn.setText(cItemData.get(2).getName());
        winterBtn.setText(cItemData.get(3).getName());
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
    private void getData() {
        cItemData = new ArrayList<ClusterItem>();
        cSubItemData = new ArrayList<ClusterSubItem>();

        String clusterString = "";
        GetJSON getcluster = new GetJSON(activity, TableNames.CLUSTER_TABLE, "parentId", (id + ""));
        try {
            clusterString = getcluster.execute().get();
            Log.w("Seasons", clusterString);

            JSONArray jsonArray = new JSONArray(clusterString);
            JSONObject cluster = jsonArray.getJSONObject(0);
            String cId = cluster.get(TableNames.CLUSTER_ID).toString();

            season = new Cluster(
                    cluster.get(TableNames.CLUSTER_ID).toString(),
                    cluster.get(TableNames.CLUSTER_CATEGORYID).toString(),
                    cluster.get(TableNames.CLUSTER_TITLE).toString(),
                    cluster.get(TableNames.CLUSTER_INSTRUCTION).toString(),
                    cluster.get(TableNames.CLUSTER_HELP).toString()
            );

            String cItems = "";
            GetJSON getCItems = new GetJSON(activity, TableNames.CLUSTERITEM_TABLE, "parentId", cId);
            cItems = getCItems.execute().get();
            Log.w("Cluster Items", cItems);
            JSONArray cItemArray = new JSONArray(cItems);
            for(int i = 0; i< cItemArray.length(); ++i){
                JSONObject jObject = cItemArray.getJSONObject(i);
                String name = jObject.get(TableNames.CLUSTERITEM_NAME).toString();
                String clusterId = jObject.get(TableNames.CLUSTER_ID).toString();
                String hierarchy = jObject.get(TableNames.CLUSTERITEM_HIERARCHY).toString();
                String id = jObject.get(TableNames.CLUSTERITEM_ID).toString();
                cItemData.add(i, new ClusterItem(id,clusterId,name,hierarchy));
            }

            for(int i = 0; i < cItemData.size(); ++i) {
                String cSubItems = "";
                GetJSON getCSItems = new GetJSON(activity, TableNames.CLUSTERSUBITEM_TABLE, "parentId", cItemData.get(i).getId());
                cSubItems = getCSItems.execute().get();
                Log.w("Cluster Sub Items", cSubItems);
                JSONArray cSItemArray = new JSONArray(cSubItems);
                for(int j = 0; j < cSItemArray.length(); ++j){
                    JSONObject jObject = cSItemArray.getJSONObject(j);

                    Log.w("Cluster Sub Item", cSItemArray.getJSONObject(j).getString(TableNames.CLUSTERSUBITEM_NAME));

                    String description = jObject.get(TableNames.CLUSTERSUBITEM_DESCRIPTION).toString();
                    String clickable = jObject.get(TableNames.CLUSTERSUBITEM_CLICKABLE).toString();
                    String audioUrl = jObject.get(TableNames.CLUSTERSUBITEM_AUDIOURL).toString();
                    String name = jObject.get(TableNames.CLUSTERSUBITEM_NAME).toString();
                    String clusterItemId = jObject.get(TableNames.CLUSTERITEM_ID).toString();
                    String hierarchy = jObject.get(TableNames.CLUSTERSUBITEM_HIERARCHY).toString();
                    String id = jObject.get(TableNames.CLUSTERSUBITEM_ID).toString();
                    cSubItemData.add(new ClusterSubItem(id, clusterItemId, name, description, clickable, audioUrl, hierarchy));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
