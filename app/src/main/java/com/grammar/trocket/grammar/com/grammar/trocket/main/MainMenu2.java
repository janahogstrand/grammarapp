//package com.grammar.trocket.grammar.com.grammar.trocket.main;
//
//        import android.app.ProgressDialog;
//        import android.database.Cursor;
//        import android.os.AsyncTask;
//        import android.support.v7.app.AppCompatActivity;
//        import android.os.Bundle;
//        import android.util.Log;
//
//        import com.grammar.trocket.grammar.com.grammar.trocket.database.DatabaseHelper;
//
//        import java.io.IOException;
//        import java.io.InputStream;
//        import java.io.InputStreamReader;
//        import java.net.HttpURLConnection;
//        import java.net.MalformedURLException;
//        import java.net.URL;
//
//public class MainActivity2 extends AppCompatActivity {
//
//    public static DatabaseHelper db;
////    public static MainActivity main = this;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        db = DatabaseHelper.getInstance(getApplicationContext());
//        db.getWritableDatabase();
//
//        Cursor result = db.selectDBTable(db.COURSE_TABLE);
//
//        while(result.moveToNext()) {
//            Log.i("Cursor", result.getString(result.getColumnIndex(db.COURSE_NAME)));
//        }
//
//// Only uncomment the following three lines if the database needs to be deleted for testing purposes
////        String dbname = db.getDatabaseName();
////        db.close();
////        getApplicationContext().deleteDatabase(dbname);
//
//        db.insertIntoTable();
//    }
//
//
//}
