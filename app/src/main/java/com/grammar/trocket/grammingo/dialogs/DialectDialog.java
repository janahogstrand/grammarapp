package com.grammar.trocket.grammingo.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

/**
 * Created by jamiemoreland on 22/02/16.
 * Options and theme for dialog
 */
public class DialectDialog extends AlertDialog.Builder {

    public final static String DIALECT_INFO = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE";
    public final static String CALLER_INFO = "com.grammar.trocket.grammar.com.grammar.trocket.MESSAGE2";

    Context context;
    Intent intent;
    int id;


    public DialectDialog(Context context, final ArrayAdapter<String> dialectOptions, Intent intent, int id) {
        super(context);
        this.context = context;
        this.intent = intent;
        this.id = id;
        addListContent(context, dialectOptions, id);
    }


    public void addListContent(final Context context, final ArrayAdapter<String> dialectOptions, final int id) {
        this.setTitle("Select a accent");
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
                dialectOptions,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        String selectedDialect = dialectOptions.getItem(position);
                        intent.putExtra(DIALECT_INFO, selectedDialect);
                        intent.putExtra(CALLER_INFO, id);
                        context.startActivity(intent);
                    }
                });
        //Show dialog
        this.show();
    }


}