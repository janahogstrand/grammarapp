package com.grammar.trocket.grammar.com.grammar.trocket.main.category;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.DialectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.QuizDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoObserveDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.dialogs.VideoReflectDialog;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;
import com.grammar.trocket.grammar.com.grammar.trocket.main.MainMenu;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.DaysOfTheWeek;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.Festivals;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.ListViewActivity;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.Times;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.alphabetAndDictionary.Alphabet;
import com.grammar.trocket.grammar.com.grammar.trocket.resources.seasons.SeasonsMain;

import java.util.ArrayList;
import java.util.List;

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
    SQLiteDatabase myDatabase = MainMenu.db.getWritableDatabase();

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
     * **/
    private void checkResource(){
        if(categories.get(0).isResource) {
            observe.setVisibility(View.INVISIBLE);
            reflect.setVisibility(View.INVISIBLE);
            experiment.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * Used by searchAndReturnQuiz
     * Gets all children of card categories so for example card
     * Greetings may have children observe reflect and experiment
     * */
    private Cursor searchParentCategory(boolean isResource, int parentCategoryID){
        Cursor cardChildren;
        if(!isResource){
            cardChildren = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'exercise' " + "AND " + MainMenu.db.CATEGORY_PARENTID + " = " + parentCategoryID, null);
        } else {
            cardChildren = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'resource' " + "AND " + MainMenu.db.CATEGORY_PARENTID + " = " + parentCategoryID, null);
        }

        return cardChildren;
    }

    /**
     * Used by searchAndReturnQuiz
     * Joins categories and quizzes to find a children of categories
     * TODO make video method for same purpose
     * */
    private Cursor searchParentQuiz(int parentCategoryID){
        Cursor cardChildren;
            cardChildren = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE +
                    " JOIN " + MainMenu.db.QUIZ_TABLE + " ON " + MainMenu.db.CATEGORY_TABLE + "." + MainMenu.db.CATEGORY_ID + " = " + MainMenu.db.QUIZ_TABLE + "." + MainMenu.db.QUIZ_CATEGORYID +
                    " WHERE " + MainMenu.db.QUIZ_CATEGORYID + " = " + parentCategoryID, null);
        return cardChildren;
    }

    /**
     * Searches a parent id to see if it belongs
     * to any quizzes
     * a dialog is returned is so
     * else returns null meaning it found none
     * **/
    private QuizDialog searchAndReturnQuiz(Context context, int parentCategoryID, int clickedIndex){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                view.getContext(),
                android.R.layout.select_dialog_singlechoice);

        ArrayList<Quiz> quizList = new ArrayList<Quiz>();

        Cursor cardChildren = searchParentCategory(false, parentCategoryID);
        //Go to clicked item
        for(int i =0; i < clickedIndex; i++){
            cardChildren.moveToNext();
        }

        Log.w("Child of parent:", cardChildren.getString(cardChildren.getColumnIndex(MainMenu.db.CATEGORY_NAME)));

        int clickedCategoryID = cardChildren.getInt(cardChildren.getColumnIndex(MainMenu.db.CATEGORY_ID));
        Cursor buttonChildren = searchParentQuiz(clickedCategoryID);

        //Creates new entries with details stored
        //Fills arrayadapter
        //Fill quizList
        while(buttonChildren.moveToNext()){
            String title =  buttonChildren.getString(buttonChildren.getColumnIndex(MainMenu.db.QUIZ_INSTRUCTION));
            String type =  buttonChildren.getString(buttonChildren.getColumnIndex(MainMenu.db.QUIZ_KIND));
            arrayAdapter.add(title);
            quizList.add(new Quiz(title, type));
        }

        //If no matches found return null
        if(arrayAdapter.isEmpty()){
            return null;
        }

        QuizDialog quizDialog = new QuizDialog(context, arrayAdapter, quizList, buttonChildren);

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
                intent.putExtra(DialectDialog.CALLER_INFO,currentItem.name);

                if (currentItem.hasDialect && currentItem.isResource) {
                    //TODO make this adapter get information from database
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                            view.getContext(),
                            android.R.layout.select_dialog_singlechoice);

                    arrayAdapter.add("Spanish");
                    arrayAdapter.add("Mexican");


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
                QuizDialog quizDialog = searchAndReturnQuiz(context, parentCategoryID, 1);

                if(quizDialog != null){
                    quizDialog.show();
                }

            }
        });

        reflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                QuizDialog quizDialog = searchAndReturnQuiz(context, parentCategoryID, 2);

                if(quizDialog != null){
                    quizDialog.show();
                }
            }
        });

        experiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                int parentCategoryID = categories.get(getAdapterPosition()).id;
                QuizDialog quizDialog = searchAndReturnQuiz(context, parentCategoryID, 3);

                if(quizDialog != null){
                    quizDialog.show();
                }
            }
        });
    }






    // TODO Adjust code below according to the database
    public void selectIntent(Context context){
        //Load dialog with adapter

        switch (currentItem.name){
            case "El Alfabeto": intent = new Intent(context, Alphabet.class);
                break;
            case "Los Numeros": intent = new Intent(context, ListViewActivity.class);
                break;
            case "Los Dias": intent = new Intent(context, DaysOfTheWeek.class);
                break;
            case "El Calendario": intent = new Intent(context, ListViewActivity.class);
                break;
            case "Festividades": intent = new Intent(context, Festivals.class);
                break;
            case "Estaciones y Meses": intent = new Intent(context, SeasonsMain.class);
                break;
            case "La Hora": intent = new Intent(context, Times.class);
                break;
            default: intent = new Intent(context, ListViewActivity.class);

        }

    }



    public void setIndex(int index){
        this.index = index;
    }

}