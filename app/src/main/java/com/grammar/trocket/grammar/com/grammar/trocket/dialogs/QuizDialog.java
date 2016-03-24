package com.grammar.trocket.grammar.com.grammar.trocket.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.grammar.trocket.grammar.com.grammar.trocket.exercises.Quiz;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.image_quiz.Image_Quiz_Main;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.multiple_quiz.Multiple_Quiz_Main_Activity;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.audio_quiz.AudioQuizMainActivity;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.text_quiz.TextQuizMainActivity;

import org.json.JSONArray;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class QuizDialog extends AlertDialog.Builder {

    public final static String QUIZ_INFO = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE";

    Context context;
    Intent intent;
    ArrayList<Quiz> quizList;
    JSONArray allQuizDetails;

    /**
     * @param allQuizDetails Use this to get information about quizzes
     * */
    public QuizDialog(Context context, final ArrayAdapter<String> quizOptions, ArrayList<Quiz> quizList, JSONArray allQuizDetails) {
        super(context);
        this.context = context;
        this.quizList = quizList;
        this.allQuizDetails = allQuizDetails;
        addListContent(context, quizOptions);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param quizOptions List of possible quiz
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> quizOptions) {
        this.setTitle("Select a quiz");

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
                            case "Multiple":  intent = new Intent(context, Multiple_Quiz_Main_Activity.class);
                                break;
                            case "Audio":  intent = new Intent(context, AudioQuizMainActivity.class);
                                break;
                            case "Picture":  intent = new Intent(context, Image_Quiz_Main.class);
                        }

//                        String selectedDialect = quizOptions.getItem(position);
//                        intent.putExtra(QUIZ_INFO, selectedDialect);


                        context.startActivity(intent);
                    }
                });
        //Show dialog
        //this.show();
    }


}