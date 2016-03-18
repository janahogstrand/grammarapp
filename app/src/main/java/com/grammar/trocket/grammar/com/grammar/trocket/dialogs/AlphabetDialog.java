package com.grammar.trocket.grammar.com.grammar.trocket.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by jamiemoreland on 22/02/16.
 * Options and theme for dialog
 */
public class AlphabetDialog extends AlertDialog.Builder {

    Context context;
    ArrayAdapter<String> translations;

    public AlphabetDialog(Context context,  ArrayAdapter<String> translations ) {
        super(context);
        this.context = context;
        this.translations = translations;
        addListContent(context, translations);
    }


    //    //TODO database implementation
//    private ArrayAdapter<String> getDictionary(String letter){
//        switch (letter){a
//            case "A":
//
//                break;
//            case "B":
//                break;
//            case "Z":
//                break;
//            default:
//                break;
//        }
//        return translations;
//    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param translations List of possible dialects
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> translations) {
        this.setTitle("Dictionary");




//        this.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        this.setNegativeButton(
                "Exit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //dialog.dismiss();
                    }
                });

        //New dialog once finished
        this.setAdapter(
                translations,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        String selectedDialect = translations.getItem(position);
                    }
                });
        //Show dialog
        this.show();


    }



}