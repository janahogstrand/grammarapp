package com.rivaan.mainmenu.database;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.rivaan.mainmenu.mainmenu.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

/**
 * Created by ran on 16/03/16.
 */
public class DatabaseOperations {

    public SQLiteDatabase myDatabase;
    public SQLiteStatement statement;
    private Activity activity;

    public DatabaseOperations(Activity activity) {
        this.activity = activity;
    }


    public void checkDB() {
        myDatabase = activity.openOrCreateDatabase("GrammarCourses", activity.MODE_PRIVATE, null);
//        CheckDBTask task = new CheckDBTask();
//        task.execute("http://grammarapp.herokuapp.com/api/table_updates", "TableUpdate");
    }


    public void updateDBTable(String url, String table) {
        myDatabase = activity.openOrCreateDatabase("GrammarCourses", activity.MODE_PRIVATE, null);
        UpdateDBTask task = new UpdateDBTask(activity);
        task.execute(url, table, "insert");
    }


    public void createDB() {
        myDatabase = activity.openOrCreateDatabase("GrammarCourses", activity.MODE_PRIVATE, null);

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS TableUpdate (_id INTEGER NOT NULL PRIMARY KEY, tableName VARCHAR(50), updateTime VARCHAR(24))");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Course (_id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(50), creator VARCHAR(50), password VARCHAR(50), Created DATETIME, LastEdit DATETIME)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Category (_id INTEGER NOT NULL PRIMARY KEY, courseId INTEGER, type VARCHAR(6), internalName VARCHAR(50), name VARCHAR(50) NOT NULL, iconURL TEXT, content INTEGER, hierarchy INTEGER, hasDialect INTEGER, parentId INTEGER, depth INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Dialect (_id INTEGER NOT NULL PRIMARY KEY, courseId INTEGER, name VARCHAR(50), code VARCHAR(15), Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Content (_id INTEGER NOT NULL PRIMARY KEY, name VARCHAR(50), Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Video (_id INTEGER NOT NULL PRIMARY KEY, categoryId INTEGER, URL TEXT, subtitledURL TEXT, hierarchy INTEGER, dialectId INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Quiz (_id INTEGER NOT NULL PRIMARY KEY, categoryId INTEGER, imageURL TEXT, instruction TEXT, type VARCHAR(10), hierarchy INTEGER, dialectId INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS QuizQuestion (_id INTEGER NOT NULL PRIMARY KEY, quizId INTEGER, audioURL TEXT, wording TEXT, hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS QuizAnswer (_id INTEGER NOT NULL PRIMARY KEY, quizQuestionId INTEGER, imageURL TEXT, wording TEXT, hierarchy INTEGER, correct INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS QuizCompletion (_id INTEGER NOT NULL PRIMARY KEY, quizId INTEGER, completed INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Tap (_id INTEGER NOT NULL PRIMARY KEY, categoryId INTEGER, title TEXT, instruction TEXT, help TEXT, dialectId INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS TapItem (_id INTEGER NOT NULL PRIMARY KEY, tapId INTEGER, label TEXT, pronunciation TEXT, audioURL TEXT, hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS ThumbnailTap (_id INTEGER NOT NULL PRIMARY KEY, categoryId INTEGER, title TEXT, instruction TEXT, help TEXT, dialectId INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS ThumbnailTapItem (_id INTEGER NOT NULL PRIMARY KEY, thumbnailTapId INTEGER, name TEXT, translation TEXT, thumbnailURL TEXT, fullImageURL TEXT, audioURL TEXT, hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Cluster (_id INTEGER NOT NULL PRIMARY KEY, categoryId INTEGER, title TEXT, instruction TEXT, help TEXT, dialectId INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS ClusterItem (_id INTEGER NOT NULL PRIMARY KEY, clusterId INTEGER, name TEXT, imageURL TEXT, hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS ClusterSubItem (_id INTEGER NOT NULL PRIMARY KEY, clusterItemId INTEGER, name TEXT, description TEXT, clickable INTEGER, audioURL TEXT, hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS Dictionary (_id INTEGER NOT NULL PRIMARY KEY, courseId INTEGER, title TEXT, instruction TEXT, help TEXT, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS DictionaryLetter (_id INTEGER NOT NULL PRIMARY KEY, dictionaryId INTEGER, label VARCHAR(10), hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS DictionaryWord (_id INTEGER NOT NULL PRIMARY KEY, dictionaryLetterId INTEGER, label VARCHAR(10), hierarchy INTEGER, Created VARCHAR(24), LastEdit VARCHAR(24) NOT NULL)");

        updateDBTable("http://grammarapp.herokuapp.com/api/table_updates", "TableUpdate");
        updateDBTable("http://grammarapp.herokuapp.com/api/courses", "Course");
        updateDBTable("http://grammarapp.herokuapp.com/api/categories", "Category");
        updateDBTable("http://grammarapp.herokuapp.com/api/dialects", "Dialect");
        updateDBTable("http://grammarapp.herokuapp.com/api/contents", "Content");
//                updateDBTable("http://grammarapp.herokuapp.com/api/videos", "Video");
        updateDBTable("http://grammarapp.herokuapp.com/api/quizzes", "Quiz");
        updateDBTable("http://grammarapp.herokuapp.com/api/quiz_questions", "QuizQuestion");
        updateDBTable("http://grammarapp.herokuapp.com/api/quiz_answers", "QuizAnswer");
        updateDBTable("http://grammarapp.herokuapp.com/api/taps", "Tap");
        updateDBTable("http://grammarapp.herokuapp.com/api/tap_items", "TapItem");
        updateDBTable("http://grammarapp.herokuapp.com/api/thumbnail_taps", "ThumbnailTap");
        updateDBTable("http://grammarapp.herokuapp.com/api/thumbnail_tap_items", "ThumbnailTapItem");
        updateDBTable("http://grammarapp.herokuapp.com/api/clusters", "Cluster");
        updateDBTable("http://grammarapp.herokuapp.com/api/cluster_items", "ClusterItem");
        updateDBTable("http://grammarapp.herokuapp.com/api/cluster_sub_items", "ClusterSubItem");
        updateDBTable("http://grammarapp.herokuapp.com/api/dictionaries", "Dictionary");
        updateDBTable("http://grammarapp.herokuapp.com/api/dictionary_letters", "DictionaryLetter");
        updateDBTable("http://grammarapp.herokuapp.com/api/dictionary_words", "DictionaryWord");

    }


    public void checkUpdateTimes(String result) {
        myDatabase = activity.openOrCreateDatabase("GrammarCourses", activity.MODE_PRIVATE, null);
        try {
            JSONArray jsonArray = new JSONArray(result);

            HashMap<String, String> hmap = new HashMap<>();


            try (Cursor c = myDatabase.rawQuery("SELECT * FROM TableUpdate", null)) {
                Log.i("Count", String.valueOf(c.getCount()));
                if (c.getCount() > 0) {
                    while(c.moveToNext()) {
                        hmap.put(c.getString(c.getColumnIndex("tableName")), c.getString(c.getColumnIndex("updateTime")));
                    }
                }
                c.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


            for (int i = 0; i < jsonArray.length(); i++) {
                if(!jsonArray.getJSONObject(i).getString("updateTime").equals(hmap.get(jsonArray.getJSONObject(i).getString("tableName"))))
                {
                    updateDBTable("http://grammarapp.herokuapp.com/api/" + jsonArray.getJSONObject(i).getString("tableName"), jsonArray.getJSONObject(i).getString("tableName"));
                }
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void insertIntoDB(String result, String table) {
        myDatabase = activity.openOrCreateDatabase("GrammarCourses", activity.MODE_PRIVATE, null);
        try {

            JSONArray jsonArray = new JSONArray(result);

            String query = "INSERT INTO " + table + " ";

            switch (table) {
                case "TableUpdate":
                    query += "(_id, tableName, updateTime) VALUES (?, ?, ?);";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("tableName"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("updateTime"));
                        statement.execute();
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Course":
                    query += "(_id, name, creator, password, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?);";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("creator"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("password"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("updated_at"));
                        statement.execute();
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Category":
                    query += "(_id, courseId, type, internalName, name, iconURL, content, hasDialect, parentId, depth, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("course_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("kind"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("internalName"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("iconUrl"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("content"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("hasDialect"));
                        statement.bindString(9, jsonArray.getJSONObject(i).getString("parent_id"));
                        statement.bindString(10, jsonArray.getJSONObject(i).getString("depth"));
                        statement.bindString(11, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(12, jsonArray.getJSONObject(i).getString("updated_at"));
                        statement.execute();
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Dialect":
                    query += "(_id, courseId, name, code, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("course_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("code"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Content":
                    query += "(_id, name, Created, LastEdit) VALUES (?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Video":
                    query += "(_id, categoryId, URL, subtitledURL, hierarchy, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("Url"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("subtitledUrl"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Quiz":
                    query += "(_id, categoryId, imageURL, instruction, type, hierarchy, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("imageUrl"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("kind"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("dialectId"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(9, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "QuizQuestion":
                    query += "(_id, quizId, audioURL, wording, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("quiz_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("audioUrl"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("text"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "QuizAnswer":
                    query += "(_id, quizQuestionId, imageURL, wording, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("quiz_question_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("imageUrl"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("text"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Tap":
                    query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "TapItem":
                    query += "(_id, tapId, label, pronunciation, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("tap_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("pronunciation"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("audioUrl"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "ThumbnailTap":
                    query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "ThumbnailTapItem":
                    query += "(_id, thumbnailTapId, name, translation, thumbnailURL, fullImageURL, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("thumbnail_tap_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("translation"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("thumbnailUrl"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("fullImageUrl"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("audioUrl"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(9, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(10, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Cluster":
                    query += "(_id, categoryId, title, instruction, help, dialectId, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("category_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("dialectId"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "ClusterItem":
                    query += "(_id, clusterId, name, imageURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("cluster_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("imageUrl"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "ClusterSubItem":
                    query += "(_id, clusterItemId, name, description, clickable, audioURL, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("cluster_item_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("name"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("description"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("clickable"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("audioUrl"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(8, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(9, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "Dictionary":
                    query += "(_id, courseId, title, instruction, help, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("course_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("title"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("instruction"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("help"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(7, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "DictionaryLetter":
                    query += "(_id, dictionaryId, label, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("dictionary_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

                case "DictionaryWord":
                    query += "(_id, dictionaryLetterId, label, hierarchy, Created, LastEdit) VALUES (?, ?, ?, ?, ?, ?)";
                    myDatabase.beginTransaction();
                    statement = myDatabase.compileStatement(query);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        statement.clearBindings();
                        statement.bindString(1, jsonArray.getJSONObject(i).getString("id"));
                        statement.bindString(2, jsonArray.getJSONObject(i).getString("dictionary_letter_id"));
                        statement.bindString(3, jsonArray.getJSONObject(i).getString("label"));
                        statement.bindString(4, jsonArray.getJSONObject(i).getString("hierarchy"));
                        statement.bindString(5, jsonArray.getJSONObject(i).getString("created_at"));
                        statement.bindString(6, jsonArray.getJSONObject(i).getString("updated_at"));
                    }
                    myDatabase.setTransactionSuccessful();
                    myDatabase.endTransaction();
                    break;

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
