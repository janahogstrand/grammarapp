package com.grammar.trocket.grammingo.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;
import com.grammar.trocket.grammingo.exercises.Quiz;
import com.grammar.trocket.grammingo.exercises.ImageQuizMainActivity;
import com.grammar.trocket.grammingo.exercises.AudioQuizMainActivity;
import com.grammar.trocket.grammingo.exercises.MultipleQuizMainActivity;
import com.grammar.trocket.grammingo.exercises.TextQuizMainActivity;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class QuizDialog extends AlertDialog.Builder {

    public static int SELECTED_QUIZ_POSITION = 0;
    public final static String SELECTED_QUIZ_TYPE = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE";

    Context context;
    Intent intent;
    public static ArrayList<Quiz> quizList;
    public static JSONArray allQuizDetails;

    /**
     * @param allQuizDetails Use this to get information about quizzes
     * */
    public QuizDialog(Context context, final ArrayAdapter<String> quizOptions, ArrayList<Quiz> quizList, JSONArray allQuizDetails) {
        super(context);
        this.context = context;
        this.quizList = quizList;
        this.allQuizDetails = allQuizDetails;
        addTitle("Select a quiz");
        addListContent(context, quizOptions);
    }

    private void addTitle(String text){
        TextView title = new TextView(context);
        title.setText(text);
        title.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(40);
        title.setTypeface(null, Typeface.BOLD);
        setCustomTitle(title);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param quizOptions List of possible quiz
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> quizOptions) {

        //Set button
        this.setNegativeButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        //New dialog once finished
        this.setAdapter(
                quizOptions,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        switch (quizList.get(position).getQuizType()) {
                            case "Text": intent = new Intent(context, TextQuizMainActivity.class);
                                break;
                            case "Multiple":  intent = new Intent(context, MultipleQuizMainActivity.class);
                                break;
                            case "Audio":  intent = new Intent(context, AudioQuizMainActivity.class);
                                break;
                            case "Picture":  intent = new Intent(context, ImageQuizMainActivity.class);
                        }

                        SELECTED_QUIZ_POSITION = position;
                        intent.putExtra(SELECTED_QUIZ_TYPE, quizList.get(position).getQuizType());


                        context.startActivity(intent);
                    }
                });
        //Show dialog
        //this.show();
    }


}