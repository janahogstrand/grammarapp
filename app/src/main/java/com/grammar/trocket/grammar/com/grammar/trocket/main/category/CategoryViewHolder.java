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
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.QuizType;
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

    private Cursor searchParentCategory(boolean isResource, int parentCategoryID){
        Cursor result;
        if(!isResource){
            result = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'exercise' " + "AND " + MainMenu.db.CATEGORY_PARENTID + " = " + parentCategoryID, null);
        } else {
            result = myDatabase.rawQuery("SELECT * FROM " + MainMenu.db.CATEGORY_TABLE + " WHERE " + MainMenu.db.CATEGORY_KIND + " = 'resource' " + "AND " + MainMenu.db.CATEGORY_PARENTID + " = " + parentCategoryID, null);
        }

        return result;
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

                //TODO make this adapter get information from database
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        view.getContext(),
                        android.R.layout.select_dialog_singlechoice);
                //TODO make DB get correct video
                arrayAdapter.add("Video 1" + with);
                arrayAdapter.add("Video 1" + without);


                int parentCategoryID = categories.get(getAdapterPosition()).id;
                int clickedCategoryID;

                Cursor result;

                result = searchParentCategory(false, parentCategoryID);
                result.moveToFirst();
                Log.w("Sub-catty", result.getString(result.getColumnIndex(MainMenu.db.CATEGORY_NAME)));
                clickedCategoryID = result.getInt(result.getColumnIndex(MainMenu.db.CATEGORY_ID));
                result.move(-1);

                Cursor result2;
                result2 = searchParentCategory(false, clickedCategoryID);
                while(result2.moveToNext()){
                    Log.w("Sub-catty2", result2.getString(result2.getColumnIndex(MainMenu.db.CATEGORY_NAME)));
                    arrayAdapter.add(result2.getString(result2.getColumnIndex(MainMenu.db.CATEGORY_NAME)));
                }

                result.move(-1);

                VideoObserveDialog observeDialog = new VideoObserveDialog(context, arrayAdapter);

            }
        });

        reflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();

                //TODO make this adapter get information from database
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        view.getContext(),
                        android.R.layout.select_dialog_singlechoice);
                //TODO make DB get correct video
                arrayAdapter.add("Video 1");
                arrayAdapter.add("Video 2" );
                arrayAdapter.add("Video 3");
                arrayAdapter.add("Video 4" );
                arrayAdapter.add("Video 5");
                arrayAdapter.add("Video 6" );

                VideoReflectDialog reflectDialog = new VideoReflectDialog(context, arrayAdapter);
            }
        });

        experiment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                //TODO make this adapter get information from database
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        view.getContext(),
                        android.R.layout.select_dialog_singlechoice);

                ArrayList<Quiz> quizList = new ArrayList<Quiz>();
                if(currentItem.name == "Sightseeing" ){
                    quizList.add(new Quiz("Sightseeing quiz 1", QuizType.TEXTQUIZ));
                    quizList.add(new Quiz("Sightseeing quiz 2", QuizType.PICTUREQUIZ));
                    quizList.add(new Quiz("Sightseeing quiz 3", QuizType.AUDIOQUIZ));
                    quizList.add(new Quiz("Sightseeing quiz 4", QuizType.TEXTQUIZ));
                    quizList.add(new Quiz("Sightseeing quiz 5", QuizType.MULTIPLEQUIZ));
                }
                else {
                    quizList.add(new Quiz("Imperativo 1", QuizType.TEXTQUIZ));
                    quizList.add(new Quiz("Imperativo 2", QuizType.PICTUREQUIZ));
                    quizList.add(new Quiz("Tu o Usted", QuizType.AUDIOQUIZ));
                    quizList.add(new Quiz("Vocabulario", QuizType.TEXTQUIZ));
                    quizList.add(new Quiz("Comprension Auditiva", QuizType.MULTIPLEQUIZ));
                }


                for(Quiz q: quizList){
                    arrayAdapter.add(q.getName());
                }

                QuizDialog quizDialog = new QuizDialog(context, arrayAdapter, quizList);
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