package com.grammar.trocket.grammar.com.grammar.trocket.main;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseHelper;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabDictionary;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabExercises;
import com.grammar.trocket.grammar.com.grammar.trocket.tabs.FragmentTabResources;

public class MainMenu extends BaseActivityDrawer {

    //TODO Shared prefs
    public static String MainLanguage = "Spanish";
    public static DatabaseHelper db;
    public static Cursor result;
    public static final String TAB_SELECT = "com.grammar.trocket.grammar.com.grammar.trocket.TAB";
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

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        //Gets tab that was last clicked
        Intent intent = getIntent();
        currentTab = intent.getIntExtra(this.TAB_SELECT, currentTab);

        TabLayout.Tab tab = tabLayout.getTabAt(currentTab);
        tab.select();
    }

    private void loadDatabase() {
        db = DatabaseHelper.getInstance(getApplicationContext());
        //db.getWritableDatabase();

        // This must be put into the refresh method, and ALSO called onCreate, or just after onCreate.
//        db.onCreate(db.getWritableDatabase());

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
                    return "EXERCISES";
                case 1:
                    return "RESOURCES";
                case 2:
                    return "DICTIONARY";
            }
            return null;
        }


    }
}
