package com.grammar.trocket.grammar;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.SparseArray;
import android.widget.ArrayAdapter;

/**
 * Created by jamiemoreland on 22/02/16.
 * Options and theme for dialog
 */
public class DialectDialog extends AlertDialog.Builder {

    protected DialectDialog(Context context,final ArrayAdapter<String> dialectOptions ) {
        super(context);
        addListContent(context, dialectOptions);
    }

    /**
     * //TODO Implementation where information is off server
     * Adds information to dialog
     * @param context context to run on
     * @param dialectOptions List of possible dialects
     * **/
    public void addListContent(final Context context, final ArrayAdapter<String> dialectOptions){
        // this.setIcon(R.drawable.ic_launcher);

        this.setTitle("Select a dialect");

        this.setNegativeButton(
                "Finshed",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

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
        this.show();
    }


}