package com.grammar.trocket.grammar.com.grammar.trocket.main;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.main.category.Category;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.DialectItem;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabDictionary;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabExercises;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabResources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainMenu extends BaseActivityDrawer {


    //TODO Shared prefs
    public static ProgressDialog progressDialog;
    public static String MainLanguage = "Spanish";
    public static int CourseID;
    public static int ExerciseID = -1;
    public static int ResourcesID = -1;
    public static int DictionaryID = -1;


//    public String exerciseIdString;
//    public String resourceIdString;

    public static HashMap<String, Integer> dialectsNameID;
    public static HashMap<Integer, String> dialectsIDCode;
    public static HashMap<Integer, String> contentsItems;
    public static Cursor dialectsCursor;
    public static final String TAB_SELECT = "com.grammar.trocket.grammar.com.grammar.trocket.TAB";
    public static final String RESOURCEID = "com.grammar.trocket.grammar.com.grammar.trocket.RESOURCEID";
    public static final String EXERCISEID = "com.grammar.trocket.grammar.com.grammar.trocket.EXERCISEID";
    public static final String DICTIONARYID = "com.grammar.trocket.grammar.com.grammar.trocket.DICTIONARYID";
    //public static final String COURSE_SELECTED = "com.grammar.trocket.grammar.com.grammar.trocket.COURSE";
    private int currentTab = 0;
    public static Context context;
    String dictionary;
    String exercises;
    String resources;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    /**
     * Creates Tabs, Navigation drawer and page adapter
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        super.onCreateDrawer();
        this.context = this.getApplicationContext();
        loadPrefs(MainMenu.this);
        SharedPreferences  settings = PreferenceManager.getDefaultSharedPreferences(context);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Gets tab that was last clicked
        try {
            Intent intent = getIntent();
            currentTab = intent.getIntExtra(this.TAB_SELECT, currentTab);

            TabLayout.Tab tab = tabLayout.getTabAt(currentTab);
            tab.select();
        } catch (Exception e) {

        }

    }

    public void loadPrefs(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(
                "com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection", context.MODE_PRIVATE);

        CourseID = prefs.getInt(ModuleSelection.COURSE, -1);


        try {
//            ExerciseID = resultExercises.getInt(resultExercises.getColumnIndex(ModuleSelection.db.CATEGORY_ID));
//            ResourcesID = resultResources.getInt(resultResources.getColumnIndex(ModuleSelection.db.CATEGORY_ID));
//            DictionaryID = resultDictionary.getInt(resultDictionary.getColumnIndex(ModuleSelection.db.DICTIONARY_ID));

            String topLevelIdString = "";

            //Get exercise and resource ids
            GetJSON getTopCats = new GetJSON(MainMenu.this, TableNames.CATEGORY_TABLE, "courseId", (CourseID + ""));
            try {
                topLevelIdString = getTopCats.execute().get();
                Log.w("Categories", topLevelIdString);

                JSONArray jsonArray = new JSONArray(topLevelIdString);
                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    int id = Integer.parseInt(jObject.get("id").toString());
                    String name = jObject.get("name").toString();

                    if (jObject.get("kind").toString().equals("exercise")) {
                        ExerciseID = id;
                        exercises = name;
                    } else {
                        ResourcesID = id;
                        resources = name;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String dictString = "";

            GetJSON getDictionary = new GetJSON(MainMenu.this, TableNames.DICTIONARY_TABLE, "courseId", (CourseID + ""));
            try {
                dictString = getDictionary.execute().get();
                Log.w("Categories", dictString);

                JSONArray jsonArray = new JSONArray(dictString);
                for (int j = 0; j < jsonArray.length(); ++j) {
                    JSONObject jObject = jsonArray.getJSONObject(j);

                    int id = Integer.parseInt(jObject.get("id").toString());
                    Log.w("Dictionary name", jObject.get("title").toString());

                    DictionaryID = id;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }




            prefs.edit().putInt(MainMenu.RESOURCEID, ResourcesID).apply();
            prefs.edit().putInt(MainMenu.EXERCISEID, ExerciseID).apply();
            prefs.edit().putInt(MainMenu.DICTIONARYID, DictionaryID).apply();
        } catch (Exception e) {
            e.printStackTrace();
            Log.w("Failed: ", "Has the user defined tabs in the course?");
        }

        CourseID = prefs.getInt(ModuleSelection.COURSE, -1);
        findDialects(CourseID);
        findContents(CourseID);
        Log.w("Prefs are: ", MainMenu.CourseID + "");
        Log.w("Resources prefs are: ", ResourcesID + "");
        Log.w("Exercise prefs are: ", ExerciseID + "");
        Log.w("Dictionary prefs are: ", DictionaryID + "");
    }

    private void findDialects(int courseID) {
        dialectsNameID = new HashMap<>();
        dialectsIDCode = new HashMap<>();
        String dialectString;
        GetJSON dialectsFinder = new GetJSON((Activity) MainMenu.this, TableNames.DIALECT_TABLE, "courseId", (CourseID + ""));
        try {
            dialectString = dialectsFinder.execute().get();
            Log.w("Categories", dialectString);

            JSONArray jsonArray = new JSONArray(dialectString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);

                int id =  Integer.parseInt(jObject.get(TableNames.DIALECT_ID).toString());
                String name =  jObject.get(TableNames.DIALECT_NAME).toString();
                String code = jObject.get(TableNames.DIALECT_CODE).toString();
                dialectsNameID.put(name, id);
                dialectsIDCode.put(id, code);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void findContents(int courseID) {
        contentsItems = new HashMap<>();
        String contentString;
        GetJSON dialectsFinder = new GetJSON((Activity) MainMenu.this, TableNames.CONTENT_TABLE);
        try {
            contentString = dialectsFinder.execute().get();
            Log.w("Categories", contentString);

            JSONArray jsonArray = new JSONArray(contentString);
            for (int j = 0; j < jsonArray.length(); ++j) {
                JSONObject jObject = jsonArray.getJSONObject(j);

                String name =  jObject.get(TableNames.CONTENT_NAME).toString();
                int id = Integer.parseInt(jObject.get(TableNames.CONTENT_ID).toString());
                contentsItems.put(id, name);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


//    private void loadDatabase(final Context context) {
//        db = TableNames.getInstance(context);
//        db.onCreate(db.getWritableDatabase());
//
//        result = db.selectDBTable(db.COURSE_TABLE);
//        Log.w("Hello", "hello");
//
////        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
////
////        // Set up the ViewPager with the sections adapter.
////        mViewPager = (ViewPager) findViewById(R.id.container);
////        mViewPager.setAdapter(mSectionsPagerAdapter);
////
////        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
////        tabLayout.setupWithViewPager(mViewPager);
////
////        //Gets tab that was last clicked
////        try{
////            Intent intent = getIntent();
////            currentTab = intent.getIntExtra(this.TAB_SELECT, currentTab);
////
////            TabLayout.Tab tab = tabLayout.getTabAt(currentTab);
////            tab.select();
////        }catch (Exception e){
////
////        }
//    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return tab at @position
         **/
        @Override
        public Fragment getItem(int position) {
            FragmentTabExercises tab1 = new FragmentTabExercises();
            FragmentTabResources tab2 = new FragmentTabResources();
            FragmentTabDictionary tab3 = new FragmentTabDictionary();
            switch (position) {
                case 0:
                    return tab1;
                case 1:
                    return tab2;
                case 2:
                    return tab3;
                default:
                    return tab3;
            }
        }

        /**
         * Gets total pages
         **/
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        /**
         * Get tab title
         **/
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    try {
                        if(exercises == null){ return "EXERCISES";}
                        return exercises;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "EXERCISES";
                    }
                case 1:
                    try {
                        if(resources == null){ return "RESOURCES";}
                        return resources;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "RESOURCES";
                    }
                case 2:
                    return "GLOSSARY";
            }
            return null;
        }


    }

    public boolean checkConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;
        return connected;
    }
}
