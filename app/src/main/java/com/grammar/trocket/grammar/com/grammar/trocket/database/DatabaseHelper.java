package com.grammar.trocket.grammar.com.grammar.trocket.database;

        import android.app.ProgressDialog;
        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.AsyncTask;
        import android.util.Log;

        import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;

        import org.json.JSONArray;
        import org.json.JSONException;

        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.MalformedURLException;
        import java.net.URL;

/**
 * Created by ran on 21/03/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GrammarCourses.db";
    public static final int DATABASE_VERSION = 1;


    /*  Todo: Copy the format of the TABLE/COLUMN variables below and do the same for the other tables.
    Todo: Also, underneath those variables, the DATABASE_TABLE_NAMES String array needs to be updated with the new table variables. */

    public static final String TABLEUPDATES_TABLE = "table_updates";
    public static final String TABLEUPDATES_ID = "id";
    public static final String TABLEUPDATES_TABLENAME = "tableName";
    public static final String TABLEUPDATES_UPDATETIME = "updateTime";
    public static final String[] TABLEUPDATE_COLUMNS = {
            TABLEUPDATES_ID,
            TABLEUPDATES_TABLENAME,
            TABLEUPDATES_UPDATETIME
    };


    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_ID = "id";
    public static final String COURSE_NAME = "name";
    public static final String COURSE_CREATEDAT = "created_at";
    public static final String COURSE_UPDATEDAT = "updated_at";
    public static final String COURSE_CREATOR = "creator";
    public static final String COURSE_PASSWORD = "password";
    public static final String[] COURSE_COLUMNS = {
            COURSE_ID,
            COURSE_NAME,
            COURSE_CREATEDAT,
            COURSE_UPDATEDAT,
            COURSE_CREATOR,
            COURSE_PASSWORD
    };


    public static final String CATEGORY_TABLE = "categories";
    public static final String CATEGORY_ID = "id";
    public static final String CATEGORY_KIND = "kind";
    public static final String CATEGORY_NAME = "name";
    public static final String CATEGORY_ICONURL = "iconUrl";
    public static final String CATEGORY_CONTENT = "content";
    public static final String CATEGORY_HIERARCHY = "hierarchy";
    public static final String CATEGORY_CREATEDAT = "created_at";
    public static final String CATEGORY_UPDATEDAT = "updated_at";
    public static final String CATEGORY_COURSEID = "course_id";
    public static final String CATEGORY_PARENTID = "parent_id";
    public static final String CATEGORY_HASDIALECT = "hasDialect";
    public static final String CATEGORY_DEPTH = "depth";
    public static final String[] CATEGORY_COLUMNS = {
            CATEGORY_ID,
            CATEGORY_KIND,
            CATEGORY_NAME,
            CATEGORY_ICONURL,
            CATEGORY_CONTENT,
            CATEGORY_HIERARCHY,
            CATEGORY_CREATEDAT,
            CATEGORY_UPDATEDAT,
            CATEGORY_COURSEID,
            CATEGORY_PARENTID,
            CATEGORY_HASDIALECT,
            CATEGORY_DEPTH
    };


    public static final String CONTENT_TABLE = "contents";
    public static final String CONTENT_ID = "id";
    public static final String CONTENT_NAME = "name";
    public static final String CONTENT_CREATEDAT = "created_at";
    public static final String CONTENT_UPDATEDAT = "updated_at";
    public static final String[] CONTENT_COLUMNS = {
            CONTENT_ID,
            CONTENT_NAME,
            CONTENT_CREATEDAT,
            CONTENT_UPDATEDAT
    };


    public static final String DIALECT_TABLE = "dialects";
    public static final String DIALECT_ID = "id";
    public static final String DIALECT_NAME = "name";
    public static final String DIALECT_CODE = "code";
    public static final String DIALECT_CREATEDAT = "created_at";
    public static final String DIALECT_UPDATEDAT = "updated_at";
    public static final String DIALECT_COURSEID = "course_id";
    public static final String[] DIALECT_COLUMNS = {
            DIALECT_ID,
            DIALECT_NAME,
            DIALECT_CODE,
            DIALECT_CREATEDAT,
            DIALECT_UPDATEDAT,
            DIALECT_COURSEID
    };


    public static final String DICTIONARY_TABLE = "dictionaries";
    public static final String DICTIONARY_ID = "id";
    public static final String DICTIONARY_TITLE = "title";
    public static final String DICTIONARY_HELP = "help";
    public static final String DICTIONARY_INSTRUCTION = "instruction";
    public static final String DICTIONARY_CREATEDAT = "created_at";
    public static final String DICTIONARY_UPDATEDDAT = "updated_at";
    public static final String DICTIONARY_COURSEID = "course_id";
    public static final String[] DICTIONARY_COLUMNS = {
            DICTIONARY_ID,
            DICTIONARY_TITLE,
            DICTIONARY_HELP,
            DICTIONARY_INSTRUCTION,
            DICTIONARY_CREATEDAT,
            DICTIONARY_UPDATEDDAT,
            DICTIONARY_COURSEID
    };


    public static final String QUIZ_TABLE = "quizzes";
    public static final String QUIZ_ID = "id";
    public static final String QUIZ_INSTRUCTION = "instruction";
    public static final String QUIZ_IMAGEURL = "imageUrl";
    public static final String QUIZ_CREATEDAT = "created_at";
    public static final String QUIZ_UPDATEDAT = "updated_at";
    public static final String QUIZ_CATEGORYID = "category_id";
    public static final String QUIZ_DIALECTID = "dialectId";
    public static final String QUIZ_KIND = "kind";
    public static final String QUIZ_HIERARCHY = "hierarchy";
    public static final String QUIZ_COURSEID = "course_id";
    public static final String[] QUIZ_COLUMNS = {
            QUIZ_ID,
            QUIZ_INSTRUCTION,
            QUIZ_IMAGEURL,
            QUIZ_CREATEDAT,
            QUIZ_UPDATEDAT,
            QUIZ_CATEGORYID,
            QUIZ_DIALECTID,
            QUIZ_KIND,
            QUIZ_HIERARCHY,
            QUIZ_COURSEID
    };

    public static final String[] DATABASE_TABLE_NAMES = {
            TABLEUPDATES_TABLE,
            COURSE_TABLE,
            CATEGORY_TABLE,
            CONTENT_TABLE,
            DIALECT_TABLE,
            DICTIONARY_TABLE,
            QUIZ_TABLE
    };


    public static String jsonData = "";
    public static Boolean taskDone = false;
    private static String[] selectedColumns = null;
    private static DatabaseHelper sInstance;
    private SQLiteDatabase db;

    public static synchronized DatabaseHelper getInstance(Context context) {

        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        for(String TABLE : DATABASE_TABLE_NAMES) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
            Log.w("Dropping..", TABLE);
        }

        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLEUPDATES_TABLE + " (" + TABLEUPDATES_ID + " INTEGER NOT NULL, " + TABLEUPDATES_TABLENAME + " VARCHAR(50), " + TABLEUPDATES_UPDATETIME + " VARCHAR(24))");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + COURSE_TABLE + " (" + COURSE_ID + " INTEGER NOT NULL, " + COURSE_NAME + " VARCHAR(50), " + COURSE_CREATOR + " VARCHAR(50), " + COURSE_PASSWORD + " VARCHAR(50), " + COURSE_CREATEDAT + " DATETIME, " + COURSE_UPDATEDAT + " DATETIME)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE + " (" + CATEGORY_ID + " INTEGER NOT NULL, " + CATEGORY_COURSEID + " INTEGER, " + CATEGORY_KIND + " VARCHAR(6), " + CATEGORY_NAME + " VARCHAR(50) NOT NULL, " + CATEGORY_ICONURL + " TEXT, " + CATEGORY_CONTENT + " INTEGER, " + CATEGORY_HIERARCHY + " INTEGER, " + CATEGORY_HASDIALECT + " INTEGER, " + CATEGORY_PARENTID + " INTEGER, " + CATEGORY_DEPTH + " INTEGER, " + CATEGORY_CREATEDAT + " VARCHAR(24), " + CATEGORY_UPDATEDAT + " VARCHAR(24) NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + DIALECT_TABLE + " (" + DIALECT_ID + " INTEGER NOT NULL, " + DIALECT_COURSEID + " INTEGER, " + DIALECT_NAME + " VARCHAR(50), " + DIALECT_CODE + " VARCHAR(15), " + DIALECT_CREATEDAT + " VARCHAR(24), " + DIALECT_UPDATEDAT + " VARCHAR(24) NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + CONTENT_TABLE + " (" + CONTENT_ID + " INTEGER NOT NULL, " + CONTENT_NAME + " VARCHAR(50), " + CONTENT_CREATEDAT + " VARCHAR(24), " + CONTENT_UPDATEDAT + " VARCHAR(24) NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + QUIZ_TABLE + " (" + QUIZ_ID + " INTEGER NOT NULL, " + QUIZ_CATEGORYID + " INTEGER, " + QUIZ_COURSEID + " INTEGER, " + QUIZ_IMAGEURL + " TEXT, " + QUIZ_INSTRUCTION + " TEXT, " + QUIZ_KIND + " VARCHAR(10), " + QUIZ_HIERARCHY + " INTEGER, " + QUIZ_DIALECTID + " INTEGER, " + QUIZ_CREATEDAT + " VARCHAR(24), " + QUIZ_UPDATEDAT + " VARCHAR(24) NOT NULL)");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + DICTIONARY_TABLE + " (" + DICTIONARY_ID + " INTEGER NOT NULL, " + DICTIONARY_COURSEID + " INTEGER, " + DICTIONARY_TITLE + " TEXT, " + DICTIONARY_INSTRUCTION + " TEXT, " + DICTIONARY_HELP + " TEXT, " + DICTIONARY_CREATEDAT + " VARCHAR(24), " + DICTIONARY_UPDATEDDAT + " VARCHAR(24) NOT NULL)");

        for(String table: DATABASE_TABLE_NAMES) {
            insertIntoTable("http://grammarapp.herokuapp.com/api/" + table, table);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for(String TABLE : DATABASE_TABLE_NAMES) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        }
        onCreate(db);

    }

    public Cursor selectDBTable(String table) {
        SQLiteDatabase myDatabase = this.getWritableDatabase();
        Cursor c = myDatabase.rawQuery("SELECT * FROM " + table, null);
        return c;
    }

    public void insertIntoTable(String url, String table) {
        downloadJSON(url);
        while(taskDone != true) {
        }
        if (insertData(table, jsonData)) {
            Log.i("Result", "Data inserted");
        } else {
            Log.i("Result", "Data HAS NOT BEEN inserted");
        }
        taskDone = false;
    }

    public Boolean insertData(String... params) {
        //getWritableDatabase();
        long result = 0;
        switch(params[0]) {
            case TABLEUPDATES_TABLE:
                selectedColumns = TABLEUPDATE_COLUMNS;
                break;
            case COURSE_TABLE:
                selectedColumns = COURSE_COLUMNS;
                break;
            case CATEGORY_TABLE:
                selectedColumns = CATEGORY_COLUMNS;
                break;
            case CONTENT_TABLE:
                selectedColumns = CONTENT_COLUMNS;
                break;
            case DIALECT_TABLE:
                selectedColumns = DIALECT_COLUMNS;
                break;
            case DICTIONARY_TABLE:
                selectedColumns = DICTIONARY_COLUMNS;
                break;
            case QUIZ_TABLE:
                selectedColumns = QUIZ_COLUMNS;
                break;
        }

        try {
            JSONArray jsonArray = new JSONArray(params[1]);
            Log.i("Columns", params[0]);
            Log.i("JSON", (Integer.toString(jsonArray.getJSONObject(0).length())));

            //SQLiteDatabase db = this.getWritableDatabase();
            for(int j = 0; j < jsonArray.length(); ++j) {
                ContentValues contentValues = new ContentValues();
                for (int i = 0; i < selectedColumns.length; ++i) {
                    contentValues.put(selectedColumns[i], jsonArray.getJSONObject(j).getString(selectedColumns[i]));
                }
                result = this.db.insert(params[0], null, contentValues);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public void downloadJSON(String url) {
        DownloadJSONTask task = new DownloadJSONTask();
        task.execute(url);
    }

    public class DownloadJSONTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            URL url;
            HttpURLConnection urlConnection = null;
            try {
                jsonData = "";
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isr = new InputStreamReader(in);

                int data = isr.read();

                while(data != -1) {
                    jsonData += (char) data;
                    data = isr.read();
                }
                taskDone = true;
                return jsonData;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            taskDone = true;
            return jsonData;
        }
    }


}
