package com.grammar.trocket.grammar.com.grammar.trocket.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

import com.grammar.trocket.grammar.com.grammar.trocket.exercises.quiz.Quiz;
import com.grammar.trocket.grammar.com.grammar.trocket.exercises.quiz.text_quiz.TextQuizMainActivity;

import java.util.ArrayList;

/**
 * Created by jamiemoreland on 09/03/16.
 */
public class VideoObserveDialog extends AlertDialog.Builder {

    public final static String QUIZ_INFO = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE";

    Context context;
    Intent intent;


    public VideoObserveDialog(Context context, final ArrayAdapter<String> observeOptions) {
        super(context);
        this.context = context;
        addListContent(context, observeOptions);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param observeOptions If it is transcription
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> observeOptions) {
        this.setTitle("Select a video");

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
                observeOptions,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {

                        if (position == 0) {
                          //  intent = new Intent(context, Video.class);
                        } else {
                           // intent = new Intent(context, Video.class);
                        }

                        context.startActivity(intent);
                    }
                });
        //Show dialog
        this.show();
    }


}