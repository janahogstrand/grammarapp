package com.grammar.trocket.grammar;

import android.widget.ImageView;

import android.widget.ImageView;

/**
 * Created by jamiemoreland on 24/02/16.
 */
public class FestivalItem {
    String name;
    int picture;

    /**
     * @param name Name of festival
     * @param picture Picture of festival
     * **/
    public FestivalItem(String name ,int picture){
        this.name = name;
        this.picture = picture;
    }

    public String getName(){
        return name;
    }
    public int getPhoto(){
        return picture;
    }


}
