package com.grammar.trocket.grammar.com.grammar.trocket.main;

import android.app.Activity;
import android.content.Context;
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
import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseOperations;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabDictionary;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabExercises;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabResources;

public class MainMenu extends BaseActivityDrawer {

    //TODO Shared prefs
    public static String MainLanguage = "Spanish";
    public String courseId = null;
    public String exercisesId = null;
    public String exercisesName = null;
    public String resourcesId = null;
    public String resourcesName = null;
    public String dictionaryId = null;
    public String dictionaryName = null;

    public static DatabaseOperations db;

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


//        getApplicationContext().deleteDatabase("GrammarCourses");
//        dbOps.DatabaseSetup();
//        Cursor result = dbOps.queryDB("SELECT * FROM Course AS co JOIN Category AS ca ON co._id=ca.courseId WHERE co.name='Spanish'");
//        Cursor result = dbOps.selectDBTable("Course");
//        Log.i("Select Count", Integer.toString(result.getCount()));


        db = DatabaseOperations.getInstance(getApplicationContext());
//        String dbName = db.getDatabaseName();
//        db.close();
//        getApplicationContext().deleteDatabase(dbName);
//        db = DatabaseOperations.getInstance(getApplicationContext());
        db.dbRefresh();


        SharedPreferences prefs = this.getSharedPreferences(
                "com.grammar.trocket.grammar.com.grammar.trocket.main", Context.MODE_PRIVATE);
        courseId = Integer.toString(prefs.getInt("courseId", 7));
        Log.i("Saved Course ID", courseId);

//        Cursor result = db.selectDBTable("Course");
//        Log.i("Select Count", Integer.toString(result.getCount()));
//
//        Cursor c1 = db.selectDBTable("Category");
//        //        Cursor c1 = db.selectDBTable("Category", "WHERE courseId='" + courseId + "' AND type='exercise' LIMIT 1");
//        Log.i("Category Cursor", Integer.toString(c1.getCount()));
//        if (c1.moveToNext()) {
//            Log.i("Cursor", "c1");
//            exercisesId = c1.getString(c1.getColumnIndex("_id"));
//            exercisesName = c1.getString(c1.getColumnIndex("name"));
//        }
//
//        //        Cursor c2 = db.selectDBTable("Category", "WHERE courseId='" + courseId + "' AND type='resource' LIMIT 1");
//        Cursor c2 = db.selectDBTable("Category", "ORDER BY Created DESC LIMIT 1");
//        if (c2.moveToNext()) {
//            Log.i("Cursor", "c2");
//            resourcesId = c2.getString(c2.getColumnIndex("_id"));
//            resourcesName = c2.getString(c2.getColumnIndex("name"));
//        }
//
//        Cursor c3 = db.selectDBTable("Dictionary", "ORDER BY Created DESC LIMIT 1");
//        //        Cursor c3 = db.selectDBTable("Dictionary", "WHERE courseId='" + courseId + "' LIMIT 1");
//        if (c3.moveToNext()) {
//            Log.i("Cursor", "c3");
//            dictionaryId = c3.getString(c3.getColumnIndex("_id"));
//            dictionaryName = c3.getString(c3.getColumnIndex("title"));
//        }
//
//        Log.i("ExercisesId", exercisesId);
//        Log.i("ResourcesId", resourcesId);
//        Log.i("DictionaryId", dictionaryId);
//
//        //        Log.i("Select Count", Integer.toString(result.getCount()));
//
//        //        db.DatabaseSetup();
//        //        Cursor result = dbOps.queryDB("SELECT * FROM Course AS co JOIN Category AS ca ON co._id=ca.courseId WHERE co.name='Spanish'");
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);

    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

//        Cursor result = db.selectDBTable("Category", "WHERE courseId='" +  + "'");

        /**
         * Return tab at @position
         **/
        @Override
        public Fragment getItem(int position) {
            Bundle bndl = new Bundle();
            bndl.putString("exercisesId", exercisesId);
            bndl.putString("resourcesId", resourcesId);
            bndl.putString("dictionaryId", dictionaryId);

            FragmentTabExercises tab1 = new FragmentTabExercises();
            tab1.setArguments(bndl);

            FragmentTabResources tab2 = new FragmentTabResources();
            tab2.setArguments(bndl);

            FragmentTabDictionary tab3 = new FragmentTabDictionary();
            tab3.setArguments(bndl);

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
                    return exercisesName;
                case 1:
                    return resourcesName;
                case 2:
                    return dictionaryName;
            }
            return null;
        }


    }
}
