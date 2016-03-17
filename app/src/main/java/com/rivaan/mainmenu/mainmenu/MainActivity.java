package com.rivaan.mainmenu.mainmenu;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.rivaan.mainmenu.R;
import com.rivaan.mainmenu.database.DatabaseOperations;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    DatabaseOperations dbOps = new DatabaseOperations(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        getApplicationContext().deleteDatabase("GrammarCourses");

        try {
            if(databaseExists()) {
                myDatabase = this.openOrCreateDatabase("GrammarCourses", MODE_PRIVATE, null);

                Log.i("Test", "TestMessage");
                // Check 'updated at' time. If remote time is later than local time, download data for all tables.
                // So abstract DB insertion from the else branch so that both can use it.
                dbOps.checkDB();
                Cursor course = selectDBTable("Course");
                Cursor category = selectDBTable("Category");
                Log.i("Course count", Integer.toString(course.getCount()));
                Log.i("Category count", Integer.toString(category.getCount()));

            } else {
                myDatabase = this.openOrCreateDatabase("GrammarCourses", MODE_PRIVATE, null);
                dbOps.createDB();
                Cursor course = selectDBTable("Course");
                Log.i("Course count", Integer.toString(course.getCount()));
                myDatabase.close();
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    protected static SQLiteDatabase myDatabase;
    protected SQLiteStatement statement;



    private Boolean databaseExists() {
        File database = getApplicationContext().getDatabasePath("GrammarCourses");

        if (!database.exists()) {
            // Database does not exist so copy it from assets here
            return false;
        } else {
            return true;
        }
    }







    protected Cursor selectDBTable(String table) {

        Cursor c = myDatabase.rawQuery("SELECT * FROM " + table, null);
        return c;
//            Log.i("Count",String.valueOf(c.getCount()));
//
//            if (c.getCount() > 0) {
//
//                switch (table) {
//                    case "TableUpdate":
//                        while (c.moveToNext()) {
//                            Log.i("TableUpdate", "ID: " + c.getString(c.getColumnIndex("_id")) + "; Name: " + c.getString(c.getColumnIndex("tableName")) + "; Update Time: " + c.getString(c.getColumnIndex("updateTime")));
//                        }
//                        break;
//
//                    case "Course":
//                        while (c.moveToNext()) {
//                            Log.i("Course", "ID: " + c.getString(c.getColumnIndex("_id")) + "; Name: " + c.getString(c.getColumnIndex("name")) + "; Created: " + c.getString(c.getColumnIndex("Created")) + "; Last Edit: " + c.getString(c.getColumnIndex("LastEdit")));
//                        }
//                        break;
//
//
//                    case "Category":
//                        while (c.moveToNext()) {
//                            Log.i("Category", "ID: " + c.getString(c.getColumnIndex("_id")) + "; CourseID: " + c.getString(c.getColumnIndex("courseId")) + "; Type: " + c.getString(c.getColumnIndex("type")) + "; Internal Name: " + c.getString(c.getColumnIndex("internalName")) + "; Name: " + c.getString(c.getColumnIndex("name")) + "; IconURL: " + c.getString(c.getColumnIndex("iconURL")) + "; Content: " + c.getString(c.getColumnIndex("content")) + "; HasDialect: " + c.getString(c.getColumnIndex("hasDialect")) + "; ParentID: " + c.getString(c.getColumnIndex("parentId")) + "; Depth: " + c.getString(c.getColumnIndex("depth")) + "; Created: " + c.getString(c.getColumnIndex("Created")) + "; Last Edit: " + c.getString(c.getColumnIndex("LastEdit")));
//                        }
//                        break;
//
//                    case "Dialect":
//                        query += "(_id, courseId, name, code, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("course_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("code"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Content":
//                        query += "(_id, name, Created, LastEdit) VALUES (?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("name"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Video":
//                        query += "(_id, categoryId, URL, subtitledURL, hierarchy, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("Url"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("subtitledUrl"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Quiz":
//                        query += "(_id, categoryId, imageURL, instruction, type, hierarchy, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("imageUrl"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("kind"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("dialectId"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(9, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "QuizQuestion":
//                        query += "(_id, quizId, audioURL, wording, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("quiz_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("audioUrl"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("text"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "QuizAnswer":
//                        query += "(_id, quizQuestionId, imageURL, wording, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("quiz_question_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("imageUrl"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("text"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Tap":
//                        query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "TapItem":
//                        query += "(_id, tapId, label, pronunciation, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("tap_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("pronunciation"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("audioUrl"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "ThumbnailTap":
//                        query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "ThumbnailTapItem":
//                        query += "(_id, thumbnailTapId, name, translation, thumbnailURL, fullImageURL, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("thumbnail_tap_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("translation"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("thumbnailUrl"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("fullImageUrl"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("audioUrl"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Cluster":
//                        query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "ClusterItem":
//                        query += "(_id, clusterId, name, imageURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("cluster_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("imageUrl"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "ClusterSubItem":
//                        query += "(_id, clusterItemId, name, description, clickable, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("cluster_item_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("description"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("clickable"));
//                            statement.bindString(6, jsonArray.getJSONObject(i).getString("audioUrl"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(9, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "Dictionary":
//                        query += "(_id, courseId, title, instruction, help, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("course_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
//                            statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "DictionaryLetter":
//                        query += "(_id, dictionaryId, label, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("dictionary_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;
//
//                    case "DictionaryWord":
//                        query += "(_id, dictionaryLetterId, label, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
//                        myDatabase.beginTransaction();
//                        statement = myDatabase.compileStatement(query);
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            statement.clearBindings();
//                            statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
//                            statement.bindString(2, jsonArray.getJSONObject(i).getString("dictionary_letter_id"));
//                            statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
//                            statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
//                            statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
//                            statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
//                        }
//                        myDatabase.setTransactionSuccessful();
//                        myDatabase.endTransaction();
//                        break;

//                }
//            }
    }

}
