package com.grammar.trocket.grammar.com.grammar.trocket.main.module_selection;

import android.app.Dialog;

/**
 * Created by jamiemoreland on 22/03/16.
 */
public class DialectItem {

    private String name;
    private String code;

    public DialectItem(String name, String code){
        this.name = name;
        this.code = code;
    }

    public String getName(){
        return name;
    }

    public String getCode(){
        return code;
    }
}
