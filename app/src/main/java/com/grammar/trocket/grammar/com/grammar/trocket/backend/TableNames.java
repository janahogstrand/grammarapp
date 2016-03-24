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


    public static final String THUMBNAILTAPITEM_TABLE = "thumbnail_tap_items";
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


}
