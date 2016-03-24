package com.grammar.trocket.grammar.com.grammar.trocket.backend;


/**
 * Created by ran on 21/03/16.
 */
public class TableNames {


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



}
