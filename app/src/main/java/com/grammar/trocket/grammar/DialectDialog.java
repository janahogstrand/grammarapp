package com.grammar.trocket.grammar;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.ArrayAdapter;

/**
 * Created by jamiemoreland on 22/02/16.
 * Options and theme for dialog
 */
public class DialectDialog extends AlertDialog.Builder {

    protected DialectDialog(Context context, final ArrayAdapter<String> dialectOptions) {
        super(context);
        addListContent(context, dialectOptions);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     *
     * @param context        context to run on
     * @param dialectOptions List of possible dialects
     **/
    public void addListContent(final Context context, final ArrayAdapter<String> dialectOptions) {
        this.setTitle("Select a dialect");

        //Set button
        this.setNegativeButton(
                "Finshed",
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
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = dialectOptions.getItem(which);
                        android.app.AlertDialog.Builder builderInner = new android.app.AlertDialog.Builder(
                                context);
                        builderInner.setMessage(strName);
                        builderInner.setTitle("This should now send you to a new activity with new dialect");
                        builderInner.setPositiveButton(
                                "Ok",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(
                                            DialogInterface dialog,
                                            int which) {
                                        dialog.dismiss();
                                    }
                                });
                        builderInner.show();
                    }
                });
        //Show dialog
        this.show();
    }


}