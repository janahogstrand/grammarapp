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
    public static final String DICTIONARY_UPDATEDAT = "updated_at";
    public static final String DICTIONARY_COURSEID = "course_id";
    public static final String[] DICTIONARY_COLUMNS = {
            DICTIONARY_ID,
            DICTIONARY_TITLE,
            DICTIONARY_HELP,
            DICTIONARY_INSTRUCTION,
            DICTIONARY_CREATEDAT,
            DICTIONARY_UPDATEDAT,
            DICTIONARY_COURSEID
    };


    public static final String DICTIONARYLETTER_TABLE = "dictionary_letters";
    public static final String DICTIONARYLETTER_ID = "id";
    public static final String DICTIONARYLETTER_LABEL = "label";
    public static final String DICTIONARYLETTER_CREATEDAT = "created_at";
    public static final String DICTIONARYLETTER_UPDATEDAT = "updated_at";
    public static final String DICTIONARYLETTER_DICTIONARYID = "dictionary_id";
    public static final String DICTIONARYLETTER_COURSEID = "course_id";
    public static final String DICTIONARYLETTER_AUDIOURL = "audioUrl";
    public static final String[] DICTIONARYLETTER_COLUMNS = {
            DICTIONARYLETTER_ID,
            DICTIONARYLETTER_LABEL,
            DICTIONARYLETTER_CREATEDAT,
            DICTIONARYLETTER_UPDATEDAT,
            DICTIONARYLETTER_DICTIONARYID,
            DICTIONARYLETTER_COURSEID,
            DICTIONARYLETTER_AUDIOURL
    };


    public static final String DICTIONARYWORD_TABLE = "dictionary_words";
    public static final String DICTIONARYWORD_ID = "id";
    public static final String DICTIONARYWORD_LABEL = "label";
    public static final String DICTIONARYWORD_CREATEDAT = "created_at";
    public static final String DICTIONARYWORD_UPDATEDAT = "updated_at";
    public static final String DICTIONARYWORD_DICTIONARYLETTERID = "dictionary_letter_id";
    public static final String DICTIONARYWORD_COURSEID = "course_id";
    public static final String[] DICTIONARYWORD_COLUMNS = {
            DICTIONARYWORD_ID,
            DICTIONARYWORD_LABEL,
            DICTIONARYWORD_CREATEDAT,
            DICTIONARYWORD_UPDATEDAT,
            DICTIONARYWORD_DICTIONARYLETTERID,
            DICTIONARYWORD_COURSEID
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


    public static final String QUIZQUESTION_TABLE = "quiz_questions";
    public static final String QUIZQUESTION_ID = "id";
    public static final String QUIZQUESTION_TEXT = "text";
    public static final String QUIZQUESTION_HIERARCHY = "hierarchy";
    public static final String QUIZQUESTION_CREATEDAT = "created_at";
    public static final String QUIZQUESTION_UPDATEDAT = "updated_at";
    public static final String QUIZQUESTION_QUIZID = "quiz_id";
    public static final String QUIZQUESTION_AUDIOURL = "audioUrl";
    public static final String QUIZQUESTION_COURSEID = "course_id";
    public static final String[] QUIZQUESTION_COLUMNS = {
            QUIZQUESTION_ID,
            QUIZQUESTION_TEXT,
            QUIZQUESTION_HIERARCHY,
            QUIZQUESTION_CREATEDAT,
            QUIZQUESTION_UPDATEDAT,
            QUIZQUESTION_QUIZID,
            QUIZQUESTION_AUDIOURL,
            QUIZQUESTION_COURSEID
    };


    public static final String QUIZANSWER_TABLE = "quiz_answers";
    public static final String QUIZANSWER_ID = "id";
    public static final String QUIZANSWER_TEXT = "text";
    public static final String QUIZANSWER_HIERARCHY = "hierarchy";
    public static final String QUIZANSWER_CREATEDAT = "created_at";
    public static final String QUIZANSWER_UPDATEDAT = "updated_at";
    public static final String QUIZANSWER_QUIZQUESTIONID = "quiz_question_id";
    public static final String QUIZANSWER_CORRECT = "correct";
    public static final String QUIZANSWER_IMAGEURL = "imageUrl";
    public static final String QUIZANSWER_COURSEID = "course_id";
    public static final String[] QUIZANSWER_COLUMNS = {
            QUIZANSWER_ID,
            QUIZANSWER_TEXT,
            QUIZANSWER_HIERARCHY,
            QUIZANSWER_CREATEDAT,
            QUIZANSWER_UPDATEDAT,
            QUIZANSWER_QUIZQUESTIONID,
            QUIZANSWER_CORRECT,
            QUIZANSWER_IMAGEURL,
            QUIZANSWER_COURSEID
    };


    public static final String VIDEO_TABLE = "videos";
    public static final String VIDEO_ID = "id";
    public static final String VIDEO_URL = "url";
    public static final String VIDEO_SUBTITLEDURL = "subtitledUrl";
    public static final String VIDEO_HIERARCHY = "hierarchy";
    public static final String VIDEO_DIALECTID = "dialectId";
    public static final String VIDEO_CREATEDAT = "created_at";
    public static final String VIDEO_UPDATEDAT = "updated_at";
    public static final String VIDEO_CATEGORYID = "category_id";
    public static final String VIDEO_TITLE = "title";
    public static final String VIDEO_COURSEID = "course_id";
    public static final String[] VIDEO_COLUMNS = {
            VIDEO_ID,
            VIDEO_URL,
            VIDEO_SUBTITLEDURL,
            VIDEO_HIERARCHY,
            VIDEO_DIALECTID,
            VIDEO_CREATEDAT,
            VIDEO_UPDATEDAT,
            VIDEO_CATEGORYID,
            VIDEO_TITLE,
            VIDEO_COURSEID
    };


    public static final String TAP_TABLE = "taps";
    public static final String TAP_ID = "id";
    public static final String TAP_TITLE = "title";
    public static final String TAP_INSTRUCTION = "instruction";
    public static final String TAP_DIALECTID = "dialectId";
    public static final String TAP_HELP = "help";
    public static final String TAP_CREATEDAT = "created_at";
    public static final String TAP_UPDATEDAT = "updated_at";
    public static final String TAP_CATEGORYID = "category_id";
    public static final String TAP_COURSEID = "course_id";
    public static final String[] TAP_COLUMNS = {
            TAP_ID,
            TAP_TITLE,
            TAP_INSTRUCTION,
            TAP_DIALECTID,
            TAP_HELP,
            TAP_CREATEDAT,
            TAP_UPDATEDAT,
            TAP_CATEGORYID,
            TAP_COURSEID
    };


    public static final String TAPITEM_TABLE = "tap_items";
    public static final String TAPITEM_ID = "id";
    public static final String TAPITEM_LABEL = "label";
    public static final String TAPITEM_PRONUNCIATION = "pronunciation";
    public static final String TAPITEM_AUDIOURL = "audioUrl";
    public static final String TAPITEM_HIERARCHY = "hierarchy";
    public static final String TAPITEM_CREATEDAT = "created_at";
    public static final String TAPITEM_UPDATEDAT = "updated_at";
    public static final String TAPITEM_TAPID = "tap_id";
    public static final String TAPITEM_COURSEID = "course_id";
    public static final String[] TAPITEM_COLUMNS = {
            TAPITEM_ID,
            TAPITEM_LABEL,
            TAPITEM_PRONUNCIATION,
            TAPITEM_AUDIOURL,
            TAPITEM_HIERARCHY,
            TAPITEM_CREATEDAT,
            TAPITEM_UPDATEDAT,
            TAPITEM_TAPID,
            TAPITEM_COURSEID
    };


    public static final String THUMBNAILTAP_TABLE = "thumbnail_taps";
    public static final String THUMBNAILTAP_ID = "id";
    public static final String THUMBNAILTAP_TITLE = "title";
    public static final String THUMBNAILTAP_INSTRUCTION = "instruction";
    public static final String THUMBNAILTAP_DIALECTID = "dialectId";
    public static final String THUMBNAILTAP_HELP = "help";
    public static final String THUMBNAILTAP_CREATEDAT = "created_at";
    public static final String THUMBNAILTAP_UPDATEDAT = "updated_at";
    public static final String THUMBNAILTAP_CATEGORYID = "category_id";
    public static final String THUMBNAILTAP_COURSEID = "course_id";
    public static final String[] THUMBNAILTAP_COLUMNS = {
            THUMBNAILTAP_ID,
            THUMBNAILTAP_TITLE,
            THUMBNAILTAP_INSTRUCTION,
            THUMBNAILTAP_DIALECTID,
            THUMBNAILTAP_HELP,
            THUMBNAILTAP_CREATEDAT,
            THUMBNAILTAP_UPDATEDAT,
            THUMBNAILTAP_CATEGORYID,
            THUMBNAILTAP_COURSEID
    };


    public static final String THUMBNAILTAPITEM_TABLE = "tap_items";
    public static final String THUMBNAILTAPITEM_ID = "id";
    public static final String THUMBNAILTAPITEM_NAME = "name";
    public static final String THUMBNAILTAPITEM_TRANSLATION = "translation";
    public static final String THUMBNAILTAPITEM_FULLIMAGEURL = "fullImageUrl";
    public static final String THUMBNAILTAPITEM_AUDIOURL = "audioUrl";
    public static final String THUMBNAILTAPITEM_HIERARCHY = "hierarchy";
    public static final String THUMBNAILTAPITEM_CREATEDAT = "created_at";
    public static final String THUMBNAILTAPITEM_UPDATEDAT = "updated_at";
    public static final String THUMBNAILTAPITEM_THUMBNAILTAPID = "thumbnail_tap_id";
    public static final String THUMBNAILTAPITEM_COURSEID = "course_id";
    public static final String[] THUMBNAILTAPITEM_COLUMNS = {
            THUMBNAILTAPITEM_ID,
            THUMBNAILTAPITEM_NAME,
            THUMBNAILTAPITEM_TRANSLATION,
            THUMBNAILTAPITEM_FULLIMAGEURL,
            THUMBNAILTAPITEM_AUDIOURL,
            THUMBNAILTAPITEM_HIERARCHY,
            THUMBNAILTAPITEM_CREATEDAT,
            THUMBNAILTAPITEM_UPDATEDAT,
            THUMBNAILTAPITEM_THUMBNAILTAPID,
            THUMBNAILTAPITEM_COURSEID
    };


    public static final String CLUSTER_TABLE = "clusters";
    public static final String CLUSTER_ID = "id";
    public static final String CLUSTER_TITLE = "title";
    public static final String CLUSTER_INSTRUCTION = "instruction";
    public static final String CLUSTER_DIALECTID = "dialectId";
    public static final String CLUSTER_HELP = "help";
    public static final String CLUSTER_CREATEDAT = "created_at";
    public static final String CLUSTER_UPDATEDAT = "updated_at";
    public static final String CLUSTER_CATEGORYID = "category_id";
    public static final String CLUSTER_COURSEID = "course_id";
    public static final String[] CLUSTER_COLUMNS = {
            CLUSTER_ID,
            CLUSTER_TITLE,
            CLUSTER_INSTRUCTION,
            CLUSTER_DIALECTID,
            CLUSTER_HELP,
            CLUSTER_CREATEDAT,
            CLUSTER_UPDATEDAT,
            CLUSTER_CATEGORYID,
            CLUSTER_COURSEID
    };


    public static final String CLUSTERITEM_TABLE = "cluster_items";
    public static final String CLUSTERITEM_ID = "id";
    public static final String CLUSTERITEM_NAME = "name";
    public static final String CLUSTERITEM_HIERARCHY = "hierarchy";
    public static final String CLUSTERITEM_CREATEDAT = "created_at";
    public static final String CLUSTERITEM_UPDATEDAT = "updated_at";
    public static final String CLUSTERITEM_CLUSTERID = "cluster_id";
    public static final String CLUSTERITEM_COURSEID = "course_id";
    public static final String[] CLUSTERITEM_COLUMNS = {
            CLUSTERITEM_ID,
            CLUSTERITEM_NAME,
            CLUSTERITEM_HIERARCHY,
            CLUSTERITEM_CREATEDAT,
            CLUSTERITEM_UPDATEDAT,
            CLUSTERITEM_CLUSTERID,
            CLUSTERITEM_COURSEID
    };


    public static final String CLUSTERSUBITEM_TABLE = "cluster_sub_items";
    public static final String CLUSTERSUBITEM_ID = "id";
    public static final String CLUSTERSUBITEM_NAME = "name";
    public static final String CLUSTERSUBITEM_DESCRIPTION = "description";
    public static final String CLUSTERSUBITEM_CLICKABLE = "clickable";
    public static final String CLUSTERSUBITEM_AUDIOURL = "audioUrl";
    public static final String CLUSTERSUBITEM_HIERARCHY = "hierarchy";
    public static final String CLUSTERSUBITEM_CREATEDAT = "created_at";
    public static final String CLUSTERSUBITEM_UPDATEDAT = "updated_at";
    public static final String CLUSTERSUBITEM_CLUSTERITEMID = "cluster_item_id";
    public static final String CLUSTERSUBITEM_COURSEID = "course_id";
    public static final String[] CLUSTERSUBITEM_COLUMNS = {
            CLUSTERSUBITEM_ID,
            CLUSTERSUBITEM_NAME,
            CLUSTERSUBITEM_DESCRIPTION,
            CLUSTERSUBITEM_CLICKABLE,
            CLUSTERSUBITEM_AUDIOURL,
            CLUSTERSUBITEM_HIERARCHY,
            CLUSTERSUBITEM_CREATEDAT,
            CLUSTERSUBITEM_UPDATEDAT,
            CLUSTERSUBITEM_CLUSTERITEMID,
            CLUSTERSUBITEM_COURSEID
    };


    public static String[] DATABASE_TABLE_NAMES = {
            TABLEUPDATES_TABLE,
            COURSE_TABLE,
            CATEGORY_TABLE,
            CONTENT_TABLE,
            DIALECT_TABLE,
            DICTIONARY_TABLE,
            DICTIONARYLETTER_TABLE,
            DICTIONARYWORD_TABLE,
            QUIZ_TABLE,
            QUIZQUESTION_TABLE,
            //QUIZANSWER_TABLE,
            VIDEO_TABLE
// IF UNCOMMENTING THE LINES BELOW, ADD A COMMA TO THE END OF THE LINE ABOVE.
//            TAP_TABLE,
//            TAPITEM_TABLE,
//            THUMBNAILTAP_TABLE,
//            THUMBNAILTAPITEM_TABLE,
//            CLUSTER_TABLE,
//            CLUSTERITEM_TABLE,
//            CLUSTERSUBITEM_TABLE
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
        Log.w("Created..", TABLEUPDATES_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + COURSE_TABLE + " (" + COURSE_ID + " INTEGER NOT NULL, " + COURSE_NAME + " VARCHAR(50), " + COURSE_CREATOR + " VARCHAR(50), " + COURSE_PASSWORD + " VARCHAR(50), " + COURSE_CREATEDAT + " DATETIME, " + COURSE_UPDATEDAT + " DATETIME)");
        Log.w("Created..", TABLEUPDATES_ID);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CATEGORY_TABLE + " (" + CATEGORY_ID + " INTEGER NOT NULL, " + CATEGORY_COURSEID + " INTEGER, " + CATEGORY_KIND + " VARCHAR(6), " + CATEGORY_NAME + " VARCHAR(50) NOT NULL, " + CATEGORY_ICONURL + " TEXT, " + CATEGORY_CONTENT + " INTEGER, " + CATEGORY_HIERARCHY + " INTEGER, " + CATEGORY_HASDIALECT + " INTEGER, " + CATEGORY_PARENTID + " INTEGER, " + CATEGORY_DEPTH + " INTEGER, " + CATEGORY_CREATEDAT + " VARCHAR(24), " + CATEGORY_UPDATEDAT + " VARCHAR(24) NOT NULL)");
        Log.w("Created..", CATEGORY_ID);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DIALECT_TABLE + " (" + DIALECT_ID + " INTEGER NOT NULL, " + DIALECT_COURSEID + " INTEGER, " + DIALECT_NAME + " VARCHAR(50), " + DIALECT_CODE + " VARCHAR(15), " + DIALECT_CREATEDAT + " VARCHAR(24), " + DIALECT_UPDATEDAT + " VARCHAR(24) NOT NULL)");
        Log.w("Created..", DIALECT_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + CONTENT_TABLE + " (" + CONTENT_ID + " INTEGER NOT NULL, " + CONTENT_NAME + " VARCHAR(50), " + CONTENT_CREATEDAT + " VARCHAR(24), " + CONTENT_UPDATEDAT + " VARCHAR(24) NOT NULL)");
        Log.w("Created..", CONTENT_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + QUIZ_TABLE + " (" + QUIZ_ID + " INTEGER NOT NULL, " + QUIZ_CATEGORYID + " INTEGER, " + QUIZ_COURSEID + " INTEGER, " + QUIZ_IMAGEURL + " TEXT, " + QUIZ_INSTRUCTION + " TEXT, " + QUIZ_KIND + " VARCHAR(10), " + QUIZ_HIERARCHY + " INTEGER, " + QUIZ_DIALECTID + " INTEGER, " + QUIZ_CREATEDAT + " VARCHAR(24), " + QUIZ_UPDATEDAT + " VARCHAR(24) NOT NULL)");
        Log.w("Created..", QUIZ_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + QUIZQUESTION_TABLE + " (" + QUIZQUESTION_ID + " INTEGER NOT NULL, \"" + QUIZQUESTION_TEXT + "\" TEXT, " + QUIZQUESTION_HIERARCHY + " INTEGER, " + QUIZQUESTION_CREATEDAT + " VARCHAR(24), " + QUIZQUESTION_UPDATEDAT + " VARCHAR(24), " + QUIZQUESTION_QUIZID + " INTEGER, " + QUIZQUESTION_AUDIOURL + " TEXT, " + QUIZQUESTION_COURSEID + " INTEGER)");
        Log.w("Created..", QUIZQUESTION_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + QUIZANSWER_TABLE + " (" + QUIZANSWER_ID + " INTEGER NOT NULL, \"" + QUIZANSWER_TEXT + "\" TEXT, " + QUIZANSWER_HIERARCHY + " INTEGER, " + QUIZANSWER_CREATEDAT + " VARCHAR(24), " + QUIZANSWER_UPDATEDAT + " VARCHAR(24), " + QUIZANSWER_QUIZQUESTIONID + " INTEGER, " + QUIZANSWER_CORRECT + " INTEGER, " + QUIZANSWER_IMAGEURL + " TEXT, " + QUIZANSWER_COURSEID + " INTEGER)");
        Log.w("Created..", QUIZANSWER_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DICTIONARY_TABLE + " (" + DICTIONARY_ID + " INTEGER NOT NULL, " + DICTIONARY_COURSEID + " INTEGER, " + DICTIONARY_TITLE + " TEXT, " + DICTIONARY_INSTRUCTION + " TEXT, " + DICTIONARY_HELP + " TEXT, " + DICTIONARY_CREATEDAT + " VARCHAR(24), " + DICTIONARY_UPDATEDAT + " VARCHAR(24) NOT NULL)");
        Log.w("Created..", DICTIONARY_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DICTIONARYLETTER_TABLE + " (" + DICTIONARYLETTER_ID + " INTEGER NOT NULL, " + DICTIONARYLETTER_LABEL + " TEXT, " + DICTIONARYLETTER_CREATEDAT + " VARCHAR(24), " + DICTIONARYLETTER_UPDATEDAT + " VARCHAR(24), " + DICTIONARYLETTER_DICTIONARYID + " INTEGER, " + DICTIONARYLETTER_COURSEID + " INTEGER, " + DICTIONARYLETTER_AUDIOURL + " TEXT)");
        Log.w("Created..", DICTIONARYLETTER_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DICTIONARYWORD_TABLE + " (" + DICTIONARYWORD_ID + " INTEGER NOT NULL, " + DICTIONARYWORD_LABEL + " TEXT, " + DICTIONARYWORD_CREATEDAT + " VARCHAR(24), " + DICTIONARYWORD_UPDATEDAT + " VARCHAR(24), " + DICTIONARYWORD_DICTIONARYLETTERID + " INTEGER, " + DICTIONARYWORD_COURSEID + " INTEGER)");
        Log.w("Created..", DICTIONARYWORD_TABLE);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + VIDEO_TABLE + " (" + VIDEO_ID + " INTEGER NOT NULL, " + VIDEO_URL + " TEXT, " + VIDEO_SUBTITLEDURL + " TEXT, " + VIDEO_HIERARCHY + " INTEGER, " + VIDEO_DIALECTID + " INTEGER, " + VIDEO_CREATEDAT + " VARCHAR(24), " + VIDEO_UPDATEDAT + " VARCHAR(24), " + VIDEO_CATEGORYID + " INTEGER, " + VIDEO_TITLE + " TEXT, " + VIDEO_COURSEID + " INTEGER)");
        Log.w("Created..", VIDEO_TABLE);

        for(String table: DATABASE_TABLE_NAMES) {
            insertIntoTable("http://grammarcms.herokuapp.com/api/" + table, table);
            Log.w("Inserted..", table);
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
        //downloadJSON(url, table);

        DownloadJSONTask task = new DownloadJSONTask();
        task.execute(url, table);

//        while(!taskDone) {
//            Log.w("WHILE", "STUCK IN WHILE LOOP");
//        }
//        if (insertData(table, jsonData)) {
//            Log.i("Result", "Data inserted");
//        } else {
//            Log.i("Result", "Data HAS NOT BEEN inserted");
//        }
        //taskDone = false;
    }

//    public Boolean insertData(String... params) {
//        //getWritableDatabase();
//        long result = 0;
//        switch(params[0]) {
//            case TABLEUPDATES_TABLE:
//                selectedColumns = TABLEUPDATE_COLUMNS;
//                break;
//            case COURSE_TABLE:
//                selectedColumns = COURSE_COLUMNS;
//                break;
//            case CATEGORY_TABLE:
//                selectedColumns = CATEGORY_COLUMNS;
//                break;
//            case CONTENT_TABLE:
//                selectedColumns = CONTENT_COLUMNS;
//                break;
//            case DIALECT_TABLE:
//                selectedColumns = DIALECT_COLUMNS;
//                break;
//            case DICTIONARY_TABLE:
//                selectedColumns = DICTIONARY_COLUMNS;
//                break;
//            case DICTIONARYLETTER_TABLE:
//                selectedColumns = DICTIONARYLETTER_COLUMNS;
//                break;
//            case DICTIONARYWORD_TABLE:
//                selectedColumns = DICTIONARYWORD_COLUMNS;
//                break;
//            case QUIZ_TABLE:
//                selectedColumns = QUIZ_COLUMNS;
//                break;
//            case QUIZQUESTION_TABLE:
//                selectedColumns = QUIZQUESTION_COLUMNS;
//                break;
//            case QUIZANSWER_TABLE:
//                selectedColumns = QUIZANSWER_COLUMNS;
//                break;
//            case VIDEO_TABLE:
//                selectedColumns = VIDEO_COLUMNS;
//                break;
//        }
//
//        try {
//            JSONArray jsonArray = new JSONArray(params[1]);
//            Log.i("Columns", params[0]);
//            Log.i("JSON", (Integer.toString(jsonArray.getJSONObject(0).length())));
//
//            //SQLiteDatabase db = this.getWritableDatabase();
//            for(int j = 0; j < jsonArray.length(); ++j) {
//                ContentValues contentValues = new ContentValues();
//                for (int i = 0; i < selectedColumns.length; ++i) {
//                    contentValues.put(selectedColumns[i], jsonArray.getJSONObject(j).getString(selectedColumns[i]));
//                }
//                result = this.db.insert(params[0], null, contentValues);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        if(result == -1) {
//            return false;
//        } else {
//            return true;
//        }
//    }

    public void downloadJSON(String url, String table) {
        DownloadJSONTask task = new DownloadJSONTask();
        task.execute(url, table);
    }

    public class DownloadJSONTask extends AsyncTask<String, Void, String> {

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
                case DICTIONARYLETTER_TABLE:
                    selectedColumns = DICTIONARYLETTER_COLUMNS;
                    break;
                case DICTIONARYWORD_TABLE:
                    selectedColumns = DICTIONARYWORD_COLUMNS;
                    break;
                case QUIZ_TABLE:
                    selectedColumns = QUIZ_COLUMNS;
                    break;
                case QUIZQUESTION_TABLE:
                    selectedColumns = QUIZQUESTION_COLUMNS;
                    break;
                case QUIZANSWER_TABLE:
                    selectedColumns = QUIZANSWER_COLUMNS;
                    break;
                case VIDEO_TABLE:
                    selectedColumns = VIDEO_COLUMNS;
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
                    result = db.insert(params[0], null, contentValues);
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

        //ProgressDialog progressDialog = new ProgressDialog(MainMenu.context);
//        @Override
//        protected void onPreExecute() {
//            //progressDialog.show();
//            super.onPreExecute();
//
//        }

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
                if (insertData(params[1], jsonData)) {
                    Log.i("Result", "Data inserted");
                } else {
                    Log.i("Result", "Data HAS NOT BEEN inserted");
                }
                return jsonData;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (insertData(params[1], jsonData)) {
                Log.i("Result", "Data inserted");
            } else {
                Log.i("Result", "Data HAS NOT BEEN inserted");
            }
            taskDone = true;
            return jsonData;
        }



        @Override
        protected void onPostExecute(String result) {;
            super.onPostExecute(result);
            MainMenu.loadPrefs(MainMenu.context);
            //progressDialog.dismiss();


        }

    }


}
