package com.grammar.trocket.grammar.com.grammar.trocket.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseHelper;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.DialectItem;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.ModuleSelection;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabDictionary;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabExercises;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabResources;

import java.util.ArrayList;

public class MainMenu extends BaseActivityDrawer {

    //TODO Shared prefs
    public static String MainLanguage = "Spanish";
    public static int CourseID;
    public static int ExerciseID = -1;
    public  static int ResourcesID = -1;
    public  static int DictionaryID = -1;
    public static DatabaseHelper db;
    public static Cursor result;
    public static Cursor resultExercises;
    public static Cursor resultResources;
    public static Cursor resultDictionary;
    public static ArrayList<DialectItem> dialectsItems;
    public static  Cursor dialectsCursor;
    public static final String TAB_SELECT = "com.grammar.trocket.grammar.com.grammar.trocket.TAB";
    public static final String RESOURCEID= "com.grammar.trocket.grammar.com.grammar.trocket.RESOURCEID";
    public static final String EXERCISEID = "com.grammar.trocket.grammar.com.grammar.trocket.EXERCISEID";
    public static final String DICTIONARYID = "com.grammar.trocket.grammar.com.grammar.trocket.DICTIONARYID";
    //public static final String COURSE_SELECTED = "com.grammar.trocket.grammar.com.grammar.trocket.COURSE";
    private int currentTab = 0;

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

        loadDatabase();
        loadPrefs();
        //db.onCreate(db.getWritableDatabase());

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Gets tab that was last clicked
        try{
            Intent intent = getIntent();
            currentTab = intent.getIntExtra(this.TAB_SELECT, currentTab);

            TabLayout.Tab tab = tabLayout.getTabAt(currentTab);
            tab.select();
        }catch (Exception e){

        }

    }

    private void loadPrefs(){
        SharedPreferences prefs = this.getApplicationContext().getSharedPreferences(
                "com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection", this.getApplicationContext().MODE_PRIVATE);

        CourseID = prefs.getInt(ModuleSelection.COURSE, -1);

        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
        resultExercises = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'exercise' " + "AND " + MainMenu.db.CATEGORY_COURSEID + " = " + MainMenu.CourseID, null);
        resultResources = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'resource' " + "AND " + MainMenu.db.CATEGORY_COURSEID + " = " + MainMenu.CourseID, null);
        resultDictionary = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.DICTIONARY_TABLE + " WHERE " + MainMenu.db.DICTIONARY_COURSEID + " = " + MainMenu.CourseID, null);

        try{
            resultDictionary.moveToFirst();
        }catch (Exception e){
            Log.w("No Dictionary found", "No Dictionary defined");
        }

        try{
            resultExercises.moveToFirst();
        }catch (Exception e){
            Log.w("No exercise found", "Non defined");
        }

        try{
            resultResources.moveToFirst();
        }catch (Exception e){
            Log.w("No resources found", "Non defined");
        }


        try{
            ExerciseID = resultExercises.getInt(resultExercises.getColumnIndex(MainMenu.db.CATEGORY_ID));
            ResourcesID = resultResources.getInt(resultResources.getColumnIndex(MainMenu.db.CATEGORY_ID));
            DictionaryID = resultDictionary.getInt(resultDictionary.getColumnIndex(MainMenu.db.DICTIONARY_ID));

            prefs.edit().putInt(this.RESOURCEID, ResourcesID ).apply();
            prefs.edit().putInt(this.EXERCISEID, ExerciseID ).apply();
            prefs.edit().putInt(this.DICTIONARYID, DictionaryID ).apply();
        }catch (Exception e){
            e.printStackTrace();
            Log.w("Failed: ", "Has the user defined tabs in the course?");
        }

        CourseID = prefs.getInt(ModuleSelection.COURSE, -1);
        findDialects(CourseID);
        Log.w("Prefs are: ", MainMenu.CourseID + "");
        Log.w("Resources prefs are: ", ResourcesID + "");
        Log.w("Exercise prefs are: ", ExerciseID + "");
        Log.w("Dictionary prefs are: ", DictionaryID + "");
    }

    private void findDialects(int courseID){
        dialectsItems = new ArrayList<DialectItem>();
        SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();
        dialectsCursor =  myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.DIALECT_TABLE + " WHERE " +  MainMenu.db.DIALECT_COURSEID + " = " + courseID, null);
        while (dialectsCursor.moveToNext()){
            String name = dialectsCursor.getString(dialectsCursor.getColumnIndex(MainMenu.db.DIALECT_NAME));
            String code = dialectsCursor.getString(dialectsCursor.getColumnIndex(MainMenu.db.DIALECT_CODE));
            dialectsItems.add(new DialectItem(name, code));
        }

    }

    private void loadDatabase() {
        db = DatabaseHelper.getInstance(getApplicationContext());
        //db.getWritableDatabase();

        // This must be put into the refresh method, and ALSO called onCreate, or just after onCreate.
        db.onCreate(db.getWritableDatabase());

        // Test cursor with select all from Course table
        result = db.selectDBTable(db.COURSE_TABLE);

        while(result.moveToNext()) {
            Log.i("Cursor", result.getString(result.getColumnIndex(db.COURSE_NAME)));
        }
    }

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
                        return resultExercises.getString(resultExercises.getColumnIndex(MainMenu.db.CATEGORY_NAME));
                    }catch (Exception e){
                        e.printStackTrace();
                        return "EXERCISES";
                    }
                case 1:
                    try {
                        return resultResources.getString(resultResources.getColumnIndex(MainMenu.db.CATEGORY_NAME));
                    }catch (Exception e){
                        e.printStackTrace();
                        return "RESOURCES";
                    }
                case 2:
                    return "GLOSSARY";
            }
            return null;
        }


    }
}
