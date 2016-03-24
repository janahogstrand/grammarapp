package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.GetJSON;
import com.grammar.trocket.grammar.com.grammar.trocket.backend.TableNames;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.VideoItem;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection.DialectItem;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.DaysOfTheWeek;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.Festivals;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.ListViewActivity;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.Times;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.Alphabet;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons.SeasonsMain;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by jamiemoreland on 24/02/16.
 * Adds on clicks for Categories
 * Opens DialectDialog with data
 * provided here
 *
 * @see DialectDialog
 */
public class CategoryViewHolder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView catName;
    TextView desc;
    ImageView icon;
    Button observe;
    Button reflect;
    Button experiment;
    public View view;
    public Category currentItem;
    List<Category> categories;
    int index;
    int contentType;
    Intent intent;
    //SQLiteDatabase myDatabase = ModuleSelection.db.getWritableDatabase();

    /**
     * Init views
     * Set on click listener
     **/
    CategoryViewHolder(View itemView, List<Category> categories) {
        super(itemView);
        this.categories = categories;
        cv = (CardView) itemView.findViewById(R.id.cv);
        catName = (TextView) itemView.findViewById(R.id.category_name);
        desc = (TextView) itemView.findViewById(R.id.desc);
        icon = (ImageView) itemView.findViewById(R.id.icon);
        observe = (Button) itemView.findViewById(R.id.observe);
        reflect = (Button) itemView.findViewById(R.id.reflect);
        experiment = (Button) itemView.findViewById(R.id.experiment);

        view = itemView;
        checkResource();
        makeOnClicks();




    }

    /**
     * Checks first card to see if it is a resources
     * if so then all of them must be
     * sets them invisible
     **/
    private void checkResource() {
        if (categories.get(0).isResource) {
            observe.setVisibility(View.INVISIBLE);
            reflect.setVisibility(View.INVISIBLE);
            experiment.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Used by searchAndReturnQuiz
     * Gets all children of card categories so for example card
     * Greetings may have children observe reflect and experiment
     */
    private String searchParentCategory(int parentCategoryID) {
        GetJSON getButtonCategories = new GetJSON((Activity) this.view.getContext(), TableNames.CATEGORY_TABLE, "parentId", (parentCategoryID + ""));

        String children = null;
        try {
            children = getButtonCategories.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.w("Found button cat... : ", children);

        return children;
    }

    private String getClickedCategoryIdsChildren(int parentCategoryID, int clickedIndex, boolean isQuiz) throws JSONException, ExecutionException, InterruptedException {
        String cardChildren = searchParentCategory(parentCategoryID);
        JSONArray jsonArray = new JSONArray(cardChildren);
        String buttonChildren;

        JSONObject cardChild = jsonArray.getJSONObject(clickedIndex);

        Log.w("Child of parent:", cardChild.get(TableNames.CATEGORY_NAME).toString());

        int clickedCategoryID = Integer.parseInt(cardChild.get(TableNames.CATEGORY_ID).toString());

        if (isQuiz) {
            buttonChildren = searchParentQuiz(clickedCategoryID);
        } else {
            buttonChildren = searchParentVideo(clickedCategoryID);
        }

        return buttonChildren;
    }

    /**
     * Used by searchAndReturnQuiz
     * Joins categories and quizzes to find a children of categories
     * TODO make video method for same purpose
     */
    private String searchParentQuiz(int parentCategoryID) throws ExecutionException, InterruptedException {
        GetJSON getQuizzes = new GetJSON((Activity) this.view.getContext(), TableNames.QUIZ_TABLE, "parentId", (parentCategoryID + ""));
        String getQuiz = getQuizzes.execute().get();
        Log.w("Found quzzies... : ", getQuiz);
        return getQuiz;
    }

    private String searchParentVideo(int parentCategoryID) throws ExecutionException, InterruptedException {
        GetJSON getVideos = new GetJSON((Activity) this.view.getContext(), TableNames.VIDEO_TABLE, "parentId", (parentCategoryID + ""));
        String getVideo = getVideos.execute().get();
        Log.w("Found videos... : ", getVideo);
        return getVideo;
    }

    /**
     * Searches a parent id to see if it belongs
     * to any quizzes
     * a dialog is returned is so
     * else returns null meaning it found none
     **/
    private QuizDialog searchAndReturnQuiz(Context context, String buttonChildren) throws JSONException, ExecutionException, InterruptedException {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.select_dialog_singlechoice);

        ArrayList<Quiz> quizList = new ArrayList<Quiz>();

//        String cardChildren = searchParentCategory(parentCategoryID);
//        JSONArray jsonArray = new JSONArray(cardChildren);
//
//        JSONObject cardChild = jsonArray.getJSONObject(clickedIndex);
//
//        Log.w("Child of parent:", cardChild.get(TableNames.CATEGORY_NAME).toString());
//
//        int clickedCategoryID = Integer.parseInt(cardChild.get(TableNames.CATEGORY_ID).toString());
//
//        String buttonChildren = searchParentQuiz(clickedCategoryID);
        JSONArray buttonChildrenJSON = new JSONArray(buttonChildren);

        //Creates new entries with details stored
        //Fills arrayadapter
        //Fill quizList
        for (int i = 0; i < buttonChildrenJSON.length(); ++i) {
            JSONObject child = buttonChildrenJSON.getJSONObject(i);
            String title = child.get(TableNames.QUIZ_INSTRUCTION).toString();
            String type = child.get(TableNames.QUIZ_KIND).toString();
            int id = Integer.parseInt(child.get(TableNames.QUIZ_ID).toString());
            arrayAdapter.add(title);
            quizList.add(new Quiz(title, type, id));
        }

        //If no matches found return null
        if (arrayAdapter.isEmpty()) {
            return null;
        }

        QuizDialog quizDialog = new QuizDialog(context, arrayAdapter, quizList, buttonChildrenJSON);

        return quizDialog;
    }

    private VideoDialog searchAndReturnVideo(Context context, String buttonChildren) throws JSONException, ExecutionException, InterruptedException {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.select_dialog_singlechoice);

        ArrayList<VideoItem> videoList = new ArrayList<VideoItem>();

        JSONArray buttonChildrenJSON = new JSONArray(buttonChildren);

        //Creates new entries with details stored
        //Fills arrayadapter
        //Fill videoList
        String with = " with subtitles";
        String without = " without subtitles";
        Log.w("Could not find quiz: ", "Searching for video");
        for (int i = 0; i < buttonChildrenJSON.length(); ++i) {
            JSONObject child = buttonChildrenJSON.getJSONObject(i);
            String title = child.get(TableNames.VIDEO_TITLE).toString();
            String url = child.get(TableNames.VIDEO_URL).toString();
            String urlSub = child.get(TableNames.VIDEO_SUBTITLEDURL).toString();
            int id = Integer.parseInt(child.get(TableNames.VIDEO_CATEGORYID).toString());
            Log.w("Found: ", title);

            //Need two videos
            if(urlSub.equals("null")){
                arrayAdapter.add(title);
            } else {
                arrayAdapter.add(title + with);
                arrayAdapter.add(title + without);
                videoList.add(new VideoItem(title + with, urlSub, id));
                videoList.add(new VideoItem(title + without, url, id));
            }

            videoList.add(new VideoItem(title, url, id));
        }

        //If no matches found return null
        if (arrayAdapter.isEmpty()) {
            return null;
        }

        VideoDialog videoDialog = new VideoDialog(context, arrayAdapter, videoList, buttonChildrenJSON);

        return videoDialog;
    }


    /**
     * When card is clicked dialogs are opened
     **/
    private void makeOnClicks() {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Card clicked
                //Check if dialog is needed
                Context context = view.getContext();


                if (currentItem.hasDialect && currentItem.isResource) {
                    //TODO make this adapter get information from database
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            view.getContext(),
                            android.R.layout.select_dialog_singlechoice);

                    selectIntent(context);
                    intent.putExtra(DialectDialog.CALLER_INFO, currentItem.id);
                    Log.w("currentItem.id", Integer.toString(currentItem.id));

                    ArrayList<DialectItem> dialectItems = MainMenu.dialectsItems;

                    for (DialectItem dialect : dialectItems) {
                        arrayAdapter.add(dialect.getName());
                    }

                    DialectDialog dialectDialog = new DialectDialog(context, arrayAdapter, intent, categories.get(getAdapterPosition()).id);

                }

            }
        });


        observe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                String with = " with transcript";
                String without = " without transcript";
                //arrayAdapter.add("Video 1" + with);
                //arrayAdapter.add("Video 1" + without)
                //VideoObserveDialog observeDialog = new VideoObserveDialog(context, arrayAdapter);

                //ParentId which is a  card for example Greetings
                int parentCategoryID = categories.get(getAdapterPosition()).id;

                loadQuizOrVideo(context, 0, parentCategoryID);

            }
        });

        reflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                loadQuizOrVideo(context, 1, parentCategoryID);
            }
        });

        experiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                loadQuizOrVideo(context, 2, parentCategoryID);
            }
        });
    }

    private void loadQuizOrVideo(Context context, int index, int parentCategoryID) {
        QuizDialog quizDialog = null;
        VideoDialog videoDialog = null;

        String buttonChildren;
        try {
            buttonChildren = getClickedCategoryIdsChildren(parentCategoryID, index, true);
            quizDialog = searchAndReturnQuiz(context, buttonChildren);
        } catch (Exception e) {
           e.printStackTrace();
        }

        try {
            buttonChildren = getClickedCategoryIdsChildren(parentCategoryID, index, false);
            videoDialog = searchAndReturnVideo(context, buttonChildren);
        } catch (Exception e1) {
            e1.printStackTrace();
        }


        if (quizDialog != null) {
            quizDialog.show();
        } else if (videoDialog != null) {
            videoDialog.show();
        } else {
            Log.w("No content: ", "Couldn't find quiz or video");
        }
    }


    // TODO Adjust code below according to the database
    public void selectIntent(Context context) {
        //Load dialog with adapter

//        String topLevelIdString = "";
//        GetJSON getTopCats = new GetJSON((Activity) context, TableNames.CATEGORY_TABLE, "parentId", (currentItem.id + ""));
//        try {
//            topLevelIdString = getTopCats.execute().get();
//            Log.w("Categories", topLevelIdString);
//
//            JSONArray jsonArray = new JSONArray(topLevelIdString);
//            for (int j = 0; j < jsonArray.length(); ++j) {
//
//

//                JSONObject jObject = jsonArray.getJSONObject(j);
//
//                int id = Integer.parseInt(jObject.get("id").toString());
//                String name = jObject.get("name").toString();
//
//                if (jObject.get("kind").toString().equals("exercise")) {
//                    ExerciseID = id;
//                    exercises = name;
//                } else {
//                    ResourcesID = id;
//                    resources = name;
//                }
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        switch (MainMenu.contentsItems.get(currentItem.contentId)) {
            case "Alphabet":
                intent = new Intent(context, Alphabet.class);
                break;
            case "Numbers":
                intent = new Intent(context, ListViewActivity.class);
                intent.putExtra("type", "number");
                break;
            case "Days of the Week":
                intent = new Intent(context, ListViewActivity.class);
                break;
            case "Calendar":
                intent = new Intent(context, ListViewActivity.class);
                intent.putExtra("type", "calendar");
                break;
            case "Festivals":
                intent = new Intent(context, Festivals.class);
                break;
            case "Seasons":
                intent = new Intent(context, SeasonsMain.class);
                break;
            case "Time":
                intent = new Intent(context, Times.class);
                break;
            default:
                intent = new Intent(context, ListViewActivity.class);

        }

    }


    public void setIndex(int index) {
        this.index = index;
    }

}