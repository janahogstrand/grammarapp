package com.grammar.trocket.grammingo.dialogs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.grammar.trocket.grammar.R;

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
        addTitle("Select an accent");
        addListContent(context, dialectOptions, id);
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


    public void addListContent(final Context context, final ArrayAdapter<String> dialectOptions, final int id) {
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