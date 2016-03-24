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
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;
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

    /**
     * Searches a parent id to see if it belongs
     * to any quizzes
     * a dialog is returned is so
     * else returns null meaning it found none
     **/
    private QuizDialog searchAndReturnQuiz(Context context, int parentCategoryID, int clickedIndex) throws JSONException, ExecutionException, InterruptedException {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.select_dialog_singlechoice);

        ArrayList<Quiz> quizList = new ArrayList<Quiz>();

        String cardChildren = searchParentCategory(parentCategoryID);
        JSONArray jsonArray = new JSONArray(cardChildren);

        JSONObject cardChild = jsonArray.getJSONObject(clickedIndex);

        Log.w("Child of parent:", cardChild.get(TableNames.CATEGORY_NAME).toString());

        int clickedCategoryID = Integer.parseInt(cardChild.get(TableNames.CATEGORY_ID).toString());

        String buttonChildren = searchParentQuiz(clickedCategoryID);
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
                selectIntent(context);
                intent.putExtra(DialectDialog.CALLER_INFO, currentItem.name);

                if (currentItem.hasDialect && currentItem.isResource) {
                    //TODO make this adapter get information from database
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            view.getContext(),
                            android.R.layout.select_dialog_singlechoice);

                    ArrayList<DialectItem> dialectItems = MainMenu.dialectsItems;

                    for (DialectItem dialect : dialectItems) {
                        arrayAdapter.add(dialect.getName());
                    }

                    DialectDialog dialectDialog = new DialectDialog(context, arrayAdapter, intent);

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
                QuizDialog quizDialog = null;
                try {
                    quizDialog = searchAndReturnQuiz(context, parentCategoryID, 0);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (quizDialog != null) {
                    quizDialog.show();
                }

            }
        });

        reflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                QuizDialog quizDialog = null;
                try {
                    quizDialog = searchAndReturnQuiz(context, parentCategoryID, 1);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (quizDialog != null) {
                    quizDialog.show();
                }
            }
        });

        experiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                QuizDialog quizDialog = null;
                try {
                    quizDialog = searchAndReturnQuiz(context, parentCategoryID, 2);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (quizDialog != null) {
                    quizDialog.show();
                }
            }
        });
    }


    // TODO Adjust code below according to the database
    public void selectIntent(Context context) {
        //Load dialog with adapter

        switch (currentItem.name) {
            case "El Alfabeto":
                intent = new Intent(context, Alphabet.class);
                break;
            case "Los Numeros":
                intent = new Intent(context, ListViewActivity.class);
                break;
            case "Los Dias":
                intent = new Intent(context, DaysOfTheWeek.class);
                break;
            case "El Calendario":
                intent = new Intent(context, ListViewActivity.class);
                break;
            case "Festividades":
                intent = new Intent(context, Festivals.class);
                break;
            case "Estaciones y Meses":
                intent = new Intent(context, SeasonsMain.class);
                break;
            case "La Hora":
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